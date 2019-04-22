package com.bridgelabz.fundoo.elasticSearch;

import java.io.IOException;

import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.Response;

public interface ElasticSearch {
	
	public Note save(Note note) throws IOException;
	public Note updateNote(Note note);
	public void deleteNote(Long NoteId);
}
