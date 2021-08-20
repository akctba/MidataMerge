package com.mi.datamerge.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.primitives.Ints;
import com.mi.datamerge.model.ReportModel;
import com.mi.datamerge.vo.ReportCsvDTO;

public class CsvItemProcessor implements ItemProcessor<ReportCsvDTO, ReportModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvItemProcessor.class);
    
    @Value("${date.format}")
    private String dateFormat;

    @Override
    public ReportModel process(final ReportCsvDTO csvLine) throws Exception {
    	String clientAddress = csvLine.getClientAddress();
    	String clientGuid = csvLine.getClientGuid();
    	String serviceGuid = csvLine.getServiceGuid();
    	Integer maxHoleSize = Ints.tryParse(csvLine.getMaxHoleSize());
    	Integer packetsRequested = Ints.tryParse(csvLine.getPacketsRequested());
    	Integer packetsServiced = Ints.tryParse(csvLine.getPacketsServiced());
    	Integer retriesRequest = Ints.tryParse(csvLine.getRetriesRequest());
    	
    	
    	Date requestTime = null;
    	
    	try {
			SimpleDateFormat df = new SimpleDateFormat(dateFormat);
			requestTime = df.parse(csvLine.getRequestTime());
		} catch (ParseException e) {
			LOGGER.debug("Error parsing date {} ", csvLine.getRequestTime());
		}

        ReportModel transformed = new ReportModel(clientAddress,  clientGuid,  requestTime,  serviceGuid,
    			 retriesRequest,  packetsRequested,  packetsServiced,  maxHoleSize);
        LOGGER.debug("Converting ( {} ) into ( {} )", csvLine, transformed);

        return transformed;
    }
}
