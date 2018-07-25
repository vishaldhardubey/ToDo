package com.bridgeit.todoapplication.userservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bridgeit.todoapplication.userservice.model.User;
/**
 * Description : Data Access Object interface extending to Mongo Repository.
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 */
public interface IUserRepository extends MongoRepository<User, String>{
		/**
		 * Function is to get the user object on the basis of email ID.
		 * @param email
		 * @return Student Object
		 */
		public Optional<User> getByEmail(String email);
		/**
		 * Function is to get the user object on the basis of user ID.
		 * @param email
		 * @return Student Object
		 */
		public Optional<User> getById(String Id);
		/**
		 * Function is to search user Email inside database.
		 * @param email
		 * @return boolean
		 */
		public boolean existsByEmail(String email);
}
