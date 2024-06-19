package com.gticket.gestionticket.controller;

import com.gticket.gestionticket.modele.Formateur;
import com.gticket.gestionticket.service.formateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formateurs")
@AllArgsConstructor
public class formateurController {
    private final formateurService formateurService;

    @PostMapping("/createFormateur")
    public Formateur create(@RequestBody Formateur formateur) {
        return formateurService.creer(formateur);
    }

    @GetMapping("/readFormateur")
    public List<Formateur> read() {
        return formateurService.lire();
    }

    @PutMapping("/updateFormateur/{id}")
    public Formateur update(@PathVariable Long id, @RequestBody Formateur formateur) {
        return formateurService.modifier(id, formateur);
    }

    @DeleteMapping("/deleteFormateur/{id}")
    public String delete(@PathVariable Long id) {
        return formateurService.supprimer(id);
    }
}
