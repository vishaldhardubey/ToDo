package com.bridgeit.todoapplication.noteservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.bridgeit.todoapplication.noteservice.model.LabelDTO;
import com.bridgeit.todoapplication.noteservice.model.Note;
import com.bridgeit.todoapplication.noteservice.model.NoteDTO;
import com.bridgeit.todoapplication.noteservice.service.INoteService;
import com.bridgeit.todoapplication.securityservice.security.TokenGenerator;
import com.bridgeit.todoapplication.userservice.model.ResponseDTO;
import com.bridgeit.todoapplication.utilservice.exceptions.RestPreconditions;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;
import com.google.common.base.Preconditions;

/**
 * <p>
 * <b>Note Controller class is resposible for getting request and giving
 * response to Swagger API</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 14-07-2018
 */
@RestController
public class NoteController {

	@Autowired
	INoteService iNoteService;
	private static final Logger logger = LoggerFactory.getLogger(NoteController.class);

	@Autowired
	private ResponseDTO response;

	@Autowired
	private TokenGenerator tokenGenerator;

	/****************************************************************************************************************************
	 * <p>
	 * Function is to create Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param note
	 * @param req
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST, value = "/createnote")
	public ResponseEntity<Note> createNote(@RequestBody NoteDTO noteDto, HttpServletRequest req) throws ToDoException {
		logger.info("Inside CreateNote Function of Controller" + req.getRequestURI());

		RestPreconditions.checkNotNull(noteDto, "No field should be empty");
		RestPreconditions.checkNotNull(noteDto.getId(), "Null Pointer Exception : Note Id must not be Null");
		RestPreconditions.checkNotNull(noteDto.getDescription(),
				"Null Pointer Exception : Description can be empty string but not null");
		RestPreconditions.checkNotNull(noteDto.getTitle(),
				"Null Pointer Exception : Title can be empty string but not null");
		if (noteDto.getDescription() == null && noteDto.getTitle() == null) {
			throw new ToDoException("NullPointerException : Title and Description both together cann't be null");
		}
		// String token=req.getHeader("token");
		String userId = tokenGenerator.parseJWT(req.getHeader("token"));
		Preconditions.checkNotNull(userId, "Invalid Link");
		iNoteService.createNote(noteDto, userId);
		logger.info("End with createNote in controller" + req.getRequestURI());
		return new ResponseEntity("Note has been saved successfully", HttpStatus.OK);

	}

	/****************************************************************************************************************************
	 * <p>
	 * Function is to update Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param note
	 * @param req
	 * @return response to swagger
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST, value = "/updatenote")
	public ResponseEntity<Note> updateNote(@RequestBody NoteDTO note, HttpServletRequest req) throws ToDoException {
		logger.info("Inside updateNote Function of Controller" + req.getRequestURI());
		String userId = tokenGenerator.parseJWT(req.getHeader("token"));
		iNoteService.updateNote(note, userId);
		logger.info("End with updateNote in controller" + req.getRequestURI());
		return new ResponseEntity("Note has been updated successfully", HttpStatus.OK);
	}

	/****************************************************************************************************************************
	 * <p>
	 * Function is to delete Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST, value = "/deletenote")
	public ResponseEntity<Note> deleteNote(@RequestParam String noteId, HttpServletRequest req) {
		logger.info("Inside deleteNote Function of Controller" + req.getRequestURI());
		// RestPreconditions.checkNotNull(reference, errorMessage)
		try {
			iNoteService.deleteNote(noteId, tokenGenerator.parseJWT(req.getHeader("token")));
			logger.info("End with updateNote in controller by giving required response" + req.getRequestURI());
			return new ResponseEntity("Note have deleted successfully", HttpStatus.OK);
		} catch (ToDoException exception) {
			response.setCode("-3");
			response.setMessage(exception.getMessage());
			logger.info("End with updateNote in controller by giving exception" + req.getRequestURI());
			return new ResponseEntity(response, HttpStatus.OK);
		}

	}

	/****************************************************************************************************************************
	 * <p>
	 * Function is to read note by ID for the authorised and logined user.
	 * </p>
	 * 
	 * @param note
	 * @param token
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(method = RequestMethod.POST, value = "/getnotebyid")
	public ResponseEntity<Note> getNoteById(@RequestBody Note note, HttpServletRequest req) throws ToDoException {
		logger.info("Inside the getNoteById in controller" + req.getRequestURI());
		// userService.loginUser(user);
		RestPreconditions.checkNotNull(note, "Fields cann't be null");
		String userId = tokenGenerator.parseJWT(req.getHeader("token"));
		List<Note> noteInfo = iNoteService.getNoteById(note, userId);
		RestPreconditions.checkNotNull(noteInfo, "No any note found exception");
		logger.info("End with getNoteById in controller" + req.getRequestURI());
		return new ResponseEntity(noteInfo, HttpStatus.OK);
	}

	/*****************************************************************************************************************************
	 * <p>
	 * Function is to get all note by ID for the authorised and logined user.
	 * </p>
	 * 
	 * @param token
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.GET, value = "/getallnote")
	public ResponseEntity<Note> getAllNotesById(HttpServletRequest req) throws ToDoException {
		logger.info("Inside the getAllNoteById function in controller" + req.getRequestURI());
		String token = req.getHeader("token");
		Preconditions.checkNotNull(tokenGenerator.parseJWT(token), "Invalid Token");
		List<Note> noteInfo = iNoteService.getAllNotesById(tokenGenerator.parseJWT(token));
		RestPreconditions.checkNotNull(noteInfo, "No any note found exception");
		logger.info("End with getAllNoteById function in controller" + req.getRequestURI());
		return new ResponseEntity(noteInfo, HttpStatus.OK);
	}

	/****************************************************************************************************************************
	 * <p>
	 * Function is to move note into trash after deletion
	 * </p>
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, value = "/movetoarchieve")
	public ResponseEntity<?> moveToArchieve(@RequestParam String noteId, HttpServletRequest req) throws ToDoException {
		logger.info("Inside the moveToArchieve in controller" + req.getRequestURI());
		String userId = tokenGenerator.parseJWT(req.getHeader("token"));
		RestPreconditions.checkNotNull(userId, "NullPointerException : User ID doesnot exists");
		RestPreconditions.checkNotNull(noteId, "NullPointerException : Note ID doesnot exists");
		iNoteService.moveToArchieve(noteId, userId);
		logger.info("End with moveToArchieve in controller" + req.getRequestURI());
		return new ResponseEntity("Successfully moved to Archieve", HttpStatus.OK);
	}

	/****************************************************************************************************************************
	 * <p>
	 * Function is to move note into trash after deletion
	 * </p>
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, value = "/restorefromarchieve")
	public ResponseEntity<?> restoreFromArchieve(@RequestParam String noteId, HttpServletRequest req)
			throws ToDoException {
		logger.info("Inside the moveToArchieve in controller" + req.getRequestURI());
		String userId = tokenGenerator.parseJWT(req.getHeader("token"));
		RestPreconditions.checkNotNull(userId, "NullPointerException : User ID doesnot exists");
		RestPreconditions.checkNotNull(noteId, "NullPointerException : Note ID doesnot exists");
		iNoteService.restoreFromArchieve(noteId, userId);
		logger.info("End with moveToArchieve in controller" + req.getRequestURI());
		return new ResponseEntity("Successfully moved to Archieve", HttpStatus.OK);
	}

	/****************************************************************************************************************************
	 * <p>
	 * Function is to create new label
	 * </p>
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, value = "/createlabel")
	public ResponseEntity<?> createLabel(@RequestParam String labelName,HttpServletRequest req)
			throws ToDoException {
		logger.info("Inside the createlabelfromNote in controller" + req.getRequestURI());
		RestPreconditions.checkNotNull(req.getHeader("token"),"NullPointerExcetion : token is not present");
		iNoteService.createLabel(labelName,tokenGenerator.parseJWT(req.getHeader("token")));
		return new ResponseEntity("Successfully moved to Archieve", HttpStatus.OK);
	}
	/****************************************************************************************************************************
	 * <p>
	 * Function is to move note into trash after deletion
	 * </p>
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.POST, value = "/addlabel")
	public ResponseEntity<?> addLabel(@RequestBody LabelDTO labelDto,HttpServletRequest req)
			throws ToDoException {
		logger.info("Inside the createlabelfromNote in controller" + req.getRequestURI());
		RestPreconditions.checkNotNull(labelDto.getLabelName(), "NoValuePresentException : Empty String is not allowed for adding the label Name");
		RestPreconditions.checkNotNull(req.getHeader("token"),"NullPointerExcetion : token is not present");
		iNoteService.addLabel(labelDto,tokenGenerator.parseJWT(req.getHeader("token")));
		return new ResponseEntity("Successfully moved to Archieve", HttpStatus.OK);
	}
	/****************************************************************************************************************************
	 * <p>
	 * Function is to move note into trash after deletion
	 * </p>
	 * 
	 * @param noteId
	 * @param req
	 * @return response
	 * @throws ToDoException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(method = RequestMethod.DELETE, value = "/deletelabel")
	public ResponseEntity<?> deleteLabel(HttpServletRequest req)
			throws ToDoException {
		logger.info("Inside the createlabelfromNote in controller" + req.getRequestURI());
		
		return new ResponseEntity("Successfully moved to Archieve", HttpStatus.OK);
	}
}