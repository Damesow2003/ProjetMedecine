package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paiement")
    Long id;
    @Column(name="mode_paiement")
    private String modeDePaiement;
    private Double montant;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name="id_paiement")
    @JsonBackReference
    private Rendezvous rendezvous;
}
