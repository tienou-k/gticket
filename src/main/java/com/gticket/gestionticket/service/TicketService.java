package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Ticket;
import com.gticket.gestionticket.models.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    Ticket creer(Ticket ticket);
    List<Ticket> lireParApprenant(long Apprenant);

    List<Ticket> lire();

    List<Ticket> lireParCategorie(String category);

    List<Ticket> lireParApprenant(Long apprenantId);

    Ticket modifier(Long id, Ticket ticket);
    String supprimer(Long id);
}
