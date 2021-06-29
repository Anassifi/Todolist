package com.pragmatic.anassifi.todolist.postgreSQL.controller;

import java.util.List;

import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pragmatic.anassifi.todolist.postgreSQL.exception.PostgresUserNotFoundException;
import com.pragmatic.anassifi.todolist.postgreSQL.repository.PostgresUserRepository;

@RestController
public class PostgresUserController {

	private final PostgresUserRepository repository;

	public PostgresUserController(PostgresUserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/users")
	List<PostgresUser> allTasks() {
		return repository.findAll();
	}

	@PostMapping("/users")
	PostgresUser newUser(@RequestBody PostgresUser newPostgresUser) {
		return repository.save(newPostgresUser);
	}

	@GetMapping("/users/{id}")
	PostgresUser getATask(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new PostgresUserNotFoundException(id));
	}

	@PutMapping("/users/{id}")
	PostgresUser replaceUser(@RequestBody PostgresUser newPostgresUser, @PathVariable Long id) {
		return repository.findById(id).map(user -> {
			user.setUsername(newPostgresUser.getUsername());
			user.setPassword(newPostgresUser.getPassword());
			return repository.save(user);
		}).orElseGet(() -> {
			newPostgresUser.setId(id);
			return repository.save(newPostgresUser);
		});
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
