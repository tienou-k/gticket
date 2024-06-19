package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.modele.Utilisateur;
import com.gticket.gestionticket.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/utilisateur")
@AllArgsConstructor
public class userController {
    private final UserService userService;

    @PostMapping("/create")
    public Utilisateur create(@RequestBody Utilisateur utilisateur){
      return userService.creer(utilisateur);
    }

    @GetMapping("/read")
    public List<Utilisateur> read(){
        return userService.lire();
    }

    @PutMapping("/update/{id}")
    public Utilisateur update(@PathVariable Long id,@RequestBody Utilisateur utilisateur){
        return userService.modifier(id, utilisateur);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return  userService.supprimer(id);
    }
}
