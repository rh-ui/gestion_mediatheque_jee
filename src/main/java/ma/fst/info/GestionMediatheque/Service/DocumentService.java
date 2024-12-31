package ma.fst.info.GestionMediatheque.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.fst.info.GestionMediatheque.Models.Document;
import ma.fst.info.GestionMediatheque.Repository.DocumentRepository;

@Service
@RequiredArgsConstructor
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public void addDocument(String titre, String auteur, Long qtte, Long prix) {

        Document document = new Document();
        document.setId(0L);
        document.setDisponible(true);
        document.setTitre(titre);
        document.setAuteur(auteur);
        document.setQtte(qtte);
        document.setPrix(prix);

        documentRepository.save(document);

    }

    public Document getDocument(Long documentId) {
        return documentRepository.findById(documentId).get();
    }

    public Long getQtte(Long documentId) {
        // return documentRepository.getQtte(documentId);
        return 0L;
    }
 
    public void updateDocument(Long id, String titre, String auteur, Long qtte, Long prix) {

        Document document = documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document not found with id: " + id));

        document.setTitre(titre);
        document.setAuteur(auteur);
        document.setQtte(qtte);
        document.setPrix(prix);
        documentRepository.save(document);
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }


    public void deleteDocument(Long id) {
        Document document = documentRepository.findById(id).orElseThrow(() -> new RuntimeException("Document not found with id: " + id));
        documentRepository.delete(document);
    }

}
