package com.bridgelabz.fundoo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;



import com.bridgelabz.fundoo.model.Response;
import com.bridgelabz.fundoo.util.Utility;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
    @Autowired
	Environment environment;

	Response response=new Response();

	Utility util=new Utility();
	
	@ExceptionHandler(UserException.class)
	  public final ResponseEntity<Response> userNotFoundException(UserException e) {
	  
		System.out.println("Not Found");
		//response.setStatusCode(100);
		//response.setStatusMessage(environment.getProperty("UserException"));
		Response status=util.statusResponse(100,e.getMessage());
	    return new ResponseEntity<Response>(status,HttpStatus.OK);
	  }

	

	@ExceptionHandler(TokenException.class)
	  public final ResponseEntity<Response> tokenNotFoundException(Exception e) {
	  
		System.out.println("Not Found");
		response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("TokenException"));
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }

	
	@ExceptionHandler(EmailException.class)
	  public final ResponseEntity<Response> emailNotFoundException(EmailException e) {
	  
		//System.out.println("Not Found");
		////response.setStatusCode(100);
		//response.setStatusMessage(environment.getProperty("EmailException"));
		Response response=util.statusResponse(300,e.getMessage());
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }

	
	@ExceptionHandler(PasswordException.class)
	  public final ResponseEntity<Response> passwordNotFoundException(Exception e) {
	  
		System.out.println("Not Found");
		response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("PasswordException"));
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }
	
	
	@ExceptionHandler(DataException.class)
	  public final ResponseEntity<Response> dataNotFoundException(Exception e) {
	  
		//System.out.println("Not Found");
		//response.setStatusCode(100);
		response.setStatusMessage(environment.getProperty("DataException"));
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }


}
