package com.bridgelabz.fundoo.noteSerivce;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.dto.NoteDto1;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.Response;



public interface NoteService {
	
	public Response createNote(NoteDto noteDto,String token);
	public List<Note> getAllNotes(String token,boolean archived,boolean trashed);
	//public List<Note> getAllNote(String token);
	 public Response getNoteById(long noteId,String token);
	// public Response deleteNote(long noteId, String token);
	 public Response updateNote(long noteId,NoteDto noteDto,String token) ;
	    public Response deleteNote(long noteId,String token);
	//public boolean registerUser(@Valid NoteDto noteDto, long noteId);
	    public Response isTrash(long noteId,String token,NoteDto1 noteDto);
	    public Response isArchive(long noteId,String token,NoteDto1 noteDto);
	    public Response isPin(long noteId,String token,boolean pin);
	   // public Response addLabel(long noteId,long labelId);
	    public Response addLabel(long noteId,long labelId);
	    public Response removeNoteToLabel(long noteId,long labelId);

	  //  public List<Label> getAllNoteLabel(long noteId);

}
