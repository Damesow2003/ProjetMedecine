package com.projetMedecine.Modele;


import jakarta.persistence.*;
import lombok.Data;

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

}
