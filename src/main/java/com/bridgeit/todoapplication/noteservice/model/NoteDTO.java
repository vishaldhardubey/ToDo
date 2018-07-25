package com.bridgeit.todoapplication.noteservice.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * <b>Model class for Note Dto</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 16-07-2018
 */
public class NoteDTO {
	private String id;
	private String title;
	private String description;
	@ApiModelProperty(hidden=true)
	private RemindMe remindMe;
	private List<String> labelNameList;

	public List<String> getLabelNameList() {
		return labelNameList;
	}

	public void setLabelNameList(List<String> labelNameList) {
		this.labelNameList = labelNameList;
	}

	public RemindMe getRemindMe() {
		return remindMe;
	}

	public void setRemindMe(RemindMe remindMe) {
		this.remindMe = remindMe;
	}

	public NoteDTO() {
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
}
