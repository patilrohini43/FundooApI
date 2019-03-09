package com.bridgelabz.fundoo.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.model.Response;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.model.Note;



public interface NoteService {
	
	public Response createNote(NoteDto noteDto,String token);
	public List<Note> getAllNotes(String token,boolean archived,boolean trashed);
	//public List<Note> getAllNote(String token);
	 public Response getNoteById(long noteId,String token);
	// public Response deleteNote(long noteId, String token);
	 public Response updateNote(long noteId,NoteDto noteDto,String token) ;
	    public Response deleteNote(long noteId,String token);
	//public boolean registerUser(@Valid NoteDto noteDto, long noteId);
	    public Response isTrash(long noteId,String token);
	    public Response isArchive(long noteId,String token);
	    public Response isPin(long noteId,String token);

}
