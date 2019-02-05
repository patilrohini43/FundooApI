package com.bridgelabz.fundoo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.model.*;
import com.bridgelabz.fundoo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


	
	public List<User> getAll()
	{
	List<User> userList=new ArrayList<>();
	userRepository.findAll()   //getting the all the instance from the table
	.forEach(userList::add);
	return userList;
	}
	

	public void registerUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		
	}
	
	
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}


	@Override
	public User findByConfirmationToken(String confirmationToken) {
		// TODO Auto-generated method stub
		return userRepository.findByConfirmationToken(confirmationToken);
	}


	

}
