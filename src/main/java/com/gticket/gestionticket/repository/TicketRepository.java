package com.gticket.gestionticket.repository;

//ticketRepository

import com.gticket.gestionticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByApprenantId(Long apprenantId);
}