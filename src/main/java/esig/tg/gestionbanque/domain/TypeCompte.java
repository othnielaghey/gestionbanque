package esig.tg.gestionbanque.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity

public class TypeCompte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idType;
    @Column(unique = true)
    private String libelleCompte;

    public TypeCompte() {

    }


    public Long getIdType() {
        return idType;
    }

    public void setIdType(Long idType) {
        this.idType = idType;
    }

    public String getLibelleCompte() {
        return libelleCompte;
    }

    public void setLibelleCompte(String libelleCompte) {
        this.libelleCompte = libelleCompte;
    }

    public TypeCompte(String libelleCompte) {
        this.libelleCompte = libelleCompte;

    }

    @OneToMany(mappedBy = "typeCompte")
    List<Compte> CompteList;

    @Override
    public String toString() {
        return "TypeCompte{" +
                "idType=" + idType +
                ", libelle='" + libelleCompte + '\'' +
                '}';
    }
}
