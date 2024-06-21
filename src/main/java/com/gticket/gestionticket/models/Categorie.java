package com.gticket.gestionticket.models;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "categorie", fetch = FetchType.LAZY)

    private List<Ticket> tickets;

    public Categorie(String nom) {
        this.nom = nom;
    }
}
