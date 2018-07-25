package com.bridgeit.todoapplication.utilservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;

/**
 * @author : Vishal Dhar Dubey
 * @version : 1.0
 *          <p>
 *          <b>RestPreconditions is to chec for null</b>
 *          </p>
 * @since : 20-07-2018
 */
public class RestPreconditions {
	private static final Logger logger = LoggerFactory.getLogger(RestPreconditions.class);
	private RestPreconditions() {
	}

/*****************************************************************************************************************************
 * @param reference
 * @param errorMessage
 *            <p>
 *            Function is to check for null object and returns object if not
 *            null
 *            </p>
 * @return reference
 * @throws ToDoException
 */
	public static <T> T checkNotNull(T reference, @Nullable Object errorMessage) throws ToDoException {
		if (reference == null) {
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return reference;
	}

/******************************************************************************************************************************
 * @param reference
 *            <p>
 *            Function is to check for existence of given variable.
 *            </p>
 * @return reference
 * @throws ToDoException
 */
	public static boolean isPresentInDB(boolean reference, @Nullable Object errorMessage) throws ToDoException {
		if (reference) {
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return reference;
	}

/**
 * @param reference
 * @param errorMessage
 *            <p>
 *            Function is to check for null object and returns object if not
 *            null
 *            </p>
 * @return reference
 * @throws ToDoException
 */
	public static <T> String checkNotNull(String reference, @Nullable Object errorMessage) throws ToDoException {
		if (reference==null||reference.equals("")) {
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return reference;
	}
}
