package com.pragmatic.anassifi.todolist.postgreSQL.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pragmatic.anassifi.todolist.postgreSQL.exception.PostgresTodoNotFoundException;
import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresTodo;
import com.pragmatic.anassifi.todolist.postgreSQL.repository.PostgresTodoRepository;


@RestController
public class PostgresTodoController {

	private final PostgresTodoRepository repository;

	public PostgresTodoController(PostgresTodoRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/tasks")
	List<PostgresTodo> allTasks() {
		return repository.findAll();
	}

	@PostMapping("/tasks")
	PostgresTodo newTodo(@RequestBody PostgresTodo newPostgresTodo) {
		return repository.save(newPostgresTodo);
	}

	@GetMapping("/tasks/{id}")
	PostgresTodo getATodo(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new PostgresTodoNotFoundException(id));
	}

	@PutMapping("/tasks/{id}")
	PostgresTodo replaceTodo(@RequestBody PostgresTodo newPostgresTodo, @PathVariable Long id) {
		return repository.findById(id).map(todo -> {
			todo.setTitle(newPostgresTodo.getTitle());
			todo.setDescription(newPostgresTodo.getDescription());
			return repository.save(todo);
		}).orElseGet(() -> {
			newPostgresTodo.setId(id);
			return repository.save(newPostgresTodo);
		});
	}

	@DeleteMapping("/tasks/{id}")
	void deleteTodo(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
