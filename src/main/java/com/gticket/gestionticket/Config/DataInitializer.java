package com.gticket.gestionticket.Config;


import com.gticket.gestionticket.models.Categorie;
import com.gticket.gestionticket.models.Priorite;
import com.gticket.gestionticket.models.Role;
import com.gticket.gestionticket.models.Statut;
import com.gticket.gestionticket.repository.CategorieRepository;
import com.gticket.gestionticket.repository.PrioriteRepository;
import com.gticket.gestionticket.repository.RoleRepository;
import com.gticket.gestionticket.repository.StatutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final StatutRepository statutRepository;
    private final CategorieRepository categorieRepository;
    private final PrioriteRepository prioriteRepository;

    @Override
    public void run(String... args) {

        initializeRoles();

        initializeStatuts();

        initializeCategories();

        initializePriorites();
    }

    private void initializeRoles() {
        if (roleRepository.findByNom("Admin").isEmpty()) {
            roleRepository.save(new Role("Admin"));
        }
        if (roleRepository.findByNom("Formateur").isEmpty()) {
            roleRepository.save(new Role("Formateur"));
        }
        if (roleRepository.findByNom("Apprenant").isEmpty()) {
            roleRepository.save(new Role("Apprenant"));
        }
    }

    private void initializeStatuts() {
        if (statutRepository.findByNom("Ouvert").isEmpty()) {
            statutRepository.save(new Statut("Ouvert"));
        }
        if (statutRepository.findByNom("EnCours").isEmpty()) {
            statutRepository.save(new Statut("EnCours"));
        }
        if (statutRepository.findByNom("Résolu").isEmpty()) {
            statutRepository.save(new Statut("Résolu"));
        }
    }

    private void initializeCategories() {
        if (categorieRepository.findByNom("Technique").isEmpty()) {
            categorieRepository.save(new Categorie("Technique"));
        }
        if (categorieRepository.findByNom("Théorique").isEmpty()) {
            categorieRepository.save(new Categorie("Théorique"));
        }
        if (categorieRepository.findByNom("Pratique").isEmpty()) {
            categorieRepository.save(new Categorie("Pratique"));
        }
    }

    private void initializePriorites() {
        if (prioriteRepository.findByNom("Urgent").isEmpty()) {
            prioriteRepository.save(new Priorite("Urgent"));
        }
        if (prioriteRepository.findByNom("Ordinaire").isEmpty()) {
            prioriteRepository.save(new Priorite("Ordinaire"));
        }
    }
}
