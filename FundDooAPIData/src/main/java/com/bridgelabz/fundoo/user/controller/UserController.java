package com.bridgelabz.fundoo.user.controller;

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


import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.UserDto;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.service.UserService;


@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins="http://localhost:4200")

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
	public ResponseEntity<Response> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, HttpServletRequest request)   

       {
		
	    if(bindingResult.hasErrors())
	    {
			logger.error("Error in Binding The User Details");
		 //  throw new UserException(201,environment.getProperty("data.message"));
			
	    }
		
			Response response= userService.registerUser(userDto);
			// System.out.println("successfully registered");
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	
		
	}

	@RequestMapping(value = "/Login", method = RequestMethod.PUT)
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto loginDto , BindingResult bindingResult) 
	{
		//System.out.println("Hello");

		{
		if(bindingResult.hasErrors()) {
			logger.error("Error in Binding The User Details");
		}
		
		Response response=userService.Login(loginDto);
	
		//httpresponse.addHeader("jwt_token", token);
	
		//response.setStatusCode(200);
		//response.setStatusMessage(environment.getProperty("message"));
	
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	}
	

	     
	
	@RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
	public ResponseEntity<Response> verifyEmail(@PathVariable String token) 
	{
		logger.info("User Verify");
		
		   Response response=userService.verifyToken(token);
			System.out.println("Verify Successfully");
	
			
		return new ResponseEntity<Response>(response,HttpStatus.OK);
  
}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ResponseEntity<Response> forgotPassword(@RequestParam("email") String email) 
	{
		logger.info("Forgot Password");
		
		Response response=userService.forgotPassword(email);
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	

	@RequestMapping(value = "/reset/{token}", method = RequestMethod.GET)
	public ResponseEntity<Response> resetPassword(@PathVariable String token,@RequestParam String password)
	{
		logger.info("resetPassword");
		
		Response response=userService.resetPassword(token, password);
		
		System.out.println("Reset SuccessFully");
		return  new ResponseEntity<Response>(response,HttpStatus.OK);
		
	} 
	
	
}



