package com.pragmatic.anassifi.todolist.mysql.controller;

import com.pragmatic.anassifi.todolist.mysql.model.AuthRequest;
import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.service.MailService;
import com.pragmatic.anassifi.todolist.mysql.service.RegistrationService;
import com.pragmatic.anassifi.todolist.mysql.service.UserService;
import com.pragmatic.anassifi.todolist.mysql.util.JwtUtil;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {

        return "Welcome to todolist app";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {

        User user = userService.findByUserName(authRequest.getUserName());

        if (user.getEnabled()) {
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
                );
            } catch (Exception ex) {
                throw new Exception("Invalid Username or password");
            }

            return jwtUtil.generateToken(authRequest.getUserName());
        } else {
            throw new Exception("You need to confirm your email");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> addNewUser(@RequestBody User user, Map<String, Object> model) throws MessagingException, IOException, TemplateException {

        String template = "email-template";

        model.put("name", user.getName());
        model.put("email", user.getEmail());
        model.put("token", userService.signUpUser(user));
        model.put("signature", "Start adding your tasks now!");

        mailService.sendmail(user, model, template);


        return new ResponseEntity<>("User was created " + user.getUserName(), HttpStatus.OK);
    }

    @GetMapping("/registration/confirm")
    public ResponseEntity<String> confirmRegistrationToken(@RequestParam("token") String token) {
        return new ResponseEntity<>(registrationService.confirmToken(token), HttpStatus.OK);
    }


}
