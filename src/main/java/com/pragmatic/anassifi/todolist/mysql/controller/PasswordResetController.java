package com.pragmatic.anassifi.todolist.mysql.controller;

import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.repository.PasswordResetRepository;
import com.pragmatic.anassifi.todolist.mysql.repository.UserRepository;
import com.pragmatic.anassifi.todolist.mysql.service.MailService;
import com.pragmatic.anassifi.todolist.mysql.service.PasswordResetService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @Autowired
    private PasswordResetRepository passwordResetRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private MailService mailService;

    @PostMapping("/password-forgot")
    public String forgotPassword(@RequestParam String email, Map<String, Object> model) throws MessagingException, TemplateException, IOException {

        Optional<User> userOpt = userRepository.findByEmail(email);

        String response = passwordResetService.forgotPassword(email);

        String template = "reset-template";

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            model.put("name", user.getName());
            model.put("email", user.getEmail());
            model.put("token", response);

            mailService.sendmail(user, model, template);
        }

        if (!response.startsWith("Invalid")) {
            response = "http://localhost:8080/reset-password?token=" + response;
        }

        return response;
    }

    @PutMapping("/password-reset")
    public String resetPassword(@RequestParam String token, @RequestParam String password, @RequestParam String repeatPassword) {
        User user = passwordResetRepository.findByToken(token).getUser();
        if (password.equals(repeatPassword)) {
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
        }
        return passwordResetService.resetPassword(token, password, repeatPassword);
    }

}
