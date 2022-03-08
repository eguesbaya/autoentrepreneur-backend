package com.autoentrepreneur.v2.autoentrepreneur2.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.exception.ResourceNotFoundException;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Client id#" + id + " n'existe pas.");
        }
    }

    @GetMapping("/siret/{siret}")
    public List<Client> getClientBySiret(@PathVariable String siret) {
        return clientRepository.findBySiret(siret);
    }

    @GetMapping("/siren/{siren}")
    public List<Client> getClientBySiren(@PathVariable String siren) {
        return clientRepository.findBySiren(siren);
    }

    @GetMapping("/nom/{nomRaisonSociale}")
    public List<Client> getClientByNomRaisonSociale(@PathVariable String nomRaisonSociale) {
        return clientRepository.findByNomRaisonSociale(nomRaisonSociale);
    }

    @GetMapping("/dateCreation/{dateCreation}")
    public List<Client> getClientByDateCreation(@PathVariable LocalDateTime dateCreation) {
        return clientRepository.findByDateCreation(dateCreation);
    }

    @GetMapping("/dateMAJ/{dateMAJ}")
    public List<Client> getClientBydateMAJ(@PathVariable LocalDateTime dateMAJ) {
        return clientRepository.findByDateMAJ(dateMAJ);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long id) {
        Client client = clientRepository.findById(id).get();
        String message;
        if (!client.getContacts().isEmpty()) {
            message = client.getNomRaisonSociale() + " et tous les contacts associés ont été supprimés.";
        } else {
            message = client.getNomRaisonSociale() + " a été supprimé. Aucun contact n'a été affecté.";
        }
        clientRepository.deleteById(id);
        return ResponseEntity.ok(message);
    }
}
