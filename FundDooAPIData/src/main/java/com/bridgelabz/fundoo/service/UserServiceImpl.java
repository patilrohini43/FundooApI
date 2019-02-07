package com.bridgelabz.fundoo.service;

import java.util.ArrayList;

import java.util.List;
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
	
	
	@Override
	public String Login(LoginDto loginDto) throws UserException
	{
		//extract user details by using emailid 
		User validUser = userRepository.findByEmail(loginDto.getEmail());
		//match user password by logindto password 
	boolean passwordStaus=passwordEncoder.matches(loginDto.getPassword(), validUser.getPassword());
		if(passwordStaus == true)
		{
		return "login successfully";
	
	}
		throw new UserException("invalid emailid or password");
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
/**
	
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
	
	
	**/
	
	 
}
	


