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
/*
 * This class is part of the book "iText in Action - 2nd Edition"
 * written by Bruno Lowagie (ISBN: 9781935182610)
 * For more info, go to: http://itextpdf.com/examples/
 * This example only works with the AGPL version of iText.
 */
import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import domain.Guest;
import domain.GuestID;
import domain.Reservation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * First iText example: Hello World.
 */
public class PDF
{
  String filePath;
     Calendar c = Calendar.getInstance();

    private String toStringForPDF(ArrayList<Guest> guestarray)
    {
        String guests = "";
        for (int i = 0; i < guestarray.size(); i++)
        {
            guests += "<<Guest " +(i+1)+">> "+"\n"+guestarray.get(i).toStringForMail();

        }
        return guests;
    }

    
    public void createInvoice(ArrayList<Guest> guestarray, Reservation res, String roomType, int roomPrice)
            throws DocumentException, IOException
    {
        filePath = "Invoice" + guestarray.get(0).getReservationNo()+ ".pdf";
        // step 1
        String firstName = guestarray.get(0).getGuestFirstName();
        String lastName = guestarray.get(0).getGuestFamilyName();
        int deposit = roomPrice / 2;

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");

        Document document = new Document();
        System.out.println("document");
        // step 2
        FileOutputStream fos = new FileOutputStream(filePath);
        PdfWriter.getInstance(document, fos);
        System.out.println("fos");
        // step 3
        document.open();
        // step 4
        Font font = FontFactory.getFont("Times-Roman", 18);
        Font fontbold = FontFactory.getFont("Times-Roman", 34, Font.BOLD);

        document.add(new Paragraph("Invoice", fontbold));
        document.add(new Paragraph("                                    Casablanca Holiday Centre", font));
        document.add(new Paragraph("                                    "+c.getTime(), font));

        
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("Dear " + firstName + " " + lastName + ",", font));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("thank you for your reservation.", font));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("++++++Reservation details+++++++", font));
        document.add(new Paragraph("Name: "+guestarray.get(0).getGuestFirstName()+" "+guestarray.get(0).getGuestFamilyName(), font));
        document.add(new Paragraph("From: " + dateFormat.format(res.getFromDate()), font));
        document.add(new Paragraph("To: " + dateFormat.format(res.getEndDate()), font));
        document.add(new Paragraph("Room type: "+roomType, font));
        document.add(new Paragraph("++++++++++++++++++++++++++++++++",font));

        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph("Please place the deposit in our follwing bank account in 5 days.", font));
        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph("Deposit ammount: " + deposit + " USD", font));
        document.add(new Paragraph("Bank account: xxxx xxxx xxxx", font));

        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph("Please read our cancel rule mentioned in the next page", font));

        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("Regards,", font));
        document.add(new Paragraph("CasaBlanca Hotel Resort", font));

        document.add(new Paragraph(Chunk.NEXTPAGE));

        document.add(new Paragraph("The deposit and rules of cancellations", fontbold));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("The deposit must be paid at the latest 5 days after receiving the invoice.", font));
        document.add(new Paragraph("The deposit is half the bill. If deposit is not received by the centre within this deadline the booking will be cancelled.", font));
        document.add(new Paragraph("In case of cancellation your deposit will be refunded after deducting the cancellation fees as follows.", font));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("2 weeks before the date of arrival: ½ the deposit will be refunded", font));
        document.add(new Paragraph("1 week before the date of arrival: ¼ the deposit will be refunded", font));
        document.add(new Paragraph("Less than a week no deposit is refunded.", font));
        // step 5
        document.close();
        System.out.println("closed");
    }

    public void createConfirmation( ArrayList<Guest> guestarray,Reservation res, String roomType)
            throws DocumentException, IOException
    {
        filePath = "Confirmation" + guestarray.get(0).getReservationNo()+ ".pdf";
        // step 1
        String firstName = guestarray.get(0).getGuestFirstName();
        String lastName = guestarray.get(0).getGuestFamilyName();
       
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");


        Document document = new Document();
        System.out.println("document");
        // step 2
        FileOutputStream fos = new FileOutputStream(filePath);
        PdfWriter.getInstance(document, fos);
        System.out.println("fos");
        // step 3
        document.open();
        // step 4
        Font font = FontFactory.getFont("Times-Roman", 14);
        Font fontbold = FontFactory.getFont("Times-Roman", 30, Font.BOLD);

        document.add(new Paragraph("Confirmation", fontbold));
        document.add(new Paragraph("                                                             Casablanca Holiday Centre", font));
        document.add(new Paragraph("                                                            "+dateFormat.format(c.getTime()), font));
        
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("Dear " + firstName + " " + lastName + ",", font));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("thank you for your reservation.", font));
        //document.add(new Paragraph("Please print out and bring this confirmation.", font));
        //document.add(new Paragraph("You will need password shown in guest details below for booking sport facilities.", font));

        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("++++++Reservation details+++++++", font));
        document.add(new Paragraph("From: " + dateFormat.format(res.getFromDate()), font));
        document.add(new Paragraph("To: " + dateFormat.format(res.getEndDate()), font));
        document.add(new Paragraph("Room type: "+roomType, font));
        

        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("++++++Guest details+++++++", font));
        document.add(new Paragraph(toStringForPDF(guestarray), font));

        document.add(new Paragraph("+++++++++++++++++++++++++++++++++",font));
        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph("We are looking forward to seeing you.", font));

        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("Regards,", font));
        document.add(new Paragraph("CasaBlanca Hotel Resort", font));

        // step 5
        document.close();
        System.out.println("closed");
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

}
