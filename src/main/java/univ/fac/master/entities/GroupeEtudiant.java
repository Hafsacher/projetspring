package univ.fac.master.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class GroupeEtudiant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "groupe_id")
	//@JsonBackReference
	Groupe groupe;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "etudiant_id")
	Etudiant etudiant;
	
	public Long getId() {
		return id;
	}

	
	public Groupe getGroupe() {
		return groupe;
	}



	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}



	public Etudiant getEtudiant() {
		return etudiant;
	}



	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public GroupeEtudiant(Long id) {
		super();
		this.id = id;
	}

	public GroupeEtudiant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
