package ma.fst.info.GestionMediatheque.Service;

import ma.fst.info.GestionMediatheque.Models.*;
import ma.fst.info.GestionMediatheque.Repository.PretRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class PretService {
    private final PretRepository pretRepository;

    public PretService(PretRepository pretRepository) {
        this.pretRepository = pretRepository;
    }

    public Prets addPret(Prets pret) {
        return pretRepository.save(pret);
    }

    public Prets savePret(Prets pret) {
        return pretRepository.save(pret);
    }

    public List<Prets> getAllPrets() {
        return pretRepository.findAll();
    }

    public Prets getPretById(Long id) {
        return pretRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prêt non trouvé"));
    }

    public void deletePret(Long id) {
        pretRepository.deleteById(id);
    }
}