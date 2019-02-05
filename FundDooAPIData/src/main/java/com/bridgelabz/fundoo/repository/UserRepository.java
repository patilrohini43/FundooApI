package com.bridgelabz.fundoo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.fundoo.model.User;

public interface UserRepository extends CrudRepository<User,Long>{

	public User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
	 

}
