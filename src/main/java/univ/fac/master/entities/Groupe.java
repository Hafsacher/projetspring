package univ.fac.master.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Groupe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 20)
    private String nomGroupe;
    private int nbetudiant;

    @Enumerated(EnumType.ORDINAL)
    private TeamStatus statut;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sujet_id")
    //@JsonBackReference
    Sujet sujet;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
    @JsonIgnore
    List<GroupeEtudiant> groupeEtudiant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
    @JsonIgnore
    List<Etudiant> etudiant;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groupe")
    @JsonIgnore
    List<Rendezvous> rendezvous;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(String nomGroupe) {
        this.nomGroupe = nomGroupe;
    }

    public int getNbetudiant() {
        return nbetudiant;
    }

    public void setNbetudiant(int nbetudiant) {
        this.nbetudiant = nbetudiant;
    }

    public TeamStatus getStatut() {
        return statut;
    }

    public void setStatut(TeamStatus statut) {
        this.statut = statut;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public List<GroupeEtudiant> getGroupeEtudiant() {
        return groupeEtudiant;
    }

    public void setGroupeEtudiant(List<GroupeEtudiant> groupeEtudiant) {
        this.groupeEtudiant = groupeEtudiant;
    }


    public List<Etudiant> getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(List<Etudiant> etudiant) {
        this.etudiant = etudiant;
    }

    public Groupe(Long id, String nomGroupe, int nbetudiant, TeamStatus statut) {
        super();
        this.id = id;
        this.nomGroupe = nomGroupe;
        this.nbetudiant = nbetudiant;
        this.statut = statut;
    }

    public Groupe() {
        super();
        // TODO Auto-generated constructor stub
    }


}
