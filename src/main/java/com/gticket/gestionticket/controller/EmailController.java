package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.models.EmailMessage;
import com.gticket.gestionticket.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public class EmailController {

    private EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/envoyer-mail")
    public ResponseEntity envoyerEmail(@RequestBody EmailMessage emailMessage){
    this.emailService.envoyerMail(emailMessage.getA(), emailMessage.getTitre(),emailMessage.getMessage());
    return ResponseEntity.ok("Message envoyer.!");
}
}
