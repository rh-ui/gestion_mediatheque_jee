package ma.fst.info.GestionMediatheque.Models;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prets {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Document document;
    
    @ManyToOne
    private Usager usager;

    @ManyToOne
    private Employe employe;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
}
