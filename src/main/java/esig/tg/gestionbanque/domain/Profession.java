package esig.tg.gestionbanque.domain;

        import lombok.Data;
        import javax.persistence.*;
        import java.util.List;

@Entity
@Data
public class Profession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfession;
    private String libelle;

    @OneToMany(mappedBy = "profession")
    List<Client> clientList;
}
