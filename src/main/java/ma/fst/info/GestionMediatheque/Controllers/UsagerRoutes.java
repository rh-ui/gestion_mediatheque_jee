package ma.fst.info.GestionMediatheque.Controllers;


import ma.fst.info.GestionMediatheque.Models.Usager;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsagerRoutes {

    @GetMapping("/usager")
    public String indexUsager() {
        return "Usager";
    }

    @GetMapping("/DocumentsU")
    public String viewDocuments(Model model) {
        return "viewDocuments";
    }

    @GetMapping("/PretsU")
    public String viewPrets(Model model) {
        return "viewPrets";
    }

    @GetMapping("/profileUsager")
    public String profile(Model model) {
        Usager usager = new Usager();
        usager.setNom("Usager");
        usager.setPrenom("Usager");
        usager.setEmail(" @ ");

        model.addAttribute("usager", usager);
        return "ProfileUsager";
    }

}
