package com.gticket.gestionticket.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.util.List;


@Entity
@EqualsAndHashCode
@PrimaryKeyJoinColumn(name = "id")
public class Apprenant extends Utilisateur {
    @OneToMany(mappedBy = "apprenant", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
