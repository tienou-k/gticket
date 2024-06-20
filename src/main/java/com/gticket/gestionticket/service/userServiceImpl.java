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
public abstract class userServiceImpl implements UserService {

    private final userRepository userRepository;
    private final AdminRepository adminRepository;
    private final FormateurRepository formateurRepository;
    private final ApprenantRepository apprenantRepository;


    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        Utilisateur savedUser = userRepository.save(utilisateur);
        switch (utilisateur.getRole()) {
            case Admin:
                Admin admin = new Admin();
                admin.setNom(savedUser.getNom());
                admin.setEmail(savedUser.getEmail());
                admin.setPassword(savedUser.getPassword());
                admin.setRole(savedUser.getRole());
                break;
            case Formateur:
                Formateur formateur = new Formateur();
                formateur.setNom(savedUser.getNom());
                formateur.setEmail(savedUser.getEmail());
                formateur.setPassword(savedUser.getPassword());
                formateur.setRole(savedUser.getRole());
                break;
            case Apprenant:
                Apprenant apprenant = new Apprenant();
                apprenant.setNom(savedUser.getNom());
                apprenant.setEmail(savedUser.getEmail());
                apprenant.setPassword(savedUser.getPassword());
                apprenant.setRole(savedUser.getRole());
                break;
        }
        return savedUser;
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

