package com.gticket.gestionticket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Ensure lazy loading properties are ignored
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
}
