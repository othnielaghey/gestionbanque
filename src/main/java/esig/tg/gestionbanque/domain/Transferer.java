package esig.tg.gestionbanque.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Transferer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numCompteExpediteur;
    private String numCompteRecepteur;
    private String montantTransfert;
    private String commentaire;
    private String dateTransfert;

    @ManyToOne
    Client clientTransft;

    @ManyToOne
    Compte compteTransft;

    public Transferer() {
    }

    public Transferer(Client clientTransft, Compte compteTransft) {
        this.clientTransft = clientTransft;
        this.compteTransft = compteTransft;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transferer{" +
                "id=" + id +
                '}';
    }
}
