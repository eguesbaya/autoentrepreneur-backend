package com.autoentrepreneur.v2.autoentrepreneur2.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByClient(Long clientId);

    Optional<Contact> findById(Long id);

    List<Contact> findByEmail(String email);

    List<Contact> findByPrenom(String prenom);

    List<Contact> findByNom(String nom);

    List<Contact> findByDateCreation(Timestamp dateCreation);

    List<Contact> findByDateMAJ(Timestamp dateMAJ);

    List<Contact> findByIsContactPrincipal(Boolean isContactPrincipal);

    List<Contact> findByMobile(String mobile);

    List<Contact> findByTelephone(String telephone);
}
