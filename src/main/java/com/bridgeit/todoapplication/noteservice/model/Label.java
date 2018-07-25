package com.bridgeit.todoapplication.noteservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;
/**
 * <p>
 * <b>Model class of Note type</b>
 * </p>
 * 
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 22-07-2018
 */
@Document(collection="label")
public class Label {
	@Id
	private String labelId;
	private String labelName;
	private String userId;
	@ApiModelProperty(hidden=true)
	private Note note;

	public Label() {
		super();
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
}
