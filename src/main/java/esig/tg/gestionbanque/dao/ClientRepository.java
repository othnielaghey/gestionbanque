package esig.tg.gestionbanque.dao;

import esig.tg.gestionbanque.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Override
    List<Client> findAll();

    @Override
    <S extends Client> S save(S s);
}
