package com.gticket.gestionticket.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity
@EqualsAndHashCode
public class Apprenant extends Utilisateur {
    @OneToMany(mappedBy = "apprenant")

    private List<Ticket> tickets;

}
