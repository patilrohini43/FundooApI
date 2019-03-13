package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MethodArgumentNotValidException extends RuntimeException{
	

private static final long serialVersionUID = 1L;
	
	int errorCode;
	String msg;
	 
	
	public MethodArgumentNotValidException(int errorCode,String msg) {
		super(msg);
		this.errorCode=errorCode;
	}



}
