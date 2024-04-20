package com.project.CarPro.controllers;

import com.project.CarPro.model.Documents;
import com.project.CarPro.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentsController {

    DocumentService documentService;
@Autowired
    public DocumentsController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public ResponseEntity<List<Documents>> getAllDocuments() {
        return ResponseEntity.ok(documentService.getAllDocuments());
    }

    @PostMapping
    public ResponseEntity<Documents> createDocument(@RequestBody Documents document) {
        return ResponseEntity.ok(documentService.saveDocument(document));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Documents> updateDocument(@PathVariable Long id, @RequestBody Documents documentDetails) {
        return documentService.getDocumentById(id)
                .map(document -> {
                    document.setName(documentDetails.getName());
                    document.setExpirationDate(documentDetails.getExpirationDate());
                    document.setStartDate(documentDetails.getStartDate());
                    document.setCar(documentDetails.getCar());
                    return ResponseEntity.ok(documentService.saveDocument(document));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
}
