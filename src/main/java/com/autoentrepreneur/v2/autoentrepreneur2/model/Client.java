package com.autoentrepreneur.v2.autoentrepreneur2.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "raison_sociale", nullable = false)
    private String raisonSociale;

    // Le numéro SIRET (ou système d'identification du répertoire des
    // établissements) identifie chaque établissement de l'entreprise.
    // Il se compose de 14 chiffres : les neuf chiffres du numéro SIREN + les cinq
    // chiffres correspondant à un numéro NIC.
    // @Column(name = "siret")
    // private String siret;

    // Le numéro SIREN (ou système d'identification du répertoire des entreprises)
    // sert à identifier l'entreprise en tant qu'entité.
    // Il s'agit d'un code unique et invariable tout au long de la vie de
    // l'entreprise. Il se compose de neuf chiffres.
    @Column(name = "siren", length = 9)
    private String siren;

    @Column(name = "date_creation")
    @CreationTimestamp
    private Timestamp dateCreation;

    @Column(name = "date_mise_a_jour")
    @UpdateTimestamp
    private Timestamp dateMAJ;

    @OneToMany(targetEntity = Contact.class, mappedBy = "client", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Contact> contacts;

    public ClientDTO convertToDTO() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(this.id);
        clientDTO.setRaisonSociale(this.raisonSociale);
        clientDTO.setSiren(this.siren);
        clientDTO.setDateCreation(this.dateCreation);
        clientDTO.setDateMAJ(this.dateMAJ);
        return clientDTO;
    }
}
