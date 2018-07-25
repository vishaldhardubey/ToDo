package com.bridgeit.todoapplication.userservice.model;
/**
 * <p>
 * <b>Model class for Email.</b>
 * </p>
 * @author  : Vishal Dhar Dubey
 * @version : 1.0
 * @since   : 16-07-2018
 */
public class Email {
	private String to;
	private String subject;
	private String text;

	public Email() {
		super();
	}

	public Email(String to, String subject, String text) {
		super();
		this.to = to;
		this.subject = subject;
		this.text = text;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
