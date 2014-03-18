/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casablancatest;

import javax.mail.MessagingException;

/**
 *
 * @author Tomoe
 */
public class Starter
{public static void main(String[] args) throws MessagingException
    {Mail mail= new Mail();
    mail.SendMail();
    }
    
}
