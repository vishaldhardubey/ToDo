package com.bridgeit.todoapplication.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bridgeit.todoapplication.userservice.model.User;
import com.bridgeit.todoapplication.utilservice.MailService;

/**
 * <p>
 * <b>Helper class is for configuring all the configuration that are required.</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 16-07-2018
 */
@Configuration
public class ToDoConfig {
	/**
	 * <p>
	 * Function is to return the bean of BCryptPasswordEncoder.
	 * </p>
	 * 
	 * @return Object of BCryptPasswordEncoder.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * <p>
	 * Function is to return the bean of MailService.
	 * </p>
	 * 
	 * @return Object of MailService.
	 */
	@Bean
	@Scope("prototype")
	public MailService mailService() {
		return new MailService();
	}

	/**
	 * <p>
	 * Function is to return the bean of ModelMapper.
	 * </p>
	 * 
	 * @return Object of ModelMapper.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * <p>
	 * Function is to create and return object of Jedis Connection Facotry
	 * </p>
	 * 
	 * @return object of jedis connection factory
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	/**
	 * <p>
	 * Helper configuration function that simplifies Redis data access code. Performs automatic
	 * serialization/deserialization between the given objects and the underlying
	 * binary data in the Redis store
	 * </p>
	 * 
	 * @return
	 */
	@Bean
	public RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<String, User>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
}
