package univ.fac.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.Domaine;
import univ.fac.master.repositories.DomaineRepository;

@CrossOrigin(origins ="http://localhost:4200/")
@RestController
public class DomaineController {
	@Autowired
	private DomaineRepository er;
	
	
	
	
	@GetMapping("domaines")
	public List<Domaine> getDomaine(){
		return er.findAll();
	}
	
	@PostMapping("Adddomaines")
	public Domaine ajouter(@RequestBody Domaine domaine, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
	
		
		Domaine et =er.save(domaine);

		return  et;
	}
}
