package com.bridgelabz.fundoo.collabrator.model;

public class CollabratorDto {
	
	private long noteId;
	private long userId;
	public long getNoteId() {
		return noteId;
	}
	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public CollabratorDto(long noteId, long userId) {
		super();
		this.noteId = noteId;
		this.userId = userId;
	}
	

}
