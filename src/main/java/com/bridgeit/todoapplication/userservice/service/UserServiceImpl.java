package com.bridgeit.todoapplication.userservice.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgeit.todoapplication.securityservice.security.TokenGenerator;
import com.bridgeit.todoapplication.userservice.model.Email;
import com.bridgeit.todoapplication.userservice.model.User;
import com.bridgeit.todoapplication.userservice.model.UserDTO;
import com.bridgeit.todoapplication.userservice.model.UserLoginDTO;
import com.bridgeit.todoapplication.userservice.model.UserResetPasswordDTO;
import com.bridgeit.todoapplication.userservice.repository.IUserRepository;
import com.bridgeit.todoapplication.utilservice.UtilService;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;
import com.bridgeit.todoapplication.utilservice.rabbitmq.RabbitMQSender;

/**
 * <p>
 * <b>User Service Implementation for the interface User service</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 09-07-2018
 */
@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private TokenGenerator token;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	RabbitMQSender mqSender;
	
	@Autowired
	UtilService utilService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * <p>
	 * Function is to check and verify user from database and generate token.
	 * </p>
	 * 
	 * @param User
	 * @return User1 object
	 * @throws ToDoException
	 */
	@Override
	public String loginUser(UserLoginDTO user) throws ToDoException {
		String email = user.getEmail();
		logger.info(email);
		utilService.emailValidation(user.getEmail(), user.getPassword());
			Optional<User> userObject = iUserRepository.getByEmail(email);
			if(!iUserRepository.existsByEmail(userObject.get().getEmail())){
				throw new ToDoException("NoSuchElementException : Your email address is not registered. Please register at http://localhost:8080//swagger-ui.html#!/user-controller/registrationUsingPOST");
			}
			if (userObject.get().getStatus() == false) {
				throw new ToDoException("User is not activated yet");
			}
			
			if (passwordEncoder.matches(user.getPassword(), userObject.get().getPassword())) {
				String validToken = token.generator(userObject.get().getId());
				logger.info(validToken);
				token.parseJWT(validToken);
				return validToken;
			} else {
				logger.error(email);
				throw new ToDoException("User Password is Incorrect !");
			}
	}

	/**
	 * <p>
	 * Function is to register the User info inside database.
	 * </p>
	 * 
	 * @param User
	 * @return boolean
	 * @throws ToDoException
	 */
	@Override
	public void registerUser(UserDTO userDto) throws ToDoException {
		utilService.emailValidation(userDto.getEmail(), userDto.getPassword());
		utilService.userNameValidator(userDto.getName());
		if (!iUserRepository.existsByEmail(userDto.getEmail())) {
			userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
			User user = modelMapper.map(userDto, User.class);
			saveUser(user);
			sendAuthenticationMail(user);
		} else {
			throw new ToDoException("User Already Exists having :" + userDto.getEmail());
		}
	}

	/**
	 * @param email
	 *            <p>
	 *            Function is to activate the user.
	 *            </p>
	 * @return boolean
	 * @throws ToDoException
	 */
	@Override
	public void activateUser(String id) throws ToDoException {
		if (id != null) {
			if (iUserRepository.existsById(id)) {
				Optional<User> user = iUserRepository.getById(id);
				user.get().setStatus(true);
				iUserRepository.save(user.get());
			} else {
				throw new ToDoException("Email Id Does Not Exists");
			}
		} else {
			throw new ToDoException("Email Id is null!");
		}
	}

	/**
	 * @param User
	 *            <p>
	 *            Function is to save User into database.
	 *            </p>
	 * @return boolean
	 * @throws ToDoException
	 */
	@Override
	public boolean saveUser(User user) throws ToDoException {
		if (user != null) {
			iUserRepository.insert(user);
			return true;
		} else {
			throw new ToDoException("user can not be null");
		}
	}

	/**
	 * @param User
	 *            <p>
	 *            Function is to send Authentication Main to every User.
	 *            </p>
	 * @return boolean
	 * @throws ToDoException
	 */
	@Override
	public boolean sendAuthenticationMail(User user2) throws ToDoException {
		String email = user2.getEmail();
		String id = user2.getId();
		String validToken = token.generator(id);
		if (validToken != null) {
			logger.info(validToken + "****************************************");
			// String email1=token.parseJWT(validToken);
			Email email1 = new Email();
			email1.setTo(email);
			email1.setSubject("VALIDATION LINK");
			email1.setText("http://localhost:8080/useractivation/" + validToken);
			if (!mqSender.produceMsg(email1)) {
				throw new ToDoException("Something went wrong while sending email");
			}
			return true;
		}
		return false;
	}

	/**
	 * @param User
	 *            <p>
	 *            Function is to call send mail function present in Mailer class
	 *            </p>
	 * @return boolean
	 * @throws ToDoException
	 */
	@Override
	public void forgotPassword(String user) throws ToDoException {
		if (user == null) {
			throw new ToDoException("Email Id can not be null");
		}
		logger.info("inside forgotpassword");
		Optional<User> userObject = iUserRepository.getByEmail(user);
		String validToken = token.generator(userObject.get().getId());
		Email email1 = new Email();
		email1.setTo(user);
		email1.setSubject(validToken);
		email1.setText("http://localhost:8080/resetpassword/");
		if (!mqSender.produceMsg(email1)) {
			throw new ToDoException("Something went wrong while sending email");
		}
		logger.info("ended forgotpassword");
	}

	/**
	 * @param User
	 * @return boolean
	 * @throws ToDoException
	 *             <p>
	 *             Function is to reset the password.
	 *             </p>
	 */
	@Override
	public void resetPassword(UserResetPasswordDTO user, String id) throws ToDoException {
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			throw new ToDoException("Password does not matches!");
		}
		Optional<User> user1 = iUserRepository.getById(id);
		if (!user1.isPresent()) {
			throw new ToDoException("No data available having given ID");
		}
		user1.get().setPassword(passwordEncoder.encode(user.getPassword()));
		user1.get().setStatus(true);
		iUserRepository.save(user1.get());
	}

/*	*//**
	 * @param User
	 * @return boolean
	 * @throws ToDoException
	 *             <p>
	 *             Function is to validate the user data
	 *             </p>
	 *//*
	public void checkUserData(UserDTO userRegister) throws ToDoException {
		if (userRegister.getEmail() == null || userRegister.getId() == null || userRegister.getName() == null
				|| userRegister.getPassword() == null) {
			throw new ToDoException("Please Fill All Inputs");
		}
	}*/
}
