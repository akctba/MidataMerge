package com.mi.datamerge.vo;

import com.google.gson.annotations.SerializedName;

public class ReportJsonDTO {
	
	@SerializedName("client-address") 
	private String clientAddress;
	
	@SerializedName("client-guid") 
	private String clientGuid;
	
	@SerializedName("request-time") 
	private String requestTime;
	
	@SerializedName("service-guid") 
	private String serviceGuid;
	
	@SerializedName("retries-request") 
	private Integer retriesRequest;
	
	@SerializedName("packets-requested") 
	private Integer packetsRequested;
	
	@SerializedName("packets-serviced") 
	private Integer packetsServiced;
	
	@SerializedName("max-hole-size") 
	private Integer maxHoleSize;
	
	public ReportJsonDTO() {
		super();
	}
	
	public ReportJsonDTO(String clientAddress, String clientGuid, String requestTime, String serviceGuid,
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

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
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
