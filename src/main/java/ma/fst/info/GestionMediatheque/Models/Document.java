package ma.fst.info.GestionMediatheque.Models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String auteur;
    private boolean disponible;
    private Long qtte;
    private Long prix;

    @OneToMany(mappedBy = "document")
    private List<Prets> prets;
}
