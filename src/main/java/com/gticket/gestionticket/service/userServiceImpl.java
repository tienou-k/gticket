package com.gticket.gestionticket.service;


import com.gticket.gestionticket.modele.Utilisateur;
import com.gticket.gestionticket.repository.userRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class userServiceImpl implements UserService {

    private final userRepository userRepository;

    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        return userRepository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> lire() {
        return userRepository.findAll();
    }

    @Override
    public Utilisateur modifier(Long id, Utilisateur utilisateur) {
        return userRepository.findById(utilisateur.getId())
                .map(user_existant->{
            user_existant.setNom(utilisateur.getNom());
            user_existant.setEmail(utilisateur.getEmail());
            user_existant.setRole(utilisateur.getRole());
            return userRepository.save(user_existant);
        }).orElseThrow(()-> new RuntimeException("Utilisateur non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        userRepository.deleteById(id);
        return "Utilisateur supprimé !";
    }
}
