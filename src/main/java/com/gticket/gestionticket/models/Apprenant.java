package com.gticket.gestionticket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Apprenant extends Utilisateur {


    @OneToMany(mappedBy = "apprenant")
    @JsonBackReference
    private List<Ticket> ticket;
}
