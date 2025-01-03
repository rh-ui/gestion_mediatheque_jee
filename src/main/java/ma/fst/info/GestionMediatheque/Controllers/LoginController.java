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
public class LoginController {

    @Autowired
    private EmployeService employeService;

    @GetMapping("/login")
    public String login(@RequestParam(value = "logout", required = false) String logout, 
                       Model model) {
        if (logout != null) {
            model.addAttribute("message", "Vous avez été déconnecté avec succès.");
        }
        return "Login";
    }
     
}