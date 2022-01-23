package esig.tg.gestionbanque.dao;

import esig.tg.gestionbanque.domain.Sexe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SexeRepository extends JpaRepository<Sexe, Long> {
    @Override
    List<Sexe> findAll();

    @Override
    <S extends Sexe> S save(S s);
}
