package com.bridgelabz.fundoo.exception;

public class EmailException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	int errorCode;
	String msg;
	 
	
	public EmailException(int errorCode,String msg) {
		super(msg);
		this.errorCode=errorCode;
	}

}