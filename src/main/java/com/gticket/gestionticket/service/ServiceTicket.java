package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Ticket;

import java.util.List;


public interface ServiceTicket {
    Ticket creer(Ticket ticket);
    List<Ticket> lireParApprenant(long Apprenant);
    Ticket modifier(Long id, Ticket ticket);
    String supprimer(Long id);
}
