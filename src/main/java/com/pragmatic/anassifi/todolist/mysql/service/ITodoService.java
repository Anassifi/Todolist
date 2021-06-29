package com.pragmatic.anassifi.todolist.mysql.service;

import java.util.List;
import java.util.Optional;

import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import com.pragmatic.anassifi.todolist.mysql.model.User;

public interface ITodoService {
    List<Todo> getTodoByUser(User user);
    Optional<Todo> getTodoById(Long id);
    void addTodo(Long id, String title, String description, boolean status);
    void deleteTodo(Long id);
    void saveTodo(Todo todo);
}

