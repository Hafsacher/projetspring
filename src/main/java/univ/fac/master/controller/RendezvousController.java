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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.MeetingStatus;
import univ.fac.master.entities.Rendezvous;
import univ.fac.master.repositories.GroupeRepository;
import univ.fac.master.repositories.RendezvousRepository;
import univ.fac.master.service.RendezvousService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class RendezvousController {
	@Autowired
	private RendezvousRepository rr;
	@Autowired
	private GroupeRepository gr;
	@Autowired
	private RendezvousService rs;

	@PostMapping("AjouterRendezvous/{idG}")
	public Rendezvous AjouterRendezvous(@RequestBody Rendezvous rendezvous, HttpServletRequest request,
			@PathVariable Long idG) throws UnsupportedEncodingException, MessagingException {
		Groupe g = gr.findById(idG).get();
		rendezvous.setGroupe(g);
		rs.demanderendezvous(rendezvous, "http://localhost:4200/");
		Rendezvous re = rr.save(rendezvous);
		return re;
	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	@GetMapping("rendezvous/{idE}")
	public List<Rendezvous> getRendezvousByEncadrant(@PathVariable Long idE) {

		return rs.getRendezvousByEncadrant(idE);
	}

	@PutMapping("rendezvous/{meetingId}")
	public Rendezvous updateRendezvous(@PathVariable Long meetingId, @RequestBody Rendezvous rdv)
			throws UnsupportedEncodingException, MessagingException {
		return rr.findById(meetingId).map(meeting -> {
			meeting.setStatut(MeetingStatus.ACCEPTED);
			meeting.setDate(rdv.getDate());
			meeting.setHeure(rdv.getHeure());

			rr.save(meeting);

			try {
				this.rs.updaterendezvous(meeting, "http://localhost:4200/");
			} catch (MessagingException | UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}

			return rr.save(meeting);
		}).orElse(null);
	}
}