package com.projetMedecine.Controller;


import com.projetMedecine.Modele.CabinetMedical;
import com.projetMedecine.Service.CabinetMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CabinetMedicalController {
    @Autowired
    private CabinetMedicalService cabinetMedicalService;

    @GetMapping("/cabinets")
    public Iterable<CabinetMedical> getCabinetMedical(){
        return cabinetMedicalService.getCabinetMedicals();
    }
    @GetMapping("/cabinets/{id}")
    public Optional<CabinetMedical> getCabinetMedicalById(@PathVariable long id){
        return cabinetMedicalService.getCabinetMedical(id);
    }
}
