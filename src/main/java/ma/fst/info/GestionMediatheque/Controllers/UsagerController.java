package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ma.fst.info.GestionMediatheque.Service.UsagerService;

import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/GestionUsager")
@Controller
public class UsagerController {

    @Autowired
    private UsagerService usagerService;

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

    @PostMapping("/addUsager")
    public ModelAndView addUsager(@RequestParam String nomUsager, @RequestParam String prenomUsager, @RequestParam String emailUsager) {
        usagerService.addUsager(nomUsager, prenomUsager, emailUsager);
        return new ModelAndView("redirect:/RoutesEmploye/gestUsager");
    }

    @PostMapping("/updateUsager")
    public ModelAndView updateUsager(@RequestParam Long id, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email) {
        usagerService.updateUsager(id, nom, prenom, email);
        return new ModelAndView("redirect:/RoutesEmploye/gestUsager");
    }

    @PostMapping("/deleteUsager")
    public ModelAndView deleteUsager(@RequestParam Long id) {
        usagerService.deleteUsager(id);
        return new ModelAndView("redirect:/RoutesEmploye/gestUsager");
    }
    


}
