/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package casablancatest;

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

/**
 * First iText example: Hello World.
 */
public class PDF
{

    String filePath = "C:\\Users\\Tomoe\\Documents\\NetBeansProjects\\test.pdf";

    public void createPdf()
            throws DocumentException, IOException
    {
        // step 1
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
document.add(new Paragraph("Casablanca Holiday Centre", font));

        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("Dear XXX,", font));
        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("thank you for your reservation.", font));

        document.add(new Paragraph("Please place the deposit in our bank account in 5 days.", font));
        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph("Deposit ammount: xxx", font));
        document.add(new Paragraph("Bank account: xxxx xxxx xxxx", font));

        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph("Please read our cancel rule mentioned in the next page", font));

        document.add(new Paragraph(Chunk.NEWLINE));

        document.add(new Paragraph(Chunk.NEWLINE));
        document.add(new Paragraph("Regards,", font));
        document.add(new Paragraph("CasaBlanca Hotel Resort", font));

        document.add(new Paragraph(Chunk.NEXTPAGE));

        document.add(new Paragraph("cancel rule. bla bla bla", font));
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
