package com.bridgelabz.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.model.User;

public interface UserRepository extends CrudRepository<User,Long>{

	public User findByEmail(String email);

}
