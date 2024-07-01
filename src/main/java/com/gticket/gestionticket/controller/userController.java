package com.gticket.gestionticket.controller;


import com.gticket.gestionticket.models.Utilisateur;
import com.gticket.gestionticket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1 Users", description = "API Gestion des utilisateurs")
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class userController {
    private UserService userService;


    public void UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Creation d'un utilisateur", description = "Fourni les coordonnées ")
    @ApiResponses({@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = userController.class), mediaType = "application/json") }),})
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        Utilisateur newUser = userService.creer(utilisateur);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED).getBody();
    }


    @Operation(summary = "La list des utilisateur", description = " ")
    @ApiResponses({@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = userController.class), mediaType = "application/json") }),})
    @GetMapping("/list")
    public List<Utilisateur> read(
          //
            @Parameter(description = "Recherche par Email", required =false ) @RequestParam(defaultValue = "abc@example.mail.com") String page)
    {

        return userService.lire();
    }

    @Operation(summary = "La list des utilisateur apr role", description = " ")
    @GetMapping("/listRole/{roleNom}")
    public ResponseEntity<List<Utilisateur>> trouverUtilisateursParRole(@PathVariable("roleNom") String roleNom){
        List<Utilisateur> utilisateurs = userService.findByRolesIn(roleNom);
        return new ResponseEntity<>(utilisateurs, HttpStatus.OK);
    }

    @Operation(summary = "Modification des données d'un utilisateur", description = " ")
    @PutMapping("/modifier/{id}")
    public Utilisateur update(@PathVariable Long id,@RequestBody Utilisateur utilisateur){
        return userService.modifier(id, utilisateur);
    }

    @Operation(summary = "Sppression d'un utilisateur", description = " ")
    @DeleteMapping("/supprimer/{id}")
    public String delete(@PathVariable Long id){

        return  userService.supprimer(id);
    }
}
