package univ.fac.master.repositories;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Groupe;

   public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
	@Query("SELECT e FROM Etudiant e LEFT JOIN FETCH e.groupe WHERE e.email = ?1")
	public Etudiant findByEmail(String email);
	/*@Query("SELECT e FROM Etudiant e WHERE e.verificationCode = ?1")
    public Etudiant findByVerificationCode(String code);
	
	@Query("UPDATE Etudiant e SET e.enabled = true WHERE e.id = ?1")
	@Modifying
	public void enabled(Integer id);
	//public Optional<Etudiant> findById(Collection idE);
	*/

	public List<Etudiant> findByGroupe(Groupe grp);
}
