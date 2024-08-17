package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.ExistingPaiementException;
import com.projetMedecine.Exceptions.PaiementImpossibleException;
import com.projetMedecine.Exceptions.PaiementNotFound;
import com.projetMedecine.Exceptions.RendezvousNotFound;
import com.projetMedecine.Modele.Paiement;
import com.projetMedecine.Modele.Rendezvous;
import com.projetMedecine.Service.PaiementService;
import com.projetMedecine.Service.RendezVousService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class PaiementController {
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private RendezVousService rendezVousService;
    @GetMapping("/paiements")
    public Iterable<Paiement> getPaiements(){
        return paiementService.paiements();
    }

    @GetMapping("/paiements/{id}")
    public ResponseEntity<Optional<Paiement>> getPaiement(@PathVariable long id){
        Optional<Paiement> existingPaiement = paiementService.paiement(id);

        if(!existingPaiement.isPresent()){
            throw new PaiementNotFound("Le paiement avec l'id "+id+" est introuvable");
        }
        return ResponseEntity.ok(existingPaiement);
    }
    @PostMapping("/paiements")
    public ResponseEntity<Paiement> savePaiement(@Valid @RequestBody Paiement paiement) {
        Optional<Rendezvous> existingPaiement = rendezVousService.getRendezvousById(paiement.getId());

        if(existingPaiement.isPresent()){
            throw new ExistingPaiementException("Paiement deja effectuer");
        }
        Paiement nouveauPaiement = paiementService.savePaiement(paiement);
        if(nouveauPaiement == null){
            throw new PaiementImpossibleException("Impossible d'effectuer un paiement pour le moment, Veuillez reesseyer plus tard");
        }

        return new ResponseEntity<Paiement>(nouveauPaiement,HttpStatus.CREATED);
    }
    @PutMapping("/paiements/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable long id,@RequestBody @Valid Paiement paiement){
        if(paiement==null){
                return ResponseEntity.badRequest().build();
        }
        Optional<Paiement> existingPaiement = paiementService.paiement(id);

        if(!existingPaiement.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Paiement updatePaiement = existingPaiement.get();

        updatePaiement.setModeDePaiement(paiement.getModeDePaiement());
        updatePaiement.setMontant(updatePaiement.getMontant());

        Paiement savedPaiement = paiementService.savePaiement(updatePaiement);

        return ResponseEntity.ok(savedPaiement);
    }
    @DeleteMapping("/paiements/{id}")
    public String deletePaiement(@PathVariable long id){
        paiementService.deletePaiementById(id);
        return "Votre paiement a ete retirer avec success";
    }

}
