package com.pragmatic.anassifi.todolist.postgreSQL.exception;

public class PostgresUserNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostgresUserNotFoundException(Long id) {
		super("Could not find user " + id);
	}
}
