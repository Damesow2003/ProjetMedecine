package com.projetMedecine.Controller;

import com.projetMedecine.Exceptions.PrescriptionBadRequest;
import com.projetMedecine.Modele.Prescription;
import com.projetMedecine.Modele.PrescriptionProxy;
import com.projetMedecine.Service.PrescriptionService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    public Iterable<Prescription> getPrescriptions(){
        return prescriptionService.prescriptions();
    }
    @GetMapping("/prescriptions/{id}")
    public ResponseEntity<Optional<Prescription>> getPrescripiton(@PathVariable long id){
        Optional<Prescription> prescription = prescriptionService.prescription(id);

        if(prescription.isEmpty()){
            throw new PrescriptionBadRequest("La prescription correspondant a l'id "+id+" est introuvable");
        }
        return ResponseEntity.ok(prescription);
    }
    @PostMapping("/prescriptions") //ici  le Post ne marche pas le probleme c'est a cause du body/
    public ResponseEntity<Prescription> savedPrescription( @RequestBody PrescriptionProxy prescriptionProxy){
        if(prescriptionProxy == null){
            throw new PrescriptionBadRequest("Body invalide");
        }

        System.out.println("Receveid PrescriptionProy: "+prescriptionProxy);
        Prescription prescription = prescriptionService.savePrescription(prescriptionProxy);

            return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
    }

    @DeleteMapping("/prescriptions/{id}")
    public String deletePrescription(@PathVariable long id){
        prescriptionService.deletePrescriptionById(id);

        return "La suppression a ete effectuer avec success";
    }
    @PutMapping("/prescriptions/{id}")
    public ResponseEntity<Prescription> updatedPrescription(@PathVariable long id,@Valid @RequestBody PrescriptionProxy prescriptionProxy){
        Prescription updatePrescription = prescriptionService.updatedPrescription(prescriptionProxy,id);

        return ResponseEntity.ok(updatePrescription);
    }


}
