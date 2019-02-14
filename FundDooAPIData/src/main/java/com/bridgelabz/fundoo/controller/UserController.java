package com.bridgelabz.fundoo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.bridgelabz.fundoo.model.User;
import com.bridgelabz.fundoo.service.UserService;


@RestController
@PropertySource("classpath:message.properties")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	
	@Autowired
	private UserService userService;

	@Autowired
	private Environment environment;
	
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
	
	
	
	
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)	public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, HttpServletRequest request) throws UserException, UnsupportedEncodingException  

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
	public ResponseEntity<String> loginForm(@Valid @RequestBody LoginDto loginDto , BindingResult bindingResult, HttpServletRequest request) throws UserException, UnsupportedEncodingException
	{
		System.out.println("Hello");

		{
		if(bindingResult.hasErrors()) {
			logger.error("Error in Binding The User Details");
		}
		
		userService.Login(loginDto);
		System.out.println("Login SuccessFully");
		return new ResponseEntity<String>(environment.getProperty("a"),HttpStatus.OK);
		}
	}
	
//	@RequestMapping(value = "/Login1", method = RequestMethod.POST)
//	public void loginForm(@Valid @RequestBody LoginDto loginDto ) throws UserException
//	{
//		userService.Login(loginDto);
//		System.out.println("Login SuccessFully");
//	}
	
	      
	
	
	
	
	@RequestMapping(value = "/verify/{token}", method = RequestMethod.GET)
	public ResponseEntity<String> verifyEmail(@PathVariable String token) throws Exception
	{
		logger.info("User Verify");
		
			userService.verifyToken(token);
			System.out.println("Verify Successfully");
	
			
		return new ResponseEntity<String>(HttpStatus.OK);
  
}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ResponseEntity<String> forgotPassword(@RequestParam String email) throws Exception, UserException
	{
		logger.info("Forgot Password");
		
		userService.forgotPassword(email);
		
		return  new ResponseEntity<String>(HttpStatus.OK);
		
	}
	
	

	@RequestMapping(value = "/reset/{token}", method = RequestMethod.GET)
	public ResponseEntity<String> resetPassword(@PathVariable String token,@RequestParam String password) throws Exception, UserException
	{
		logger.info("resetPassword");
		
		userService.resetPassword(token, password);
		
		System.out.println("Reset SuccessFully");
		return  new ResponseEntity<String>(HttpStatus.OK);
		
	} 

}



