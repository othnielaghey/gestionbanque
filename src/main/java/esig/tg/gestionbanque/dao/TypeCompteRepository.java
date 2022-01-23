package esig.tg.gestionbanque.dao;

import esig.tg.gestionbanque.domain.TypeCompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeCompteRepository extends JpaRepository<TypeCompte, Long> {
    @Override
    List<TypeCompte> findAll();

    TypeCompte findByIdType(Long id);

}
