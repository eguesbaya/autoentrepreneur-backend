package com.autoentrepreneur.v2.autoentrepreneur2.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.autoentrepreneur.v2.autoentrepreneur2.dto.ContactDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "mobile")
    private String mobile;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @JsonBackReference
    private Client client;

    @Column(name="is_contact_principal")
    private Boolean isContactPrincipal;

    @Column(name= "date_creation")
    @CreationTimestamp
    private Timestamp dateCreation;

    @Column(name ="date_mise_a_jour")
    @UpdateTimestamp
    private Timestamp dateMAJ;

    public ContactDTO convertToDTO() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(this.id);
        contactDTO.setPrenom(this.prenom);
        contactDTO.setNom(this.nom);
        contactDTO.setEmail(this.email);
        contactDTO.setTelephone(this.telephone);
        contactDTO.setMobile(this.mobile);
        contactDTO.setClient(this.client);
        contactDTO.setDateCreation(this.dateCreation);
        contactDTO.setDateMAJ(this.dateMAJ);
        return contactDTO;
    }

}
