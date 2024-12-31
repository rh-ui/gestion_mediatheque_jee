package ma.fst.info.GestionMediatheque.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import ma.fst.info.GestionMediatheque.Service.UsagerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import ma.fst.info.GestionMediatheque.Models.Usager;
import org.springframework.web.server.ResponseStatusException;
// iText imports
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

// Java I/O imports
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/GestionUsager")
@RestController
public class UsagerController {

    @Autowired
    private UsagerService usagerService;

    @GetMapping("/EspaceUsager")
    public String usager(Model model) {
        return "Usager";
    }

    @GetMapping("/ConsulterDocuments")
    public String viewDocuments(Model model) {
        return "viewDocuments";
    }


    @GetMapping("/ConsulterPretsU")
    public String viewPrets(Model model) {
        return "viewPrets";
    }

    @PostMapping("/addUsager")
    public ModelAndView addUsager(@RequestParam String nomUsager, @RequestParam String prenomUsager, @RequestParam String emailUsager) {
        usagerService.addUsager(nomUsager, prenomUsager, emailUsager);
        return new ModelAndView("redirect:/RoutesEmploye/gestUsager");
    }

    @PostMapping("/updateUsager")
    public ModelAndView updateUsager(@RequestParam Long id, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email) {
        usagerService.updateUsager(id, nom, prenom, email);
        return new ModelAndView("redirect:/RoutesEmploye/gestUsager");
    }

    @PostMapping("/deleteUsager")
    public ModelAndView deleteUsager(@RequestParam Long id) {
        usagerService.deleteUsager(id);
        return new ModelAndView("redirect:/RoutesEmploye/gestUsager");
    }
    @GetMapping("/get/{id}") 
    public Usager getUsager(@PathVariable Long id) {
        return usagerService.getUsagerById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usager non trouvé"));
    }
    //Pour le pdf de lecture
    @GetMapping("/ficheLecture/{id}")
    public ResponseEntity<byte[]> getFicheLecture(@PathVariable Long id) {
        try {
            //Récupérer l'usager
            Usager usager = usagerService.getUsagerById(id).orElseThrow(() -> new RuntimeException("Usager non trouvé")); 

            //On va générer le PDF
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, baos);

            document.open();
            
            //En-tête du pdf
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph("Fiche de Lecture - " + usager.getNom() + " " + usager.getPrenom(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20f);
            document.add(title);
            
            //Infos d'usager
            document.add(new Paragraph("Informations personnelles:", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD)));
            document.add(new Paragraph("Nom: " + usager.getNom()));
            document.add(new Paragraph("Prénom: " + usager.getPrenom()));
            document.add(new Paragraph("Email: " + usager.getEmail()));
            document.add(new Paragraph("\n"));
            
            document.close();

            // Configuration de la réponse HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "fiche_lecture_" + usager.getNom() + ".pdf");

            return ResponseEntity
                .ok()
                .headers(headers)
                .body(baos.toByteArray());
            
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); 
        }
    }
}
