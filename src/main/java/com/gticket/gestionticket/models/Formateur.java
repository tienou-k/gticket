package com.gticket.gestionticket.models;

import  jakarta.persistence.*;


@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Formateur extends Utilisateur{
}
