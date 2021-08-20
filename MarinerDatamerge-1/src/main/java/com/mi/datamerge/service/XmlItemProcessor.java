package com.mi.datamerge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.google.common.primitives.Ints;
import com.mi.datamerge.model.ReportModel;
import com.mi.datamerge.vo.ReportXmlDTO;

public class XmlItemProcessor implements ItemProcessor<ReportXmlDTO, ReportModel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvItemProcessor.class);

    @Override
    public ReportModel process(final ReportXmlDTO line) throws Exception {
    	try {
			String clientAddress = line.getClientAddress();
			String clientGuid = line.getClientGuid();
			String serviceGuid = line.getServiceGuid();
			Integer maxHoleSize = Ints.tryParse(line.getMaxHoleSize());
			Integer packetsRequested = Ints.tryParse(line.getPacketsRequested());
			Integer packetsServiced = Ints.tryParse(line.getPacketsServiced());
			Integer retriesRequest = Ints.tryParse(line.getRetriesRequest());
			
			
			Date requestTime = null;
			
			try {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss zzz");
				requestTime = df.parse(line.getRequestTime());
			} catch (ParseException e) {
				LOGGER.debug("Error parsing date {} ", line.getRequestTime());
			}

			ReportModel transformed = new ReportModel(clientAddress,  clientGuid,  requestTime,  serviceGuid,
					 retriesRequest,  packetsRequested,  packetsServiced,  maxHoleSize);
			LOGGER.debug("Converting ( {} ) into ( {} )", line, transformed);

			return transformed;
		} catch (Exception e) {
			LOGGER.error("Error converting xml report {}", e.getMessage());
			e.printStackTrace();
		}
    	return null;
    }
}
