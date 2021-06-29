package com.pragmatic.anassifi.todolist.postgreSQL.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresTodo;
import com.pragmatic.anassifi.todolist.postgreSQL.repository.PostgresTodoRepository;


@Component
public class PostgresMyRunner implements CommandLineRunner {
	
	 private static final Logger logger = LoggerFactory.getLogger(PostgresMyRunner.class);
	
	@Autowired
	private PostgresTodoRepository repository;
	

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		repository.deleteAll();
		
		repository.save(new PostgresTodo(1L, "TodoTask", "TodoDes project", true));
		repository.save(new PostgresTodo(2L, "TodoTask2", "TodoDes project2", false));
		repository.save(new PostgresTodo(3L, "TodoTask3", "TodoDes project3", false));
		
		repository.findAll().forEach((todo) -> {
            logger.info("{}", todo);
        });
	}

}
