package com.pragmatic.anassifi.todolist.mysql.controller;

import com.pragmatic.anassifi.todolist.mysql.exception.TodoNotFoundException;
import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import com.pragmatic.anassifi.todolist.mysql.repository.TodoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mysql")
public class MysqlTodoController {
	
	private final Logger LOG = LoggerFactory.getLogger(Todo.class);
	
	private final TodoRepository repository;

	public MysqlTodoController(TodoRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/tasks")
	public ResponseEntity<List<Todo>> allTasks() {
		LOG.debug("REST request to get all Todos");
		List<Todo> result =  repository.findAll();
		
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/tasks")
	public ResponseEntity<Todo> newTodo(@RequestBody Todo newTodo) {
		LOG.debug("REST request to save Todo: {}", newTodo);
        Todo result = repository.save(newTodo);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Todo> getATodo(@PathVariable Long id) {
		LOG.debug("REST request to get Todo : {}", id);
		Todo todo = repository.findById(id).orElseThrow( ()-> new TodoNotFoundException(id));
		return new ResponseEntity<Todo>(todo, HttpStatus.OK);
	}

	@PutMapping("/tasks/{id}")
	public ResponseEntity<Todo> replaceTodo(@RequestBody Todo newTodo) {
		LOG.debug("REST request to update Todo : {}", newTodo);
        Todo result = repository.save(newTodo);

        return new ResponseEntity<Todo>(result, HttpStatus.OK);
	}

	@DeleteMapping("/tasks/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
		LOG.debug("REST request to delete Todo : {}", id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
	}
}
