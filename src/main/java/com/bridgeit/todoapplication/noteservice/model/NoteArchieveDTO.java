package com.bridgeit.todoapplication.noteservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection = "Archieve")
public class NoteArchieveDTO {
	private String id;
	private String title;
	private String description;
	@ApiModelProperty(hidden = true)
	private String createdDate;
	@ApiModelProperty(hidden = true)
	private String userId;
	@ApiModelProperty(hidden = true)
	private String lastDatModified;
	@ApiModelProperty(hidden = true)
	private boolean trashStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLastDatModified() {
		return lastDatModified;
	}

	public void setLastDatModified(String lastDatModified) {
		this.lastDatModified = lastDatModified;
	}

	public boolean isTrashStatus() {
		return trashStatus;
	}

	public void setTrashStatus(boolean trashStatus) {
		this.trashStatus = trashStatus;
	}
}
