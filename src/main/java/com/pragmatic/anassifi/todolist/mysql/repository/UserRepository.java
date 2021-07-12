package com.pragmatic.anassifi.todolist.mysql.repository;

import com.pragmatic.anassifi.todolist.mysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository("mysqlUserRepository")
@Transactional(transactionManager = "transactionManagerUser")
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String username);

    Optional<User> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE User a " +
            "SET a.enabled = TRUE WHERE a.email = ?1")
    void enableUser(String email);
}
