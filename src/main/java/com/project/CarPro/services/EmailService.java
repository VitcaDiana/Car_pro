package com.project.CarPro.services;

import com.project.CarPro.model.Documents;
import com.project.CarPro.repositories.DocumentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmailService {
    private DocumentRepository documentRepository;
    private JavaMailSender javaMailSender;
@Autowired
    public EmailService(DocumentRepository documentRepository, JavaMailSender javaMailSender) {
        this.documentRepository = documentRepository;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void checkDocumentExpirations() {
        LocalDate currentDate = LocalDate.now();
        LocalDate fiveDaysBefore = currentDate.plusDays(5);

        List<Documents> expiringDocuments = documentRepository.findByExpirationDateBetween(currentDate, fiveDaysBefore);

        for (Documents document : expiringDocuments) {
            sendEmailToDriverOrManager(document);
        }
    }

    private void sendEmailToDriverOrManager(Documents document) {
        String subject = "Document Expiration Reminder";
        String messageText = "The document '" + document.getName() + "' is expiring soon. Please take action.Expiration date: "+ document.getExpirationDate()+" Car: "+document.getCar().getId();

        if (document.getCar() != null ) {
            //verific daca masina face parte dint-o flota trimit email la manager
            if(document.getCar().getFleet() != null){
                sendEmail(document.getCar().getFleet().getManager().getEmail(),subject, messageText);
            }else if (!document.getCar().getCarDriverList().isEmpty()){
                sendEmail(document.getCar().getCarDriverList().get(0).getDriver().getEmail(),subject,messageText);
            } else {
                System.out.println("Cannot send email, no driver or fleet manager associated with the car");

            }

        }else {
            System.out.println("Cannot send email, no car");
        }
    }
//    public void sendDocumentAddedEmail(Documents document) {
//        String subject = "New Document Added";
//        String messageText = "A new document '" + document.getName() + "' has been added to the system.";
//
//        if (document.getCar() != null) {
//            // Verific dacă există un șofer pentru mașină
//            if (!document.getCar().getCarDriverList().isEmpty()) {
//                sendEmail(document.getCar().getCarDriverList().get(0).getDriver().getEmail(), subject, messageText);
//            } else if (document.getCar().getFleet() != null) {
//                // Trimit email la managerul de flotă
//                sendEmail(document.getCar().getFleet().getManager().getEmail(), subject, messageText);
//            }
//        }
//    }


    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

}
