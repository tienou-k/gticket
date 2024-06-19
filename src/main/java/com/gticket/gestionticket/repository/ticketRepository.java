package com.gticket.gestionticket.repository;

//ticketRepository

import com.gticket.gestionticket.modele.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ticketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatut(String statut);
}