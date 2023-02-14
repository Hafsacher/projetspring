package univ.fac.master.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import univ.fac.master.entities.Rendezvous;
import univ.fac.master.repositories.RendezvousRepository;

@Service
@EnableAsync
public class EmailNotifierService {
    @Autowired
    private RendezvousRepository repo;

    @Autowired
    private JavaMailSender mailSender;

    @Async
    @Scheduled(cron = "0 0 16 * * *")
    public void sendNotification() {
        // Load all users from database
        // For each user, send an email
        // List<Users> users = userRepo.findAll();
        List<Rendezvous> meetings = repo.getUnrealizedMeetings();

        for (Rendezvous meeting : meetings) {
            meeting.getGroupe().getEtudiant().forEach(e -> {
                String email = e.getEmail();

                sendEmail(
                        email,
                        "Reminder",
                        "Hello " + e.getPrenom() + " " + e.getNom() + ",\r"
                                + "You have a meeting with your supervisor on " + meeting.getDate() + " at "
                                + meeting.getHeure() + ".\r");
            });
        }
    }

    public boolean sendEmail(String to, String subject, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailMessage.setFrom("hafsacherik2000@gmail.com");

        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
