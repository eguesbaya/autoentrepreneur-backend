package com.autoentrepreneur.v2.autoentrepreneur2.controller;

import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;
import com.autoentrepreneur.v2.autoentrepreneur2.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/contact")
public class ContactController {
    
    @Autowired
    private ContactRepository contactRepository;

    //get all contacts
    @GetMapping("/all")
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    //get contact by ID
    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Long id) {
        return contactRepository.findById(id).get();
    }
    
    // get contact by clientId
    // @GetMapping("/{clientId}")
    // public List<Contact> getContactByClient(@PathVariable("client") Long clientId){
    //     return contactRepository.findByClient(clientId);
    // }
}
