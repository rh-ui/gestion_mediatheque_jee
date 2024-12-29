package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Security.EmployeUserDetails;
import ma.fst.info.GestionMediatheque.Service.DocumentService;
import ma.fst.info.GestionMediatheque.Service.UsagerService;
import org.springframework.security.core.Authentication;


@RequestMapping("/RoutesEmploye")
@Controller
public class EmployeController {
    
    @Autowired
    DocumentService documentService;

    @Autowired
    UsagerService usagerService;


    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/EspaceEmploye")
    public String employe(Model model) {
        return "Employe";
    }

    @GetMapping("/documentsE")
    public String gestionDocuments(Model model) {
        
        
        model.addAttribute("documents", documentService.getAllDocuments());
        return "Documents";
    }
    
    @GetMapping("/gestUsager")
    public String gestUsager(Model model) {
        model.addAttribute("usagers", usagerService.getAllUsagers());
        return "GestUsager";
    }

    @GetMapping("/gestPrets")
    public String gestPrets(Model model) {
        return "GestPrets";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        // Récupérer l'authentification actuelle
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EmployeUserDetails employeDetails = (EmployeUserDetails) authentication.getPrincipal();

        // Obtenir l'employé connecté
        Employe employe = employeDetails.getEmploye();

        // Ajouter l'employé au modèle
        model.addAttribute("employeP", employe);

        return "Profile"; // Retourne la page de profil (profile.html)
    }
}
