package com.mi.datamerge;

import java.util.Date;

public class ReportDTO {
	
	private String clientAddress;
	private String clientGuid;
	private Date requestTime;
	private String serviceGuid;
	private Integer retriesRequest;
	private Integer packetsRequested;
	private Integer packetsServiced;
	private Integer maxHoleSize;
	
	public ReportDTO() {
		super();
	}
	
	public ReportDTO(String clientAddress, String clientGuid, Date requestTime, String serviceGuid,
			Integer retriesRequest, Integer packetsRequested, Integer packetsServiced, Integer maxHoleSize) {
		super();
		this.clientAddress = clientAddress;
		this.clientGuid = clientGuid;
		this.requestTime = requestTime;
		this.serviceGuid = serviceGuid;
		this.retriesRequest = retriesRequest;
		this.packetsRequested = packetsRequested;
		this.packetsServiced = packetsServiced;
		this.maxHoleSize = maxHoleSize;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientGuid() {
		return clientGuid;
	}

	public void setClientGuid(String clientGuid) {
		this.clientGuid = clientGuid;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getServiceGuid() {
		return serviceGuid;
	}

	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}

	public Integer getRetriesRequest() {
		return retriesRequest;
	}

	public void setRetriesRequest(Integer retriesRequest) {
		this.retriesRequest = retriesRequest;
	}

	public Integer getPacketsRequested() {
		return packetsRequested;
	}

	public void setPacketsRequested(Integer packetsRequested) {
		this.packetsRequested = packetsRequested;
	}

	public Integer getPacketsServiced() {
		return packetsServiced;
	}

	public void setPacketsServiced(Integer packetsServiced) {
		this.packetsServiced = packetsServiced;
	}

	public Integer getMaxHoleSize() {
		return maxHoleSize;
	}

	public void setMaxHoleSize(Integer maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}
	
	
	

}
