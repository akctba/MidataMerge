package com.mi.datamerge.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.google.common.primitives.Longs;
import com.mi.datamerge.model.ReportModel;
import com.mi.datamerge.vo.ReportJsonDTO;

public class JsonItemProcessor implements ItemProcessor<ReportJsonDTO, ReportModel> {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonItemProcessor.class);

	@Override
	public ReportModel process(final ReportJsonDTO json) throws Exception {
		String clientAddress = json.getClientAddress();
		String clientGuid = json.getClientGuid();
		String serviceGuid = json.getServiceGuid();
		Integer maxHoleSize = json.getMaxHoleSize();
		Integer packetsRequested = json.getPacketsRequested();
		Integer packetsServiced = json.getPacketsServiced();
		Integer retriesRequest = json.getRetriesRequest();

		Date requestTime = new Date(Longs.tryParse(json.getRequestTime()));

		ReportModel transformed = new ReportModel(clientAddress, clientGuid, requestTime, serviceGuid, retriesRequest,
				packetsRequested, packetsServiced, maxHoleSize);
		LOGGER.debug("Converting ( {} ) into ( {} )", json, transformed);

		return transformed;
	}
}
