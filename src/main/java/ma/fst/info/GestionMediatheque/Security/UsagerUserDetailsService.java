package ma.fst.info.GestionMediatheque.Security;

import ma.fst.info.GestionMediatheque.Models.Usager;
import ma.fst.info.GestionMediatheque.Repository.UsagerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsagerUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsagerRepository usagerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usager usager = usagerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usager non trouvé avec l'email : " + email));
        
        return new UsagerUserDetails(usager);
    }
}