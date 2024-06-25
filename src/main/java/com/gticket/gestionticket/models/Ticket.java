package com.gticket.gestionticket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorie")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Categorie categorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priorite")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Priorite priorite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "statut")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Statut statut;

    private LocalDateTime dateCreation;
    private LocalDateTime dateMiseAJour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apprenant")
    @JsonBackReference
    private Apprenant apprenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Notification")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Notification notification;
}
