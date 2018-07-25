package com.bridgeit.todoapplication.noteservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgeit.todoapplication.noteservice.model.Label;

public interface INoteLabelRepository extends MongoRepository<Label, String>{
	public boolean existsByLabelName(List<String> list);
	public Optional<Label> findByLabelName(List<String> list);
}
