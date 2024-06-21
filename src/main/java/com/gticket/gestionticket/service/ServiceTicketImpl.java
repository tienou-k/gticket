package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.*;
import com.gticket.gestionticket.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public  class ServiceTicketImpl implements TicketService {

        private final TicketRepository ticketRepository;
        private final ApprenantRepository apprenantRepository;
        private final CategorieRepository categorieRepository;
        private final StatutRepository statutRepository;
        private final PrioriteRepository prioriteRepository;

        @Override
        @Transactional
        public Ticket creer(Ticket ticket) {
            // Validate and fetch the Apprenant
            Apprenant apprenant = apprenantRepository.findById(ticket.getApprenant().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Apprenant non trouvé"));
            ticket.setApprenant(apprenant);
            ticket.setDateCreation(LocalDateTime.now());

            // Fetch and set Categorie if provided
            if (ticket.getCategorie() != null && ticket.getStatut().getNom() != null) {
                Categorie categorie = categorieRepository.findByNom(ticket.getCategorie().getNom())
                        .orElseThrow(() -> new IllegalArgumentException("Categorie non trouvée"));
                ticket.setCategorie(categorie);
            }

            // Fetch and set Priorite if provided
            if (ticket.getPriorite() != null && ticket.getPriorite().getNom() != null) {
                Priorite priorite = prioriteRepository.findByNom(ticket.getPriorite().getNom())
                        .orElseThrow(() -> new IllegalArgumentException("Priorite non trouvée"));
                ticket.setPriorite(priorite);
            }

            // Fetch and set Statut if provided
            if (ticket.getStatut() != null && ticket.getStatut().getNom() != null) {
                Statut statut = statutRepository.findByNom(ticket.getStatut().getNom())
                        .orElseThrow(() -> new IllegalArgumentException("Statut non trouvé"));
                ticket.setStatut(statut);
            }

            // Save the ticket with all associations
            return ticketRepository.save(ticket);
        }

    @Override
    public List<Ticket> lireParApprenant(String Apprenant) {
        return List.of();
    }


    @Override
        public List<Ticket> lire() {
            return ticketRepository.findAll();
        }

        @Override
        public List<Ticket> lireParCategorie(String categorieNom) {
            Categorie categorie = categorieRepository.findByNom(categorieNom)
                    .orElseThrow(() -> new IllegalArgumentException("Categorie non trouvée"));
            return ticketRepository.findByCategorie(categorie);
        }

        @Override
        public List<Ticket> lireParPriorite(String prioriteNom) {
            Priorite priorite = prioriteRepository.findByNom(prioriteNom)
                    .orElseThrow(() -> new IllegalArgumentException("Priorite non trouvée"));
            return ticketRepository.findByPriorite(priorite);
        }

    @Override
    public List<Ticket> lireParApprenant(Long apprenantId) {
        return ticketRepository.findByApprenantId(apprenantId);
    }

    @Override
        public Ticket modifier(Long id, Ticket ticket) {
            return ticketRepository.findById(id)
                    .map(ticketExistant -> {
                        ticketExistant.setTitre(ticket.getTitre());
                        ticketExistant.setDescription(ticket.getDescription());
                        if (ticket.getCategorie() != null) {
                            Categorie categorie = categorieRepository.findByNom(ticket.getCategorie().getNom())
                                    .orElseThrow(() -> new IllegalArgumentException("Categorie non trouvée"));
                            ticketExistant.setCategorie(categorie);
                        }
                        if (ticket.getPriorite() != null) {
                            Priorite priorite = prioriteRepository.findByNom(ticket.getPriorite().getNom())
                                    .orElseThrow(() -> new IllegalArgumentException("Priorite non trouvée"));
                            ticketExistant.setPriorite(priorite);
                        }
                        if (ticket.getStatut() != null && ticket.getStatut().getId() != null) {
                            Statut statut = statutRepository.findByNom(ticket.getStatut().getNom())
                                    .orElseThrow(() -> new IllegalArgumentException("Statut non trouvé"));
                            ticketExistant.setStatut(statut);
                        }
                        return ticketRepository.save(ticketExistant);
                    })
                    .orElseThrow(() -> new IllegalArgumentException("Ticket non trouvé"));
        }
    @Override
    public String supprimer(Long id){
        ticketRepository.deleteById(id);
        return "Ticket supprimé !";
    }
}
