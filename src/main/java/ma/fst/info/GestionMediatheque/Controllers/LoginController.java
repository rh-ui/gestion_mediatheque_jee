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
import ma.fst.info.GestionMediatheque.Repository.EmployeRepository;
import ma.fst.info.GestionMediatheque.Service.DocumentService;

@Controller
public class LoginController {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String logout, 
                       Model model) {
        if (logout != null) {
            model.addAttribute("message", "Vous avez été déconnecté avec succès.");
        }
        return "Login";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "Register";
    }

    @PostMapping("/register")
    public String registerEmploye(@ModelAttribute Employe employe, Model model) {
        try {
            // Vérifier si l'email existe déjà
            if (employeRepository.findByEmail(employe.getEmail()).isPresent()) {
                model.addAttribute("error", "Cet email est déjà utilisé");
                return "Register";
            }

            // Encoder le mot de passe
            employe.setPassword(passwordEncoder.encode(employe.getPassword()));
            
            // Sauvegarder l'employé
            employeRepository.save(employe);
            
            model.addAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            return "Login";
            
        } catch (Exception e) {
            model.addAttribute("error", "Une erreur est survenue lors de l'inscription");
            return "Register";
        }
    }
}