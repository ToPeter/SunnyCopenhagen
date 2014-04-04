/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Controller;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
con.getFacArrayForJlist("tennis", date, 8);
        }      
    
}
