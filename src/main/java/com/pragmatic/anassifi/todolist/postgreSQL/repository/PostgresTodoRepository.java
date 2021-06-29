package com.pragmatic.anassifi.todolist.postgreSQL.repository;

import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresTodo;

import java.util.List;

@Repository("postgresTodoRepository")
@Transactional(transactionManager = "postTransactionManager")
public interface PostgresTodoRepository extends JpaRepository<PostgresTodo, Long> {
    List<PostgresTodo> findByPostgresUser(PostgresUser postgresUser);
}
