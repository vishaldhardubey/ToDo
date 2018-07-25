package com.bridgeit.todoapplication.noteservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.todoapplication.noteservice.model.Label;
import com.bridgeit.todoapplication.noteservice.model.LabelDTO;
import com.bridgeit.todoapplication.noteservice.model.Note;
import com.bridgeit.todoapplication.noteservice.model.NoteArchieveDTO;
import com.bridgeit.todoapplication.noteservice.model.NoteDTO;
import com.bridgeit.todoapplication.noteservice.repository.INoteArchieveRepository;
import com.bridgeit.todoapplication.noteservice.repository.INoteLabelRepository;
import com.bridgeit.todoapplication.noteservice.repository.INoteRepository;
import com.bridgeit.todoapplication.utilservice.exceptions.RestPreconditions;
import com.bridgeit.todoapplication.utilservice.exceptions.ToDoException;
import com.bridgeit.todoapplication.utilservice.modelmapperservice.ModelMapperService;

/**
 * <p>
 * <b>Note Service Implementation for the Note Service interface.</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 16-07-2018
 */

@Service
public class NoteServiceImpl implements INoteService {
	private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
	@Autowired
	private INoteRepository iNoteRepository;

	@Autowired
	private INoteLabelRepository iNoteLabelRepository;

	@Autowired
	private INoteArchieveRepository iNoteArchieveRepository;

	@Autowired
	ModelMapperService modelMapperService;

	/***********************************************************************************************************************************
	 * <p>
	 * Function is to create Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param notedto
	 * @param userId
	 * @throws ToDoException
	 */
	@Override
	public void createNote(NoteDTO noteDto, String userId) throws ToDoException {
		logger.info("Inside Create Note method");
		// RestPreconditions.checkNotNull(noteDto,"");
		/*
		 * RestPreconditions.isPresentInDB(!iNoteRepository.existsById(noteDto.getId()),
		 * "NoValueException : No value present for the given note Id");
		 */
		RestPreconditions.checkNotNull(noteDto, "NullPointerException : noteDto must contain some value");
		Note note = modelMapperService.map(noteDto, Note.class);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		note.setUserId(userId);
		note.setCreatedDate(timeStamp);
		note.setLastDatModified(timeStamp);
		if ((noteDto.getDescription() == null && noteDto.getTitle() == null) && noteDto.getLabelNameList() != null
				&& (noteDto.getDescription().equals("") && noteDto.getTitle().equals(""))
				&& !noteDto.getLabelNameList().equals("")) {
			if (iNoteLabelRepository.existsByLabelName(noteDto.getLabelNameList())) {
				Optional<Label> labelObject = iNoteLabelRepository.findByLabelName(noteDto.getLabelNameList());
				Label label = new Label();
				List<Label> list = new ArrayList<Label>();
				label.setLabelName(labelObject.get().getLabelName());
				list.add(label);
				note.setLabel(list);
			}
			// createLabel(noteDto.getLabelName(), userId);
		}
		
		List<Label> list = new ArrayList<Label>();
		
		for (int j = 0; j < noteDto.getLabelNameList().size(); j++) {
			Label label = new Label();
			label.setLabelName(noteDto.getLabelNameList().get(j));
			list.add(label);
			//iNoteLabelRepository.insert(noteDto.getLabelNameList().get(j));
		}
		
		note.setLabel(list);
		logger.info("Ended after Creating a Note");
		iNoteRepository.insert(note);
	}

	/***********************************************************************************************************************************
	 * <p>
	 * Function is to get note by note Id.
	 * </p>
	 * 
	 * @param note
	 * @param userId
	 * @throws ToDoException
	 */
	@Override
	public List<Note> getNoteById(Note note, String userId) throws ToDoException {
		logger.info("Inside getNoteById method");
		String noteId = note.getId();
		RestPreconditions.checkNotNull(iNoteRepository.existsById(note.getId()),
				"No note is available for this note ID");
		List<Note> fullNote = iNoteRepository.getById(noteId);
		logger.info("Ended after getting a Note");
		return fullNote;
	}

	/*********************************************************************************************************************
	 * <p>
	 * Function is to get all notes by Id
	 * </p>
	 * 
	 * @param userId
	 * @throws ToDoException
	 */
	@Override
	public List<Note> getAllNotesById(String userId) throws ToDoException {
		logger.info("Inside getAllNoteById method");
		List<Note> list = iNoteRepository.findAllByUserId(userId);
		RestPreconditions.checkNotNull(list, "NoValueException : No Any Note is present for the given User Id");
		List<Note> noteList = new ArrayList<Note>();
		for (Note note : noteList) {
			if (!note.isTrashStatus()) {
				noteList.add(note);
			}
		}
		RestPreconditions.checkNotNull(list, "No any List is present for given user ID");
		System.out.println(noteList);
		logger.info("Ended after getting all Note");
		return noteList;
	}

