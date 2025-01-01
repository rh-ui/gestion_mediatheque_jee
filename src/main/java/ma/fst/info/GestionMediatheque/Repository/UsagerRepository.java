package ma.fst.info.GestionMediatheque.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Models.Usager;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Long> {
     Optional<Usager> findByEmail(String email);
}
