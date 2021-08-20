package com.mi.datamerge.model;

import java.util.Date;

import lombok.Data;

@Data
public class ReportDTO {
	private String clientAddress;
	private String clientGuid;
	private Date requestTime;
	private String requestGuid;
	private Integer retriesRequest;
	private Integer packetsRequested;
	private Integer packetsServiced;
	private Integer maxHoleSize;
	
}