	/*********************************************************************************************************************
	 * <p>
	 * Function is to update Note for the authorised and logined user.
	 * </p>
	 * 
	 * @param note
	 * @param userId
	 */
	@Override
	public void updateNote(NoteDTO noteDto, String userId) throws ToDoException {
		logger.info("Inside updateNote method");
		RestPreconditions.checkNotNull(noteDto.getId(), "NullPointerException : noteId must not be null.");
		RestPreconditions.checkNotNull(noteDto.getTitle(),
				"NullPointerException : Title can be empty string but not null.");
		RestPreconditions.checkNotNull(noteDto.getDescription(),
				"Null Exception : Description can be empty string but not null");
		Optional<Note> repositoryNote = iNoteRepository.findById(noteDto.getId());
		RestPreconditions.checkNotNull(repositoryNote, "repositoryNote is not present");
		if (!repositoryNote.isPresent()) {
			throw new ToDoException("note is not present for the given Note Id");
		}
		if (!noteDto.getTitle().equals("")) {
			repositoryNote.get().setTitle(noteDto.getTitle());
		}
		if (!noteDto.getDescription().equals("")) {
			repositoryNote.get().setTitle(noteDto.getTitle());
		}
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repositoryNote.get().setLastDatModified(timeStamp);
		Note updated = repositoryNote.get();
		iNoteRepository.save(updated);
		logger.info("Ended after updating a Note");
	}

	/*********************************************************************************************************************
	 * <p>
	 * Function is to delete note by Id.
	 * </p>
	 * 
	 * @param notedId
	 * @param userId
	 * @throws ToDoException
	 */
	@Override
	public void deleteNote(String noteId, String userId) throws ToDoException {
		logger.info("Inside deleteNote method");
		if (!iNoteRepository.existsById(userId)) {
			throw new ToDoException("Note is not Available for this Note Id");
		}
		if (iNoteRepository.existsById(userId)) {
			Optional<Note> note = iNoteRepository.findById(noteId);
			note.get().setTrashStatus(true);
			iNoteRepository.save(note.get());
		}
		logger.info("Ended after deleting a Note");
	}

	/************************************************************************************************************************
	 * <p>
	 * Function is to move note to the Archieve by Id.
	 * </p>
	 * 
	 * @param notedId
	 * @param userId
	 * @throws ToDoException
	 */
	@Override
	public void moveToArchieve(String noteId, String userId) throws ToDoException {
		logger.info("Inside moveToArchieve method");
		RestPreconditions.isPresentInDB(iNoteRepository.existsById(userId),
				"No Value Present : Given User Id doesn't exists in our database");
		RestPreconditions.isPresentInDB(iNoteRepository.existsById(noteId),
				"No Value Present : Given Note Id doesn't exists in our database");

		Optional<Note> noteObject = iNoteRepository.findById(noteId);
		RestPreconditions.checkNotNull(noteObject, "NullPointerException : Note is not present for this note Id");
		NoteArchieveDTO note = modelMapperService.map(noteObject.get(), NoteArchieveDTO.class);
		iNoteArchieveRepository.insert(note);
		iNoteRepository.deleteById(noteId);
		logger.info("Ended after deleting a Note and stored in trash memory");
	}

	/**************************************************************************************************************************
	 * <p>
	 * Function is to give reminder to the User
	 * </p>
	 * 
	 * @param dateAndTime
	 */
	public void remindMe(String dateAndTime) {
		// long startTime = System.currentTimeMillis();
		// compare the time and calculate the total time required.
	}

	/************************************************************************************************************************
	 * <p>
	 * Function is to restore note from the Archieve by note Id.
	 * </p>
	 * 
	 * @param notedId
	 * @param userId
	 * @throws ToDoException
	 */
	@Override
	public void restoreFromArchieve(String noteId, String userId) throws ToDoException {
		logger.info("Inside moveToArchieve method");
		RestPreconditions.isPresentInDB(iNoteRepository.existsById(userId),
				"No Value Present : Given User Id doesn't exists in our database");
		RestPreconditions.isPresentInDB(iNoteRepository.existsById(noteId),
				"No Value Present : Given Note Id doesn't exists in our database");

		Optional<NoteArchieveDTO> noteObject = iNoteArchieveRepository.findById(noteId);
		RestPreconditions.checkNotNull(noteObject, "NullPointerException : Note is not present for this note Id");
		Note note = modelMapperService.map(noteObject.get(), Note.class);
		iNoteRepository.insert(note);
		iNoteArchieveRepository.deleteById(noteId);
		logger.info("Ended after restoring a Note.");

	}

	/****************************************************************************************************************************
	 * <p>
	 * Function is to move note into trash after deletion
	 * </p>
	 * 
	 * @param noteId
	 * @param req
	 */
	@Override
	public void createLabelFromNote(NoteDTO noteDto, String userId) throws ToDoException {
		logger.info("Inside the createlabelfromNote in controller");

	}

	@Override
	public void createLabel(String labelName, String userId) throws ToDoException {
		RestPreconditions.checkNotNull(labelName, "NullPointerExceptioin : LabelName cann't be null");

	}

	@Override
	public void addLabel(LabelDTO labelDto, String userId) {

	}
}