package ma.fst.info.GestionMediatheque.Controllers;

import ma.fst.info.GestionMediatheque.Models.Prets;
import ma.fst.info.GestionMediatheque.Service.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@Controller
@RequestMapping("/GestionPret")
public class PretController {
    @Autowired
    private PretService pretService;
    
    @Autowired
    private DocumentService documentService;
    
    @Autowired
    private UsagerService usagerService;
    
    @GetMapping("/gestPrets")
    public String showPrets(Model model) {
        // model.addAttribute("prets", pretService.getAllPrets());
        // model.addAttribute("documents", documentService.getAllDocuments());
        // model.addAttribute("usagers", usagerService.getAllUsagers());
        return "gestPrets";
    }
    
    @PostMapping("/addPret")
    public String addPret(@RequestParam("documentId") Long documentId,
                         @RequestParam("usagerId") Long usagerId,
                         @RequestParam("dateEmprunt") String dateEmprunt,
                         @RequestParam("dateRetour") String dateRetour) {
        Prets pret = new Prets();
        pret.setDocument(documentService.getDocument(documentId));
        pret.setUsager(usagerService.getUsager(usagerId));
        pret.setDateEmprunt(LocalDate.parse(dateEmprunt));
        pret.setDateRetour(LocalDate.parse(dateRetour));
        pretService.savePret(pret);
        return "redirect:/GestionPret/gestPrets";
    }
    
    @PostMapping("/modifyPret")
    public String modifyPret(@ModelAttribute Prets pret) {
        pretService.savePret(pret);
        return "redirect:/GestionPret/gestPrets";
    }
    
    @PostMapping("/deletePret")
    public String deletePret(@RequestParam Long id) {
        pretService.deletePret(id);
        return "redirect:/GestionPret/gestPrets";
    }
}