package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.models.Ticket;
import com.gticket.gestionticket.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "2 Tickets", description = "API Gestion des tickets")
@RestController
@RequestMapping("/api/tickets")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @Operation(summary = "Creation d'un ticket ", description = " ")
    @PostMapping("/create-ticket")
    public ResponseEntity<Ticket> creerTicket(@RequestBody Ticket ticket) {
        //System.out.println("Content-Type: application/json");
        Ticket nouveauTicket = ticketService.creer(ticket);
        return new ResponseEntity<>(nouveauTicket, HttpStatus.CREATED);
    }

    @Operation(summary = "liste des tickets ", description = " ")
    @GetMapping("/list-tickets")
    public ResponseEntity<List<Ticket>> lireTickets() {
        List<Ticket> tickets = ticketService.lire();
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Liste des tickets par Categorie ", description = " ")
    @GetMapping("/categorie/{categorieName}")
    public ResponseEntity<List<Ticket>> lireTicketsParCategorie(@PathVariable String categorieName) {
        List<Ticket> tickets = ticketService.lireParCategorie(categorieName);
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Liste des tickets par Apprenant", description = " ")
    @GetMapping("/apprenant/{apprenantId}")
    public ResponseEntity<List<Ticket>> lireTicketsParApprenant(@PathVariable Long apprenantId) {
        List<Ticket> tickets = ticketService.lireParApprenant(apprenantId);
        return ResponseEntity.ok(tickets);
    }

    @Operation(summary = "Modification d'un ticket ", description = " ")
    @PutMapping("/modifier-ticket/{id}")
    public ResponseEntity<Ticket> modifierTicket(@PathVariable Long id, @RequestBody Ticket modifT) {
        modifT.setId(id);
        Ticket ticket = ticketService.modifier(id,modifT);
        return ResponseEntity.ok(ticket);
    }

    @Operation(summary = "Suppression d'un ticket ", description = " ")
    @DeleteMapping("/supprimer-ticket/{id}")
    public ResponseEntity<String> supprimerTicket(@PathVariable Long id) {
        String message = ticketService.supprimer(id);
        return ResponseEntity.ok(message);
    }


    @Operation(summary = "Changer d'un ticket ", description = " ")
    @DeleteMapping("/changerStatuttTicket/{id}")
    public ResponseEntity<String> changerStatut(@PathVariable Long id) {
        String message = ticketService.changerStatut(id);
        return ResponseEntity.ok(message);
    }

    @Operation(summary = "Résolution d'un ticket", description = " ")
    @PutMapping("/resoudre-ticket/{id}/{formateurId}")
    public ResponseEntity<Ticket> resoudreTicket(@PathVariable Long id, @PathVariable Long formateurId) {
        Ticket ticket = ticketService.ResoluTicket(id, formateurId);
        return ResponseEntity.ok(ticket);
    }
}
