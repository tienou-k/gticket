package com.gticket.gestionticket.service;

import com.gticket.gestionticket.modele.Apprenant;
import com.gticket.gestionticket.repository.ApprenantRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApprenantServiceImpl implements serviceApprenant {

    private final ApprenantRepository apprenantRepository;

    @Override
    public Apprenant creer(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }

    @Override
    public List<Apprenant> lire() {
        return apprenantRepository.findAll();
    }

    @Override
    public Apprenant modifier(Long id, Apprenant apprenant) {
        return apprenantRepository.findById(id)
                .map(apprenantExistant -> {
                    apprenantExistant.setNom(apprenant.getNom());
                    apprenantExistant.setEmail(apprenant.getEmail());
                    apprenantExistant.setRole(apprenant.getRole());
                    return apprenantRepository.save(apprenantExistant);
                }).orElseThrow(() -> new RuntimeException("Apprenant non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        apprenantRepository.deleteById(id);
        return "Apprenant supprimé !";
    }

    @Override
    public List<Apprenant> lireParRole(String role) {
        return List.of();
    }

    @Override
    public List<Apprenant> lireParRole(Role role) {
        return apprenantRepository.findByRole(role);
    }
}
