package com.autoentrepreneur.v2.autoentrepreneur2.controller;

import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ContactDTO;

import com.autoentrepreneur.v2.autoentrepreneur2.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/contacts")
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    //get all contacts
    @GetMapping("")
    public List<ContactDTO> getContacts() {
        return contactService.getContacts();
    }

    @GetMapping("/{id}")
    public ContactDTO getClient(@PathVariable Long id) {
        return contactService.getById(id);
    }

}
