package com.gticket.gestionticket.controller;



import com.gticket.gestionticket.models.Ticket;
import com.gticket.gestionticket.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/create")
    public Ticket create(@RequestBody Ticket ticket) {
        return ticketService.creer(ticket);
    }

    @GetMapping("/readAll")
    public List<Ticket> read() {
        return ticketService.lire();
    }

    @GetMapping("/readCat/{category}")
    public List<Ticket> readByCategory(@PathVariable String category) {
        return ticketService.lireParCategorie(category);
    }

    @GetMapping("/readAppr/{apprenantId}")
    public List<Ticket> readByApprenant(@PathVariable Long apprenantId) {
        return ticketService.lireParApprenant(apprenantId);
    }


    @PutMapping("/update/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ticketService.modifier(id, ticket);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return ticketService.supprimer(id);
    }
}
