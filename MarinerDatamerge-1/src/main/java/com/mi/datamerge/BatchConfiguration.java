package com.mi.datamerge;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.mi.datamerge.model.ReportModel;
import com.mi.datamerge.service.CsvItemProcessor;
import com.mi.datamerge.service.XmlItemProcessor;
import com.mi.datamerge.vo.ReportCsvDTO;
import com.mi.datamerge.vo.ReportXmlDTO;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Value("${file.json}")
    private String fileJson;
    
    @Value("${file.xml}")
    private String fileXml;
    
    @Value("${file.csv}")
    private String fileCsv;

    @Bean
    public FlatFileItemReader<ReportCsvDTO> csvReader() throws MalformedURLException {
        return new FlatFileItemReaderBuilder<ReportCsvDTO>().name("csvItemReader")
        	.resource(new ClassPathResource(fileCsv))
        	.linesToSkip(1)
            .delimited()
            .names(new String[] { "clientAddress", "clientGuid", "requestTime", "serviceGuid", "retriesRequest", "packetsRequested", "packetsServiced", "maxHoleSize" })
            .fieldSetMapper(new BeanWrapperFieldSetMapper<ReportCsvDTO>() {{
                setTargetType(ReportCsvDTO.class);
             }})
            .build();
    }
    
    @Bean
    public ItemReader<ReportXmlDTO> xmlReader() {
        Jaxb2Marshaller reportMarshaller = new Jaxb2Marshaller();
        reportMarshaller.setClassesToBeBound(ReportXmlDTO.class);

        return new StaxEventItemReaderBuilder<ReportXmlDTO>()
                .name("xmlReader")
                .resource(new ClassPathResource(fileXml))
                .addFragmentRootElements("report")
                .unmarshaller(reportMarshaller)
                .build();
    }

    @Bean
    public CsvItemProcessor csvProcessor() {
    	return new CsvItemProcessor();
    }
    
    @Bean
    public XmlItemProcessor xmlProcessor() {
    	return new XmlItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<ReportModel> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ReportModel>().itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO report (clientAddress, clientGuid, requestTime, serviceGuid, retriesRequest, packetsRequested, packetsServiced, maxHoleSize) VALUES (:clientAddress, :clientGuid, :requestTime, :serviceGuid, :retriesRequest, :packetsRequested, :packetsServiced, :maxHoleSize)")
            .dataSource(dataSource)
            .build();
    }

    @Bean
    public Job importDataJob(JobCompletionNotificationListener listener, Step stepCsv, Step stepXml) {
        return jobBuilderFactory.get("importDataJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(stepCsv).next(stepXml)
            .end()
            .build();
    }

    @Bean
    public Step stepCsv(JdbcBatchItemWriter<ReportModel> writer) throws Exception {
        return stepBuilderFactory.get("stepCsv")
            .<ReportCsvDTO, ReportModel> chunk(10)
            .reader(csvReader())
            .processor(csvProcessor())
            .writer(writer)
            .build();
    }
    
    @Bean
    public Step stepXml(JdbcBatchItemWriter<ReportModel> writer) throws Exception {
    	return stepBuilderFactory.get("stepXml")
    			.<ReportXmlDTO, ReportModel> chunk(10)
    			.reader(xmlReader())
    			.processor(xmlProcessor())
    			.writer(writer)
    			.build();
    }

}
