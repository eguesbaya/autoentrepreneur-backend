package com.autoentrepreneur.v2.autoentrepreneur2.service;

import com.autoentrepreneur.v2.autoentrepreneur2.repository.ContactRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ContactDTO;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<ContactDTO> getContacts() {
        return contactRepository.findAll()
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }

    public ContactDTO getById(Long id) {
        return contactRepository.findById(id).get().convertToDTO();
    }

    public List<ContactDTO> getByClient(Long clientId) {
        return contactRepository.findByClient(clientId)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ContactDTO> getByEmail(String email) {
        return contactRepository.findByEmail(email)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ContactDTO> getByPrenom(String prenom) {
        return contactRepository.findByPrenom(prenom)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ContactDTO> getByNom(String nom) {
        return contactRepository.findByNom(nom)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ContactDTO> getByDateCreation(Timestamp dateCreation) {
        return contactRepository.findByDateCreation(dateCreation)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ContactDTO> getByDateMAJ(Timestamp dateMAJ) {
        return contactRepository.findByDateMAJ(dateMAJ)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }


    // List<Contact> findByDateMAJ(Timestamp dateMAJ);

    public List<ContactDTO> getByContactPrincipal(Boolean isContactPrincipal) {
        return contactRepository.findByIsContactPrincipal(isContactPrincipal)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ContactDTO> getByMobile(String mobile) {
        return contactRepository.findByMobile(mobile)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ContactDTO> getByTelephone(String telephone) {
        return contactRepository.findByTelephone(telephone)
                .stream()
                .map(Contact::convertToDTO)
                .collect(Collectors.toList());
    }
    
}
