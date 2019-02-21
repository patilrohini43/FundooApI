package com.bridgelabz.fundoo.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;



public interface NoteService {
	
	public boolean createNote(NoteDto noteDto,String token) throws Exception;
	public List<Note> getAllNotes();
	 public boolean updateNote(Note note,long noteId) throws UserException;
	 public Note deleteNote(long noteId) throws UserException;
	//public boolean registerUser(@Valid NoteDto noteDto, long noteId);

}
