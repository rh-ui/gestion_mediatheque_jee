package ma.fst.info.GestionMediatheque.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ma.fst.info.GestionMediatheque.Models.Prets;

@Repository
public interface PretRepository extends JpaRepository<Prets, Long> {

    // @Query("SELECT p FROM prets p WHERE p.date_retour < CURRENT_DATE") // hna ghadi n3ayet les prets li en retard (st3mlna requete personalisé jpa & data spring)
    // List<Prets> getPretsEnRetard();
}
