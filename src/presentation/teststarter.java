/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

import domain.Booking;
import domain.Controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tomoe
 */
public class teststarter
{
    public static void main(String args[]) throws ParseException
    {Controller c = new Controller();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
ArrayList<Booking> booking=    c.getBookedfac("tennis", dateFormat.parse("14-05-14"), 8);
        System.out.println( c.remaingPlace("tennis", dateFormat.parse("15-05-14"), 8,102
        ));

      
    }
}
