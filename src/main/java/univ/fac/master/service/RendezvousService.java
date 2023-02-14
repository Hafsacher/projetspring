package univ.fac.master.service;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.Rendezvous;
import univ.fac.master.repositories.EtudiantRepository;
import univ.fac.master.repositories.RendezvousRepository;

@Service
@Primary
public class RendezvousService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RendezvousRepository rr;
    @Autowired
    private EtudiantRepository er;

    public void demanderendezvous(Rendezvous rendezvous, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = rendezvous.getGroupe().getSujet().getEncadrant().getEmail();
        String fromAddress = "hafsacherik2000@gmail.com";
        String senderName = " PFE";
        String subject = "Demande rendez-vous";
        String content = " [[name]],<br>" +
                "Le groupe [[groupe]] demander un rendez-vous,<br> "
                + "return a votre espace pour conirmer :<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">return</a></h3>"
                + "Merci,<br>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", rendezvous.getGroupe().getSujet().getEncadrant().getPrenom() + " " + rendezvous.getGroupe().getSujet().getEncadrant().getNom());
        content = content.replace("[[groupe]]", rendezvous.getGroupe().getNomGroupe());
        String verifyURL = siteURL + "login";

        content = content.replace("[[URL]]", verifyURL);

        helper.setFrom("hafsacherik2000@gmail.com", senderName);
        helper.setTo(rendezvous.getGroupe().getSujet().getEncadrant().getEmail());
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);

    }

    public List<Rendezvous> getRendezvousByEncadrant(Long supervisorId) {
        return rr.getMeetingsBySupervisor(supervisorId);
    }
	


    public void updaterendezvous(Rendezvous rendezvous, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        Groupe grp = rendezvous.getGroupe();
        List<Etudiant> etudiant = er.findByGroupe(grp);
        System.out.println(etudiant.size());
        for (Etudiant value : etudiant) {
            String email = value.getEmail();
            System.out.println(email);
            String fromAddress = "hafsacherik2000@gmail.com";
            String senderName = " PFE";
            String subject = "Rendez-vous";
            String content = " [[name]],<br>" +
                    " Encadrant Pr. [[encadrant]]  un rendez-vous le [[date]] à [[time]],<br> "
                    + "Etes-vous disponible  :<br>"
                    + "<h3><a href=\"[[Oui]]\" target=\"_self\">Oui</a>&nbsp;<a href=\"[[Non]]\" target=\"_self\">Non</a></h3>"
                    + "Merci,<br>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(fromAddress, senderName);
            helper.setTo(email);
            helper.setSubject(subject);
            System.out.println("email" + email);
            content = content.replace("[[name]]", value.getPrenom() + " " + value.getNom());
            content = content.replace("[[encadrant]]", rendezvous.getGroupe().getSujet().getEncadrant().getPrenom() + " " + rendezvous.getGroupe().getSujet().getEncadrant().getNom());
            String date = convertDateToString(rendezvous.getDate());
            String time = convertTimeToString(rendezvous.getHeure());
            content = content.replace("[[date]]", date);
            content = content.replace("[[time]]", time);
            String ouiURL = siteURL + "login";

            content = content.replace("[[Oui]]", ouiURL);

            helper.setFrom("hafsacherik2000@gmail.com", senderName);
            helper.setTo(value.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);
        }


    }

    public String convertDateToString(Date dt) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(dt);
    }

    public String convertTimeToString(Time dt) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(dt);
    }


}
