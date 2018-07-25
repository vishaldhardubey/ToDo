package com.bridgeit.todoapplication.noteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgeit.todoapplication.noteservice.model.Note;

/**
 * <p>
 * <b>INoteRepository extending to mongo repository</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 15-07-2018
 */
@Repository
public interface INoteRepository extends MongoRepository<Note, String> {
	
	/**
	 * @param noteId
	 * @return List of all Notes
	 */
	public List<Note> getById(String noteId);

	/**
	 * @param userId
	 * @return List of all notes
	 */
	public List<Note> findAllByUserId(String userId);
}
