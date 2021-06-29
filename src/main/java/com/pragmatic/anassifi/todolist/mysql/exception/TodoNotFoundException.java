package com.pragmatic.anassifi.todolist.mysql.exception;

public class TodoNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TodoNotFoundException(Long id) {
		super("Could not find task " + id);
	}
}
