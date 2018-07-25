package com.bridgeit.todoapplication.userservice.model;
/**
 * <p>
 * <b>DTO class for UserLoginDTO</b>
 * </p>
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 16-07-2018
 */
public class UserLoginDTO {
	
	private String email;
	private String password;

	public UserLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public UserLoginDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
