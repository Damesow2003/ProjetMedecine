package com.projetMedecine.Controller;


import com.projetMedecine.Exceptions.CabinetMedicalBadRequest;
import com.projetMedecine.Modele.CabinetMedical;
import com.projetMedecine.Modele.CabinetMedicalProxy;
import com.projetMedecine.Service.CabinetMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CabinetMedicalController {
    @Autowired
    private CabinetMedicalService cabinetMedicalService;

    @GetMapping("/cabinets")
    public Iterable<CabinetMedical> getCabinetMedical() {
        return cabinetMedicalService.getCabinetMedicals();
    }

    @GetMapping("/cabinets/{id}")
    public Optional<CabinetMedical> getCabinetMedicalById(@PathVariable long id) {
        return cabinetMedicalService.getCabinetMedical(id);
    }

    @PostMapping("/cabinets")
    public ResponseEntity<CabinetMedical> saveCabinetMedical(@RequestBody CabinetMedicalProxy cabinetMedicalProxy) {
        if (cabinetMedicalProxy == null) {
            throw new CabinetMedicalBadRequest("Verifiez le body de la cabinetMedical");
        }
        CabinetMedical saveCabinetMedical = cabinetMedicalService.saveCabinetMedical(cabinetMedicalProxy);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveCabinetMedical);
    }

    @PutMapping("/cabinets/{id}")
    public ResponseEntity<CabinetMedical> updateCabinetMedical(@PathVariable long id,@RequestBody CabinetMedical cabinetMedical){
        Optional<CabinetMedical> existingCabinet = cabinetMedicalService.getCabinetMedical(id);
        CabinetMedical updateCabinet = existingCabinet.get();
        updateCabinet.setNom(cabinetMedical.getNom());
        updateCabinet.setAdresse(cabinetMedical.getAdresse());
        updateCabinet.setIdCabinet(cabinetMedical.getIdCabinet());

        return ResponseEntity.ok(updateCabinet);
    }
    @DeleteMapping("/cabinets")
    public String deleteCabinetMedical(@PathVariable long id){
        cabinetMedicalService.deleteCabinetMedical(id);
        return "Le cabinet a ete supprimer avec success";
    }
}
