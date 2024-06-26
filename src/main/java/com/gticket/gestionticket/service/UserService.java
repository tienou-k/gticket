package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Utilisateur;

import java.util.List;


public interface UserService {

    Utilisateur creer(Utilisateur utilisateur);

    List<Utilisateur> lire();

    Utilisateur modifier(Long id, Utilisateur utilisateur);

    String supprimer(Long id);

    List<Utilisateur> findByRolesIn(String roleNom);

    List<Utilisateur> findByRole(String roleNom);
}