package com.autoentrepreneur.v2.autoentrepreneur2.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import lombok.Data;

@Data
public class ClientDTO {

    private Long id;

    @NotBlank(message = "La raison sociale est obligatoire!")
    @Size(max = 255, min = 2, message = "La raison sociale doit faire entre 2 et 255 caractères.")
    private String raisonSociale;

    @NotBlank(message = "Le numéro de SIREN est obligatoire!")
    @Size(max = 9, message = "Le numéro de SIREN doit faire exactement 9 chiffres.")
    @Size(min = 9, message = "Le numéro de SIREN doit faire exactement 9 chiffres.")
    private String siren;

    // private Timestamp dateCreation;

    // private Timestamp dateMAJ;

    private List<Contact> contacts;

    public Client convertToEntity() {
        Client client = new Client();
        client.setId(this.id);
        client.setRaisonSociale(this.raisonSociale);
        client.setSiren(this.siren);
        client.setContacts(this.contacts);
        return client;
    }
    
}
