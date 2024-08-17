package com.projetMedecine.Modele;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "medecin")
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long matricule;
    String specialite;
    String telephone;

}
