package com.mi.datamerge.vo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="report")
public class ReportXmlDTO {
	
	
	private String clientAddress;
	
	
	private String clientGuid;
	
	
	private String requestTime;
	
	
	private String serviceGuid;
	
	
	private String retriesRequest;
	
	
	private String packetsRequested;
	
	
	private String packetsServiced;
	
	
	private String maxHoleSize;
	
	public ReportXmlDTO() {
		super();
	}
	
	public ReportXmlDTO(String clientAddress, String clientGuid, String requestTime, String serviceGuid,
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

	@XmlElement(name="client-address")
	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	@XmlElement(name="client-guid")
	public String getClientGuid() {
		return clientGuid;
	}

	public void setClientGuid(String clientGuid) {
		this.clientGuid = clientGuid;
	}

	@XmlElement(name="request-time")
	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

	@XmlElement(name="service-guid")
	public String getServiceGuid() {
		return serviceGuid;
	}

	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}

	@XmlElement(name="retries-request")
	public String getRetriesRequest() {
		return retriesRequest;
	}

	public void setRetriesRequest(String retriesRequest) {
		this.retriesRequest = retriesRequest;
	}

	@XmlElement(name="packets-requested")
	public String getPacketsRequested() {
		return packetsRequested;
	}

	public void setPacketsRequested(String packetsRequested) {
		this.packetsRequested = packetsRequested;
	}

	@XmlElement(name="packets-serviced")
	public String getPacketsServiced() {
		return packetsServiced;
	}

	public void setPacketsServiced(String packetsServiced) {
		this.packetsServiced = packetsServiced;
	}

	@XmlElement(name="max-hole-size")
	public String getMaxHoleSize() {
		return maxHoleSize;
	}

	public void setMaxHoleSize(String maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}
}
