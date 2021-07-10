package com.pragmatic.anassifi.todolist.mysql.repository;

import com.pragmatic.anassifi.todolist.mysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("mysqlUserRepository")
@Transactional(transactionManager = "transactionManagerUser")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);
}
