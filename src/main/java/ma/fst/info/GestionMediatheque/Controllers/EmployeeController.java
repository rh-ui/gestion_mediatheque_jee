package ma.fst.info.GestionMediatheque.Controllers;


import org.hibernate.annotations.OptimisticLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Security.EmployeUserDetails;
import ma.fst.info.GestionMediatheque.Service.EmployeService;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequestMapping("/GestionEmployee")
@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeService employeService;

    @PostMapping("/modifyEmploye")
    public ModelAndView modifyEmploye(@RequestParam Long id, @RequestParam String nom,
            @RequestParam String prenom, @RequestParam String email,
            @RequestParam Long telephone, @RequestParam String poste) {
        
        // Mettre à jour l'employé
        Employe updatedEmploye = employeService.updateEmploye(id, nom, prenom, email, telephone, poste);
        
        // Mettre à jour l'authentification
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        EmployeUserDetails newPrincipal = new EmployeUserDetails(updatedEmploye);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
            newPrincipal, 
            auth.getCredentials(),
            auth.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        
        return new ModelAndView("redirect:/RoutesEmploye/profile");
    }

}
