package com.barexas.services.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;

    @Override
    public void sendMail(MimeMessagePreparator messagePreparator) {
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public MimeMessagePreparator buildMail(String recipient, String subject, String message) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(env.getProperty("mail.from"));
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
        };
    }
}
