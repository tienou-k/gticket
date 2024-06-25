package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.models.Notification;
import com.gticket.gestionticket.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@AllArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping(value = "/create-notification", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Notification> creerTicket(@RequestBody Notification notification) {
        System.out.println("Content-Type: application/json");
        Notification nouvelNotification = notificationService.creer(notification);
        return new ResponseEntity<>(nouvelNotification, HttpStatus.CREATED);
    }

    @GetMapping("/list-notification")
    public ResponseEntity<List<Notification>> lireNotification() {
        List<Notification> notification = notificationService.lire();
        return ResponseEntity.ok(notification);
    }



    @PutMapping("/modifier-notification/{id}")
    public ResponseEntity<Notification> modifierNotification(@PathVariable Long id, @RequestBody Notification modifN) {
        modifN.setId(id);
        Notification notification = notificationService.modifier(id,modifN);
        return ResponseEntity.ok(notification);
    }

    @DeleteMapping("/supprimer-notification/{id}")
    public ResponseEntity<String> supprimerNotification(@PathVariable Long id) {
        String message = notificationService.supprimer(id);
        return ResponseEntity.ok(message);
    }
}
