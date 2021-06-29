package com.pragmatic.anassifi.todolist.postgreSQL.service;

import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresTodo;
import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresUser;
import com.pragmatic.anassifi.todolist.postgreSQL.repository.PostgresTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServicePostgres implements PostgresITodoService {

    @Autowired
    @Qualifier("postgresTodoRepository")
    private PostgresTodoRepository todoUserRepository;

    @Override
    public List<PostgresTodo> getTodoByUser(PostgresUser postgresUser) {
        return todoUserRepository.findByPostgresUser(postgresUser);
    }

    @Override
    public Optional<PostgresTodo> getTodoById(Long id) {
        return todoUserRepository.findById(id);
    }

    @Override
    public PostgresTodo updateTodo(PostgresTodo postgresTodo) {
        return todoUserRepository.save(postgresTodo);
    }

    @Override
    public void addTodo(Long id, String title, String description, boolean status) {
        todoUserRepository.save(new PostgresTodo(id, title, description, status));
    }

    @Override
    public void deleteTodo(Long id) {
        Optional<PostgresTodo> todo = todoUserRepository.findById(id);
        if(todo.isPresent()){
            todoUserRepository.deleteById(id);
        }
    }

    @Override
    public void saveTodo(PostgresTodo postgresTodo) {
        todoUserRepository.save(postgresTodo);
    }
}
