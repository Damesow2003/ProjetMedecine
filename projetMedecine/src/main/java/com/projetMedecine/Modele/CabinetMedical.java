package com.projetMedecine.Modele;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cabinet_medical")
@Data
public class CabinetMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cabinet")
    private Long idCabinet;
    private String adresse;
    private String nom;
    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
        }
    )
    @JoinTable(
            name = "cabinet_medecin",
            joinColumns = @JoinColumn(name="id_cabinet"),
            inverseJoinColumns = @JoinColumn(name="matricule")
    )
    private List<Medecin> medecins = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @JoinColumn(name="id_cabinet")
    private List<Salle> salles = new ArrayList<>();
}
