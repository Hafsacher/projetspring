package univ.fac.master.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Domaine {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	Long idD;
    String domaineName;
	String universite;
	String  etablissement;
     String roles="" ;
	public Long getIdD() {
		return idD;
	}
	public void setIdD(Long idD) {
		this.idD = idD;
	}
	public String getDomaineName() {
		return domaineName;
	}
	public void setDomaineName(String domaineName) {
		this.domaineName = domaineName;
	}
	public String getUniversite() {
		return universite;
	}
	public void setUniversite(String universite) {
		this.universite = universite;
	}
	public String getEtablissement() {
		return etablissement;
	}
	public void setEtablissement(String etablissement) {
		this.etablissement = etablissement;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
     
}
