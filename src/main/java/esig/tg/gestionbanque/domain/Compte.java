package esig.tg.gestionbanque.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCpt;
    private String soldeCpt = "0";
    private String dateCreation;
    private String numCompte;

    @ManyToOne
    TypeCompte typeCompte;

    @ManyToOne
    Client client;

    @OneToMany(mappedBy = "compteOp")
    List<Operer> operers;

    @OneToMany(mappedBy = "compteTransft")
    List<Transferer> transfererList;

    public Compte(String soldeCpt, String dateCreation, TypeCompte typeCompte,String numCompte, Client client) {
        this.soldeCpt = soldeCpt;
        this.dateCreation = dateCreation;
        this.typeCompte = typeCompte;
        this.client = client;
        this.numCompte = numCompte;
    }

    public Compte(String soldeCpt, String dateCreation) {
        this.soldeCpt = soldeCpt;
        this.dateCreation = dateCreation;
    }

    public Compte() {

    }

    public Long getIdCpt() {
        return idCpt;
    }

    public void setIdCpt(Long idCpt) {
        this.idCpt = idCpt;
    }

    public String getSoldeCpt() {
        return soldeCpt;
    }

    public void setSoldeCpt(String soldeCpt) {
        this.soldeCpt = soldeCpt;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "idCpt=" + idCpt +
                ", soldeCpt='" + soldeCpt + '\'' +
                ", dateCreation='" + dateCreation + '\'' +
                ", numCompte='" + numCompte + '\'' +
                '}';
    }
}
