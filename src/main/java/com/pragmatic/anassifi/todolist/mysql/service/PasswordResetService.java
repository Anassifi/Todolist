package com.pragmatic.anassifi.todolist.mysql.service;

import com.pragmatic.anassifi.todolist.mysql.model.PasswordResetToken;
import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.repository.PasswordResetRepository;
import com.pragmatic.anassifi.todolist.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    private static final long EXPIRE_TOKEN_AFTER_MINS = 30;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetRepository passwordResetRepository;


    public String forgotPassword(String email) {
        Optional<User> userReset = userRepository.findByEmail(email);


        if (!userReset.isPresent()) {
            return "Invalid email";
        }

        User user = userReset.get();
        PasswordResetToken passwordReset = new PasswordResetToken();
        String token = generateToken();

        String tempoPassword = email.toLowerCase().trim().substring(14);
        passwordReset.setPassword(tempoPassword);
        passwordReset.setRepeatPassword(tempoPassword);
        passwordReset.setToken(token);
        passwordReset.setTokenCreationDate(LocalDateTime.now());
        passwordReset.setUser(user);

        passwordResetRepository.save(passwordReset);

        return passwordReset.getToken();
    }
    
    public String resetPassword(String token, String password, String repeatPassword) {
        Optional<PasswordResetToken> resetToken = Optional.ofNullable(passwordResetRepository.findByToken(token));

        if (!resetToken.isPresent()) {
            return "Invalid token";
        }

        LocalDateTime tokenCreationDate = resetToken.get().getTokenCreationDate();

        if (isTokenExpired(tokenCreationDate)) {
            return "Token expired.";
        }

        PasswordResetToken passwordReset = resetToken.get();
        passwordReset.setPassword(password);
        passwordReset.setRepeatPassword(repeatPassword);

        passwordResetRepository.save(passwordReset);

        return "Your password successfully updated";
    }

    /**
     * Check whether the created token expired or not.
     *
     * @param tokenCreationDate
     * @return true or false
     */
    private boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);

        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINS;
    }

    private String generateToken() {
        return UUID.randomUUID().toString() +
                UUID.randomUUID().toString();
    }

}
