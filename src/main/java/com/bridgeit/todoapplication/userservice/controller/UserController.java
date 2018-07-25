package com.bridgeit.todoapplication.userservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.todoapplication.securityservice.security.TokenGenerator;
import com.bridgeit.todoapplication.userservice.model.ResponseDTO;
import com.bridgeit.todoapplication.userservice.model.User;
import com.bridgeit.todoapplication.userservice.model.UserDTO;
import com.bridgeit.todoapplication.userservice.model.UserLoginDTO;
import com.bridgeit.todoapplication.userservice.model.UserResetPasswordDTO;
import com.bridgeit.todoapplication.userservice.service.IUserService;
import com.bridgeit.todoapplication.utilservice.UtilService;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;

/**
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 *          <p>
 *          <b>User Login controller will control the funcationality of all the
 *          API's and will be responsible for interacting with the postman.</b>
 *          </p>
 * @since : 20-07-2018
 */
@RestController
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private IUserService userService;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private UtilService utilService; 
	@Autowired
	private ResponseDTO response;

	/**
	 * @param User
	 * @return response to postman/User
	 *         <p>
	 *         Function is to login.
	 *         </p>
	 * @throws ToDoException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/userlogin")
	public ResponseEntity<UserLoginDTO> loginEmail(@RequestBody UserLoginDTO user, HttpServletResponse resp)
			throws ToDoException {
		LOGGER.info("Entered in Login Function");
		utilService.emailValidation(user.getEmail(),user.getPassword());
		
		// userService.loginUser(user);
		// RestPreconditions.checkNotNull(user, "");
		String token;
		try {
			token = userService.loginUser(user);
			resp.addHeader("token", token);
			LOGGER.info("Successfully Logged-In");
			return new ResponseEntity(new ResponseDTO("Successfully Login","200"), HttpStatus.OK);
		} catch (ToDoException exception) {
			response.setMessage(exception.getMessage());
			response.setCode("110");
			LOGGER.error("Exception occured during login");
			return new ResponseEntity(response, HttpStatus.CONFLICT);
		}

	}

	/**
	 * <p>
	 * Function is to register into database
	 * </p>
	 * 
	 * @param User
	 * @param resp
	 * @return resp to postman
	 * @throws ToDoException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/userregister")
	public ResponseEntity<User> registration(@RequestBody UserDTO user, HttpServletResponse resp) {
		try {
			userService.registerUser(user);
			return new ResponseEntity("Registration link has been sent successfully", HttpStatus.OK);
		} catch (ToDoException exception) {
			response.setMessage(exception.getMessage());
			response.setCode("-2");
			LOGGER.error("Exception occured during registration");
			return new ResponseEntity(response, HttpStatus.CONFLICT);
		}
	}

	/**
	 * @param req
	 *            <p>
	 *            Function is to verify the user by authenticated link.
	 *            </p>
	 * @return successfull message.
	 * @throws ToDoException
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/useractivation")
	public ResponseEntity<String> userActivation(@RequestParam(value = "token") String token) {
		String id;
		try {
			id = tokenGenerator.parseJWT(token);
			userService.activateUser(id);
			return new ResponseEntity("Registered Successfully ", HttpStatus.OK);
		} catch (ToDoException exception) {
			response.setMessage(exception.getMessage());
			response.setCode("-2");
			LOGGER.error("Exception occured while activation");
			return new ResponseEntity(response, HttpStatus.CONFLICT);
		}

	}

	/**
	 * @param User,resp
	 *            <p>
	 *            Function is to recover password.
	 *            </p>
	 * @return response
	 * @throws ToDoException
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/forgetpassword")
	public ResponseEntity<User> forgotPassword(@RequestParam(value = "email") String user) {
		try {
			userService.forgotPassword(user);
			return new ResponseEntity("Request to reset password link has been sent to your registered Email ID",
					HttpStatus.OK);
		} catch (ToDoException exception) {
			response.setMessage(exception.getMessage());
			response.setCode("-3");
			LOGGER.error("Exception occured for forgot password");
			return new ResponseEntity(response, HttpStatus.CONFLICT);
		}

	}

	/**
	 * @param User
	 * @throws ToDoException
	 *             <p>
	 *             Function is to reset the password.
	 *             </p>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/resetpassword")
	public ResponseEntity<User> resetPassword(@RequestParam(value = "token") String token,
			@RequestBody UserResetPasswordDTO user) throws ToDoException {
		String id = tokenGenerator.parseJWT(token);
		userService.resetPassword(user, id);
		return new ResponseEntity("Password Changed Successfully", HttpStatus.OK);

	}
}
