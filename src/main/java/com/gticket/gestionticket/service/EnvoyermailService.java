package com.gticket.gestionticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EnvoyermailService {


    @Autowired
    private JavaMailSender javaMailSender;
    

    public void envoyerEmail(String email, String subject, String text) {
    }

    public void sendEmail(String email, String subject, String text) {

    }
}