package com.bridgelabz.fundoo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.*;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.util.UserToken;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private ModelMapper modelMapper;
	
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
	
	
	public User registerUser1(UserDto userDto)
	{
		
		User user=modelMapper.map(userDto, User.class);
		user.setPassword( passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	
		
	}
	
	
	public String login(LoginDto loginDto) throws UserException
	{
		return userRepository.findUserByEmail(loginDto.getEmail())
				.map(fromDBUser-> {
					try {
						return this.validUser(fromDBUser, loginDto.getPassword());
					} catch (UserException e) {
						new UserException(100,"Please Verify Your mail"); 
						e.printStackTrace();
					}
					return null;
				})
           .orElseThrow(()-> new UserException(100,"Not valid User"));
				
		
		
	}
	
	
	
	
	
	private String validUser(User fromDBUser, String password) throws UserException {
		boolean isValid =passwordEncoder.matches(password, fromDBUser.getPassword());
		if(isValid){ 
			return UserToken.generateToken(fromDBUser.getId());
		}
		throw new UserException(100,"Not valid User");
}
	
	
	
	//public String validUser(User fromdbUser,String password)
	//{
	//	boolean isvalid=passwordEncoder.matches(password, fromdbUser.getPassword());
	//	
	//	if(isvalid)
	//	{
		 
	//	}
	//	return password;

/**
	@Override
	public User findByConfirmationToken(String confirmationToken) {
		// TODO Auto-generated method stub
		return userRepository.findByConfirmationToken(confirmationToken);
	}


	@Override
	public void userVerify(String token) throws Exception {
		// TODO Auto-generated method stub
		long userId = UserToken.tokenVerify(token);
	}


	**/
				 
}
	


