package com.mi.datamerge.vo;

public class ReportVO {
	
	private String clientAddress;
	private String clientGuid;
	private String requestTime;
	private String serviceGuid;
	private String retriesRequest;
	private String packetsRequested;
	private String packetsServiced;
	private String maxHoleSize;
	
	public ReportVO() {
		super();
	}
	
	public ReportVO(String clientAddress, String clientGuid, String requestTime, String serviceGuid,
			String retriesRequest, String packetsRequested, String packetsServiced, String maxHoleSize) {
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

	public String getRetriesRequest() {
		return retriesRequest;
	}

	public void setRetriesRequest(String retriesRequest) {
		this.retriesRequest = retriesRequest;
	}

	public String getPacketsRequested() {
		return packetsRequested;
	}

	public void setPacketsRequested(String packetsRequested) {
		this.packetsRequested = packetsRequested;
	}

	public String getPacketsServiced() {
		return packetsServiced;
	}

	public void setPacketsServiced(String packetsServiced) {
		this.packetsServiced = packetsServiced;
	}

	public String getMaxHoleSize() {
		return maxHoleSize;
	}

	public void setMaxHoleSize(String maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}
	
	
	

}
