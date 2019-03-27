package com.bridgelabz.fundoo.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.User;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{


	
//	@Query("delete from note where noteId=")
//	void deleteByNoteId(Long noteId);
}
