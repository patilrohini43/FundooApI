package com.bridgelabz.fundoo.label.model;

import javax.validation.constraints.NotNull;

public class LabelDto {
	
	
	@NotNull(message="Can not be null")
	private String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
	

}
