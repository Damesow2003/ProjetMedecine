package com.projetMedecine.Service;

import com.projetMedecine.Repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalleService {
    @Autowired
    SalleRepository salleRepository;


}
