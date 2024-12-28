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


    public void addUsager(Usager usager) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsager'");
    }
}
