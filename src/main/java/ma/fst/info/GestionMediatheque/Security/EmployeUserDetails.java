package ma.fst.info.GestionMediatheque.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ma.fst.info.GestionMediatheque.Models.Employe;

import java.util.Collection;
import java.util.Collections;

public class EmployeUserDetails implements UserDetails {

    // @Autowired
    private final Employe employe;

    public EmployeUserDetails(Employe employe) {
        this.employe = employe;
    }

    public Employe getEmploye() {
        return employe;
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
