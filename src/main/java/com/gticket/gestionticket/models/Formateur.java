package com.gticket.gestionticket.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.List;


@Entity
public class Formateur extends Utilisateur{



    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_notification")
    @JsonManagedReference
    private List<Notification> notifications;


    @OneToMany(mappedBy = "formateur", fetch = FetchType.LAZY)
    private List<BaseDeConnaissance> baseDeConnaissances;
}
