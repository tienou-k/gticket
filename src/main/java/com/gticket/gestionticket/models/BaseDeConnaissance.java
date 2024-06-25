package com.gticket.gestionticket.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BaseDeConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formateur")
    private Formateur formateur;
}
