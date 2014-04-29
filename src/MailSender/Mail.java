package MailSender;

/**
 *
 * @author Tomoe
 */
import com.itextpdf.text.DocumentException;
import domain.Guest;
import domain.GuestID;
import domain.Reservation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

public class Mail
{

    public boolean sendInvoice(ArrayList<Guest> guestarray ,Reservation res, String roomType, int roomPrice) throws MessagingException
    {

        PDF pdf = new PDF();
        String firstName = guestarray.get(0).getGuestFirstName();
        String lastName = guestarray.get(0).getGuestFamilyName();

        try
        {
            pdf.createInvoice(guestarray, res, roomType, roomPrice);
        }
        catch (DocumentException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        catch (IOException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        String host = "smtp.gmail.com";
        String Password = "cphbusiness11";
        String from = "casablancaholidaycentre@gmail.com ";
        String toAddress = guestarray.get(0).getEmail();
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

        message.setSubject("Confirmation of your reservation (#" + guestarray.get(0).getReservationNo() + ')');

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText("Dear " + firstName + " " + lastName + "," + "\n" + "\n"
                + "thank you for your reservation." + "\n" + "\n"
                + "We kindly ask you to pay the deposit to the account shown in the attached file." + "\n" + "\n"
                + "Regards," + "\n"
                + "Casablanca Holiday Centre"
        );

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(source));

        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        try
        {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            return true;

        }
        catch (SendFailedException sfe)
        {
            return false;
        }
    }

    public boolean sendConfirmation(ArrayList<Guest> guestarray ,Reservation res, String roomType) throws MessagingException
    {

        PDF pdf = new PDF();
        int reservationNo = guestarray.get(0).getReservationNo();
        String firstName = guestarray.get(0).getGuestFirstName();
        String lastName = guestarray.get(0).getGuestFamilyName();

        try
        {
            pdf.createConfirmation(guestarray, res, roomType);
        }
        catch (DocumentException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        catch (IOException ex)
        {
            Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        String host = "smtp.gmail.com";
        String Password = "cphbusiness11";
        String from = "casablancaholidaycentre@gmail.com ";
        String toAddress = guestarray.get(0).getEmail();
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

        message.setSubject("Confirmation of your reservation (#" + reservationNo + ')');

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText("Dear " + firstName + " " + lastName + "," + "\n" + "\n"
                + "hereby we gladly inform you that your reservation has been confirmed." + "\n" + "\n"
                + "Please print out and bring the confirmation attached." + "\n" + "\n"
                + "You will need password found in the confirmation for booking sport facilities." + "\n" + "\n"
                + "Regards," + "\n"
                + "Casablanca Holiday Centre"
        );

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBodyPart);

        messageBodyPart = new MimeBodyPart();

        DataSource source = new FileDataSource(filename);

        messageBodyPart.setDataHandler(new DataHandler(source));

        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        try
        {
            Transport tr = session.getTransport("smtps");
            tr.connect(host, from, Password);
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
            return true;

        }
        catch (SendFailedException sfe)
        {
            return false;
        }
    }
}
