package com.projetMedecine.Service;


import com.projetMedecine.Modele.Prescription;
import com.projetMedecine.Modele.PrescriptionProxy;
import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Repository.PrescriptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private RendezVousService rendezVousService;
    public Iterable<Prescription> prescriptions(){

        return prescriptionRepository.findAll();
    }
    public Optional<Prescription> prescription(long id){
        return prescriptionRepository.findById(id);
    }

    public Prescription savePrescription(PrescriptionProxy prescriptionProxy){
        System.out.println("medicament "+prescriptionProxy.getMedicament());
        System.out.println("Date "+prescriptionProxy.getDate());
        System.out.println("IdRendezvous "+prescriptionProxy.getIdRendezvous());
        Prescription newPrescription = new Prescription();
        newPrescription.setMedicament(prescriptionProxy.getMedicament());
        newPrescription.setDate(prescriptionProxy.getDate());
        if(prescriptionProxy.getIdRendezvous() != 0){
            Optional<Rendezvous> existingRendezvous = rendezVousService.getRendezvousById(prescriptionProxy.getIdRendezvous());
            newPrescription.setRendezvous(existingRendezvous.get());
        }
        return prescriptionRepository.save(newPrescription);
    }
    public Prescription updatedPrescription(PrescriptionProxy prescriptionProxy, long id){
        Optional<Prescription> existingPrescription = prescriptionRepository.findById(id);

            Prescription updatePrescription = existingPrescription.get();
            updatePrescription.setDate(prescriptionProxy.getDate());
            updatePrescription.setMedicament(prescriptionProxy.getMedicament());

            if(prescriptionProxy.getIdRendezvous() != null){
                Optional<Rendezvous> existingRendezvous = rendezVousService.getRendezvousById(prescriptionProxy.getIdRendezvous());
                updatePrescription.setRendezvous(existingRendezvous.get());

            }
            return prescriptionRepository.save(updatePrescription);

    }


    public List<Prescription> allPrescriptionById(List<Long>ids){
        return prescriptionRepository.findAllById(ids);
    }
    public void deletePrescriptionById(long id){
        prescriptionRepository.deleteById(id);
    }
}
