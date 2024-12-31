package ma.fst.info.GestionMediatheque.Controllers;


import ma.fst.info.GestionMediatheque.Models.Usager;
import ma.fst.info.GestionMediatheque.Service.DocumentService;
import ma.fst.info.GestionMediatheque.Service.PretService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsagerRoutes {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PretService pretService;

    @GetMapping("/usager")
    public String indexUsager() {
        return "Usager";
    }

    @GetMapping("/DocumentsU")
    public String viewDocuments(Model model) {
        model.addAttribute("documents", documentService.getAllDocuments());
        return "viewDocuments";
    }

    @GetMapping("/PretsU")
    public String viewPrets(Model model) {
        model.addAttribute("prets", pretService.getPretByUsagerId(2L));
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
