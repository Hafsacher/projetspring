package univ.fac.master.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Sujet;


public interface SujetRepository extends JpaRepository<Sujet, Long> {

	//4
	List<Sujet> findByEncadrant(Encadrant e);

	//Optional<Etudiant> findById(Collection idS);

}
