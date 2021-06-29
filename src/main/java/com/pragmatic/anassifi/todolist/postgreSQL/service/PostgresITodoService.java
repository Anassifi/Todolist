package com.pragmatic.anassifi.todolist.postgreSQL.service;

import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresTodo;
import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresUser;

import java.util.List;
import java.util.Optional;

public interface PostgresITodoService {
    List<PostgresTodo> getTodoByUser(PostgresUser postgresUser);
    Optional<PostgresTodo> getTodoById(Long id);
    PostgresTodo updateTodo(PostgresTodo postgresTodo);
    void addTodo(Long id, String title, String description, boolean status);
    void deleteTodo(Long id);
    void saveTodo(PostgresTodo postgresTodo);
}

