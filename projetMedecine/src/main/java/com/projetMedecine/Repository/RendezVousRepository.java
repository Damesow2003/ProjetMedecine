package com.projetMedecine.Repository;

import com.projetMedecine.Modele.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<Rendezvous,Long> {

}
