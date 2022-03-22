package com.autoentrepreneur.v2.autoentrepreneur2.service;


import com.autoentrepreneur.v2.autoentrepreneur2.repository.ContactRepository;

import java.sql.Timestamp;

import java.util.List;
import java.util.stream.Collectors;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ContactDTO;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;


    @Autowired
    private ClientService clientService;

    public List<ContactDTO> getContacts() {
         List<ContactDTO> contacts = contactRepository.findAll()
                .stream()
                .map(Contact::convertToDTO)
                 .collect(Collectors.toList());
        // List<Client> clients = clientRepository.findAll();
        // for (Client client : clients) {
        //     //Récupère les contacts
        //     contacts.addAll(client.getContacts().stream().map(Contact::convertToDTO).collect(Collectors.toList()));
        //     ;
        // }
         return contacts;
        
        // return contactRepository.findAll()
        //         .stream()
        //         .map(Contact::convertToDTO)
        //         .collect(Collectors.toList());
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

    private void setAttributesExceptClient(ContactDTO contactDTO, ContactDTO inputContact) {
        if (inputContact.getId() != null) {
            contactDTO.setId(inputContact.getId());
        }
        if (inputContact.getNom() != null) {
            contactDTO.setNom(inputContact.getNom());
        }
        if (inputContact.getPrenom() != null) {
            contactDTO.setPrenom(inputContact.getPrenom());
        }
        if (inputContact.getEmail() != null) {
            contactDTO.setEmail(inputContact.getEmail());
        }
        if (inputContact.getTelephone() != null) {
            contactDTO.setTelephone(inputContact.getTelephone());
        }
        if (inputContact.getMobile() != null) {
            contactDTO.setMobile(inputContact.getMobile());
        }
        if (inputContact.getIsContactPrincipal() != null) {
            contactDTO.setIsContactPrincipal(inputContact.getIsContactPrincipal());
        }

    }
    
    private void setAttributeClient(Contact contact) {
        // 1- Retrieving client from input
        Long clientId = contact.getClient().getId();
        Client client = clientService.getById(clientId).convertToEntity();
        // 2- Adding contact to this client's list of contacts
        List<Contact> contactList = client.getContacts();
        contactList.add(contact);
        client.setContacts(contactList);
        // 3- Adding client to this contact
        contact.setClient(client);
    }

    public ContactDTO create(ContactDTO inputContact) {

        //TRANSFORMING DTO INTO ENTITY
        Contact contact = inputContact.convertToEntity();
        //SETTING CLIENT
        setAttributeClient(contact);
        //SAVING NEW CONTACT TO DB
        contact = contactRepository.saveAndFlush(contact);
        //CONVERTING CONTACT BACK TO DTO
        return contact.convertToDTO();
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    public ContactDTO update(Long id, ContactDTO inputContact) {
        //FETCHING CONTACT TO UPDATE AND CONVERTING IT TO DTO
        ContactDTO contactDTO = contactRepository.findById(id).get().convertToDTO();
        //UPDATING CONTACT BASED ON INPUT, AND CONVERTING IT BACK TO ENTITY
        setAttributesExceptClient(contactDTO, inputContact);
        Contact contact = contactDTO.convertToEntity();
        //UPDATING CLIENT
        setAttributeClient(contact);
        //SAVING CONTACT TO DB
        contact = contactRepository.saveAndFlush(contact);
        return contact.convertToDTO();
    }
    
}
