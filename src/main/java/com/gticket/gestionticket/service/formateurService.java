package com.gticket.gestionticket.service;

import com.gticket.gestionticket.modele.Formateur;
import java.util.List;


public interface formateurService {
    Formateur creer(Formateur formateur);
    List<Formateur> lire();
    Formateur modifier(Long id, Formateur formateur);
    String supprimer(Long id);

}