package com.autoentrepreneur.v2.autoentrepreneur2.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import lombok.Data;

@Data
public class ContactDTO {

    private Long id;

    @Size(min = 2, max = 255)
    private String nom;

    @Size(min = 2, max = 255)
    private String prenom;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String telephone;

    private String mobile;

    @NotNull
    private ClientDTO client;

    private Boolean isContactPrincipal;

    private Timestamp dateCreation;

    private Timestamp dateMAJ;

    public Contact convertToEntity() {
        Contact contact = new Contact();
        contact.setId(this.id);
        contact.setNom(this.nom);
        contact.setPrenom(this.prenom);
        contact.setEmail(this.email);
        contact.setTelephone(this.telephone);
        contact.setMobile(this.mobile);
        contact.setClient(this.client.convertToEntity());
        contact.setIsContactPrincipal(this.isContactPrincipal);
        contact.setDateCreation(this.dateCreation);
        contact.setDateMAJ(this.dateMAJ);
        return contact;
    }

}
