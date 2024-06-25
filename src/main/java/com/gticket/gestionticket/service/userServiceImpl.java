package com.gticket.gestionticket.service;


import com.gticket.gestionticket.models.*;
import com.gticket.gestionticket.repository.AdminRepository;
import com.gticket.gestionticket.repository.ApprenantRepository;
import com.gticket.gestionticket.repository.FormateurRepository;
import com.gticket.gestionticket.repository.userRepository;
import com.gticket.gestionticket.repository.RoleRpository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
public class userServiceImpl implements UserService {

    private final userRepository userRepository;
    private final AdminRepository adminRepository;
    private final FormateurRepository formateurRepository;
    private final ApprenantRepository apprenantRepository;


    @Override
    public Utilisateur creer(Utilisateur utilisateur) {
        Set<Role> roles = utilisateur.getRoles();
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("L'utilisateur doit avoir au moins un rôle");
        }

        for (Role role : roles) {
            switch (role.getNom()) {
                case "Admin":
                    Admin admin = new Admin();
                    copierUtilisateur(admin, utilisateur);
                    return adminRepository.save(admin);

                case "Formateur":
                    Formateur formateur = new Formateur();
                    copierUtilisateur(formateur, utilisateur);
                    return formateurRepository.save(formateur);

                case "Apprenant":
                    Apprenant apprenant = new Apprenant();
                    copierUtilisateur(apprenant, utilisateur);
                    return apprenantRepository.save(apprenant);

                default:
                    throw new IllegalArgumentException("Type d'utilisateur non pris en charge: " + role.getNom());
            }
        }

        throw new IllegalArgumentException("Aucun rôle spécifié pour l'utilisateur");
    }

    // Méthode utilitaire pour copier les données de l'utilisateur vers un utilisateur spécifique (Admin, Formateur, Apprenant)
    private void copierUtilisateur(Utilisateur dest, Utilisateur src) {
        dest.setNom(src.getNom());
        dest.setEmail(src.getEmail());
        dest.setPassword(src.getPassword());
        dest.setRoles(src.getRoles());
    }
        @Override
        public List<Utilisateur> lire () {
            return userRepository.findAll();
        }

        @Override
        public Utilisateur modifier (Long id, Utilisateur utilisateur){
            return userRepository.findById(id)
                    .map(user_existant -> {
                        user_existant.setNom(utilisateur.getNom());
                        user_existant.setEmail(utilisateur.getEmail());
                        user_existant.setRoles(utilisateur.getRoles());
                        return userRepository.save(user_existant);
                    }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        }
        @Override
        public String supprimer (Long id){
            userRepository.deleteById(id);
            return "Utilisateur supprimé !";
        }
    @Override
    public List<Utilisateur> findByRole(String roleNom) {
        // Utilisez votre méthode de recherche personnalisée dans le repository de rôle
        List<Role> roles = RoleRpository.findByNom(roleNom);

        // Récupérez tous les utilisateurs ayant un des rôles trouvés
        return userRepository.findByRolesIn(roles);
    }


}

