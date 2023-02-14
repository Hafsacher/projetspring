package univ.fac.master.entities;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.*;

@Entity

public class Rendezvous {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Time heure;

	private Date date;
	@Enumerated(EnumType.ORDINAL)
	private MeetingStatus statut;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "goupe_Id")
	//@JsonBackReference
	Groupe groupe;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Time getHeure() {
		return heure;
	}
	public void setHeure(Time heure) {
		this.heure = heure;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public MeetingStatus getStatut() {
		return statut;
	}
	public void setStatut(MeetingStatus statut) {
		this.statut = statut;
	}
	
	
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public Rendezvous(Long id,Time heure, Date date, MeetingStatus statut) {
		super();
		this.id = id;
		this.heure = heure;
		this.date = date;
		this.statut = statut;
	}
	public Rendezvous() {
		super();
		// TODO Auto-generated constructor stub
	}
}
