/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Controller;
import domain.Facility;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Tomoe
 */
public class starter
{public static void main(String[] args) throws ParseException
{//DBFacadeForFacility dff= new DBFacadeForFacility();
//DBFacadeForFacility dff=DBFacadeForFacility.getInstance();
Controller con = new Controller();
    DateFormat format = new SimpleDateFormat("dd-MM-yy");
Date date=format.parse("16-05-14");
ArrayList<Facility> fac= con.getFacArrayForJlist("tennis", date, 8);
//con.createFacilityBooking(fac.get(0), "10199-1", date, 8, 0);
        }      
    
}
