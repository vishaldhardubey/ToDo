package com.bridgeit.todoapplication.userservice.model;
/**
 * <p><b>Model class of UserResetPassword type</b></p>
 * @author 	: Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 12-07-2018
 */
public class UserResetPasswordDTO {
	private String password;
	private String confirmPassword;

	public UserResetPasswordDTO() {
		super();
	}

	public UserResetPasswordDTO(String password, String confirmPassword) {
		super();
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
