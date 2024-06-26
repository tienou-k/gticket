package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Apprenant;
import org.springframework.context.annotation.Role;

import java.util.List;


public interface ApprenantService {

    Apprenant creer(Apprenant apprenant);

    List<Apprenant> lire();

    Apprenant modifier(Long id, Apprenant apprenant);

    String supprimer(Long id);

   /* List<Apprenant> lireParRole(String role);

    List<Apprenant> lireParRole(Role role);*/
}
