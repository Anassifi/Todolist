package com.pragmatic.anassifi.todolist.mysql.service;

import com.pragmatic.anassifi.todolist.mysql.model.ConfirmationToken;
import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final static String USER_NOT_FOUND_MSG =
            "user with userName %s not found";

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    public User save(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUserName(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        
        if(user == null) {
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, userName));
        }
        return userRepository.findByUserName(userName);
    }

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = encoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        // TODO: SEND EMAIL

        return token;
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}



