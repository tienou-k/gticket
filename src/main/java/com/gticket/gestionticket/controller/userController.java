package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.models.Utilisateur;
import com.gticket.gestionticket.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController

@RequestMapping("/api/users")
@AllArgsConstructor
public class userController {
    private UserService userService;


    public void UserController(UserService userService) {
        this.userService = userService;
    }





    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        Utilisateur newUser = userService.creer(utilisateur);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED).getBody();
    }




    @GetMapping("/list")
    public List<Utilisateur> read(){

        return userService.lire();
    }

    @GetMapping("/listRole/{roleNom}")
    public ResponseEntity<List<Utilisateur>> trouverUtilisateursParRole(@PathVariable("roleNom") String roleNom) {
        List<Utilisateur> utilisateurs = userService.findByRolesIn(roleNom);
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @PutMapping("/modifier/{id}")
    public Utilisateur update(@PathVariable Long id,@RequestBody Utilisateur utilisateur){
        return userService.modifier(id, utilisateur);
    }

    @DeleteMapping("/supprimer/{id}")
    public String delete(@PathVariable Long id){

        return  userService.supprimer(id);
    }
}
