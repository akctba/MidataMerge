package com.mi.datamerge;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.mi.datamerge.vo.ReportVO;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Value("${file.input}")
    private String fileInput;
    
    @Value("${file.csv}")
    private String fileCsv;

    @Bean
    public FlatFileItemReader<ReportVO> reader() throws MalformedURLException {
        return new FlatFileItemReaderBuilder<ReportVO>().name("csvItemReader")
        	.resource(new ClassPathResource(fileCsv))
        	.linesToSkip(1)
            .delimited()
            .names(new String[] { "clientAddress", "clientGuid", "requestTime", "serviceGuid", "retriesRequest", "packetsRequested", "packetsServiced", "maxHoleSize" })
            .fieldSetMapper(new BeanWrapperFieldSetMapper<ReportVO>() {{
                setTargetType(ReportVO.class);
             }})
            .build();
    }

    @Bean
    public CsvItemProcessor processor() {
    	return new CsvItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<ReportDTO> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ReportDTO>().itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
            .sql("INSERT INTO report (clientAddress, clientGuid, requestTime, serviceGuid, retriesRequest, packetsRequested, packetsServiced, maxHoleSize) VALUES (:clientAddress, :clientGuid, :requestTime, :serviceGuid, :retriesRequest, :packetsRequested, :packetsServiced, :maxHoleSize)")
            .dataSource(dataSource)
            .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(listener)
            .flow(step1)
            .end()
            .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<ReportDTO> writer) throws Exception {
        return stepBuilderFactory.get("step1")
            .<ReportVO, ReportDTO> chunk(10)
            .reader(reader())
            .processor(processor())
            .writer(writer)
            .build();
    }

}
