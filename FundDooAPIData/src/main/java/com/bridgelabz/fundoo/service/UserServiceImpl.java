package com.bridgelabz.fundoo.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.EmailException;
import com.bridgelabz.fundoo.exception.PasswordException;
import com.bridgelabz.fundoo.exception.TokenException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.*;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.util.EmailUtil;
import com.bridgelabz.fundoo.util.UserToken;
import com.bridgelabz.fundoo.util.Utility;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private ModelMapper modelMapper;
    
   
	@Autowired
	private Environment environment;
    
    private Response response;
    
    private Utility util;
	
	public List<User> getAll()
	{
	List<User> userList=new ArrayList<>();
	userRepository.findAll()   //getting the all the instance from the table
	.forEach(userList::add);
	return userList;
	}
	

	public Response registerUser(UserDto userDto) 
	{
		
		User userExist=userRepository.findByEmail(userDto.getEmail());
		//System.out.println(userExist);
		
		if (userExist != null) {
			
			 System.out.println("already registered ");
			 throw new UserException(201,environment.getProperty("user.register.exist"));
			// throw new UserException(environment.getProperty("1"));
		}
		else
		{
		User user=modelMapper.map(userDto, User.class);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		String url =this.getUrl("loginVerify", user.getId());
   	    EmailUtil.sendEmail(userDto.getEmail(),"Successfully send","click on link "+ url);
		//EmailUtil.sendEmail(userDto.getEmail(), "Successfully send", getBody(user));
   	    //response.setStatusCode(100);
   	    //response.setStatusMessage("Registered Succssfully");
		 Response response1=Utility.statusResponse(200, environment.getProperty("user.register.success.message"));
		 return response1;
		
	}
		
		}
	

	

	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}
	
	
//	public User registerUser1(UserDto userDto) throws UserException, UnsupportedEncodingException
//	{
//		
//		User user=modelMapper.map(userDto, User.class);
//		user.setPassword( passwordEncoder.encode(user.getPassword()));
//		user=userRepository.save(user);
//		EmailUtil.sendEmail(userDto.getEmail(), "Successfully send", getBody(user));
//		return user; 
//	
//		
//	}
	
	
//
//	public String Login(LoginDto loginDto) 
//	{
//		//extract user details by using emailid 
//	Optional<User> validUser =userRepository.findUserByEmail(loginDto.getEmail());
//	
//		//match user password by logindto password 
//	boolean passwordStaus=passwordEncoder.matches(loginDto.getPassword(), validUser.get().getPassword());
//	if(passwordStaus){ 
//		
//		return "login succesfully";
//		//return UserToken.createToken(validUser.getId()); 
//	}
//	
//	return "login invalid";
//   
//		
//	}
	
	
	
	
	
//	
//	public String Login1(LoginDto loginDto) throws UserException
//	{
//		
//		//extract user details by using emailid 
//		
//		String emailId = loginDto.getEmail();
//		User validUser = userRepository.findUserByEmail(emailId).orElseThrow(()-> new UserException("Email not found"));
//		//matche user password by logindto password 
//		boolean passwordStaus=passwordEncoder.matches(loginDto.getPassword(), validUser.getPassword());	
//		if(passwordStaus == true)
//		{
//			return "login successfully";
//
//		}
//			throw new UserException("invalid emailid or password");
//	
//		
//	}
	

	private String getBody(User user) {

		
		return "http://localhost:8081/verify/" 
				  + UserToken.createToken(user.getId());
      }
	


	
	
	
	
	public Response verifyToken(String token) 
	{
		System.out.println(token);
		
		Long userID = UserToken.tokenVerify(token);
	
    	   User user=userRepository.findById(userID)
			   .orElseThrow(() -> new TokenException(401, "token.error"));

		user.setIsVerify(true);
		userRepository.save(user);
		
		Response response=Utility.statusResponseToken(103, environment.getProperty("token.verify.message"), token);
		return response;
		
   }


	@Override
	public Response forgotPassword(String email)  {
		// TODO Auto-generated method stub
		
     
		User user=userRepository.findUserByEmail(email)
				.orElseThrow(() -> new EmailException(300, environment.getProperty("email.error")));
		System.out.println(user);
		
		if (user != null) {
			
			System.out.println("already registered ");
			// throw new UserException(201,environment.getProperty("user.register.exist"));
			
		}
		
		String url =this.getUrl("forgetVerify", user.getId());
    	EmailUtil.sendEmail(user.getEmail(),"reset your password","click on link "+ url);
    	
    	Response response=Utility.statusResponse(101, environment.getProperty("user.forgot.success.message"));
		
		return response;
	}
	
	

	
	public Response resetPassword(String token,String password)
	{
		System.out.println(token);
		
		Long userID = UserToken.tokenVerify(token);
	
	   User user=userRepository.findById(userID)
			   .orElseThrow(() -> new TokenException( 400,"User is not valid........."));

		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		
		Response response=Utility.statusResponse(102,environment.getProperty("user.reset.success.message"));
		
		return  response;
		
   }
	



public String getUrl(String service, Long id) {
	
	return "http://localhost:4200/" + service 
			+ "/" + UserToken.createToken(id);

}



  public Response Login2(LoginDto loginDto) {
	//Response respone = new Response();
	return userRepository.findUserByEmail(loginDto.getEmail())
			.map(validUser -> {
				
						return this.authenticate(validUser,loginDto);
                   
			}) .orElseThrow(() -> new EmailException(300,environment.getProperty("email.error")));

				 
}
  
  

   /**
	 * @param dbUser
	 * @param password
	 * @return
 * @throws UnsupportedEncodingException 
 * @throws UserException 
	 */
	private Response authenticate(User validUser,LoginDto loginDto)  {
		System.out.println("hello");
		boolean isVerify=passwordEncoder.matches(loginDto.getPassword(),validUser.getPassword());
		 
		System.out.println(isVerify);
		if(isVerify)
		 {
			String token= UserToken.createToken(validUser.getId());
			Response response=Utility.statusResponseToken(200,environment.getProperty("login.message"), token);
			return response;
		 }
	        throw new PasswordException(300, environment.getProperty("password.error"));
				
}

      


	
	
	
}
	


