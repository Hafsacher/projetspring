package univ.fac.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import univ.fac.master.entities.Groupe;
import univ.fac.master.entities.Sujet;
import univ.fac.master.entities.TeamStatus;
import univ.fac.master.repositories.GroupeRepository;
import univ.fac.master.repositories.SujetRepository;

@Service
public class GroupeService {

    @Autowired
    SujetRepository sr;

    @Autowired
    GroupeRepository gr;


    public Groupe addGroupe(Groupe g, Long idS) {
        return sr.findById(idS).map(s -> {
            g.setSujet(s);
            return gr.save(g);
        }).orElse(null);
    }

    public List<Groupe> getAllGroupes() {
        return gr.findAll();
    }

    public Optional<Groupe> getGroupeById(Long id) {
        return gr.findById(id);
    }


    public void deleteGroupe(Long id) {
        gr.deleteById(id);

    }

    public Groupe updateGroupe(Groupe g, Long idS) {
        return sr.findById(idS).map(s -> {
            g.setSujet(s);
            int nbG = g.getNbetudiant();
            int nbS = s.getNbetudiant();
            if (nbG == nbS) {
                g.setStatut(TeamStatus.COMPLETED);
            }
            return gr.save(g);
        }).orElse(null);
    }

    public Groupe updateGroupe1(Groupe g, Long idS) {
        return sr.findById(idS).map(s -> {
            g.setSujet(s);
            g.setStatut(TeamStatus.ACCEPTED);
        
            return gr.save(g);
        }).orElse(null);
    }
    public Groupe updateGroupe2(Groupe g, Long idS) {
        return sr.findById(idS).map(s -> {
            g.setSujet(s);
            g.setStatut(TeamStatus.REJECTED);
        
            return gr.save(g);
        }).orElse(null);
    }
    public Optional<Groupe> getGroupeByNomGroupe(String nomGroupe) {
        return gr.findByNomGroupe(nomGroupe);
    }

    public List<Groupe> getGroupeBySujetAndStatut(Long idS, TeamStatus statut) {
        Sujet s = new Sujet();
        s.setId(idS);
        return gr.findBySujetAndStatut(s, statut);
    }

}
