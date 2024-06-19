package com.gticket.gestionticket.modele;


import jakarta.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends  Utilisateur{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nom;
        private String email;
        private String password;


}
