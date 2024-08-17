package com.projetMedecine.Service;

import com.projetMedecine.Exceptions.PaiementNotFound;
import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.NotificationRepository;
import com.projetMedecine.Repository.PaiementRepository;
import com.projetMedecine.Repository.PrescriptionRepository;
import com.projetMedecine.Repository.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RendezVousService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PaiementRepository paiementRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;


    public Iterable<Rendezvous> listRendezvous(){
        return rendezVousRepository.findAll();
    }
    public Optional<Rendezvous> getRendezvousById(long id){
        return rendezVousRepository.findById(id);
    }
    public Rendezvous saveRendezvous(RendezvousProxy rendezvousProxy){
        Rendezvous newRendezvous = new Rendezvous();

        newRendezvous.setDateRv(rendezvousProxy.getDateRv());
        newRendezvous.setHeureRv(rendezvousProxy.getHeureRv());
        newRendezvous.setDuree(rendezvousProxy.getDuree());

        //Association avec Notification
        List<Notification> notificationList = notificationRepository.findAllById(rendezvousProxy.getIdNotification());
        newRendezvous.setNotifications(notificationList);

        //Association avec Prescription
        List<Prescription> prescriptionList = prescriptionRepository.findAllById(rendezvousProxy.getIdPrescription());
        newRendezvous.setPrescriptions(prescriptionList);

        //Association avec Paiement
        Optional<Paiement> existingPaiement = Optional.ofNullable(paiementRepository.findById(rendezvousProxy.getIdPaiement())
                .orElseThrow(() -> new PaiementNotFound("Paiement non trouve")));
        newRendezvous.setPaiement(existingPaiement.get());
        return rendezVousRepository.save(newRendezvous);
    }

    public void deleteRendezvous(long id){
        rendezVousRepository.deleteById(id);
    }
}