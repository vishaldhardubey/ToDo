package com.bridgeit.todoapplication.utilservice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgeit.todoapplication.utilservice.exceptions.RestPreconditions;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;

/**
 * <p>
 * <b>Util Service developed for the validation purpose</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 24-07-2018
 */
@Component
public class UtilService {

	@Value("${validation.email}")
	String emailRegex;

	@Value("${validation.password}")
	String passwordRegex;

	@Value("${validation.userName}")
	String userName;
	
	static final Logger logger = LoggerFactory.getLogger(UtilService.class);

	/**
	 * @param email
	 * @param password
	 *            <p>
	 *            Validate the email Id and Password based on Regex used for both
	 *            parameters.
	 *            </p>
	 * @return boolean
	 * @throws ToDoException
	 */
	public boolean emailValidation(String email, String password) throws ToDoException {
		RestPreconditions.checkNotNull(email, "NullPointerException : Email Cann't Be null");
		RestPreconditions.checkNotNull(password, "NullPointerException : Password Cann't Be null");
		logger.info("Inside Email Validator");
		logger.info(emailRegex);
		logger.info(email);
		logger.info(passwordRegex);
		logger.info(password);
		if (!email.equals("") && !password.equals("")) {
			Pattern patternEmail = Pattern.compile(emailRegex);
			Pattern patternPassword = Pattern.compile(passwordRegex);

			Matcher matcherEmail = patternEmail.matcher(email);
			Matcher matcherPassword = patternPassword.matcher(password);
			if (matcherEmail.matches() && matcherPassword.matches()) {
				logger.info("Email Id and Password Format is correct");
				return true;
			} else {
				logger.info("Email Id and Password Format is not correct");
				throw new ToDoException("InputTypeMismatchException : Entered Email ID or Password is not correct");
			}
		} else {
			logger.info("Email Id/Password is null");
			throw new ToDoException("NullPointerException : Fields cann't be null or Empty String");
		}
	}

	/**
	 * @param user
	 *            <p>
	 * 			Validate user name based on regex used for the validation
	 *            </p>
	 * @return boolean
	 * @throws ToDoException
	 */
	public boolean userNameValidator(String user) throws ToDoException {
		logger.info("Inside User Name Validator");
		RestPreconditions.checkNotNull(user, "NoSuchElementException : No Value Present for the given String variable");
		Pattern patternUserName = Pattern.compile(userName);
		Matcher matcher = patternUserName.matcher(user);
		if (matcher.matches()) {
			logger.info("True : " + matcher.matches());
			return true;
		}
		logger.info("False : " + matcher.matches());
		throw new ToDoException("InputMismatchException : Invalid Input!");
	}

	/**
	 * @param userId
	 *            <p>
	 * 			validate user Id based on regex used
	 *            </p>
	 * @return boolean
	 * @throws ToDoException
	 *//*
	public boolean userIdValidator(String userIdentity) throws ToDoException {
		logger.info("Inside User Name Validator");
		RestPreconditions.checkNotNull(userIdentity,
				"NoSuchElementException : No Value Present for the given String variable");
		Pattern patternUserName = Pattern.compile(userId);
		Matcher matcher = patternUserName.matcher(userIdentity);
		if (matcher.matches()) {
			logger.info("True : " + matcher.matches());
			return true;
		}
		logger.info("False : " + matcher.matches());
		throw new ToDoException("InputTypeMismatchException : Invalid Input! Only Integer Value is Allowed.");
	}
*/
	/**
	 * @param test
	 *            <p>
	 * 			Validate the json format entered by user
	 *            </p>
	 * @return boolean
	 */
	public boolean isJSONValid(String test) {
		try {
			new JSONObject(test);
		} catch (JSONException ex) {
			try {
				new JSONArray(test);
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}
}