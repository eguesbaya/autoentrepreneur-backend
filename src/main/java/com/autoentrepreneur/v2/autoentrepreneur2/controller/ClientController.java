package com.autoentrepreneur.v2.autoentrepreneur2.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ClientDTO;
import com.autoentrepreneur.v2.autoentrepreneur2.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("")
    public List<ClientDTO> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        return clientService.getById(id);
    }

    @GetMapping("/siren/{siren}")
    public List<ClientDTO> getClientBySiren(@PathVariable String siren) {
        return clientService.getBySiren(siren);
    }

    @GetMapping("/nom/{raisonSociale}")
    public List<ClientDTO> getClientByRaisonSociale(@PathVariable String raisonSociale) {
        return clientService.getByRaisonSociale(raisonSociale);
    }

    @GetMapping("/dateCreation/{dateCreation}")
    public List<ClientDTO> getClientByDateCreation(@PathVariable LocalDateTime dateCreation) {
        return clientService.getByDateCreation(dateCreation);
    }

    @GetMapping("/dateMAJ/{dateMAJ}")
    public List<ClientDTO> getClientBydateMAJ(@PathVariable LocalDateTime dateMAJ) {
        return clientService.getByDateMAJ(dateMAJ);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable Long id) {
        ClientDTO client = clientService.getById(id);
        String message;
        if (!client.getContacts().isEmpty()) {
            message = client.getRaisonSociale() + " et tous les contacts associés ont été supprimés.";
        } else {
            message = client.getRaisonSociale() + " a été supprimé. Aucun contact n'a été affecté.";
        }
        clientService.deleteById(id);
        return ResponseEntity.ok(message);
    }

    @PostMapping("")
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO inputClientDTO) {
        ClientDTO clientDTO = clientService.create(inputClientDTO);
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO inputClientDTO) {
        ClientDTO clientDTO = clientService.update(id, inputClientDTO);
        return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
    }
}
