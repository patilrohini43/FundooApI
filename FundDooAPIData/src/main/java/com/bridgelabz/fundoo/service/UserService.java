package com.bridgelabz.fundoo.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.User;

public interface UserService {
	
	public List<User> getAll();
	public void registerUser(UserDto userDto);
    public User findByEmail(String email);
	//public User registerUser1(UserDto userDto) throws UserException, UnsupportedEncodingException;
	//public void userVerify(String token) throws Exception;
	//public String Login(LoginDto loginDto) throws UserException;
	public Long verifyToken(String token);
    public boolean forgotPassword(String email);
    public String resetPassword(String token,String password);
    //public String Login1(LoginDto loginDto) throws UserException;
    public String Login2(LoginDto loginDto);
}