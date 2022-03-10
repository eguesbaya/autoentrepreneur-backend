package com.autoentrepreneur.v2.autoentrepreneur2.service;

import com.autoentrepreneur.v2.autoentrepreneur2.repository.ContactRepository;

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
    
}
