package com.bridgeit.todoapplication.noteservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * <b>Model class of Note type</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since :10-07-2018
 */
@Document
public class Note {
	@Id
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
	private List<Label> label;

	public Note() {
		super();
	}

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

	public void setCreatedDate(String timeStamp) {
		this.createdDate = timeStamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Label> getLabel() {
		return label;
	}

	public void setLabel(List<Label> label) {
		this.label = label;
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