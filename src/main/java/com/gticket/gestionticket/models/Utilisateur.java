package com.gticket.gestionticket.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "apprenant")
    private List<Ticket> tickets;
}

