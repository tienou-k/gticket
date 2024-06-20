package com.gticket.gestionticket.controller;



import com.gticket.gestionticket.models.Ticket;
import com.gticket.gestionticket.service.ServiceTicket;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {
    private final ServiceTicket serviceTicket;

    @PostMapping("/create")
    public Ticket create(@RequestBody Ticket ticket) {
        return serviceTicket.creer(ticket);
    }

    @GetMapping("/read{apprenantId}")
    public List<Ticket> readByApprenant(@PathVariable Long apprenantId) {
        return serviceTicket.lireParApprenant(apprenantId);
    }

    @PutMapping("/update/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody Ticket ticket) {
        return serviceTicket.modifier(id, ticket);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return serviceTicket.supprimer(id);
    }
}
