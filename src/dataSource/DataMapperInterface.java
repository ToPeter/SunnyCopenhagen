/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Reservation;
import java.sql.Connection;

/**
 *
 * @author Tomoe
 */
public interface DataMapperInterface
{ 
   Reservation getreservation(int reservationNo,Connection con);
   
              
}
