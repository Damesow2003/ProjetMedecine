package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_prescription")
    Long idPrescription;
    private String medicament;
    private LocalDate date;

    @ManyToOne(
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            },
            fetch = FetchType.LAZY
    )
    @JsonBackReference
    @JoinColumn(name="id_rendezvous")
    private Rendezvous rendezvous;
}
