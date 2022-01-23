package esig.tg.gestionbanque.dao;

import esig.tg.gestionbanque.domain.Transferer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfererRepository extends JpaRepository<Transferer, Long> {
    @Override
    List<Transferer> findAll();

}