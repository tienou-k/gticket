package com.gticket.gestionticket.models;



import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailMessage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String a;
    private String Titre;
    private String message;
}
