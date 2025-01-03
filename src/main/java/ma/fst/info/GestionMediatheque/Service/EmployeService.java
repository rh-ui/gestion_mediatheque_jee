package ma.fst.info.GestionMediatheque.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.*;
import ma.fst.info.GestionMediatheque.Models.Document;
import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Models.Prets;
import ma.fst.info.GestionMediatheque.Repository.EmployeRepository;


@Service
@RequiredArgsConstructor
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private PretService pretService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void emprunterDocument(Long userId, Long documentId, Long employeId) {

        // il reste le test de la disponibilité du document et le test de l'existence de
        // l'usager et de l'employé

        UsagerService us = new UsagerService();
        DocumentService doc = new DocumentService();
        EmployeService emp = new EmployeService();

        Prets pret = new Prets();

        pret.setUsager(us.getUsager(userId));
        pret.setDocument(doc.getDocument(documentId));
        pret.setEmploye(emp.getEmploye(employeId));
        pret.setDateRetour(LocalDate.now().plusDays(15)); // 15 jours bach yreje3 les prets li khda

        pretService.addPret(pret);

        // decrementer la quantite du document :

        Document document = doc.getDocument(documentId);
        Long qtte = document.getQtte();
        if (qtte > 1) {
            document.setQtte(qtte - 1);
        } else {
            document.setDisponible(false);
        }
        doc.updateDocument(document.getId(), document.getTitre(), document.getAuteur(), document.getQtte(),
                document.getPrix());

    }

    public void returnDocument(Long pretId) {

        // il reste le test de l'existence du pret

        Prets pret = pretService.getPretById(pretId);
        Document document = pret.getDocument();

        document.setQtte(document.getQtte() + 1);
        document.setDisponible(true);

        DocumentService doc = new DocumentService();
        
        doc.updateDocument(document.getId(), document.getTitre(), document.getAuteur(), document.getQtte(),
                document.getPrix());
        pretService.deletePret(pretId);

    }

    private Employe getEmploye(Long employeId) {
        return employeRepository.findById(employeId).get();
    }
    
    public Employe updateEmploye(Long id, String nom, String prenom, String email,
                                     Long telephone, String poste) {

        Employe employe = employeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employe not found with id: " + id));
  
        employe.setNom(nom);
        employe.setPrenom(prenom);
        employe.setEmail(email);
        employe.setTelephone(telephone);
        employe.setPoste(poste);
        // employe.setPassword(passwordEncoder.encode(password));
        

        employeRepository.save(employe);

        return employe;
    }

    public boolean addEmploye(@ModelAttribute Employe employe) {

        try {
            if (employeRepository.findByEmail(employe.getEmail()).isPresent()) {
                return false;
            }
    
            employe.setPassword(passwordEncoder.encode(employe.getPassword()));
            
            employeRepository.save(employe);

            return true;
            
        } catch (Exception e) {
            return true;
        }

    }
    

    

        

}
