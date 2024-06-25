package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.models.Utilisateur;
import com.gticket.gestionticket.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class userController {
    private UserService userService;


    public void UserController(UserService userService) {
        this.userService = userService;
    }





    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {

        return userService.creer(utilisateur);
    }




    @GetMapping("/read")
    public List<Utilisateur> read(){

        return userService.lire();
    }

    @GetMapping("/findByRole/{roleNom}")
    public ResponseEntity<List<Utilisateur>> trouverUtilisateursParRole(@PathVariable("roleNom") String roleNom) {
        List<Utilisateur> utilisateurs = userService.findByRole(roleNom);
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
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
