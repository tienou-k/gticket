package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Statut;
import com.gticket.gestionticket.models.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.gticket.gestionticket.repository.TicketRepository;



@Service
@AllArgsConstructor
public abstract class ServiceTicketImpl implements ServiceTicket {
    private final TicketRepository ticketRepository;

    @Override
    public Ticket creer(Ticket ticket){
        ticket.setStatut(Statut.Ouvert);
        return ticketRepository.save(ticket);
    }
    @Override
    public Ticket modifier(Long id, Ticket ticket) {
        return ticketRepository.findById(id)
                .map(ticket_existant -> {
                    ticket_existant.setTitre(ticket.getTitre());
                    ticket_existant.setDescription(ticket.getDescription());
                    ticket_existant.setCategorie(ticket.getCategorie());
                    ticket_existant.setPriorite(ticket.getPriorite());
                    return ticketRepository.save(ticket_existant);
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }
    @Override
    public String supprimer(Long id){
        ticketRepository.deleteById(id);
        return "Ticket supprimé !";
    }
}
