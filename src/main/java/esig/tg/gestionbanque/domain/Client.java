package esig.tg.gestionbanque.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    private Long idClient;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private String adresse;

    @ManyToOne
    Sexe sexe;

    @ManyToOne
    Profession profession;

    @OneToMany(mappedBy = "client")
    List<Compte> compteList;

    @OneToMany(mappedBy = "clientOp")
    List<Operer> opererList;

    @OneToMany(mappedBy = "clientTransft")
    List<Transferer> transferers;

    public Client() {
    }

    public Client(String nom, String prenom, String dateNaissance, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;

    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
