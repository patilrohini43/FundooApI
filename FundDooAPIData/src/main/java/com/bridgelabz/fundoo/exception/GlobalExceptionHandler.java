package com.bridgelabz.fundoo.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.bridgelabz.fundoo.user.model.Response;
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
	  
		Response status=Utility.statusResponse(100,e.getMessage());
	    return new ResponseEntity<Response>(status,HttpStatus.OK);
	  }

	

	@ExceptionHandler(TokenException.class)
	  public final ResponseEntity<Response> tokenNotFoundException(TokenException e) {
	  
		Response status=Utility.statusResponse(100,e.getMessage());
	    return new ResponseEntity<Response>(status,HttpStatus.OK);
	  }

	
	@ExceptionHandler(EmailException.class)
	  public final ResponseEntity<Response> emailNotFoundException(EmailException e) {
	  
	
		Response response=Utility.statusResponse(300,e.getMessage());
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }

	
	@ExceptionHandler(PasswordException.class)
	  public final ResponseEntity<Response> passwordNotFoundException(Exception e) {
	  
		//System.out.println("Not Found");
		//response.setStatusCode(100);
		//response.setStatusMessage(environment.getProperty("PasswordException"));
		Response response=Utility.statusResponse(300,e.getMessage());
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }
	
	
//	@ExceptionHandler(DataException.class)
//	  public final ResponseEntity<Response> dataNotFoundException(Exception e) {
//
//		Response response=Utility.statusResponse(100,e.getMessage());
//	    return new ResponseEntity<Response>(response,HttpStatus.OK);
//	  }

	
	@ExceptionHandler(NoteException.class)
	  public final ResponseEntity<Response> noteNotFoundException(NoteException e) {
	  
		
		Response response=Utility.statusResponseNote(405, e.getMessage(),100);
	
        return new ResponseEntity<Response>(response,HttpStatus.OK);
	  }
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	 public final ResponseEntity<Response> handleDMSRESTException(MethodArgumentNotValidException e)
	 {
		Response response=Utility.statusResponse(300,"Cannot Null Any Field");
		
        return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	 


}
