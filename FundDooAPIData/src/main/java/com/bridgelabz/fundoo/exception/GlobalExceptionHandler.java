package com.bridgelabz.fundoo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;



import com.bridgelabz.fundoo.model.Response;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
    @Autowired
	Environment environment;

	Response response=new Response();
	
	@ExceptionHandler(UserException.class)
	  public final ResponseEntity<Response> userNotFoundException(Exception e) {
	  
		System.out.println("Not Found");
		response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("UserException"));
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }

	

	@ExceptionHandler(TokenException.class)
	  public final ResponseEntity<Response> tokenNotFoundException(Exception e) {
	  
		System.out.println("Not Found");
		response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("TokenException"));
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }

	
	@ExceptionHandler(EmailException.class)
	  public final ResponseEntity<Response> emailNotFoundException(Exception e) {
	  
		System.out.println("Not Found");
		response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("EmailException"));
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }

	
	@ExceptionHandler(PasswordException.class)
	  public final ResponseEntity<Response> passwordNotFoundException(Exception e) {
	  
		System.out.println("Not Found");
		response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("PasswordException"));
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }
	
	



}
