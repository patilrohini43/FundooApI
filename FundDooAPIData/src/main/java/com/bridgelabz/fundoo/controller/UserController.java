package com.bridgelabz.fundoo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.Response;
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.service.UserService;


@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins="http://localhost:4200",allowedHeaders = "*",exposedHeaders= {"jwt_token"})

public class UserController {
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;
	
	private Response response;
	
	static Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/user")
	public List<User> getAllUser()
	{
		return userService.getAll();
		
	}

	
//	@RequestMapping(method=RequestMethod.POST,value="/register")
//	public void addUser(@RequestBody UserDto userDto) throws UnsupportedEncodingException, UserException
//	
//	{
//		
//		userService.registerUser(userDto);
//	}
//	
	
	
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)	
	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, HttpServletRequest request) throws UserException, UnsupportedEncodingException  

       {
	    if(bindingResult.hasErrors())
	    {
			logger.error("Error in Binding The User Details");
	    }
		
			 userService.registerUser(userDto);
			 System.out.println("successfully registered");
		
		return new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
	
		
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto , BindingResult bindingResult, HttpServletResponse httpresponse) throws UserException, UnsupportedEncodingException
	{
		//System.out.println("Hello");

		{
		if(bindingResult.hasErrors()) {
			logger.error("Error in Binding The User Details");
		}
		
		String token=userService.Login2(loginDto);
	
		httpresponse.addHeader("jwt_token", token);
		Response response=new Response();
		response.setStatusCode(200);
		response.setStatusMessage(environment.getProperty("message"));
	
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	}
	

	     
	
	@RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
	public ResponseEntity<String> verifyEmail(@PathVariable String token) 
	{
		logger.info("User Verify");
		
			userService.verifyToken(token);
			System.out.println("Verify Successfully");
	
			
		return new ResponseEntity<String>("Account Verified",HttpStatus.OK);
  
}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ResponseEntity<String> forgotPassword(@RequestParam("email") String email) 
	{
		logger.info("Forgot Password");
		
		userService.forgotPassword(email);
		return  new ResponseEntity<String>("SuccessFully",HttpStatus.OK);
		
	}
	
	

	@RequestMapping(value = "/reset/{token}", method = RequestMethod.GET)
	public ResponseEntity<String> resetPassword(@PathVariable String token,@RequestParam String password)
	{
		logger.info("resetPassword");
		
		userService.resetPassword(token, password);
		
		System.out.println("Reset SuccessFully");
		return  new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
		
	} 

}



