package com.projetMedecine.Modele;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="salle")
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_salle")
    private Integer idSalle;
    @Column(name="numero_salle")
    private String numeroSalle;
    @Column(name="nom_salle")
    private String nomSalle;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="id_cabinet")
    @JsonBackReference
    private CabinetMedical cabinetMedical;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            }
    )
    @JoinTable(
            name="salle_medecin",
            joinColumns = @JoinColumn(name = "id_salle"),
            inverseJoinColumns = @JoinColumn(name="matricule")
    )
    private List<Medecin> medecins = new ArrayList<>();
}
