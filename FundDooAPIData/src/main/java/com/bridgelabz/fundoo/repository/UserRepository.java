package com.bridgelabz.fundoo.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.bridgelabz.fundoo.model.User;

public interface UserRepository extends CrudRepository<User,Long>{


	User findByEmail(String email);
	//User findById(Long userId);
	Optional<User> findUserByEmail(String email);
	void save(Optional<User> user);
	
}
