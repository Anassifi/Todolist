package com.pragmatic.anassifi.todolist.mysql.controller;

import com.pragmatic.anassifi.todolist.mysql.model.AuthRequest;
import com.pragmatic.anassifi.todolist.mysql.model.User;
import com.pragmatic.anassifi.todolist.mysql.service.UserService;
import com.pragmatic.anassifi.todolist.mysql.util.JwtUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@RestController
public class WelcomeController {

    @Autowired
    UserService userService;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    Configuration configuration;

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
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid Username or password");
        }

        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping("/register")
    public ResponseEntity<User> addNewUser(@RequestBody User user, Map<String, Object> model) throws MessagingException, IOException, TemplateException {

        model.put("name", user.getName());
        model.put("email", user.getEmail());
        model.put("signature", "Start adding your tasks now!");

        sendmail(user, model);

        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void sendmail(User user, Map<String, Object> model) throws MessagingException, IOException, TemplateException {

        final String emailToReciptient = user.getEmail();
        final String emailSubject = "Successfully Registered To PragmaticTodolist";
        MimeMessage message = javaMailSender.createMimeMessage();

        //set Media Type
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        Template template = configuration.getTemplate("email-template.ftl");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setTo(emailToReciptient);
        helper.setText(html, true);
        helper.setSubject(emailSubject);

        javaMailSender.send(message);
    }


}
