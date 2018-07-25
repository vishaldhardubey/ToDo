package com.bridgeit.todoapplication.noteservice.service;

import java.util.List;

import com.bridgeit.todoapplication.noteservice.model.LabelDTO;
import com.bridgeit.todoapplication.noteservice.model.Note;
import com.bridgeit.todoapplication.noteservice.model.NoteDTO;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;

/**
 * Purpose : Note Service Interface.
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 */
public interface INoteService {
	/**
	 * <p>
	 * Function is to create Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param note
	 * @param req
	 * @throws ToDoException
	 */
	public void createNote(NoteDTO note, String userId) throws ToDoException;

	/**
	 * <p>
	 * Function is to update Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param note
	 * @param req
	 */
	public void updateNote(NoteDTO note, String userId) throws ToDoException;

	/**
	 * <p>
	 * Function is to delete Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param noteId
	 * @param userId
	 * @return List
	 */
	public List<Note> getNoteById(Note note, String userId) throws ToDoException;

	/**
	 * <p>
	 * Function is to read note by ID for the authorised and logined user.
	 * </p>
	 * 
	 * @param userId
	 * @return List
	 * @throws ToDoException
	 */
	public List<Note> getAllNotesById(String userId) throws ToDoException;

	/**
	 * <p>
	 * Function is to get all note by ID for the authorised and logined user.
	 * </p>
	 * 
	 * @param noteId
	 * @param userId
	 */
	public void deleteNote(String noteId, String userId) throws ToDoException;

	/**
	 * <p>
	 * Function is to move note by note Id to Archieve.
	 * </p>
	 * 
	 * @param noteId
	 * @param userId
	 * @throws ToDoException
	 */
	public void moveToArchieve(String noteId, String userId) throws ToDoException;

	/**
	 * <p>
	 * Function is to move note by note Id to note database.
	 * </p>
	 * 
	 * @param noteId
	 * @param userId
	 * @throws ToDoException
	 */
	public void restoreFromArchieve(String noteId, String userId) throws ToDoException;

	public void createLabelFromNote(NoteDTO noteDto, String parseJWT) throws ToDoException;

	public void createLabel(String labelName, String string) throws ToDoException;

	public void addLabel(LabelDTO labelDto, String parseJWT);
}
