package com.pragmatic.anassifi.todolist.mysql.controller;

import com.pragmatic.anassifi.todolist.mysql.exception.UserNotFoundException;
import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.repository.UserRepository;
import com.pragmatic.anassifi.todolist.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mysql")
public class MysqlUserController {

	private final UserRepository repository;

	@Autowired
	private UserService service;

	public MysqlUserController(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/users")
	List<User> allTasks() {
		return repository.findAll();
	}

	@PostMapping("/users")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}

	@GetMapping("/users/{id}")
	User getATask(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PutMapping("/users/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable Long id) {
		return repository.findById(id).map(user -> {
			user.setUserName(newUser.getUserName());
			user.setPassword(newUser.getPassword());
			return repository.save(user);
		}).orElseGet(() -> {
			newUser.setId(id);
			return repository.save(newUser);
		});
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
