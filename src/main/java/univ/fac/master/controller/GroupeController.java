package univ.fac.master.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.Sujet;
import univ.fac.master.entities.TeamStatus;
import univ.fac.master.service.GroupeService;
import univ.fac.master.service.SujetService;

@RestController
@CrossOrigin(origins ="http://localhost:4200/")
public class GroupeController {
	
	@Autowired
	GroupeService gs;
	
	@GetMapping("groupe")
	public List<Groupe> getAllGroupes(){
		List<Groupe>  g =gs.getAllGroupes();
		return g;
	}
	@GetMapping("groupe/{id}")
	public Optional<Groupe> getGroupeById(@PathVariable Long id){
		return gs.getGroupeById(id);
	}
	@PostMapping("groupe/{idS}")
	public Groupe addGroupe(@RequestBody Groupe g , @PathVariable Long idS ){
	
		return gs.addGroupe(g,idS);
	}
	@DeleteMapping("groupe/{id}")
	public void deleteGroupe(@PathVariable Long id){
		gs.deleteGroupe(id);
	}
	@PutMapping("groupes/{idS}")
	public Groupe updateGroupe(@RequestBody Groupe g , @PathVariable Long idS){
		return gs.updateGroupe(g,idS);
	}
	@PutMapping("groupes1/{idS}")
	public Groupe updateGroupe1(@RequestBody Groupe g , @PathVariable Long idS){
		return gs.updateGroupe1(g,idS);
	}
	@PutMapping("groupes2/{idS}")
	public Groupe updateGroupe2(@RequestBody Groupe g , @PathVariable Long idS){
		return gs.updateGroupe2(g,idS);
	}
	@GetMapping("groupe/{nomGroupe}/nom")
	public Optional<Groupe> getGroupeByNomGroupe(@PathVariable String nomGroupe){
		return gs.getGroupeByNomGroupe(nomGroupe);
	}	
	
	@GetMapping("groupe/{idS}/sujet/{statut}/statut")
	public List<Groupe> getGroupeBySujetAndStatut(@PathVariable Long idS , @PathVariable TeamStatus statut){
		return gs.getGroupeBySujetAndStatut(idS, statut);
	}
}
