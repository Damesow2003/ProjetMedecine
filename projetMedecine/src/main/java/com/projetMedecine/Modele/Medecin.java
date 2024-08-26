package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "medecin")
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long matricule;
    String specialite;
    String telephone;
    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "medecin"
    )
    @JsonManagedReference
    private List<Traitement> traitementList = new ArrayList<>();
}
