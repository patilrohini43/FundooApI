package com.bridgelabz.fundoo.service;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.dto.UserDto;
import com.bridgelabz.fundoo.model.User;

public interface UserService {
	
	public List<User> getAll();
	public void registerUser(User user);
    public User findByEmail(String email);
	//public User findByConfirmationToken(String confirmationToken);
	public User registerUser1(UserDto userDto);
	//public void userVerify(String token) throws Exception;
}
