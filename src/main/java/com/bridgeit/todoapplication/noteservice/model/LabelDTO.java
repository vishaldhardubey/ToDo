package com.bridgeit.todoapplication.noteservice.model;

import org.springframework.data.annotation.Id;

public class LabelDTO {
	@Id
	private String labelId;
	private String labelName;

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
