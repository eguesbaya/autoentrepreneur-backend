package dto;

import java.sql.Timestamp;
import java.util.List;

import com.autoentrepreneur.v2.autoentrepreneur2.model.Contact;

import lombok.Data;

@Data
public class ClientDTO {

    private Long id;

    private String raisonSociale;

    private String siren;

    private Timestamp dateCreation;

    private Timestamp dateMAJ;

    private List<Contact> contacts;
    
}
