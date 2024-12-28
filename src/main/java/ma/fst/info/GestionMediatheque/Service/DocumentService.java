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

    public void addDocument(Document document) {
        documentRepository.save(document);
    }

    public Document getDocument(Long documentId) {
        return documentRepository.findById(documentId).get();
    }

    public Long getQtte(Long documentId) {
        // return documentRepository.getQtte(documentId);
        return 0L;
    }

    public void updateDocument(Document document) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateDocument'");
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

}
