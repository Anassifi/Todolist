package com.pragmatic.anassifi.todolist.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import com.pragmatic.anassifi.todolist.mysql.model.User;

import java.util.Optional;

@Repository("mysqlTodoRepository")
@Transactional(transactionManager = "transactionManagerUser")
public interface TodoRepository extends JpaRepository<Todo, Long> {
    Optional<Todo> findTodoByUser(User user);

}
