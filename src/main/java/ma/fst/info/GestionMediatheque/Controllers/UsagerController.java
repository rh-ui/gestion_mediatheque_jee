package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsagerController {
    @GetMapping("/EspaceUsager")
    public String employe(Model model) {
        return "Usager";
    }
}
