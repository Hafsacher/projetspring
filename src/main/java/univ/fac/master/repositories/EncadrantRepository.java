package univ.fac.master.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import univ.fac.master.entities.Encadrant;

public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {
	@Query("SELECT e FROM Encadrant e WHERE e.email = ?1")
	public Encadrant findByEmail(String email);
	
}