package ma.fst.info.GestionMediatheque.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.fst.info.GestionMediatheque.Models.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {}

