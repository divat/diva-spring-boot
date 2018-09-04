package com.diva.config.app;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app") //Read the family of application properties during runtime
public class AppConfiguration {

	private String appName;
	private int port;
	private boolean status;
	private List<String> uri;
	
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<String> getUri() {
		return uri;
	}
	public void setUri(List<String> uri) {
		this.uri = uri;
	}
}