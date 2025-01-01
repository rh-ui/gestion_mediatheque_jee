package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Service.DocumentService;
import ma.fst.info.GestionMediatheque.Service.EmployeService;

@Controller
public class RegisterController {

    @Autowired
    private EmployeService employeService;
    

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "Register";
    }

    @PostMapping("/register")
    public String registerEmploye(@ModelAttribute Employe employe, Model model) {
        try {
            if (!employeService.addEmploye(employe)) {
                model.addAttribute("error", "Cet email est déjà utilisé");
                return "Register";
            }
            
            model.addAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            return "Login";
            
        } catch (Exception e) {
            model.addAttribute("error", "Une erreur est survenue lors de l'inscription");
            return "Register";
        }
    }

     
}