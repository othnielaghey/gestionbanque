package esig.tg.gestionbanque.dao;

import esig.tg.gestionbanque.domain.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    @Override
    List<Compte> findAll();

    Compte findByIdCpt(Long id);
    Compte findCompteByNumCompte(String numCompte);
}
