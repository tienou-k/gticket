package com.gticket.gestionticket.modele;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}

enum Role {
    Apprenant,
    Formateur,
    Admin
}
