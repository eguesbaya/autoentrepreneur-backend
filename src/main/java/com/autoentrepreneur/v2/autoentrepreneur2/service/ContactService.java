package com.autoentrepreneur.v2.autoentrepreneur2.service;

import com.autoentrepreneur.v2.autoentrepreneur2.repository.ClientRepository;
import com.autoentrepreneur.v2.autoentrepreneur2.repository.ContactRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ClientDTO;
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

    public ContactDTO create(ContactDTO inputContact) {
        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setId(inputContact.getId());
        contactDTO.setNom(inputContact.getNom());
        contactDTO.setPrenom(inputContact.getPrenom());
        contactDTO.setEmail(inputContact.getEmail());
        contactDTO.setTelephone(inputContact.getTelephone());
        contactDTO.setMobile(inputContact.getMobile());
        contactDTO.setIsContactPrincipal(inputContact.getIsContactPrincipal());
        // DTO becomes Entity
        Contact contact = contactDTO.convertToEntity();

        //ATTRIBUTING NEWLY CREATED CONTACT TO CLIENT
        //1- Retrieving client
        Long clientId = inputContact.getClient().getId();
        Client client = clientService.getById(clientId).convertToEntity();
        //2- Adding newly created contact to this client's list of contacts
        List<Contact> contactList = client.getContacts();
        contactList.add(contact);
        client.setContacts(contactList);

        //ATTRIBUTING CLIENT TO NEWLY CREATED CONTACT
        contact.setClient(client);

        //SAVING NEW CONTACT TO DB
        contact = contactRepository.saveAndFlush(contact);

        //CONVERTING CONTACT BACK TO DTO
        return contact.convertToDTO();
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
    
}
