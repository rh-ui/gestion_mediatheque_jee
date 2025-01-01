package ma.fst.info.GestionMediatheque.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import ma.fst.info.GestionMediatheque.Models.Employe;
import ma.fst.info.GestionMediatheque.Repository.EmployeRepository;

import java.util.Collection;
import java.util.Collections;


public class EmployeUserDetails implements UserDetails {

    private  Employe employe;
  
    private EmployeRepository employeRepository;
    
    private Long employeId; 

    public EmployeUserDetails(Employe employe) {
        this.employe = employe;
        this.employeId = employe.getId();
    }

    public Employe getEmploye() {
        // Récupérer l'employé à jour depuis la base de données
        if (employeRepository != null) {
            return employeRepository.findById(employeId)
                .orElse(this.employe);
        }
        return this.employe;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Si vous utilisez des rôles, vous pouvez les retourner ici
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return employe.getPassword();
    }

    @Override
    public String getUsername() {
        return employe.getEmail(); // Utiliser l'email comme nom d'utilisateur
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
