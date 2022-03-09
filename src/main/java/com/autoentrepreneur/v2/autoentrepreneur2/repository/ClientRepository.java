package com.autoentrepreneur.v2.autoentrepreneur2.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findByDateCreation(LocalDateTime dateCreation);

    List<Client> findByDateMAJ(LocalDateTime dateMAJ);
    
    List<Client> findByRaisonSociale(String raisonSociale);

    List<Client> findBySiren(String siren);
    
}
