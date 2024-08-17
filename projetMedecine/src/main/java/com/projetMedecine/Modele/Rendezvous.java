package com.projetMedecine.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "rendezvous")
public class Rendezvous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_rendezvous")
    Long id;

    @Column(name="date_rv")
    @NotNull(message = "La date de rendez-vous ne doit pas etre null")
    private LocalDate dateRv;
    @Column(name="heure_rv")
    @NotNull(message = "Il nous faut une heure pour le rendez-voud")
    private LocalDateTime heureRv;
    @NotNull(message = "Duree ne doit pas etre null")
    private int duree;


    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            mappedBy = "rendezvous"
    )
    @JsonManagedReference
    Paiement paiement;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
           orphanRemoval = true,
            mappedBy = "rendezvous"
    )
    @JsonManagedReference
    List<Notification> notifications = new ArrayList<Notification>();
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "rendezvous"
    )
    @JsonManagedReference
    List<Prescription> prescriptions = new ArrayList<Prescription>();

    //methode Utilitaire Notification(helpers methode)

    public void addNotification(Notification notification){
        notifications.add(notification);
        notification.setRendezvous(this);
    }
    public void removeNotification(Notification notification){
        notifications.remove(notification);
        notification.setRendezvous(null);
    }
    //methode utiliraire Prescriptions

    public void addPrescription(Prescription prescription){
        prescriptions.add(prescription);
        prescription.setRendezvous(this);
    }

    public void removePrescription(Prescription prescription){
        prescriptions.remove(prescription);
        prescription.setRendezvous(null);
    }
}