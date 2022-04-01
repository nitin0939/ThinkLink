package com.ThinkLink.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class AlertEmail {
    @Value("${myconfig.to}")
    String  to;

    @Value("${myconfig.from}")
    String  from;

    @Value("${smtp.password}")
    String  password;

    @Value("${smtp.user}")
    String  user;

    @Value("${smtp.host}")
    String  host;

    @Value("${smtp.port}")
    String  port;

    @Async
    public void sendEmail(String content){
        System.out.println("============:"+user+"=========:"+password);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");//it’s optional in Mailtrap
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);// use one of the options in the SMTP settings tab in your Mailtrap Inbox
        props.put("mail.smtp.ssl.enable", "false");// use one of the options in the SMTP settings tab in your Mailtrap Inbox
        props.put("mail.smtp.smtp.tls", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress(from));

            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("BTC Price Alert");

            // Put the content of your message
            message.setText(content);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
