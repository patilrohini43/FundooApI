package com.bridgelabz.fundoo.model;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.bridgelabz.fundoo.note.model.Note;

@Entity
public class User {
	
	
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
	private Boolean isVerify;
	
	private boolean isVerified;
	
	@JoinColumn(name="Id")
	 @OneToMany(targetEntity = Note.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 private List<Note> note=new ArrayList<Note>();
	 
	 
	 
	
	public List<Note> getNote() {
		return note;
	}

	public void setNote(List<Note> note) {
		this.note = note;
	}



	private LocalDateTime updatedDate=LocalDateTime.now();
	
	//private LocalDateTime reminder;

	private LocalDateTime createDate=LocalDateTime.now();
	
	
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




	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public User()
	{
		
	}


	public Boolean getIsVerify() {
		return isVerify;
	}


	public void setIsVerify(Boolean isVerify) {
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



	public boolean isVerify() {
		// TODO Auto-generated method stub
		return false;
	}








	
	
	
	

}
