package com.autoentrepreneur.v2.autoentrepreneur2.dto;

import java.sql.Timestamp;
import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import lombok.Data;

@Data
public class ClientDTO {

    private Long id;

    private String raisonSociale;

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
