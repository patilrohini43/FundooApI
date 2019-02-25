package com.bridgelabz.fundoo.exception;

public class DataException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	int errorCode;
	String msg;
	 
	
	public DataException(int errorCode,String msg) {
		super(msg);
		this.errorCode=errorCode;
	}



}
