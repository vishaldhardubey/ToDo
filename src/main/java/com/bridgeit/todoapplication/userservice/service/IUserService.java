package com.bridgeit.todoapplication.userservice.service;

import com.bridgeit.todoapplication.userservice.model.User;
import com.bridgeit.todoapplication.userservice.model.UserDTO;
import com.bridgeit.todoapplication.userservice.model.UserLoginDTO;
import com.bridgeit.todoapplication.userservice.model.UserResetPasswordDTO;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;
/**
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since 	: 13-07-2018
 * 	<p><b>
 * 		Service Interface to provide services for User.
 *  </b></p>
 */
public interface IUserService {
		/**
		 * @param student
		 * <p>Function is to check and verify user from database and generate token.</p>
		 * @throws ToDoException 
		 * @return student1 object
		 */
		public String loginUser(UserLoginDTO user) throws ToDoException;
		/**
		 * @param user object
		 *  <p>Function is to register the student info inside database.</p>
		 * @throws ToDoException 
		 */
		public void registerUser(UserDTO user) throws ToDoException;
		/**
		 * @param user
		 * <p>Function is to save student into database.</p>
		 * @return boolean
		 * @throws ToDoException 
		 */
		public boolean saveUser(User user) throws ToDoException;
		/**
		 * @param user
		 * @return boolean
		 * <p>Function is to send Authentication Main to every Student.</p>
		 * @throws ToDoException 
		 */
		public boolean sendAuthenticationMail(User user) throws ToDoException;
		/**
		 * @param user
		 * @throws ToDoException 
		 * <p>Function is to call send mail function present in Mailer class.</p>
		 */
		public void forgotPassword(String user) throws ToDoException;
		/**
		 * @param User
		 * @throws ToDoException 
		 * <p>Function is to varify the user and send success response to user.</p>
		 */
		public void activateUser(String email) throws ToDoException;
		/**
		 * @param User
		 * @throws ToDoException
		 * <p> Function is to reset the password.</p>
		 */
		public void resetPassword(UserResetPasswordDTO user, String email) throws ToDoException;
	}
