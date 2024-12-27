package ma.fst.info.GestionMediatheque.Models;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@Entity
public class Usager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    
    @OneToMany(mappedBy = "usager")
    private List<Prets> prets;

}
