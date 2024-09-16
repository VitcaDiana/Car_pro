package com.project.CarPro.services;

import com.project.CarPro.dto.request.DocumentRequestDTO;
import com.project.CarPro.model.Car;
import com.project.CarPro.model.Documents;
import com.project.CarPro.repositories.CarRepository;
import com.project.CarPro.repositories.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    DocumentRepository documentRepository;
    CarRepository carRepository;
    @Autowired
    private EmailService emailService;


    @Autowired
    public DocumentService(DocumentRepository documentRepository, CarRepository carRepository) {
        this.documentRepository = documentRepository;
        this.carRepository = carRepository;
    }

    public Optional<Documents> getDocumentById(Long id) {

        return documentRepository.findById(id);
    }

    @Transactional
    public Documents addDocument(Long carId, DocumentRequestDTO documentRequestDTO) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Documents document = new Documents();
        document.setName(documentRequestDTO.getName());
        document.setStartDate(documentRequestDTO.getStartDate());
        document.setExpirationDate(documentRequestDTO.getExpirationDate());
        document.setCar(car);
       // emailService.sendDocumentAddedEmail(document);
        emailService.checkDocumentExpirations();
        return documentRepository.save(document);
    }
    @Transactional
    public Documents updateDocument(Long documentId, DocumentRequestDTO documentRequestDTO) {
        Documents document = documentRepository.findById(documentId).orElseThrow(()-> new RuntimeException("Document not found"));

        if (documentRequestDTO.getName() != null) {
            document.setName(documentRequestDTO.getName());
        }

        if (documentRequestDTO.getStartDate() != null) {
            document.setStartDate(documentRequestDTO.getStartDate());
        }
        
        if (documentRequestDTO.getExpirationDate() != null) {
            document.setExpirationDate(documentRequestDTO.getExpirationDate());
        }
        return documentRepository.save(document);
    }
    public List<Documents> getDocumentsByCar(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        return documentRepository.findByCar(car);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
