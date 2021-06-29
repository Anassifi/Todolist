package com.pragmatic.anassifi.todolist.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pragmatic.anassifi.todolist.mysql.model.User;

@Repository("mysqlUserRepository")
@Transactional(transactionManager = "transactionManagerUser")
public interface UserRepository extends JpaRepository<User, Long> {
}
