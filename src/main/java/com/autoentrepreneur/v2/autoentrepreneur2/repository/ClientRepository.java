package com.autoentrepreneur.v2.autoentrepreneur2.repository;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
