package com.autoentrepreneur.v2.autoentrepreneur2.service;


import java.time.LocalDateTime;
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
        return clientRepository.findAll()
                .stream()
                .map(Client::convertToDTO)
            .collect(Collectors.toList());
    }

    public ClientDTO getById(Long id) {
        if (clientRepository.findById(id).isPresent()) {
            return clientRepository.findById(id).get().convertToDTO();
        } else {
            throw new ResourceNotFoundException("Client id#" + id + " n'existe pas.");
        }
    }

    public List<ClientDTO> getBySiren(String siren) {
        return clientRepository.findBySiren(siren)
                .stream()
                .map(Client::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<ClientDTO> getByRaisonSociale(String raisonSociale) {
        return clientRepository.findByRaisonSociale(raisonSociale)
                .stream()
                .map(Client::convertToDTO)
                .collect(Collectors.toList());
    }
    
    //might need to change type of input to TimeStamp
    public List<ClientDTO> getByDateCreation(LocalDateTime dateCreation) {
        return clientRepository.findByDateCreation(dateCreation)
                .stream()
                .map(Client::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // might need to change type of input to TimeStamp
    public List<ClientDTO> getByDateMAJ(LocalDateTime dateCreation) {
        return clientRepository.findByDateMAJ(dateCreation)
                .stream()
                .map(Client::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientDTO create(ClientDTO inputClient) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(inputClient.getId());
        clientDTO.setRaisonSociale(inputClient.getRaisonSociale());
        clientDTO.setSiren(inputClient.getSiren());
        //might need to convert contacts to objects
        clientDTO.setContacts(inputClient.getContacts());
        Client client = clientRepository.saveAndFlush(clientDTO.convertToEntity());
        return client.convertToDTO();
    }

    // ClientDTO updateClient();
    public ClientDTO update(Long id, ClientDTO inputClientDTO) {
        Client client = clientRepository.findById(id).get();
        if (inputClientDTO.getId() != null) {
            client.setId(inputClientDTO.getId());
        }
        if (inputClientDTO.getRaisonSociale() != null){
            client.setRaisonSociale(inputClientDTO.getRaisonSociale());
        }
        if (inputClientDTO.getSiren() != null) {
            client.setSiren(inputClientDTO.getSiren());
        }
        // // will need to convert contacts into DTOs
        // if (inputClientDTO.getContacts() != null) {
        //     client.setContacts(inputClientDTO.getContacts());
        // }
        return clientRepository.saveAndFlush(client).convertToDTO();
    }

    // void deleteClient();

}
