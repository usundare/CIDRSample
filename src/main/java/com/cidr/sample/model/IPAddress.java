package com.cidr.sample.model;

public class IPAddress {

	private int id;
	private String cidr_id;
	private String ipaddress;
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidr_id() {
		return cidr_id;
	}
	public void setCidr_id(String cidr_id) {
		this.cidr_id = cidr_id;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
