package com.bridgelabz.fundoo.label.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;

import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Label implements Serializable { 
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	//@Column(name="LabelId")
	private long labelId;
	//@Column(name="UserId")
	//private long userId;
	    
	
	private String labelName;                              
	
	
	
	public Label()
	{
		
	}
	
	
	public Label(long labelId)
	{
		this.labelId=labelId;
	}
	
//	@ManyToMany(mappedBy="label")
//	private Set<Note> notes;
//	
	
	
	
	@ManyToMany()

	@JoinTable(name="Note_Labels",
	joinColumns= @JoinColumn(name="labelId",referencedColumnName="labelId"),
	inverseJoinColumns= @JoinColumn(name ="noteId",referencedColumnName="noteId"))
	@JsonIgnore
	private Set<Note> notes;
	

	
	
	
	
public Set<Note> getNotes() {
		return notes;
	}



	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}


//
//@ManyToOne()
// private Note note;
//
// public Note getNote() {
//  return note;
//}
//
//
//public void setNote(Note note) {
//	this.note = note;
//}


	
	 @ManyToOne()
	 private User user;
	


	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getLabelName() {
		return labelName;
	}



	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}



	public long getLabelId() {
		return labelId;
	}



	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}



//	public long getUserId() {
//		return userId;
//	}
//
//
//
//	public void setUserId(long userId) {
//		this.userId = userId;
//	}



	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}



	
	
	
	

}
