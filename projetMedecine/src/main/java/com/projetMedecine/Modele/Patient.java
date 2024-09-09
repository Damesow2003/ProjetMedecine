package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@DynamicUpdate
@Table(name = "patient")
@JsonTypeName("PATIENT")
@PrimaryKeyJoinColumn(name="id_utilisateur")
public class Patient  extends Utilisateur{
    @Column(name="id_patient")
    private long idPatient;
    @Column(name="date_de_naissance")
    @NotBlank(message = "Date de naissance ne doit pas etre null")
    private LocalDate dateDeNaissance;

    public long getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(long idPatient) {
        this.idPatient = idPatient;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }
}
