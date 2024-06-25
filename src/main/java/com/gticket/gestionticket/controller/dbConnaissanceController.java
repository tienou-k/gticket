package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.models.BaseDeConnaissance;
import com.gticket.gestionticket.models.Formateur;
import com.gticket.gestionticket.models.Notification;
import com.gticket.gestionticket.repository.FormateurRepository;
import com.gticket.gestionticket.service.NotificationService;
import com.gticket.gestionticket.service.dbConnaissanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.gticket.gestionticket.*;


import java.util.List;

@RestController
@RequestMapping("/api/db")
@AllArgsConstructor
public class dbConnaissanceController {
    private final dbConnaissanceService dbConnaissanceService;


    @PostMapping(value = "/create-db", consumes = "application/json", produces = "application/json")
    public ResponseEntity<BaseDeConnaissance> creerTicket(@RequestBody BaseDeConnaissance baseDeConnaissance) {
        System.out.println("Content-Type: application/json");
        BaseDeConnaissance nouveldb = dbConnaissanceService.creer(baseDeConnaissance);
        return new ResponseEntity<>(nouveldb, HttpStatus.CREATED);
    }

    @GetMapping("/list-db")
    public ResponseEntity<List<BaseDeConnaissance>> liredb() {
        List<BaseDeConnaissance> baseDeConnaissance = dbConnaissanceService.lire();
        return ResponseEntity.ok(baseDeConnaissance);
    }



    @PutMapping("/modifier-db/{id}")
    public ResponseEntity<BaseDeConnaissance> modifierNotification(@PathVariable Long id, @RequestBody BaseDeConnaissance modifB) {
        modifB.setId(id);
        BaseDeConnaissance baseDeConnaissance = dbConnaissanceService.modifier(id,modifB);
        return ResponseEntity.ok(baseDeConnaissance);
    }

    @DeleteMapping("/supprimer-db/{id}")
    public ResponseEntity<String> supprimerdb(@PathVariable Long id) {
        String message = dbConnaissanceService.supprimer(id);
        return ResponseEntity.ok(message);
    }
}
