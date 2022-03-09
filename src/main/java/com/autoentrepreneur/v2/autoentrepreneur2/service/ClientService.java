package com.autoentrepreneur.v2.autoentrepreneur2.service;

import java.util.List;
import java.util.stream.Collectors;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ClientDTO;
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

    // ClientDTO convertEntityToDTO(Client client);

    // ClientDTO createClient();

    // ClientDTO updateClient();

    // void deleteClient();

    // ClientDTO getClientById();
}
