package ma.fst.info.GestionMediatheque.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.fst.info.GestionMediatheque.Repository.PretRepository;
import ma.fst.info.GestionMediatheque.Models.Prets;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class PretService {

    @Autowired
    private PretRepository pretRepository;

    public void addPret(Prets pret) {
        pretRepository.save(pret);
    }

    public void updatePret(Prets pret) {
        pretRepository.save(pret);
    }

    public Prets getPretById(Long id) {
        return pretRepository.findById(id).get();
    }

    public List<Prets> getPretsRetard() {
        // return pretRepository.getPretsEnRetard();
        return null;
    }

    public List<Prets> displayPret() {
        return pretRepository.findAll();
    }

    public void deletePret(Long id) {
        pretRepository.deleteById(id);
    }
}
