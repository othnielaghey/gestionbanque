package esig.tg.gestionbanque.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Sexe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSexe;
    private String sexe;

    @OneToMany(mappedBy = "sexe")
    List<Client> clientList;
}
