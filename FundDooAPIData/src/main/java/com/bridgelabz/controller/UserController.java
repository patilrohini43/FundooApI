package com.bridgelabz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bridgelabz.model.*;
import com.bridgelabz.service.UserService;
@RestController
public class UserController {
	
	
	@Autowired
	private UserService userService;
	

	
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
	public ResponseEntity<String> processRegistrationForm(@RequestBody User user, HttpServletRequest request) 

	{
		User userExist=userService.findByEmail(user.getEmail());
		System.out.println(userExist);
		
		if (userExist != null) {
			
			System.out.println("already registered ");
		}
	
		else
		{
			 userService.registerUser(user);
			 System.out.println("successfully registered");
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	
		
	}
	
	
	
	

}
