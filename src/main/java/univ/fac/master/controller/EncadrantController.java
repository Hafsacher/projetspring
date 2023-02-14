package univ.fac.master.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.AuthenticationBean;
import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.repositories.EncadrantRepository;
import univ.fac.master.repositories.EtudiantRepository;
import univ.fac.master.service.EncadrantService;
import univ.fac.master.service.EtudiantService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class EncadrantController {

	@Autowired
	private EncadrantRepository er;
	
	@Autowired
	private EncadrantService es;
	
	
	
	
	@PostMapping("process_encadrant")
	public Encadrant processRegister(@RequestBody Encadrant encadrant, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		Encadrant e=es.register(encadrant);
		
		es.sendVerificationEmail(encadrant,"http://localhost:4200");

		
		return e;
	}
	@GetMapping("verify3/{code}")
	public String verifyUser2(@PathVariable String code) {
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
	@GetMapping("encadrant/{email}")
	public Encadrant getEncadrantByEmail(@PathVariable String email){
		return es.getEncadrantByEmail(email);
	}
	@GetMapping("/encadrants")
	public String listEncadrants(Model model) {
		List<Encadrant> listEncadrants = er.findAll();
		model.addAttribute("listEncadrants", listEncadrants);
		
		return "encadrants";
	}
	
}

