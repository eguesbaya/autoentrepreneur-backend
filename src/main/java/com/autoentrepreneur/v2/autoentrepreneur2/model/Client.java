package com.autoentrepreneur.v2.autoentrepreneur2.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_raison_sociale", nullable = false)
    private String nomRaisonSociale;

    @Column(name = "siret")
    private String siret;

    @Column(name = "siren")
    private String siren;

    @Column(name = "date_creation")
    @CreatedDate
    private LocalDateTime dateCreation;

    @Column(name = "date_mise_a_jour")
    @LastModifiedDate
    private LocalDateTime dateMAJ;

    @OneToMany(mappedBy="client")
    private Set<Contact> contacts;

    public Client(String nomRaisonSociale, String siren) {
        this.nomRaisonSociale = nomRaisonSociale;
        this.siren = siren;
    }
    
}
