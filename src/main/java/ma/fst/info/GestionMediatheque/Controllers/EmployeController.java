package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/RoutesEmploye")
@Controller
public class EmployeController {
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
        return "Documents";
    }

    @GetMapping("/gestUsager")
    public String gestUsager(Model model) {
        return "GestUsager";
    }

    @GetMapping("/gestPrets")
    public String gestPrets(Model model) {
        return "GestPrets";
    }
}
