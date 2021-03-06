package com.bridgelabz.fundoo.note.model;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Note implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="NoteId")
	private Long noteId;
	@Column(name="Title")
	private String title;
	public String getTitle() {
		return title;
	}
	@Access(AccessType.PROPERTY)
	@ManyToMany(mappedBy="notes")
	private Set<Label> label;
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

	@ManyToOne
	@JoinColumn(name="Id")
	private User user=new User();

	
	
	@ManyToMany(mappedBy="collabnote")
	private Set<User> collabuser;
	
	@JsonIgnore
	private LocalDateTime updatedDate;
	@JsonIgnore
	private LocalDateTime createDate;
	private Date reminder;
	//constructor
	public Note()
	{
	}

	public Set<Label> getLabel() {
		return label;
	}

	public void setLabel(Set<Label> label) {
		this.label = label;
	}

	public Note(long noteId)
	{
		this.noteId=noteId;
	}
	public Set<User> getCollabuser() {
		return collabuser;
	}

	public void setCollabuser(Set<User> collabuser) {
		this.collabuser = collabuser;
	}

	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
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


	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", description=" + description
				+ ", color=" + color + ", isPin=" + isPin + ", isTrash=" + isTrash + ", isArchive=" + isArchive
				+ ", updatedDate=" + updatedDate + ", reminder=" + reminder + ", createDate=" + createDate + "]";
	}
}
