package com.bridgeit.todoapplication.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgeit.todoapplication.userservice.model.ResponseDTO;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;
/**
 * <p>
 * <b>Global Exception Handler is to handle all the exception.</b>
 * </p>
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 14-07-2018
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ResponseDTO responseDTO;
	
/*	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ResponseDTO> loginException(LoginException doException){
		responseDTO.setMessage(doException.getMessage());
		responseDTO.setCode("-1");
		return new ResponseEntity(responseDTO.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<ResponseDTO> registrationException(RegistrationException doException){
		responseDTO.setMessage(doException.getMessage());
		responseDTO.setCode("-2");
		return new ResponseEntity(responseDTO.getMessage(),HttpStatus.BAD_REQUEST);
	}*/
	
	/**
	 * <p> Function is to handle the exception by giving custom error message</p>
	 * @param toDoException
	 * @return ResponseEntity
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(ToDoException.class)
	public ResponseEntity<ResponseDTO> toDoException(ToDoException toDoException){
		responseDTO.setMessage(toDoException.getMessage());
		responseDTO.setCode("-1");
		return new ResponseEntity(responseDTO,HttpStatus.BAD_REQUEST);
	}
}
