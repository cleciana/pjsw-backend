package com.example.demo.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * MailController, essa classe deveria enviar um email, porém querer não é poder e eu vou dormir
 */
@RestController
public class MailController {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setText("Hello from UCDb. This is an antomatic email to inform your account has been created successfully, do not answer.");
        msg.setTo("lozanedias23@gmail.com");
        msg.setFrom("conta.semnada23007@gmail.com");

        try {
            mailSender.send(msg);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "An error occurred. Email not sent.";
        }
    }
}