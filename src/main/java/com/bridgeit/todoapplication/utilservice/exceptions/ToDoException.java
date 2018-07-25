package com.bridgeit.todoapplication.utilservice.exceptions;
/**
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since 	: 13-07-2018
 * 	<p><b>
 * 		Class is to initialize the exception message.
 *  </b></p>
 */
public class ToDoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	/**
	 * <p>Constructor is to initialize the message</p>
	 * @param message
	 */
	public ToDoException(String message){
		super(message);
	}
}
