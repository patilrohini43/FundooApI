package com.bridgelabz.fundoo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.*;
import com.bridgelabz.fundoo.service.UserService;
import com.bridgelabz.fundoo.util.EmailUtil;


@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	///@Autowired
//	JwtTokenProvider jwtTokenProvider;
	
	// @Autowired
	// AuthenticationManager authenticationManager;

	
	static Logger logger=LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("/user")
	public List<User> getAllUser()
	{
		return userService.getAll();
		
	}

	
	@RequestMapping(method=RequestMethod.POST,value="/register")
	public void addUser(@RequestBody User user)
	
	{
		
		userService.registerUser(user);
	}
	
	
	@RequestMapping(value = "/registers", method = RequestMethod.POST)
	public ResponseEntity<String> processRegistrationForm(@Valid @RequestBody UserDto userDto, BindingResult bindingResult, HttpServletRequest request)  

	{
		if(bindingResult.hasErrors()) {
			logger.error("Error in Binding The User Details");
	}
		User userExist=userService.findByEmail(userDto.getEmail());
		System.out.println(userExist);
		
		if (userExist != null) {
			
			System.out.println("already registered ");
		}
	
		else
		{
			 userService.registerUser1(userDto);
			 EmailUtil.sendEmail(userDto.getEmail(), "Successfully send", "Howdy");
			 System.out.println("successfully registered");
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	
		
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String loginForm(@Valid @RequestBody LoginDto loginDto ,BindingResult bindingResult, HttpServletRequest request) throws UserException
	{
		System.out.println("Login SuccessFully");
		return userService.Login(loginDto);
		
		//return new ResponseEntity<String>(HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
              
               
      
    

      
    }



