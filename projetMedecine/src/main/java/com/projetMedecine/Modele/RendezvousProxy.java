package com.projetMedecine.Modele;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RendezvousProxy {
    private LocalDate dateRv;
    private LocalDateTime heureRv;
    private int duree;
    private List<Long> idNotification;
    private List<Long> idPrescription;
    private Long idPaiement;

}
