package com.pragmatic.anassifi.todolist.mysql.service;

import com.pragmatic.anassifi.todolist.mysql.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    Configuration configuration;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendmail(User user, Map<String, Object> model, String typeTemplate) throws MessagingException, IOException, TemplateException {

        final String emailToReciptient = user.getEmail();
        final String emailSubject = "PragmaticTodolist Mailing";
        MimeMessage message = javaMailSender.createMimeMessage();

        //set Media Type
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
        helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

        Template template = configuration.getTemplate(typeTemplate + ".ftl");

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setTo(emailToReciptient);
        helper.setText(html, true);
        helper.setSubject(emailSubject);

        javaMailSender.send(message);
    }
}
