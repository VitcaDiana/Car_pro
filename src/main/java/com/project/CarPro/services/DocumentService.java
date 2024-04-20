package com.project.CarPro.services;

import com.project.CarPro.model.Documents;
import com.project.CarPro.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    DocumentRepository documentRepository;
@Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Documents> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Documents> getDocumentById(Long id) {
        return documentRepository.findById(id);
    }
    public Documents saveDocument(Documents document) {
        return documentRepository.save(document);
    }
    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
