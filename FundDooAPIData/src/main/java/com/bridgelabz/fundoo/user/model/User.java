package com.bridgelabz.fundoo.user.model;



import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

//import com.bridgelabz.fundoo.collabrator.model.Collabrator;
import com.bridgelabz.fundoo.label.model.Label;
import com.bridgelabz.fundoo.note.model.Note;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
public class User {
	
	///private static final long serialVersionUID = 1L;
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	
	@Column(name = "UserName")
	private String username;
	
	@Column(name = "Password")
    private String password;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "Mobile")
      private String mobilenumber;
	
	
	
	@Column(name="Verification")
    private boolean isVerify;
	
	@JsonIgnore
	private LocalDateTime updatedDate=LocalDateTime.now();
	
	//private LocalDateTime reminder;
	@JsonIgnore
	private LocalDateTime createDate=LocalDateTime.now();
	
	@Column(name="Image")
	private String image;



	@ManyToMany()
	@JoinTable(name="Collab_Note",
	joinColumns= @JoinColumn(name="id",referencedColumnName="id"),
	inverseJoinColumns= @JoinColumn(name ="noteId",referencedColumnName="noteId"))
	@JsonIgnore
	
    private Set<Note> collabnote;
	
	

	public Set<Note> getCollabnote() {
		return collabnote;
	}

	public void setCollabnote(Set<Note> collabnote) {
		this.collabnote = collabnote;
	}

	public User(Long id)
	{
		this.id=id;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	
	}



	public LocalDateTime getCreateDate() {
		return createDate;
	}




	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public User()
	{
		
	}

	

	public boolean isVerify() {
		return isVerify;
	}

	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilenumber() {
		return mobilenumber;
	}

	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}


	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}


//
//	public boolean isVerify() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//







	
	
	
	

}
