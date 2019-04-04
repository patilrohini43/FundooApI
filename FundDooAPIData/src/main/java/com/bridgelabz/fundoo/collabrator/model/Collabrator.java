package com.bridgelabz.fundoo.collabrator.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Collabrator {
	
	@Id
	@Column(name = "CollabratorId")
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private long colId;
	private long noteId;
	private long userId;
	
	

	


	public long getColId() {
		return colId;
	}
	public void setColId(long colId) {
		this.colId = colId;
	}
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
	 
	public Collabrator(long noteId, long userId) {
		super();
		this.noteId = noteId;
		this.userId = userId;
	}
	
	
	
	
	
	

}
