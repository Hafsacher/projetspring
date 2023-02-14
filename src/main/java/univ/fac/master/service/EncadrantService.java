package univ.fac.master.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import univ.fac.master.entities.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import univ.fac.master.entities.Encadrant;
import univ.fac.master.entities.Etudiant;
import univ.fac.master.entities.Users;
import univ.fac.master.repositories.EncadrantRepository;
import univ.fac.master.repositories.EtudiantRepository;
import univ.fac.master.repositories.UsersRepository;

@Service
public class EncadrantService {

	@Autowired
    private EncadrantRepository er;
	@Autowired
    private UsersRepository ur;
    @Autowired
    private PasswordEncoder passwordEncoder;
     
    @Autowired
    private JavaMailSender mailSender;
 
     
    public Encadrant register(Encadrant encadrant) 
    	throws UnsupportedEncodingException, MessagingException {
    	String encodedPassword = passwordEncoder.encode(encadrant.getPassword());
    	encadrant.setPassword(encodedPassword);
    	     
    	String randomCode = RandomString.make(64);
    	encadrant.setVerificationCode(randomCode);
    	encadrant.setEnabled(false);
    	     
    	return er.save(encadrant);
    	     
    	}
     
    
     
   public void sendVerificationEmail(Users users, String siteURL) 
    		throws MessagingException, UnsupportedEncodingException {
	   String toAddress = users.getEmail();
       String fromAddress = "Your email address";
       String senderName = "Gestion PFE";
       String subject = "Please verify your registration";
       String content = "Dear [[name]],<br>"
               + "Please click the link below to verify your registration:<br>"
               + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
               + "Thank you,<br>"
               + "Your company name.";

       MimeMessage message = mailSender.createMimeMessage();
       MimeMessageHelper helper = new MimeMessageHelper(message);

       helper.setFrom(fromAddress, senderName);
       helper.setTo(toAddress);
       helper.setSubject(subject);

       Users u = getEncadrantByEmail(users.getEmail());

       content = content.replace("[[name]]", u.getPrenom() + " " + u.getNom());

       String verifyURL = siteURL + "/verify3/" + users.getVerificationCode();

       content = content.replace("[[URL]]", verifyURL);

       helper.setFrom("hafsacherik2000@gmail.com", senderName);
       helper.setTo(users.getEmail());
       helper.setSubject(subject);
       helper.setText(content, true);

       mailSender.send(message);
         
    }
    
    public boolean verify(String verificationCode) {
    	  Users users = ur.findByVerificationCode(verificationCode);

          if (users == null || users.isEnabled()) {
              return false;
          } else {
              users.setVerificationCode(null);
              users.setEnabled(true);
              ur.save(users);

              return true;
          }

    }
    
    public Encadrant getEncadrantByEmail(String email) {
		return er.findByEmail(email);
	}
}
