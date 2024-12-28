package ma.fst.info.GestionMediatheque.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ma.fst.info.GestionMediatheque.Models.Document;
import ma.fst.info.GestionMediatheque.Service.DocumentService;


@RestController
public class DocumentController {
    
    @Autowired
    private DocumentService documentService;


    @GetMapping("/displayAllDocument")
    public ModelAndView displayAllDocument() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Documents");
        modelAndView.addObject("documents", documentService.getAllDocuments());
        return modelAndView;
    }

    @PostMapping("/addDocument")
    public ModelAndView addDocument(@RequestParam String titre, @RequestParam String auteur, @RequestParam Long qtte, @RequestParam Long prix) {
        Document document = new Document();
        document.setId(0L);
        document.setDisponible(true);
        document.setTitre(titre);
        document.setAuteur(auteur);
        document.setQtte(qtte);
        document.setPrix(prix);

        documentService.addDocument(document);

        return new ModelAndView("redirect:/RoutesEmploye/documentsE");

    }
}
