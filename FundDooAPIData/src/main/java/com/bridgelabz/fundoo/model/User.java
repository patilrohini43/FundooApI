package com.bridgelabz.fundoo.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	
	
	
	

}
