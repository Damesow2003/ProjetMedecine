package com.projetMedecine.Controller;

import com.projetMedecine.Modele.Salle;
import com.projetMedecine.Service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalleController {
    @Autowired
    private SalleService salleService;

}
