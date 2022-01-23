package esig.tg.gestionbanque.dao;

import esig.tg.gestionbanque.domain.Operer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpererRepository extends JpaRepository<Operer, Long> {
    @Override
    List<Operer> findAll();

}
