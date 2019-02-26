package com.bridgelabz.fundoo.model;

public class Response {
	
	private int statusCode;
	private String statusMessage;
	private String token;
	private Long noteId;
	
	
	
	

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
	


	
	
	
	
	
	
	
	
	

}
