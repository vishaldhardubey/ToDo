package com.bridgeit.todoapplication.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * <b>Model class of User type</b>
 * </p>
 * 
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 09-07-2018
 */
@Document
public class User {
	@Id
	private String id;
	private String email;
	private String name;
	private String password;
	@ApiModelProperty(hidden = true)
	private boolean status;

	public User() {
	}

	public User(String email, String id, String name, String password, boolean status) {
		super();
		this.email = email;
		this.id = id;
		this.name = name;
		this.password = password;
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", id=" + id + ", name=" + name + ", password=" + password + ", status="
				+ status + "]";
	}

}
