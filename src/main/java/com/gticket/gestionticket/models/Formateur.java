package com.gticket.gestionticket.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Formateur extends Utilisateur{



    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_notification")
    @JsonIgnoreProperties
    private List<Notification> notifications;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_db")
    @JsonIgnoreProperties
    private List<BaseDeConnaissance> baseDeConnaissances;
}
