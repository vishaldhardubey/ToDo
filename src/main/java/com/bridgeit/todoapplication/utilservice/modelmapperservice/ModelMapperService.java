package com.bridgeit.todoapplication.utilservice.modelmapperservice;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgeit.todoapplication.utilservice.exceptions.RestPreconditions;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;
/**
 * <p>
 * <b>ModelMapperService</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 22-07-2018
 */
@Component
public class ModelMapperService {
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * @param source
	 * @param destinationType
	 * @return
	 * @throws ToDoException
	 */
	public <D> D map(Object source, Class<D> destinationType) throws ToDoException {
	    RestPreconditions.checkNotNull(source, "NullPointerException : source cann't be null");
	    RestPreconditions.checkNotNull(destinationType, "NullPointerException : destinationType cann't be null");
	    return modelMapper.map(source, destinationType);
	  }
	
}
