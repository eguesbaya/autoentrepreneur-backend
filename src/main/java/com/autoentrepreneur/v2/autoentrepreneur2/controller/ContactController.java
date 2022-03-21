package com.autoentrepreneur.v2.autoentrepreneur2.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.Valid;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ContactDTO;
import com.autoentrepreneur.v2.autoentrepreneur2.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/contacts")
@CrossOrigin
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public List<ContactDTO> getContacts() {
        return contactService.getContacts();
    }

    @GetMapping("/{id}")
    public ContactDTO getById(@PathVariable Long id) {
        return contactService.getById(id);
    }

    @GetMapping("/client/{id}")
    public List<ContactDTO> getByClient(@PathVariable Long id) {
        return contactService.getByClient(id);
    }

    @GetMapping("/email/{email}")
    public List<ContactDTO> getByEmail(@PathVariable String email) {
        return contactService.getByEmail(email);
    }

    @GetMapping("/prenom/{prenom}")
    public List<ContactDTO> getByPrenom(@PathVariable String prenom) {
        return contactService.getByPrenom(prenom);
    }

    @GetMapping("/nom/{nom}")
    public List<ContactDTO> getByNom(@PathVariable String nom) {
        return contactService.getByNom(nom);
    }

    @GetMapping("/principal/{isContactPrincipal}")
    public List<ContactDTO> getByContactPrincipal(@PathVariable Boolean isContactPrincipal) {
        return contactService.getByContactPrincipal(isContactPrincipal);
    }

    @GetMapping("/mobile/{mobile}")
    public List<ContactDTO> getByMobile(@PathVariable String mobile) {
        return contactService.getByMobile(mobile);
    }

    @GetMapping("/telephone/{telephone}")
    public List<ContactDTO> getByTelephone(@PathVariable String telephone) {
        return contactService.getByMobile(telephone);
    }

    @GetMapping("/dateCreation/{dateCreation}")
    public List<ContactDTO> getByTelephone(@PathVariable Timestamp dateCreation) {
        return contactService.getByDateCreation(dateCreation);
    }

    @GetMapping("/dateMAJ/{dateMAJ}")
    public List<ContactDTO> getByDateMAJ(@PathVariable Timestamp dateMAJ) {
        return contactService.getByDateMAJ(dateMAJ);
    }

    @PostMapping("")
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO inputContact){
        ContactDTO contactDTO = contactService.create(inputContact);
        return new ResponseEntity<ContactDTO>(contactDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable Long id) {
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> putMethodName(@PathVariable Long id, @Valid @RequestBody ContactDTO inputContact) {
        ContactDTO contactDTO = contactService.update(id, inputContact);

        return new ResponseEntity<ContactDTO>(contactDTO, HttpStatus.CREATED);
    }
}
