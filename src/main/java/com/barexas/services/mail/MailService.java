package com.barexas.services.mail;


import org.springframework.mail.javamail.MimeMessagePreparator;

public interface MailService {
    void sendMail(MimeMessagePreparator messagePreparator);
    MimeMessagePreparator buildMail(String recipient, String subject, String message);
}
