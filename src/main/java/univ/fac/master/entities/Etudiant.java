package univ.fac.master.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("ETUDIANT")
public class Etudiant extends Users {

    @Column(name = "code_apogee", length = 20)
    private String codeApogee;

    @Column(name = "filiere", length = 20)
    private String filiere;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "etudiant")
    @JsonIgnore
    List<GroupeEtudiant> groupeEtudiant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupe_id")
    //@JsonBackReference
    Groupe groupe;

    public String getCodeApogee() {
        return codeApogee;
    }

    public void setCodeApogee(String codeApogee) {
        this.codeApogee = codeApogee;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }


    public List<GroupeEtudiant> getGroupeEtudiant() {
        return groupeEtudiant;
    }


    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public void setGroupeEtudiant(List<GroupeEtudiant> groupeEtudiant) {
        this.groupeEtudiant = groupeEtudiant;
    }

    public Etudiant(String nom, String prenom, String codeApogee, String filiere, String email, String password) {
        super(nom, prenom, email, password);

        this.codeApogee = codeApogee;
        this.filiere = filiere;
    }

    public Etudiant() {
        super();
        // TODO Auto-generated constructor stub
    }


}
