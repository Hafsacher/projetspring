package univ.fac.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.AuthenticationBean;
import univ.fac.master.entities.CustomEtudiantDetails;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Sujet;
import univ.fac.master.repositories.EtudiantRepository;
import univ.fac.master.service.EtudiantService;

@CrossOrigin(origins ="http://localhost:4200/")
@RestController

public class EtudiantController {

	@Autowired
	private EtudiantRepository er;
	
	@Autowired
	private EtudiantService es;
	
	
	@GetMapping("etudiant/{email}")
	public Etudiant getEtudiantByEmail(@PathVariable String email){
		return es.getEtudiantByEmail(email);
	}
	
	@PostMapping("process_register")
	public Etudiant processRegister(@RequestBody Etudiant etudiant, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
	
		
		Etudiant et =es.register(etudiant);
		es.sendVerificationEmail(etudiant,"http://localhost:4200");

		return  et;
	}
	@GetMapping("verify2/{code}")
	public String verifyUser1(@PathVariable String code) {
		System.out.println("zertt"+code);
	    if (es.verify(code)) {
	    	
	        return "<div class='container text-center'>"+
	       " <h3>Congratulations, your account has been verified.</h3>"+
	        "<a href='http://localhost:4200'>login for home</a>"+
	    "</div>";
	    } else {
	        return "<h1> Sorry, we could not verify account. It maybe already verified,"
	        		+ "        or verification code is incorrect "
	        		+ "<a href='http://localhost:4200/add'>reteurn pour repter inscription</a>";
	    }
	}
	


    
	
	@GetMapping("/etudiants")
	public String listEtudiants(Model model) {
		List<Etudiant> listEtudiants = er.findAll();
		model.addAttribute("listEtudiants", listEtudiants);
		
		return "etudiants";
	}
	///
	@GetMapping("etudiants/{idE}")
	public Optional<Etudiant> getEtudiantById(@PathVariable Long idE){
		return es.getEtudiantById(idE);
	}
	
	
}
