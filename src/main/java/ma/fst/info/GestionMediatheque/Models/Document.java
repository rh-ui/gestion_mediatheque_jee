package ma.fst.info.GestionMediatheque.Models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
    private boolean disponible;

    @OneToMany(mappedBy = "document")
    private List<Prets> prets;
}
