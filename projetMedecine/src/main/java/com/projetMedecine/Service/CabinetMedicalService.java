package com.projetMedecine.Service;

import com.projetMedecine.Modele.CabinetMedical;
import com.projetMedecine.Repository.CabinetMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CabinetMedicalService {
    @Autowired
    private CabinetMedicalRepository cabinetMedicalRepository;

    public Iterable<CabinetMedical> getCabinetMedicals(){
        return cabinetMedicalRepository.findAll();
    }
    public Optional<CabinetMedical> getCabinetMedical(long id){
        return cabinetMedicalRepository.findById(id);
    }
}
