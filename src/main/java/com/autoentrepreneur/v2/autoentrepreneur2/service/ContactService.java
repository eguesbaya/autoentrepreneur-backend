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

    private void setAttributesExceptClient(Contact contact, ContactDTO inputContact) {
        if (inputContact.getId() != null) {
            contact.setId(inputContact.getId());
        }
        if (inputContact.getNom() != null) {
            contact.setNom(inputContact.getNom());
        }
        if (inputContact.getPrenom() != null) {
            contact.setPrenom(inputContact.getPrenom());
        }
        if (inputContact.getEmail() != null) {
            contact.setEmail(inputContact.getEmail());
        }
        if (inputContact.getTelephone() != null) {
            contact.setTelephone(inputContact.getTelephone());
        }
        if (inputContact.getMobile() != null) {
            contact.setMobile(inputContact.getMobile());
        }
        if (inputContact.getIsContactPrincipal() != null) {
            contact.setIsContactPrincipal(inputContact.getIsContactPrincipal());
        }

    }
    
    private void setAttributeClient(Contact contact, ContactDTO inputContact) {
        // 1- Retrieving client from input
        Long clientId = inputContact.getClient().getId();
        Client client = clientService.getById(clientId).convertToEntity();
        // 2- Adding contact to this client's list of contacts
        List<Contact> contactList = client.getContacts();
        contactList.add(contact);
        client.setContacts(contactList);
        // 3- Adding client to this contact
        contact.setClient(client);
    }

    public ContactDTO create(ContactDTO inputContact) {
        Contact contact = new Contact();
        setAttributesExceptClient(contact, inputContact);

        //ATTRIBUTING NEWLY CREATED CONTACT TO CLIENT
        //1- Retrieving client
        // Long clientId = inputContact.getClient().getId();
        // Client client = clientService.getById(clientId).convertToEntity();
        // //2- Adding newly created contact to this client's list of contacts
        // List<Contact> contactList = client.getContacts();
        // contactList.add(contact);
        // client.setContacts(contactList);

        // //ATTRIBUTING CLIENT TO NEWLY CREATED CONTACT
        // contact.setClient(client);
        setAttributeClient(contact, inputContact);

        //SAVING NEW CONTACT TO DB
        contact = contactRepository.saveAndFlush(contact);

        //CONVERTING CONTACT BACK TO DTO
        return contact.convertToDTO();
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    public ContactDTO update(Long id, ContactDTO inputContact) {
        //FETCHING CONTACT TO UPDATE
        Contact contact = contactRepository.findById(id).get();
        //UPDATING CONTACT BASED ON INPUT
        setAttributesExceptClient(contact, inputContact);
        //UPDATING CLIENT
        setAttributeClient(contact, inputContact);
        //SAVING CONTACT TO DB
        contact = contactRepository.saveAndFlush(contact);
        return contact.convertToDTO();
    }
    
}
