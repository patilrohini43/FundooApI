package com.bridgelabz.fundoo.elasticSearch;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.Response;

@Service
public interface ElasticSearch {
	
	public Note save(Note note) throws IOException;
	public Note updateNote(Note note);
	public void deleteNote(Long NoteId);
	public List<Note> searchData(String query,long userId);
}
