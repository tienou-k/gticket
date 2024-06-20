package com.gticket.gestionticket.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Titre;
    private String description;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @Enumerated(EnumType.STRING)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    private Statut statut;


    private LocalDateTime dateCreation;
    private LocalDateTime dateMiseAJour;

    @ManyToOne
    @JoinColumn(name = "id_apprenant")
    @JsonBackReference
    private Apprenant apprenant;
}
