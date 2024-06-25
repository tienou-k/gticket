package com.gticket.gestionticket.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@EqualsAndHashCode
public class Apprenant extends Utilisateur {
    //@JsonIgnore
    @OneToMany(mappedBy = "apprenant")
    @JsonBackReference
    private List<Ticket> tickets;
}
