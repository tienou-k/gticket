package com.gticket.gestionticket.modele;

import  jakarta.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Formateur extends Utilisateur{
}
