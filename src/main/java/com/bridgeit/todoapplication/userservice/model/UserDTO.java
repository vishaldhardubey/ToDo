package com.bridgeit.todoapplication.userservice.model;

/**
 * <p>
 * <b>Model class of UserDTO type</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 20-07-2018
 */
public class UserDTO {
	private String id;
	private String email;
	private String name;
	private String password;

	public UserDTO() {
		super();
	}

	/**
	 * @param id
	 * @param email
	 * @param name
	 * @param password
	 *            <p>
	 * 			Constructor is to initialize the fields
	 *            </p>
	 */
	public UserDTO(String id, String email, String name, String password) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
