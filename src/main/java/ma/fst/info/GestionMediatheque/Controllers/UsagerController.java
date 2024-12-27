package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsagerController {
    @GetMapping("/EspaceUsager")
    public String usager(Model model) {
        return "Usager";
    }

    @GetMapping("/ConsulterDocuments")
    public String viewDocuments(Model model) {
        return "viewDocuments";
    }


    @GetMapping("/ConsulterPretsU")
    public String viewPrets(Model model) {
        return "viewPrets";
    }
}
