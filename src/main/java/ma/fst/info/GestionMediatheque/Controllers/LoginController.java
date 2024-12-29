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
    public String login() {
        return "Login"; // this will return the login.html page
    }

    // @PostMapping("/loginForm")
    // public String login(@RequestParam("username") String email,
    //                     @RequestParam("password") String password,
    //                     Model model) {
    //     // Rechercher l'employé par email
    //     Employe employe = employeRepository.findByEmail(email).orElse(null);
    //                         // System.out.println("ok");
    //     if (employe != null && passwordEncoder.matches(password, employe.getPassword())) {
    //         // Si les informations sont valides, rediriger vers le profil
    //         model.addAttribute("employeP", employe);
    //         return "redirect:/RoutesEmploye/profile";
    //     }

    //     // Sinon, retourner à la page de connexion avec un message d'erreur
    //     model.addAttribute("error", "Email ou mot de passe invalide.");
    //     return "Login";
    // }

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