package com.pragmatic.anassifi.todolist.mysql.util;

import com.pragmatic.anassifi.todolist.mysql.model.Todo;
import com.pragmatic.anassifi.todolist.mysql.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private TodoRepository repository;

    @Override
    public void run(String... args) {
        repository.deleteAll();

        repository.save(new Todo(1L, "TodoTask", "TodoDes project", true));
        repository.save(new Todo(2L, "TodoTask2", "TodoDes project2", false));
        repository.save(new Todo(3L, "TodoTask3", "TodoDes project3", false));

        repository.findAll().forEach((todo) -> logger.info("{}", todo));
    }

}
