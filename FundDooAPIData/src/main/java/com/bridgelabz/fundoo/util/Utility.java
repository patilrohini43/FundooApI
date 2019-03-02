package com.bridgelabz.fundoo.util;

import java.util.List;

import com.bridgelabz.fundoo.model.Response;
import com.bridgelabz.fundoo.note.model.Note;

public class Utility {
	
	
	
	public static Response statusResponse(int statusCode,String statusMessage)
	{
	  Response response=new Response();
	  response.setStatusCode(statusCode);
	  response.setStatusMessage(statusMessage);
	 return response;
	}
	
	public static Response statusResponseToken(int statusCode,String statusMessage,String token)
	{
	  Response response=new Response();
	  response.setStatusCode(statusCode);
	  response.setStatusMessage(statusMessage);
	  response.setToken(token);
	 return response;
	}
	
	public static Response statusResponseNote(int statusCode,String statusMessage,long noteId)
	{
	  Response response=new Response();
	  response.setStatusCode(statusCode);
	  response.setStatusMessage(statusMessage);
	  response.setNoteId(noteId);
	  return response;
	}
	
	public static Response statusResponseNote1(int statusCode,String statusMessage,Note note)
	{
	  Response response=new Response();
	  response.setStatusCode(statusCode);
	  response.setStatusMessage(statusMessage);
	  response.setNote(note);
	  return response;
	}

	public static Response statusResponseNote2(int statusCode,String statusMessage,List<Note> notes)
	{
	  Response response=new Response();
	  response.setStatusCode(statusCode);
	  response.setStatusMessage(statusMessage);
	  return response;
	}
}
