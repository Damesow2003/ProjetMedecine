package com.projetMedecine.Service;

import com.projetMedecine.Modele.CabinetMedical;
import com.projetMedecine.Modele.CabinetMedicalProxy;
import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Repository.CabinetMedicalRepository;
import com.projetMedecine.Repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabinetMedicalService {
    @Autowired
    private CabinetMedicalRepository cabinetMedicalRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;

    public Iterable<CabinetMedical> getCabinetMedicals() {
        return cabinetMedicalRepository.findAll();
    }

    public Optional<CabinetMedical> getCabinetMedical(long id) {
        return cabinetMedicalRepository.findById(id);
    }

    public CabinetMedical saveCabinetMedical(CabinetMedicalProxy cabinetMedicalProxy) {
        CabinetMedical newCabinetMedical = new CabinetMedical();
        newCabinetMedical.setAdresse(cabinetMedicalProxy.getAdresse());
        newCabinetMedical.setNom(cabinetMedicalProxy.getNom());
        //Association avec Rendezvous(sa permet l'ajout de la clef etrangeres)
        if (cabinetMedicalProxy.getIdCabinetMedical() != null) {
            List<Rendezvous> rendezvousList = rendezVousRepository.findAllById(cabinetMedicalProxy.getIdCabinetMedical());
            newCabinetMedical.setRendezvous(rendezvousList);
        }
        return cabinetMedicalRepository.save(newCabinetMedical);
    }
    public void deleteCabinetMedical(long id){
        cabinetMedicalRepository.deleteById(id);
    }
}
