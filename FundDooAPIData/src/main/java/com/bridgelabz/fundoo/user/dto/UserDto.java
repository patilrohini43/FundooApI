package com.bridgelabz.fundoo.user.dto;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

public class UserDto {

@NotNull(message="Can not be null")
private String username;

@NotNull(message="Can not be null Password")
private String password;

@NotNull(message="Can not be null Email")
private String email;

@NotNull(message="Can not be null Mobile Number")
private String mobilenumber;

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
public String getEmail() {
	return email;
}
@Override
public String toString() {
	return "UserDto [username=" + username + ", password=" + password + ", email=" + email + ", mobilenumber="
			+ mobilenumber + "]";
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





}
