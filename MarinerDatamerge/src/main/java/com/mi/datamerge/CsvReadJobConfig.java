package com.mi.datamerge;

import java.net.MalformedURLException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import com.mi.datamerge.model.ReportDTO;

@Configuration
public class CsvReadJobConfig {
	
	@Bean
    public ItemReader<ReportDTO> csvReader() throws MalformedURLException {
        LineMapper<ReportDTO> reportLineMapper = createReportLineMapper();
 
			return new FlatFileItemReaderBuilder<ReportDTO>()
			        .name("reportReader")
			        .resource(new FileUrlResource("https://raw.githubusercontent.com/kyleboyle/datamerge/master/reports.csv"))
			        .linesToSkip(1)
			        .lineMapper(reportLineMapper)
			        .build();
    }
	
	private LineMapper<ReportDTO> createReportLineMapper() {
		DefaultLineMapper<ReportDTO> reportLineMapper = new DefaultLineMapper<>();
		
		LineTokenizer reportLineTokenizer = createReportLineTokenizer();
		
		reportLineMapper.setLineTokenizer(reportLineTokenizer);
		
		FieldSetMapper<ReportDTO> studentInformationMapper = createReportInformationMapper();
		reportLineMapper.setFieldSetMapper(studentInformationMapper);
 
        return reportLineMapper;
	}
	
	private LineTokenizer createReportLineTokenizer() {
        DelimitedLineTokenizer reportLineTokenizer = new DelimitedLineTokenizer();
        reportLineTokenizer.setDelimiter(",");
        reportLineTokenizer.setNames(new String[]{
                "clientAddress", 
                "clientGuid", 
                "requestTime", 
                "requestGuid", 
                "retriesRequest", 
                "packetsRequested", 
                "packetsServiced", 
                "maxHoleSize"
        });
        return reportLineTokenizer;
    }
 
    private FieldSetMapper<ReportDTO> createReportInformationMapper() {
        BeanWrapperFieldSetMapper<ReportDTO> reportInformationMapper = new BeanWrapperFieldSetMapper<>();
        reportInformationMapper.setTargetType(ReportDTO.class);
        return reportInformationMapper;
    }

}
