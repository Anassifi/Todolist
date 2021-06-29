package com.pragmatic.anassifi.todolist.mysql.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TodoNotAddedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6928582491494373819L;

	public TodoNotAddedException(String message) {
        super(message);
    }
}
