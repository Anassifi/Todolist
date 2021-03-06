package com.pragmatic.anassifi.todolist.mysql.service;

import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements ITodoService {

    @Autowired
    @Qualifier("mysqlTodoRepository")
    private TodoRepository todoUserRepository;

    @Override
    public Optional<Todo> getTodoByUser(User user) {
        return todoUserRepository.findTodoByUser(user);
    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoUserRepository.findById(id);
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoUserRepository.save(todo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoUserRepository.deleteById(id);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoUserRepository.findAll();
    }
}
