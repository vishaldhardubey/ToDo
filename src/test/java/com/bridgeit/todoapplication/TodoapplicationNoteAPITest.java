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

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoapplicationNoteAPITest.class)
@SpringBootTest
public class TodoapplicationNoteAPITest {

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

	/****************************************************************************************************************************
	 * <p>
	 * test the user password and email Id whether they are matching or not
	 * </p>
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/userlogin").contentType(MediaType.APPLICATION_JSON)
				.content("{\"description\": \"String\",\"id\": \"string\",\"label\": {\"labelList\": [{}],\"labelName\": \"string\" },\"remindMe\": {\"laterToday\": \"2018-07-24T12:16:36.826Z\",\"nextWeek\": \"2018-07-24T12:16:36.826Z\",\"tomorrow\": \"2018-07-24T12:16:36.826Z\"},\"title\": \"string\"}")
				.accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.message").value("Successfully Login"))
				.andExpect(jsonPath("$.code").value("200"));
	}

}
