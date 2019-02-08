package com.bridgelabz.fundoo.service;

import java.util.ArrayList;

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
import com.bridgelabz.fundoo.util.EmailUtil;
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
	
	
	public User registerUser1(UserDto userDto) throws UserException
	{
		
		User user=modelMapper.map(userDto, User.class);
		user.setPassword( passwordEncoder.encode(user.getPassword()));
		 EmailUtil.sendEmail(userDto.getEmail(), "Successfully send", getBody(user));
		return userRepository.save(user);
	
		
	}
	
	
	@Override
	public String Login(LoginDto loginDto) throws UserException
	{
		//extract user details by using emailid 
		User validUser = userRepository.findByEmail(loginDto.getEmail());
		//match user password by logindto password 
	boolean passwordStaus=passwordEncoder.matches(loginDto.getPassword(), validUser.getPassword());
	if(passwordStaus){ 
		return UserToken.createToken(validUser.getId());
	}
	return "invalid user and password";
   
		
		}
	

	private String getBody( User user) throws UserException{

		return ("http://localhost:8081"+UserToken.createToken(user.getId()));
      }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Long verifyToken(String token) throws Exception
	{
	  long userId=UserToken.tokenVerify(token);
	   User user=userRepository.findById(userId)
			   .orElseThrow(() -> new UserException(400, "Token is not valid........."));

		user.setIsVerify(true);
		userRepository.save(user);
		
		return user.getId();
		
   }
	
	
	
	
	
}
	


