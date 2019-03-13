package com.bridgelabz.fundoo.note.dto;

import javax.validation.constraints.NotNull;

public class NoteDto {
	
	
	
	@NotNull(message="Can not be null")
	private String title;
	
	@NotNull(message="Can not be null")
	private String description;
	
	@NotNull(message="Can not be null")
    private String color;
    
	   

	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTitle() {
		return title;
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
	
	
	
	

}
