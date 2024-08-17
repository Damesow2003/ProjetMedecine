package com.projetMedecine.Service;

import com.projetMedecine.Modele.Paiement;
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

    public Paiement savePaiement(Paiement paiement){

        return paiementRepository.save(paiement);
    }

    public void deletePaiementById(long id){
        paiementRepository.deleteById(id);
    }
}