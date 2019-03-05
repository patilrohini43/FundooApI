package com.bridgelabz.fundoo.note.model;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.model.User;

@Entity
public class Note {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	
	@Column(name="NoteId")
	private Long noteId;
	


	@Column(name="Title")
	private String title;
	

	@Column(name="Description")
	private String description;
	

	@Column(name="Color")
    private String color;
	

	@Column(name="Pin")
    private boolean isPin;
	
	
	@Column(name="Trash")
    private boolean isTrash;
	

	@Column(name="Archive")
    private boolean isArchive;
	

	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Id")
	private User user;
 



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}




	private LocalDateTime updatedDate=LocalDateTime.now();
	
	private LocalDateTime reminder;

	private LocalDateTime createDate=LocalDateTime.now();
	


    public LocalDateTime getReminder() {
		return reminder;
	}

	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	
	
	
	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}



	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isPin() {
		return isPin;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", description=" + description
				+ ", color=" + color + ", isPin=" + isPin + ", isTrash=" + isTrash + ", isArchive=" + isArchive
				+ ", updatedDate=" + updatedDate + ", reminder=" + reminder + ", createDate=" + createDate + "]";
	}


    

}
