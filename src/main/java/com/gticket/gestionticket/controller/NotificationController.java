package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.models.Notification;
import com.gticket.gestionticket.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "3 Notification", description = "API Gestion des notificatios")
@RestController
@RequestMapping("/api/notification")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @Operation(summary = "Creation d'une  notification", description = " ")
    @PostMapping(value = "/create-notification", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Notification> creerTicket(@RequestBody Notification notification) {
        System.out.println("Content-Type: application/json");
        Notification nouvelNotification = notificationService.creer(notification);
        return new ResponseEntity<>(nouvelNotification, HttpStatus.CREATED);
    }

    @Operation(summary = "Liste des notifications", description = " ")
    @GetMapping("/list-notification")
    public ResponseEntity<List<Notification>> lireNotification() {
        List<Notification> notification = notificationService.lire();
        return ResponseEntity.ok(notification);
    }

    @Operation(summary = "Modification d'une notification", description = " ")
    @PutMapping("/modifier-notification/{id}")
    public ResponseEntity<Notification> modifierNotification(@PathVariable Long id, @RequestBody Notification modifN) {
        modifN.setId(id);
        Notification notification = notificationService.modifier(id,modifN);
        return ResponseEntity.ok(notification);
    }

    @Operation(summary = "Suppression d'une notification", description = " ")
    @DeleteMapping("/supprimer-notification/{id}")
    public ResponseEntity<String> supprimerNotification(@PathVariable Long id) {
        String message = notificationService.supprimer(id);
        return ResponseEntity.ok(message);
    }
}
