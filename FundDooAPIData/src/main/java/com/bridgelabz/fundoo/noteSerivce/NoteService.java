package com.bridgelabz.fundoo.noteSerivce;

import java.util.List;

import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;



public interface NoteService {
	
	public void createNote(NoteDto noteDto,String token) throws Exception;
	public List<Note> getAllNotes();

}
