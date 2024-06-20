package com.gticket.gestionticket.service;


import com.gticket.gestionticket.models.*;
import com.gticket.gestionticket.repository.AdminRepository;
import com.gticket.gestionticket.repository.ApprenantRepository;
import com.gticket.gestionticket.repository.FormateurRepository;
import com.gticket.gestionticket.repository.userRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class userServiceImpl implements UserService {

    private final userRepository userRepository;
    private final AdminRepository adminRepository;
    private final FormateurRepository formateurRepository;
    private final ApprenantRepository apprenantRepository;


    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        switch (utilisateur.getRole()) {
            case Admin:
                Admin admin = new Admin();
                admin.setNom(utilisateur.getNom());
                admin.setEmail(utilisateur.getEmail());
                admin.setPassword(utilisateur.getPassword());
                admin.setRole(utilisateur.getRole());
                return adminRepository.save(admin);

            case Formateur:
                Formateur formateur = new Formateur();
                formateur.setNom(utilisateur.getNom());
                formateur.setEmail(utilisateur.getEmail());
                formateur.setPassword(utilisateur.getPassword());
                formateur.setRole(utilisateur.getRole());
                return formateurRepository.save(formateur);

            case Apprenant:
                Apprenant apprenant = new Apprenant();
                apprenant.setNom(utilisateur.getNom());
                apprenant.setEmail(utilisateur.getEmail());
                apprenant.setPassword(utilisateur.getPassword());
                apprenant.setRole(utilisateur.getRole());
                return apprenantRepository.save(apprenant);

            default:
                throw new IllegalArgumentException("Type d'utilisateur non pris en charge: " + utilisateur.getRole());
        }
    }
    @Override
    public List<Utilisateur> lire(){
        return userRepository.findAll();
    }

    @Override
    public Utilisateur modifier(Long id, Utilisateur utilisateur) {
        return userRepository.findById(id)
                .map(user_existant -> {
                    user_existant.setNom(utilisateur.getNom());
                    user_existant.setEmail(utilisateur.getEmail());
                    user_existant.setRole(utilisateur.getRole());
                    return userRepository.save(user_existant);
                }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
    @Override
    public String supprimer(Long id){
        userRepository.deleteById(id);
        return "Utilisateur supprimé !";
    }
    @Override
    public List<Utilisateur> findByRole(Role role){
        return userRepository.findByRole(role);
    }
}

