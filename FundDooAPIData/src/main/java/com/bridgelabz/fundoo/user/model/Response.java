package com.bridgelabz.fundoo.user.model;

import java.util.List;

import com.bridgelabz.fundoo.note.model.Note;

public class Response {
	
	private int statusCode;
	private String statusMessage;
	private String token;
	private Long noteId;
	
	private Note note;
	private List<Note> notes;
	
	
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	public Long getNoteId() {
		return noteId;
	}
	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", statusMessage=" + statusMessage + ", notes=" + notes + "]";
	}
	
	
	


	
	
	
	
	
	
	
	
	

}
