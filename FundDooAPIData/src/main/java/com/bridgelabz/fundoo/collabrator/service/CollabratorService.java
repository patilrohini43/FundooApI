package com.bridgelabz.fundoo.collabrator.service;

import java.util.List;

import com.bridgelabz.fundoo.collabrator.model.CollabratorDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.Response;

public interface CollabratorService {
	
	Response addCollabrator(String token, long noteId, long userId);
	//public List<Note> getCollabratorNotes(String token);
	public Response add(String token,long noteId,String email);
	public Response removeCollbrator(long noteId,String token,String email);
	
	
	
}
