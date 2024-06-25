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
        private final userRepository userRepository;
        private final EnvoyermailServiceImpl emailService;


    @Override
        @Transactional
        public Ticket creer(Ticket ticket) {

            Apprenant apprenant = apprenantRepository.findById(ticket.getApprenant().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Apprenant non trouvé"));
            ticket.setApprenant(apprenant);
            ticket.setDateCreation(LocalDateTime.now());


            if (ticket.getCategorie() != null && ticket.getStatut().getNom() != null) {
                Categorie categorie = categorieRepository.findByNom(ticket.getCategorie().getNom())
                        .orElseThrow(() -> new IllegalArgumentException("Categorie non trouvée"));
                ticket.setCategorie(categorie);
            }


            if (ticket.getPriorite() != null && ticket.getPriorite().getNom() != null) {
                Priorite priorite = prioriteRepository.findByNom(ticket.getPriorite().getNom())
                        .orElseThrow(() -> new IllegalArgumentException("Priorite non trouvée"));
                ticket.setPriorite(priorite);
            }


            if (ticket.getStatut() != null && ticket.getStatut().getNom() != null) {
                Statut statut = statutRepository.findByNom(ticket.getStatut().getNom())
                        .orElseThrow(() -> new IllegalArgumentException("Statut non trouvé"));
                ticket.setStatut(statut);
            }


        Statut statutOuvert = statutRepository.findByNom("ouvert")
                .orElseThrow(() -> new IllegalArgumentException("Statut 'ouvert' non trouvé"));
        ticket.setStatut(statutOuvert);


        Ticket savedTicket = ticketRepository.save(ticket);



            List<Utilisateur> adminsAndFormateurs = userRepository.findByRoles_NomIn(List.of("Admin", "Formateur"));
            String subject = "Nouveau Ticket: " + savedTicket.getTitre();
            String text = "Un nouveau ticket a été crée. Please check the details.";

            adminsAndFormateurs.forEach(user -> emailService.envoyerMail(user.getEmail(), subject, text));

            //
            return ticketRepository.save(ticket);
        }

  /*"  private String savedTicket() {
        return null;
    }"*/

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
    public Ticket resolveTicket(Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    Statut statutResolu = statutRepository.findByNom("Résolu")
                            .orElseThrow(() -> new IllegalArgumentException("Statut 'Résolu' non trouvé"));
                    ticket.setStatut(statutResolu);
                    ticket.setDateMiseAJour(LocalDateTime.now());

                    Ticket resolvedTicket = ticketRepository.save(ticket);


                    List<Utilisateur> Apprenant = userRepository.findByRoles_NomIn(List.of( "Apprenant"));
                    String subject = "Ticket Résolu: " + resolvedTicket.getTitre();
                    String text = "Un ticket à été résolu . Verifier les détails.!";

                    Apprenant.forEach(user ->
                        emailService.envoyerMail(user.getEmail(), subject, text)
                    );

                    return resolvedTicket;
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
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
