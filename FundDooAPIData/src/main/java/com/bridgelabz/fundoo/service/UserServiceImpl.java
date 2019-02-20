package com.bridgelabz.fundoo.service;

import java.io.UnsupportedEncodingException;
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
	

	public void registerUser(UserDto userDto) throws UnsupportedEncodingException, UserException
	{
		
		User userExist=userRepository.findByEmail(userDto.getEmail());
		System.out.println(userExist);
		
		if (userExist != null) {
			
			System.out.println("already registered ");
		}
		else
		{
		User user=modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		String url =this.getUrl("loginVerify", user.getId());
   	    EmailUtil.sendEmail(userDto.getEmail(),"Successfully send","click on link "+ url);
		//EmailUtil.sendEmail(userDto.getEmail(), "Successfully send", getBody(user));
		
		
	}
		}
	

	


		
	

	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}
	
	
	public User registerUser1(UserDto userDto) throws UserException, UnsupportedEncodingException
	{
		
		User user=modelMapper.map(userDto, User.class);
		user.setPassword( passwordEncoder.encode(user.getPassword()));
		user=userRepository.save(user);
		EmailUtil.sendEmail(userDto.getEmail(), "Successfully send", getBody(user));
		return user; 
	
		
	}
	
	

	public String Login(LoginDto loginDto) throws UserException
	{
		//extract user details by using emailid 
	Optional<User> validUser =userRepository.findUserByEmail(loginDto.getEmail());
	
		//match user password by logindto password 
	boolean passwordStaus=passwordEncoder.matches(loginDto.getPassword(), validUser.get().getPassword());
	if(passwordStaus){ 
		
		return "login succesfully";
		//return UserToken.createToken(validUser.getId()); 
	}
	
	return "login invalid";
   
		
	}
	
	
	
	
	
	
	public String Login1(LoginDto loginDto) throws UserException
	{
		
		//extract user details by using emailid 
		
		String emailId = loginDto.getEmail();
		User validUser = userRepository.findUserByEmail(emailId).orElseThrow(()-> new UserException("Email not found"));
		//matche user password by logindto password 
		boolean passwordStaus=passwordEncoder.matches(loginDto.getPassword(), validUser.getPassword());	
		if(passwordStaus == true)
		{
			return "login successfully";

		}
			throw new UserException("invalid emailid or password");
	
		
	}
	

	private String getBody(User user) throws UserException, UnsupportedEncodingException{

		
		return "http://localhost:8081/verify/" 
				  + UserToken.createToken(user.getId());
      }
	


	
	
	
	
	public Long verifyToken(String token) throws Exception
	{
		System.out.println(token);
		
		Long userID = UserToken.tokenVerify(token);
	
    	   User user=userRepository.findById(userID)
			   .orElseThrow(() -> new UserException(400, "Token is not valid........."));

		user.setIsVerify(true);
		userRepository.save(user);
		
		return user.getId();
		
   }


	@Override
	public boolean forgotPassword(String email) throws UserException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
     
    	User user=userRepository.findByEmail(email);
		System.out.println(user);
		
		if (user != null) {
			
			System.out.println("already registered ");
		}
		
		String url =this.getUrl("forgetVerify", user.getId());
    	EmailUtil.sendEmail(user.getEmail(),"reset your password","click on link "+ url);
		
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String resetPassword(String token,String password) throws Exception
	{
		System.out.println(token);
		
		Long userID = UserToken.tokenVerify(token);
	
	   User user=userRepository.findById(userID)
			   .orElseThrow(() -> new UserException( "User is not valid........."));

		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		
		return "reset Successfully";
		
   }
	

//	
//private String getBody1(User user,String link) throws UserException, UnsupportedEncodingException{
//
//		
//		return "http://localhost:8081/verify/" 
//				  + UserToken.createToken(user.getId());
//      }



public String getUrl(String service, Long id) throws UserException, UnsupportedEncodingException {
	
	return "http://localhost:4200/" + service 
			+ "/" + UserToken.createToken(id);

}
	
	
	
}
	


