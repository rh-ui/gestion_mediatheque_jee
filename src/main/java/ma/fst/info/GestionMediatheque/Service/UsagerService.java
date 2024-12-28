package ma.fst.info.GestionMediatheque.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fst.info.GestionMediatheque.Models.Usager;
import ma.fst.info.GestionMediatheque.Repository.UsagerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsagerService {

    @Autowired
    private UsagerRepository usagerRepository;


    public void addUsager(String nomUsager, String prenomUsager, String emailUsager) {

        Usager usager = new Usager();
        usager.setNom(nomUsager);
        usager.setPrenom(prenomUsager);
        usager.setEmail(emailUsager);

        usagerRepository.save(usager);
    }

    public void updateUsager(Usager usager) {
        usagerRepository.save(usager);
    }

    public void deleteUsager(Long id) {
        usagerRepository.deleteById(id);
    }
    
    public List<Usager> displayUsager() {
        return usagerRepository.findAll();
    }

    public Usager getUsager(Long userId) {
        
        return usagerRepository.findById(userId).get();
    }

    public List<Usager> getAllUsagers(){
        return usagerRepository.findAll();
    }

    public void updateUsager(Long id, String nomUsager, String prenomUsager, String emailUsager) {
        
        Usager usager = usagerRepository.findById(id).get();
        usager.setNom(nomUsager);
        usager.setPrenom(prenomUsager);
        usager.setEmail(emailUsager);
        usagerRepository.save(usager);
    }
}
