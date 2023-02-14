package univ.fac.master.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.GroupeEtudiant;


@Repository
@Transactional
public interface GroupeEtudiantRepository extends JpaRepository<GroupeEtudiant, Long> {

	List<GroupeEtudiant> findByGroupe(Groupe g);
	List<GroupeEtudiant> findByEtudiant(Etudiant e);
	int deleteGEByEtudiant(Optional<Etudiant> e);


}
