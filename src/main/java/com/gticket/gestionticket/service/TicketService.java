package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    Ticket creer(Ticket ticket);

    List<Ticket> lireParApprenant(String Apprenant);

    List<Ticket> lire();

    List<Ticket> lireParCategorie(String category);

    List<Ticket> lireParPriorite(String priorite);

    List<Ticket> lireParApprenant(Long apprenantId);

    Ticket modifier(Long id, Ticket ticket);
    String supprimer(Long id);
}
