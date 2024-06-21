package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.models.Ticket;
import com.gticket.gestionticket.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/create-ticket")
    public ResponseEntity<Ticket> creerTicket(@RequestBody Ticket ticket) {
        System.out.println("Content-Type: application/json");
        Ticket nouveauTicket = ticketService.creer(ticket);
        return new ResponseEntity<>(nouveauTicket, HttpStatus.CREATED);
    }

    @GetMapping("/list-tickets")
    public ResponseEntity<List<Ticket>> lireTickets() {
        List<Ticket> tickets = ticketService.lire();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/categorie/{categorieName}")
    public ResponseEntity<List<Ticket>> lireTicketsParCategorie(@PathVariable String categorieName) {
        List<Ticket> tickets = ticketService.lireParCategorie(categorieName);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/apprenant/{apprenantId}")
    public ResponseEntity<List<Ticket>> lireTicketsParApprenant(@PathVariable Long apprenantId) {
        List<Ticket> tickets = ticketService.lireParApprenant(apprenantId);
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/modifier-ticket/{id}")
    public ResponseEntity<Ticket> modifierTicket(@PathVariable Long id, @RequestBody Ticket modifT) {
        modifT.setId(id);
        Ticket ticket = ticketService.modifier(id,modifT);
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/supprimer-ticket/{id}")
    public ResponseEntity<String> supprimerTicket(@PathVariable Long id) {
        String message = ticketService.supprimer(id);
        return ResponseEntity.ok(message);
    }
}
