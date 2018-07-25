package com.bridgeit.todoapplication.userservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * @author : Vishal Dhar Dubey
 * @version: 1.0
 *          <p><b>User Login controller will control the funcationality of all the
 *          API's and will be responsible for interacting with the postman.</b>
 *          </p>
 * @since : 16-07-2018
 */
public class LoggerInterceptor extends HandlerInterceptorAdapter{
	static Logger logger=LoggerFactory.getLogger(LoggerInterceptor.class);
	
	   
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	    	logger.info("gfgdfgdffff"+request.getRequestURL());
	        return true;
	    }
	 
	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
	            Object handler, ModelAndView modelAndView) throws Exception {
	         
	    }
	 
	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
	            Object handler, Exception ex) throws Exception {
	 
	    }
}
