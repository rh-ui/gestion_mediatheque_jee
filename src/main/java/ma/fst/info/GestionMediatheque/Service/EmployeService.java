package ma.fst.info.GestionMediatheque.Service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;
import ma.fst.info.GestionMediatheque.Models.Document;
import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Models.Prets;
import ma.fst.info.GestionMediatheque.Repository.EmployeRepository;

@NoArgsConstructor
@Service
public class EmployeService {

    @Autowired
    private EmployeRepository employeRepository;
    
    @Autowired
    private PretService pretService;

    public void emprunterDocument(Long userId, Long documentId, Long employeId) {

        // il reste le test de la disponibilité du document et le test de l'existence de l'usager et de l'employé

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
        if(qtte > 1) {
            document.setQtte(qtte - 1);
        } else {
            document.setDisponible(false);
        }
        doc.updateDocument(document);

    }


    public void returnDocument(Long pretId) {

        // il reste le test de l'existence du pret
        Prets pret = pretService.getPretById(pretId);
        Document document = pret.getDocument();
        Long qtte = document.getQtte();
        document.setQtte(qtte + 1);
        document.setDisponible(true);
        DocumentService doc = new DocumentService();
        doc.updateDocument(document);
        pretService.deletePret(pretId);

    }

    private Employe getEmploye(Long employeId) {
        return employeRepository.findById(employeId).get();
    }


}
