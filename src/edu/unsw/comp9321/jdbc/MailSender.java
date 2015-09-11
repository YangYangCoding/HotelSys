package edu.unsw.comp9321.jdbc;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailSender {
	
	final static private String userName = "unswcomp9321@gmail.com";
    final static private String userPassword = "comp9321unsw";
    final static private String mailSmtpHost = "smtp.gmail.com";

    static public void sendEmail(Email email) {
        try {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", mailSmtpHost);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587");
            properties.setProperty("mail.smtp.ssl.trust", mailSmtpHost);
            

            Session emailSession = Session.getInstance(properties, new Authenticator(){
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, userPassword);
                }
            });

            Message emailMessage = new MimeMessage(emailSession);
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getEmailTo()));
            emailMessage.setFrom(new InternetAddress(email.getEmailFrom()));
            emailMessage.setSubject(email.getEmailSubject());
            emailMessage.setText(email.getEmailText());
            emailSession.setDebug(true);

            Transport.send(emailMessage);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
