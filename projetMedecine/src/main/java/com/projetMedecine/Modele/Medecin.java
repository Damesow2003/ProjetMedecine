package com.projetMedecine.Modele;

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

    @ManyToMany(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="cabinet_medecin",
            joinColumns = @JoinColumn(name="matricule"),
            inverseJoinColumns = @JoinColumn(name = "id_cabinet")
    )
    private List<CabinetMedical> cabinetMedicals = new ArrayList<>();
}
