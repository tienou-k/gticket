package com.gticket.gestionticket.service;

import com.gticket.gestionticket.models.Formateur;
import com.gticket.gestionticket.repository.FormateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FormateurServiceImpl implements formateurService {
    private final FormateurRepository formateurRepository;

    @Override
    public Formateur creer(Formateur formateur) {
        return formateurRepository.save(formateur);
    }


    @Override
    public List<Formateur> lire() {
        return formateurRepository.findAll();
    }


    @Override
    public Formateur modifier(Long id, Formateur formateur) {
        return formateurRepository.findById(id)
                .map(formateurExistant -> {
                    formateurExistant.setNom(formateur.getNom());
                    formateurExistant.setEmail(formateur.getEmail());
                    formateurExistant.setRoles(formateur.getRoles());
                    return formateurRepository.save(formateurExistant);
                }).orElseThrow(() -> new RuntimeException("Formateur non trouvé"));
    }


    @Override
    public String supprimer(Long id) {
        formateurRepository.deleteById(id);
        return "Formateur supprimé !";
    }
}
