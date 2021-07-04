package com.pragmatic.anassifi.todolist.mysql.service;

import java.util.List;
import java.util.Optional;

import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import com.pragmatic.anassifi.todolist.mysql.model.User;

public interface ITodoService {
    Optional<Todo> getTodoByUser(User user);
    Optional<Todo> getTodoById(Long id);
    Todo addTodo(Todo todo);
    void deleteTodo(Long id);
    List<Todo> getAllTodos();
}

