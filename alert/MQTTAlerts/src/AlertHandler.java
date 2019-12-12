import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;


/*
--------------Error Mail Report by J.M. 5AHIT--------------
--2 Methods for sending Error Mails available / 1 with attatchment file, one without
--Call the Methods by creating a new AlterHandler. Pass that object the reciepients mail adress(es), your
--Gmail user (mail) your gmail Password and a file name if you call the Method with attachment
 */

public class AlertHandler {
    public void sendMailwithAttatchment(String tomail, String frommail, String gmailUser, String gmailpass, String file) {

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(gmailUser, gmailpass);

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(frommail));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(tomail));

            // Set Subject: header field
            message.setSubject("MQTT Error Service!");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f = new File(file);

                attachmentPart.attachFile(f);
                textPart.setText("Test");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }


    public void sendErrorMail(Alarm alarm, ArrayList<String> reciepients, String fromMail, String gmailUser, String gmailPass) {
        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(gmailUser, gmailPass);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromMail));

            // Set To: header field of the header.
            for (String s : reciepients
            ) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(s));
            }

            // Set Subject: header field
            message.setSubject("An MQTT Error occured!");

            // Now set the actual message
            message.setContent(
                    "<h1>MQTT Error Report</h1><br><h2>Data</h2><br>" +
                            "<ul>" +
                            "<li>Timestamp: " + alarm.getTimestamp().toString() + "</li>" +
                            "<li>Description: " + alarm.getDescription() + "</li>" +
                            "<li>Handler: " + alarm.getHandler() + "</li>" +
                            "<li>Origin: " + alarm.getOrigin() + "</li>" +
                            "</ul>",
                    "text/html");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}