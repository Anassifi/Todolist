package com.pragmatic.anassifi.todolist.mysql.repository;

import com.pragmatic.anassifi.todolist.mysql.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
