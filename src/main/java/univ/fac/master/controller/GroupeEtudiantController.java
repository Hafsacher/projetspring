package univ.fac.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.GroupeEtudiant;
import univ.fac.master.service.GroupeEtudiantService;

@RestController
@CrossOrigin(origins ="http://localhost:4200/")
public class GroupeEtudiantController {

	@Autowired
	GroupeEtudiantService gs;
	
	@RequestMapping("groupeetudiant/{idE}/groupe/{idG}")
	public GroupeEtudiant addGroupeEtudiant(@RequestBody GroupeEtudiant ge , @PathVariable Long idE, @PathVariable Long idG ){
	    System.out.print(idG);
		return gs.addGroupeEtudiant(ge,idE,idG);
	}
	
	/*@GetMapping("groupeetudiant/{idG}/etudiant")
	public Etudiant GroupeEtudiantEtudiant(@PathVariable Long idG){
		Etudiant e=gs.getGroupeEtudiantById(idG).get().getEtudiant();
	
			return  gs.getGroupeEtudiantById(idG).get().getEtudiant();
	}*/
	
	@GetMapping("etudiant/{idG}/groupe")
	public List<GroupeEtudiant> getEtudiantByGroupe (@PathVariable Long idG){
		
		return gs.getGroupeById(idG);
	}
	
	@GetMapping("groupes/{idE}/etudiant")
	public List<GroupeEtudiant> getGroupeByEtudiant (@PathVariable Long idE){
		
		return gs.getGroupeByEtudiant(idE);
	}
	
	
	//delete
	@DeleteMapping("groupe/{idG}/etudiants")
	public void deleteEtudiant(@PathVariable Long idG){
		gs.deleteEtudiants(idG);
	}

}
