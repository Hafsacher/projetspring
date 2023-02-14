package univ.fac.master.entities;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@DiscriminatorValue("ENCADRANT")
public class Encadrant extends Users {

    @Column(name = "departement", length = 20)
    private String departement;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "encadrant")
    @JsonIgnore
    List<Sujet> sujet;


    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}
