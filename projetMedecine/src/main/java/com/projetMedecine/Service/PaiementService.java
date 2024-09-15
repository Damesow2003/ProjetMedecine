package com.projetMedecine.Service;

import com.projetMedecine.Modele.Paiement;
import com.projetMedecine.Modele.PaiementProxy;
import com.projetMedecine.Repository.PaiementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class PaiementService {
    @Autowired
    private PaiementRepository paiementRepository;

    public Iterable<Paiement> paiements(){
        return paiementRepository.findAll();
    }

    public Optional<Paiement> paiement(long id){
        return paiementRepository.findById(id);
    }

    public Paiement savePaiement(PaiementProxy paiementProxy){

        Paiement newPaiement = new Paiement();
        newPaiement.setModeDePaiement(paiementProxy.getModePaiement());
        newPaiement.setMontant(paiementProxy.getMontant());

        return paiementRepository.save(newPaiement);
    }
    public Paiement updatePaiement(PaiementProxy paiementProxy, long id){
        Optional<Paiement> existingPaiement = paiementRepository.findById(id);
        Paiement paiement = existingPaiement.get();

        paiement.setModeDePaiement(paiementProxy.getModePaiement());
        paiement.setMontant(paiementProxy.getMontant());

        return paiementRepository.save(paiement);
    }

    public void deletePaiementById(long id){
        paiementRepository.deleteById(id);
    }
}