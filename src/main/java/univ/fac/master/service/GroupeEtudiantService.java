package univ.fac.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.GroupeEtudiant;
import univ.fac.master.entities.Sujet;
import univ.fac.master.repositories.EtudiantRepository;
import univ.fac.master.repositories.GroupeEtudiantRepository;
import univ.fac.master.repositories.GroupeRepository;

@Service
public class GroupeEtudiantService {

	@Autowired
	GroupeEtudiantRepository ger;
	
	@Autowired
	EtudiantRepository er;
	@Autowired
	GroupeRepository gr;
	
	
	public GroupeEtudiant addGroupeEtudiant(GroupeEtudiant ge, Long idE, Long idG) {
		Etudiant e =er.findById(idE).get();
		ge.setEtudiant(e);
		Groupe g =gr.findById(idG).get();
		ge.setGroupe(g);
		System.out.println("ID: " + ge.getId());
		System.out.println("Student: " + ge.getEtudiant().getId());
		System.out.println("Group: " + ge.getGroupe().getId());
		return ger.save(ge);
	}


	public List<GroupeEtudiant> getGroupeById(Long idG) {
		Groupe g = new Groupe();
		g.setId(idG);
		return ger.findByGroupe(g);
	}
	
	public List<GroupeEtudiant> getGroupeByEtudiant(Long idE) {
		Etudiant e = new Etudiant();
		e.setId(idE);
		return ger.findByEtudiant(e);
	}
	
	
	public void deleteEtudiants(Long idG) {
		Groupe g = new Groupe();
		g.setId(idG);
		int ge=ger.findByGroupe(g).size();
		System.out.print(ge);
		int i=0;
		while(!ger.findByGroupe(g).isEmpty()){
			Long id=ger.findByGroupe(g).get(i).getEtudiant().getId();
			String email=ger.findByGroupe(g).get(i).getEtudiant().getEmail();
			Optional<Etudiant> e = er.findById(id);	
	       
			Etudiant etu = er.findByEmail(email);
	        etu.setGroupe(g);
	        er.save(etu);
	        
			//ger.deleteGEByEtudiant(e);
			
		}
		
	}
	
}
