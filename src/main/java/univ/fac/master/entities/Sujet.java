package univ.fac.master.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Sujet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private int nbetudiant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "encadrant_Id")
    //@JsonBackReference
    Encadrant encadrant;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sujet")
    @JsonIgnore
    List<Groupe> groupe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbetudiant() {
        return nbetudiant;
    }

    public void setNbetudiant(int nbetudiant) {
        this.nbetudiant = nbetudiant;
    }

    public Encadrant getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(Encadrant encadrant) {
        this.encadrant = encadrant;
    }


    public List<Groupe> getGroupe() {
        return groupe;
    }

    public void setGroupe(List<Groupe> groupe) {
        this.groupe = groupe;
    }

    public Sujet(String titre, String description, int nbetudiant) {
        super();
        this.titre = titre;
        this.description = description;
        this.nbetudiant = nbetudiant;
    }

    public Sujet() {
        super();
        // TODO Auto-generated constructor stub
    }


}
