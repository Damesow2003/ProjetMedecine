package com.projetMedecine.Service;

import com.projetMedecine.Exceptions.PaiementNotFound;
import com.projetMedecine.Exceptions.RendezvousBadRequest;
import com.projetMedecine.Modele.*;
import com.projetMedecine.Repository.*;
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
    @Autowired
    private CabinetMedicalRepository cabinetMedicalRepository;

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
        /*List<Notification> notificationList = notificationRepository.findAllById(rendezvousProxy.getIdNotification());
        newRendezvous.setNotifications(notificationList);*/

        //Association avec Prescription
        /*List<Prescription> prescriptionList = prescriptionRepository.findAllById(rendezvousProxy.getIdPrescription());
        newRendezvous.setPrescriptions(prescriptionList);*/

        //Association avec Paiement
        Optional<Paiement> existingPaiement = Optional.ofNullable(paiementRepository.findById(rendezvousProxy.getIdPaiement())
                .orElseThrow(() -> new PaiementNotFound("Paiement non trouve")));
        newRendezvous.setPaiement(existingPaiement.get());
        //Association avec Cabinet
        Optional<CabinetMedical> existingCabinet = Optional.ofNullable(cabinetMedicalRepository.findById(rendezvousProxy.getIdCabinet())
                .orElseThrow(() -> new RuntimeException("Cabinet medecial non trouve")));
        newRendezvous.setCabinetMedical(existingCabinet.get());
        return rendezVousRepository.save(newRendezvous);
    }
    public Rendezvous updateRendezvous(long id, RendezvousProxy rendezvousProxy){
        Optional<Rendezvous> existingRendezvous = rendezVousRepository.findById(id);
        if(existingRendezvous.isEmpty()){
            throw new RendezvousBadRequest("Veuillez saisir un body non null (remplissez les infos que vous souhaitez mettre a jour)");
        }
        Rendezvous updatedRendezvous = existingRendezvous.get();
        updatedRendezvous.setDateRv(rendezvousProxy.getDateRv());
        updatedRendezvous.setHeureRv(rendezvousProxy.getHeureRv());
        updatedRendezvous.setDuree(rendezvousProxy.getDuree());

        Optional<Paiement> existingPaiement = paiementRepository.findById(rendezvousProxy.getIdPaiement());
        if(existingRendezvous.isPresent()){
            updatedRendezvous.setPaiement(existingPaiement.get());
        }
        Optional<CabinetMedical> existingCabinet = cabinetMedicalRepository.findById(rendezvousProxy.getIdCabinet());
        if(existingCabinet.isPresent()){
            updatedRendezvous.setCabinetMedical(existingCabinet.get());
        }
        return updatedRendezvous;
    }
    public void deleteRendezvous(long id){
        rendezVousRepository.deleteById(id);
    }
}