package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Apprenant;
import com.gticket.gestionticket.models.Categorie;
import com.gticket.gestionticket.models.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.gticket.gestionticket.repository.TicketRepository;
import com.gticket.gestionticket.repository.ApprenantRepository;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public  class ServiceTicketImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ApprenantRepository apprenantRepository;



    @Override
    public Ticket creer(Ticket ticket){
        // Validate the Apprenant ID
        if (ticket.getApprenant() == null || ticket.getApprenant().getId() == null) {
            throw new IllegalArgumentException("Apprenant ID ne doit pas être null");
        }
        //
        Apprenant apprenant = apprenantRepository.findById(ticket.getApprenant().getId())
                .orElseThrow(() -> new IllegalArgumentException("Apprenant non  trouvé"));

        //
        ticket.setApprenant(apprenant);
        ticket.setDateCreation(LocalDateTime.now());


        //
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> lireParApprenant(long Apprenant) {
        return List.of();
    }


    @Override
    public List<Ticket> lire() {
        return ticketRepository.findAll();
    }

    @Override
    public List<Ticket> lireParCategorie(String category) {
        return ticketRepository.findByCategorie(Categorie.valueOf(category));
    }

    @Override
    public List<Ticket> lireParApprenant(Long apprenantId) {
        return ticketRepository.findByApprenantId(apprenantId);
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
