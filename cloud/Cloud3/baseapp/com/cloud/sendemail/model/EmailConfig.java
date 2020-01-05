package com.cloud.sendemail.model;

public class EmailConfig {

	private String serverHost;
	private String serverPort;
	private String fromAddress;
	private String pwd;
	
	public String getServerHost() {
		return serverHost;
	}
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	public String getServerPort() {
		return serverPort;
	}
	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
}
