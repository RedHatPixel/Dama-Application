package com.db.service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import com.db.model.ModelMessage;

public class ServiceMail {

    public ModelMessage sendMail(final String toEmail, final String code) {
        final ModelMessage ms = new ModelMessage(false, "");
        final String from = "mobarahkc@gmail.com";
        final Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        final String username = "mobarahkc@gmail.com";
        final String password = "lrjz mvwv mded tsun";
        final Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Verify Code");
            message.setText(
                    """
                                Welcome to Dama!
                    The verification for your sign in account:
                    """ + 
                    "                     " + code + 
                    """
                        
                        Hope you enjoy our service.
                    """
                   );
            Transport.send(message);
            ms.setSuccess(true);
        } catch (MessagingException e) {
            if (e.getMessage().equals("Invalid Address")) {
                ms.setMessage("Invalid email");
            } else {
                ms.setMessage("Error");
            }
        }
        return ms;
    }
}