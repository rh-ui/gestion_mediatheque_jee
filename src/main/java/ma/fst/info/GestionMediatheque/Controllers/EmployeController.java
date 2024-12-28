package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ma.fst.info.GestionMediatheque.Service.DocumentService;
import ma.fst.info.GestionMediatheque.Service.UsagerService;

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
}
