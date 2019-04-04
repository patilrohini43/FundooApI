package com.bridgelabz.fundoo.note.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.note.model.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{


//	@Query("delete from note where noteId=")
//	void deleteByNoteId(Long noteId);
	
	@Query(value="select * from collab_note where note_id IN (:noteId) ",nativeQuery=true)
	Optional<List<Note>> findCollabratorNotes(@Param("noteId") List<Long> allNote);
	
	
	@Query(value="select note_id from  collab_note  where id=?",nativeQuery=true)
	Optional<List<Long>> findNoteAllById(@Param("id") long userId);

	@Query(value="select note_id from  collab_note  where id=?",nativeQuery=true)
	Optional<List<Long>> findAllById(Long userId);

}
