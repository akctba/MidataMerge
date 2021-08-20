package com.mi.datamerge.model;

public class Summary {
	
	private String serviceGuid;
	
	private Integer records;
	
	public Summary() {
		super();
	}

	public Summary(String serviceGuid, Integer records) {
		super();
		this.serviceGuid = serviceGuid;
		this.records = records;
	}

	public String getServiceGuid() {
		return serviceGuid;
	}

	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}

	public Integer getRecords() {
		return records;
	}

	public void setRecords(Integer records) {
		this.records = records;
	}

	@Override
	public String toString() {
		return "Summary [serviceGuid=" + serviceGuid + ", records=" + records + "]";
	}

}
