package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.dto.LoginDto;
import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.User;

public interface UserService {
	
	public List<User> getAll();
	public void registerUser(User user);
    public User findByEmail(String email);
	//public User findByConfirmationToken(String confirmationToken);
	public User registerUser1(UserDto userDto);
	public String login(LoginDto loginDto) throws UserException;
	//public void userVerify(String token) throws Exception;
}
