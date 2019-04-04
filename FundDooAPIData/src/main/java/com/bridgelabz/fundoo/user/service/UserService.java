package com.bridgelabz.fundoo.user.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.UserDto;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.user.model.User;

public interface UserService {
	
	public List<User> getAll();
	public List<User> getById(String token);
	public Response registerUser(UserDto userDto);
    public User findByEmail(String email);
	//public User registerUser1(UserDto userDto) throws UserException, UnsupportedEncodingException;
	//public void userVerify(String token) throws Exception;
	//public String Login(LoginDto loginDto) throws UserException;
	public Response verifyToken(String token);
    public Response forgotPassword(String email);
    public Response resetPassword(String token,String password);
    //public String Login1(LoginDto loginDto) throws UserException;
    public Response Login(LoginDto loginDto);
	public Response imageUpload(String image,String token);
	public String getImage(long id);
	public Response getUserData(String email);
	public List<User> getByEmail(String email);
}