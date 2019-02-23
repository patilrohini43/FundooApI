package com.bridgelabz.fundoo.exception;

public class PasswordException extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	int errorCode;
	String msg;
	 
	
	public PasswordException(int errorCode,String msg) {
		super(msg);
		this.errorCode=errorCode;
	}


}
