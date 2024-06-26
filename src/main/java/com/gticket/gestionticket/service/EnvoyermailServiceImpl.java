package com.gticket.gestionticket.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EnvoyermailServiceImpl implements EmailService{
private final JavaMailSender envoyeurEmail;

    public EnvoyermailServiceImpl(JavaMailSender envoyeurEmail) {
        this.envoyeurEmail = envoyeurEmail;
    }

    @Override
    public void envoyerMail(String a, String Tittre, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("alextienou14@gmail.com");
        mailMessage.setTo(a);
        mailMessage.setSubject(Tittre);
        mailMessage.setText(message);

        this.envoyeurEmail.send(mailMessage);
    }
}