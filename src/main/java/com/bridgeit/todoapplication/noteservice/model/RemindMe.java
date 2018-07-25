package com.bridgeit.todoapplication.noteservice.model;

import java.util.Date;

/**
 * <p>
 * <b>Model class of reminder type</b>
 * </p>
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 */
public class RemindMe {
	private String userId;
	private String id;
	private Date laterToday;
	private Date tomorrow;
	private Date NextWeek;

	public RemindMe() {
		super();
	}
	
	public Date getLaterToday() {
		return laterToday;
	}

	public void setLaterToday(Date laterToday) {
		this.laterToday = laterToday;
	}

	public Date getTomorrow() {
		return tomorrow;
	}

	public void setTomorrow(Date tomorrow) {
		this.tomorrow = tomorrow;
	}

	public Date getNextWeek() {
		return NextWeek;
	}

	public void setNextWeek(Date nextWeek) {
		NextWeek = nextWeek;
	}

}
