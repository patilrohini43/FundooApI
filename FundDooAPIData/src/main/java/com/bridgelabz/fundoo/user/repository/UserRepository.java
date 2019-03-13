package com.bridgelabz.fundoo.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{


	User findByEmail(String email);
	//User findById(Long userId);
	Optional<User> findUserByEmail(String email);
	
	void save(Optional<User> user);

	
}
