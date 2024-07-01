package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.*;
import com.gticket.gestionticket.repository.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    private final JavaMailSenderImpl mailSender;


    @Override
    @Transactional
    public Ticket creer(Ticket ticket) {
        Apprenant apprenant = apprenantRepository.findById(ticket.getApprenant().getId())
                .orElseThrow(() -> new IllegalArgumentException("Apprenant non trouvé"));
        ticket.setApprenant(apprenant);
        ticket.setDateCreation(LocalDateTime.now());
        if (ticket.getCategorie() != null && ticket.getCategorie().getId() != null) {
            Categorie categorie = categorieRepository.findById(ticket.getCategorie().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Categorie non trouvée"));
            ticket.setCategorie(categorie);
        }
        if (ticket.getPriorite() != null && ticket.getPriorite().getId() != null) {
            Priorite priorite = prioriteRepository.findById(ticket.getPriorite().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Priorite non trouvée"));
            ticket.setPriorite(priorite);
        }
        if (ticket.getStatut() != null && ticket.getStatut().getId() != null) {
            Statut statut = statutRepository.findById(ticket.getStatut().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Statut non trouvé"));
            ticket.setStatut(statut);
        } else {
            Statut statutOuvert = statutRepository.findByNom("Ouvert")
                    .orElseThrow(() -> new IllegalArgumentException("Statut 'Ouvert' non trouvé"));
            ticket.setStatut(statutOuvert);
        }
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
    public Ticket ResoluTicket(Long id, Long formateurId) {
        Formateur formateur = (Formateur) userRepository.findById(formateurId)
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getNom().equals("Formateur")))
                .orElseThrow(() -> new IllegalArgumentException("Formateur non trouvé ou non autorisé"));

        return ticketRepository.findById(id)
                .map(ticket -> {
                    Statut statutResolu = statutRepository.findByNom("Résolu")
                            .orElseThrow(() -> new IllegalArgumentException("Statut 'Résolu' non trouvé"));
                    ticket.setStatut(statutResolu);
                    ticket.setDateMiseAJour(LocalDateTime.now());
                    Ticket updatedTicket = ticketRepository.save(ticket);
                    try {
                        sendResolutionEmail(ticket);
                    } catch (MessagingException e) {
                        throw new RuntimeException("Failed to send email", e);
                    }
                    return updatedTicket;
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }

    private void sendResolutionEmail(Ticket ticket) throws MessagingException {
        Apprenant apprenant = ticket.getApprenant();
        String email = apprenant.getEmail();
        String subject = "Votre ticket a été résolu";
        String body = "Bonjour " + apprenant.getNom() + ",\n\n" +
                "Votre ticket '" + ticket.getTitre() + "' a été résolu.\n\n" +
                "Merci,\n" +
                "L'équipe de gestion des tickets";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper();
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(body);

        mailSender.send(message);
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

    @Override
    public String changerStatut(Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    Statut statutEnCours = statutRepository.findByNom("En cours")
                            .orElseThrow(() -> new IllegalArgumentException("Statut 'En cours' non trouvé"));
                    ticket.setStatut(statutEnCours);
                    ticket.setDateMiseAJour(LocalDateTime.now());
                    ticketRepository.save(ticket);
                    return "Statut du ticket mis à jour à 'En cours'";
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }
}
