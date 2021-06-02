package com.iss.cms.core.service;

import com.iss.cms.core.domain.Mail;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService{
    public static void sendCreatedAccountMail(String email, String username, String userPassword) throws MessagingException {
        Properties properties = new Properties();
        Mail mail = new Mail();

        mail.setupCreatedAccountMail(email, username, userPassword);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.port", "587");


        String myAccountEmail = "conference.management.system2021@gmail.com";
        String password = "conference2021";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, mail, myAccountEmail);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, Mail mail, String myAccountEmail) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(myAccountEmail));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail.getToUser()));
        message.setSubject(mail.getSubject());
        message.setText(mail.getMessage());

        return message;
    }
}
