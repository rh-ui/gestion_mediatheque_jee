package ma.fst.info.GestionMediatheque.Security;

import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Repository.EmployeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeRepository employeRepository;
    

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Rechercher l'employé par email
        Employe employe = employeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Employé non trouvé avec l'email : " + email));

        return new EmployeUserDetails(employe);
    }

 
}
