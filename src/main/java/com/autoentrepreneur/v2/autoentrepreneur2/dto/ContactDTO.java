package com.autoentrepreneur.v2.autoentrepreneur2.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import lombok.Data;

@Data
public class ContactDTO {

    private Long id;

    @Size(min= 2, max= 255)
    private String nom;

    @Size(min= 2, max= 255)
    private String prenom;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String telephone;

    private String mobile;

    @NotNull
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
