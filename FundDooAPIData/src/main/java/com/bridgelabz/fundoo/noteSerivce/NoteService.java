package com.bridgelabz.fundoo.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.Response;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;



public interface NoteService {
	
	public Response createNote(NoteDto noteDto,String token);
	public List<Note> getAllNotes();
	 public boolean updateNote(Note note,String token) throws Exception;
	    public boolean deleteNote(long noteId,String token) throws Exception;
	//public boolean registerUser(@Valid NoteDto noteDto, long noteId);

}
