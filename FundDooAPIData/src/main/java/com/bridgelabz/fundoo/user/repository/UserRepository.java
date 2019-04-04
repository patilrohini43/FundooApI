package com.bridgelabz.fundoo.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.user.model.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{


	User findByEmail(String email);
	//User findById(Long userId);
	Optional<User> findUserByEmail(String email);
	
	void save(Optional<User> user);
	

	
	
//	@Query(value="select note_id from  collab_note  where id=?",nativeQuery=true)
//	Optional<List<Long>> findNoteAllById(@Param("id") long userId);
//
//	@Query(value="select note_id from  collab_note  where id=?",nativeQuery=true)
//	Optional<List<Long>> findAllById(Long userId);
	
}
