package com.gticket.gestionticket.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private Date dateDeNotification;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ticket")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formateur")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Formateur formateur;

}

