package esig.tg.gestionbanque.dao;

import esig.tg.gestionbanque.domain.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionRepository extends JpaRepository<Profession, Long> {
    @Override
    List<Profession> findAll();

    @Override
    <S extends Profession> S save(S s);
}
