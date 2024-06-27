package com.gticket.gestionticket.service;


import com.gticket.gestionticket.models.*;
import com.gticket.gestionticket.repository.AdminRepository;
import com.gticket.gestionticket.repository.ApprenantRepository;
import com.gticket.gestionticket.repository.FormateurRepository;
import com.gticket.gestionticket.repository.userRepository;
import com.gticket.gestionticket.repository.RoleRepository;
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
        private final RoleRepository roleRepository;


        @Override
        public Utilisateur creer(Utilisateur utilisateur) {
            Set<Role> roles = utilisateur.getRoles();
            if (roles.isEmpty()) {
                throw new IllegalArgumentException("L'utilisateur doit avoir au moins un rôle");
            }
            Role getRole = roles.iterator().next();
            Utilisateur savedUser;
            switch (getRole.getNom()) {
                case "Admin":
                    Admin admin = new Admin();
                    copierUtilisateur(admin, utilisateur);
                    savedUser = adminRepository.save(admin);
                    break;
                case "Formateur":
                    Formateur formateur = new Formateur();
                    copierUtilisateur(formateur, utilisateur);
                    savedUser = formateurRepository.save(formateur);
                    break;
                case "Apprenant":
                    Apprenant apprenant = new Apprenant();
                    copierUtilisateur(apprenant, utilisateur);
                    savedUser = apprenantRepository.save(apprenant);
                    break;
                default:
                    throw new IllegalArgumentException("Type d'utilisateur non pris en charge: " + getRole.getNom());
            }
            return savedUser;
        }

        private void copierUtilisateur(Utilisateur dest, Utilisateur src) {
            dest.setNom(src.getNom());
            dest.setEmail(src.getEmail());
            dest.setPassword(src.getPassword());
            dest.setRoles(src.getRoles());
        }

        @Override
        public List<Utilisateur> lire() {
            return userRepository.findAll();
        }


        @Override
        public Utilisateur modifier(Long id, Utilisateur utilisateur) {
            return userRepository.findById(id)
                    .map(userExistant -> {
                        userExistant.setNom(utilisateur.getNom());
                        userExistant.setEmail(utilisateur.getEmail());
                        userExistant.setRoles(utilisateur.getRoles());
                        return userRepository.save(userExistant);
                    }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        }


        @Override
        public String supprimer(Long id) {
            userRepository.deleteById(id);
            return "Utilisateur supprimé !";
        }


        @Override
        public List<Utilisateur> findByRolesIn(String roleNom) {
            List<Role> roles = roleRepository.findByNom(roleNom);
            return userRepository.findByRolesIn(roles);
        }


        @Override
        public List<Utilisateur> findByRole(String roleNom) {
            List<Role> roles = roleRepository.findByNom(roleNom);
            return userRepository.findByRolesIn(roles);
        }
}
