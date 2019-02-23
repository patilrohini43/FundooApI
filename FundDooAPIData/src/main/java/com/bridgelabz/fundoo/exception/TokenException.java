package com.bridgelabz.fundoo.exception;

public class TokenException  extends RuntimeException{
	
private static final long serialVersionUID = 1L;
	
	int errorCode;
	String msg;
	 
	
	public TokenException(int errorCode,String msg) {
		super(msg);
		this.errorCode=errorCode;
	}

}
