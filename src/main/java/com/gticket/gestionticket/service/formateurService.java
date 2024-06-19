package com.gticket.gestionticket.service;

import com.gticket.gestionticket.modele.Utilisateur;
import java.util.List;


public interface formateurService {
    Utilisateur creer(Utilisateur utilisateur);
    List<Utilisateur> lire();
    Utilisateur modifier(Long id, Utilisateur utilisateur);
    String supprimer(Long id);

}