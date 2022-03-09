package com.autoentrepreneur.v2.autoentrepreneur2.service;

import java.util.List;
import java.util.stream.Collectors;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ClientDTO;
import com.autoentrepreneur.v2.autoentrepreneur2.exception.ResourceNotFoundException;
import com.autoentrepreneur.v2.autoentrepreneur2.model.Client;
import com.autoentrepreneur.v2.autoentrepreneur2.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream().map(Client::convertToDTO).collect(Collectors.toList());
    }

    public ClientDTO getClientById(Long id) {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get().convertToDTO();
        } else {
            throw new ResourceNotFoundException("Client id#" + id + " n'existe pas.");
        }
    }

    // ClientDTO convertEntityToDTO(Client client);

    // ClientDTO createClient();

    // ClientDTO updateClient();

    // void deleteClient();

    // ClientDTO getClientById();
}
