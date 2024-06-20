package com.gticket.gestionticket.models;


import jakarta.persistence.*;
import java.util.Date;


@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date dateDeNotification;




    @ManyToOne
    public Utilisateur utilisateur;
}

