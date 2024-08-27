package com.projetMedecine.Service;

import com.projetMedecine.Modele.Traitement;
import com.projetMedecine.Repository.TraitementRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TraitementService {
    @Autowired
    private TraitementRepository traitementRepository;

    public Iterable<Traitement> getTraitements(){
        return traitementRepository.findAll();
    }

    public Optional<Traitement> getTraitement(long id){
        return  traitementRepository.findById(id);
    }
}
