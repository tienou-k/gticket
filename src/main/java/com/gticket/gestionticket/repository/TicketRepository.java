package com.gticket.gestionticket.repository;

//ticketRepository

import com.gticket.gestionticket.models.Categorie;
import com.gticket.gestionticket.models.Priorite;
import com.gticket.gestionticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByApprenantId(Long apprenantId);

    List<Ticket> findByCategorie(Categorie categorie);

    List<Ticket> findByPriorite(Priorite priorite);
}