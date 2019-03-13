package com.bridgelabz.fundoo.label.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CascadeType;

import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.user.model.User;


@Entity
public class Label {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private long labelId;
	
	private long userId;
	
	
	private String labelName;
	
@ManyToOne()
@JoinColumn(name="noteId")
 private Note note;



	
	
	
	public Note getNote() {
	return note;
}



public void setNote(Note note) {
	this.note = note;
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



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}



	
	
	
	

}
