package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="traitement")
@Data
public class Traitement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_traitement")
    private Long idTraitement;
    //nouvelle modification de la modele j'ai ajoute une attribut nom du traitement
    private String nom;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JsonBackReference
    @JoinColumn(name = "id_salle")
    private Salle salle;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JsonBackReference
    @JoinColumn(name="matricule_medecin")
    private Medecin medecin;
}
