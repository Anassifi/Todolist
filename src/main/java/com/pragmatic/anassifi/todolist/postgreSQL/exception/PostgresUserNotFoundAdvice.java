package com.pragmatic.anassifi.todolist.postgreSQL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PostgresUserNotFoundAdvice {
	@ResponseBody
	@ExceptionHandler(PostgresUserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String UserNotFoundHandler(PostgresUserNotFoundException ex) {
	    return ex.getMessage();
	}
}
