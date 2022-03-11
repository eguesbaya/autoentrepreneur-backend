package com.autoentrepreneur.v2.autoentrepreneur2.dto;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import lombok.Data;

@Data
public class ContactDTO {

    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String telephone;

    private String mobile;

    private Client client;

    private Boolean isContactPrincipal;

    public Contact convertToEntity() {
        Contact contact = new Contact();
        contact.setId(this.id);
        contact.setNom(this.nom);
        contact.setPrenom(this.prenom);
        contact.setEmail(this.email);
        contact.setTelephone(this.telephone);
        contact.setMobile(this.mobile);
        contact.setClient(this.client);
        contact.setIsContactPrincipal(this.isContactPrincipal);
        return contact;
    }
    
}
