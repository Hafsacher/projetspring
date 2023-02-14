package univ.fac.master.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.Sujet;
import univ.fac.master.entities.TeamStatus;

public interface GroupeRepository extends JpaRepository<Groupe, Long> {

	Optional<Groupe> findByNomGroupe(String nomGroupe);

	List<Groupe> findBySujetAndStatut(Sujet s, TeamStatus statut);

}
