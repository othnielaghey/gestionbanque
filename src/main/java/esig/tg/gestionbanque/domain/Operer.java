package esig.tg.gestionbanque.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
public class Operer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeOperation;
    private String soldeOperation;
    private String commentaire;
    private String dateOperation;

    @ManyToOne
    Client clientOp;

    @ManyToOne
    Compte compteOp;

    public Operer() {
    }

    public Operer(String typeOperation, String soldeOperation, String commentaire, String dateOperation, Client clientOp, Compte compteOp) {
        this.typeOperation = typeOperation;
        this.soldeOperation = soldeOperation;
        this.commentaire = commentaire;
        this.dateOperation = dateOperation;
        this.clientOp = clientOp;
        this.compteOp = compteOp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getSoldeOperation() {
        return soldeOperation;
    }

    public void setSoldeOperation(String soldeOperation) {
        this.soldeOperation = soldeOperation;
    }

    public String getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(String dateOperation) {
        this.dateOperation = dateOperation;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Client getClientOp() {
        return clientOp;
    }

    public void setClientOp(Client clientOp) {
        this.clientOp = clientOp;
    }

    public Compte getCompteOp() {
        return compteOp;
    }

    public void setCompteOp(Compte compteOp) {
        this.compteOp = compteOp;
    }

    @Override
    public String toString() {
        return "Operer{" +
                "id=" + id +
                ", typeOperation='" + typeOperation + '\'' +
                ", soldeOperation='" + soldeOperation + '\'' +
                ", commentaire='" + commentaire + '\'' +
                ", dateOperation='" + dateOperation + '\'' +
                ", clientOp=" + clientOp +
                ", compteOp=" + compteOp +
                '}';
    }
}
