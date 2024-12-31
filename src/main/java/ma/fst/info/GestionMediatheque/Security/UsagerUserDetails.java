
package ma.fst.info.GestionMediatheque.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ma.fst.info.GestionMediatheque.Models.Usager;
import ma.fst.info.GestionMediatheque.Repository.UsagerRepository;

import java.util.Collection;
import java.util.Collections;

public class UsagerUserDetails implements UserDetails {
    private Usager usager;
    private UsagerRepository usagerRepository;
    private Long usagerId;

    public UsagerUserDetails(Usager usager) {
        this.usager = usager;
        this.usagerId = usager.getId();
    }

    public Usager getUsager() {
        if (usagerRepository != null) {
            return usagerRepository.findById(usagerId)
                    .orElse(this.usager);
        }
        return this.usager;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return usager.getPassword();
    }

    @Override
    public String getUsername() {
        return usager.getEmail();
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