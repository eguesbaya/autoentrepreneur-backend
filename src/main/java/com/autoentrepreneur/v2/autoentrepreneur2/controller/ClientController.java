package com.autoentrepreneur.v2.autoentrepreneur2.controller;

import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.exception.ResourceNotFoundException;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/all")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    // get contact by ID
    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id) {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get();
        } else {
            throw new ResourceNotFoundException("Client id#" + id + " n'existe pas.");
        }
    }
    
}
