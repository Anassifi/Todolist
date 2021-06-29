package com.pragmatic.anassifi.todolist.postgreSQL.repository;

import com.pragmatic.anassifi.todolist.postgreSQL.model.PostgresUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("postgresUserRepository")
@Transactional(transactionManager = "postTransactionManager")
public interface PostgresUserRepository extends JpaRepository<PostgresUser, Long> {
}
