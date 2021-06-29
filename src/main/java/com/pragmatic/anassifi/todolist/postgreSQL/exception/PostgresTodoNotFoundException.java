package com.pragmatic.anassifi.todolist.postgreSQL.exception;

public class PostgresTodoNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostgresTodoNotFoundException(Long id) {
		super("Could not find task " + id);
	}
}
