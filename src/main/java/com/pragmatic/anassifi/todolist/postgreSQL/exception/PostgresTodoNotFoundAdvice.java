package com.pragmatic.anassifi.todolist.postgreSQL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PostgresTodoNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(PostgresTodoNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String TodoNotFoundHandler(PostgresTodoNotFoundException ex) {
	    return ex.getMessage();
	}
}
