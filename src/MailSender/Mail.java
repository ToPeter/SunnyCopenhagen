/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MailSender;

/**
 *
 * @author Tomoe
 */

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail {
    
    public void sendInvoice(int reservationNo, String firstName, String lastName) throws MessagingException {
        
        PDF pdf= new PDF();
        
        try
        {
            pdf.createPdf(reservationNo, firstName,lastName);
        }
        catch (DocumentException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String host = "smtp.gmail.com";
        String Password = "cphbusiness11";
        String from = "casablancaholidaycentre@gmail.com ";
        String toAddress = "thehappygolfer@gmail.com";
        String filename = pdf.getFilePath();
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO, toAddress);

        message.setSubject("Thank you for your reservation (#"+reservationNo+')');

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText("Dear "+firstName+","+"\n"+"\n"
        +"thank you for your reservation."+"\n"+"\n"
        +"We kindly ask you to pay the deposit to the account shown in the attached file."+"\n"+"\n"
                +"Regards,"+"\n"
                +"Casablanca Holiday Centre"
        );

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(source));

        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Sent Successfully");
            tr.close();

        } catch (SendFailedException sfe) {

            System.out.println(sfe);
        }
    }
     public void SendMail(int reservationNo, String firstName, String lastName) throws MessagingException {
        
        PDF pdf= new PDF();
        
        try
        {
            pdf.createPdf(reservationNo, firstName,lastName);
        }
        catch (DocumentException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String host = "smtp.gmail.com";
        String Password = "cphbusiness11";
        String from = "casablancaholidaycentre@gmail.com ";
        String toAddress = "Tomoetom@gmail.com,thehappygolfer@gmail.com";
        String filename = pdf.getFilePath();
        // Get system properties
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, null);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));

        message.setRecipients(Message.RecipientType.TO, toAddress);

        message.setSubject("JavaMail Attachment test");

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText("Tomoe is trying");

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(source));

        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        try {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            System.out.println("Mail Sent Successfully");
            tr.close();

        } catch (SendFailedException sfe) {

            System.out.println(sfe);
        }
    }
}
    

