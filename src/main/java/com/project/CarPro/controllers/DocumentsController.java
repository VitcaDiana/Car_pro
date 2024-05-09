package com.project.CarPro.controllers;

import com.project.CarPro.dto.request.DocumentRequestDTO;
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


    @PostMapping("/{carId}")
    public ResponseEntity<Documents> createDocument(@PathVariable Long carId, @RequestBody DocumentRequestDTO documentRequestDTO) {
        return ResponseEntity.ok(documentService.addDocument(carId, documentRequestDTO));
    }
    @GetMapping("/{documentId}")
    public Documents getDocumentById(@PathVariable Long documentId) {
        return documentService.getDocumentById(documentId).orElseThrow(()-> new RuntimeException("Document not found"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/car/{carId}")
    public List<Documents> getDocumentsByCar(@PathVariable Long carId) {
        return documentService.getDocumentsByCar(carId);
    }

    @PutMapping("/{documentId}")
    public Documents updateDocument(@PathVariable Long documentId, @RequestBody DocumentRequestDTO documentRequestDTO) {
        return documentService.updateDocument(documentId, documentRequestDTO);
    }
}
