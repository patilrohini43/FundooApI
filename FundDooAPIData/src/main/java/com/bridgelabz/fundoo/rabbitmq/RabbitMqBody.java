package com.bridgelabz.fundoo.rabbitmq;

import java.io.Serializable;

public class RabbitMqBody implements Serializable  {

	
	private String emailId;
	private String url;
	private String subject;

	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	public RabbitMqBody()
	{
		
	}
	
	public RabbitMqBody(String emailId, String url, String subject) {
		super();
		this.emailId = emailId;
		this.url = url;
		this.subject = subject;
		
	}
	
	
	

}
