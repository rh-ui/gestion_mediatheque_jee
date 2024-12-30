package ma.fst.info.GestionMediatheque.Models;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id") 
    private Document document;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usager_id") 
    private Usager usager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employe_id") 
    private Employe employe;

    private LocalDate dateEmprunt;
    private LocalDate dateRetour;
}
