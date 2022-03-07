package com.autoentrepreneur.v2.autoentrepreneur2.repository;

import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByClient(Long clientId);

}
