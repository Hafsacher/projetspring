package univ.fac.master.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.Rendezvous;

public interface RendezvousRepository extends JpaRepository<Rendezvous, Long> {

	List<Rendezvous> findByGroupe(Groupe grp);

	@Query("SELECT r FROM Rendezvous r JOIN FETCH r.groupe AS g JOIN FETCH g.etudiant WHERE r.statut = univ.fac.master.entities.MeetingStatus.ACCEPTED AND r.date > now()")
	List<Rendezvous> getUnrealizedMeetings();

	@Query("SELECT r FROM Rendezvous r JOIN FETCH r.groupe AS g JOIN FETCH g.sujet AS s JOIN FETCH s.encadrant AS e WHERE e.id = ?1")
	List<Rendezvous> getMeetingsBySupervisor(Long supervisorId);
}
