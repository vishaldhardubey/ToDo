package com.bridgeit.todoapplication;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bridgeit.todoapplication.userservice.controller.UserController;

/**
 * <p>
 * <b>Application Programming Interface test class for testing all the APIs</b>
 * </p>
 * 
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 * @since : 09-07-2018
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoapplicationApplication.class)
@SpringBootTest
public class TodoapplicationApplicationTests {

	static final String filePath = "/home/administrator/Hibernate/todoapplication/src/main/java/com/bridgeit/todoapplication/todoApplicationTestFile.json";

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webappcon;

	/**
	 * <p>
	 * </p>
	 */
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webappcon).build();
	}

	/**
	 * <p>
	 * test the user password and email Id whether they are matching or not
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserPassword() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/userlogin").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\": \"yugandharap2013@gmail.com\",\"password\":\"Yuga@#123\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").value("Successfully Login"))
				.andExpect(jsonPath("$.code").value("200"));
	}

	/**
	 * <p>
	 * test the user password by entering wrong password
	 * </p>
	 * @throws Exception
	 */
	@Test
	public void testUserInvalidPassword() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/userlogin").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\": \"yugandharap2013@gmail.com\",\"password\":\"Yuga@#\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message")
						.value("InputTypeMismatchException : Entered Email ID or Password is not correct"))
				.andExpect(jsonPath("$.code").value("-1"));
	}

	/**
	 * <p>
	 * test the user password by giving null value
	 * </p>
	 * @throws Exception
	 */
	@Test
	public void testUserNullPassword() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/userlogin").contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\": \"vishaldhardubey1807@gmail.com\"}").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("NullPointerException : Password Cann't Be null"))
				.andExpect(jsonPath("$.code").value("-1"));
	}

	/*
	 * @Test public void testActivateUser() throws Exception {
	 * mockMvc.perform(MockMvcRequestBuilders.get("/useractivation")).andExpect(
	 * MockMvcResultMatchers.status().isOk())
	 * .andExpect(MockMvcResultMatchers.content().string("Registered Successfully ")
	 * ); }
	 */
}
