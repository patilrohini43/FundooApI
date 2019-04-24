package com.bridgelabz.fundoo.note.noteSerivce;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bridgelabz.fundoo.exception.NoteException;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.note.dto.NoteDto;
import com.bridgelabz.fundoo.note.dto.NoteDto1;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.Response;
import com.bridgelabz.fundoo.user.model.User;



public interface NoteService {
	
	public Response createNote(NoteDto noteDto,String token) throws IOException;
	public List<Note> getAllNotes(String token,boolean archived,boolean trashed);
	//public List<Note> getAllNote(String token);
	 public Response getNoteById(long noteId,String token);
	// public Response deleteNote(long noteId, String token);
	 public Response updateNote(long noteId,NoteDto noteDto,String token) ;
	    public Response deleteNote(long noteId,String token);
	    public Response changeColor(long noteId,NoteDto dto,String token);
	//public boolean registerUser(@Valid NoteDto noteDto, long noteId);
	    public Response isTrash(long noteId,String token,NoteDto1 noteDto);
	    public Response isArchive(long noteId,String token,NoteDto1 noteDto);
	    public Response isPin(long noteId,String token,boolean pin);
	   // public Response addLabel(long noteId,long labelId);
	    public Response addLabel(long noteId,long labelId);
	    public Response removeNoteToLabel(long noteId,long labelId);
	    public Response ReminderSet(long noteId,String time) throws ParseException ;
		public Response ReminderRemove(long noteId,String time);
		public Response add(String token,long noteId,String email);
		public Response removeCollbrator(String token,long noteId,String email);
		public List<Note> getCollabratorNotes(String token);
		public List<User> getCollabNote(String token,long noteId);
		public List<Note> searchNote(String query,String token);
	  //  public List<Label> getAllNoteLabel(long noteId);

}
