package com.bridgelabz.fundoo.collabrator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundoo.collabrator.model.Collabrator;


@Repository
public interface CollabratorRepository extends JpaRepository<Collabrator,Long> {
	

	@Query(value="select collabrator_id from collabrator where note_id=? && user_id=?",nativeQuery=true)
	Optional<Long> findByValue(@Param("noteId") long noteId,@Param("userId") long userId);

	@Query(value="select note_id from collabrator where user_id=?",nativeQuery=true)
	Optional<List<Long>> findNoteAllById(@Param("userId") long userId);
	
}

