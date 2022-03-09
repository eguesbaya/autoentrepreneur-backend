package com.autoentrepreneur.v2.autoentrepreneur2.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    // ClientDTO convertEntityToDTO(Client client);

    // ClientDTO createClient();

    // ClientDTO updateClient();

    // void deleteClient();

}
