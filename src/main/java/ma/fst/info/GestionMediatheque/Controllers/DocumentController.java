package ma.fst.info.GestionMediatheque.Controllers;


import org.hibernate.annotations.OptimisticLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ma.fst.info.GestionMediatheque.Models.Document;
import ma.fst.info.GestionMediatheque.Service.DocumentService;

@RequestMapping("/GestionDocument")
@RestController
public class DocumentController {
    
    @Autowired
    private DocumentService documentService;

    @PostMapping("/addDocument")
    public ModelAndView addDocument(@RequestParam String titre, @RequestParam String auteur, @RequestParam Long qtte, @RequestParam Long prix) {
        
        documentService.addDocument(titre, auteur, qtte, prix);

        return new ModelAndView("redirect:/RoutesEmploye/documentsE");

    }

    @PostMapping("/modifyDocument")
    public ModelAndView modifyDocument(@RequestParam Long id, @RequestParam String titre, @RequestParam String auteur, @RequestParam Long qtte, @RequestParam Long prix) {

        documentService.updateDocument(id, titre, auteur, qtte, prix);

        return new ModelAndView("redirect:/RoutesEmploye/documentsE");

    }

    @PostMapping("/deleteDocument")
    public ModelAndView deleteDocument(@RequestParam Long id) {

        documentService.deleteDocument(id);

        return new ModelAndView("redirect:/RoutesEmploye/documentsE");

    }
    
}
