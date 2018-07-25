package com.bridgeit.todoapplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * <p>
 * <b>Class is to initialize the servlet</b>
 * </p>
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 09-07-2018
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TodoapplicationApplication.class);
	}

}
