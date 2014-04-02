/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.Controller;
import domain.Facility;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;

/**
 *
 * @author Milkman
 */
public class CasablancaFacilities extends javax.swing.JFrame
{

    /**
     * Creates new form CasablancaFacilities
     */
    private int facilityType = 0;
    private DefaultListModel model;
    private Controller control;

    public CasablancaFacilities()
    {
        initComponents();
        model = new DefaultListModel();
        control = new Controller();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jComboBoxFacilityType = new javax.swing.JComboBox();
        jDateChooserFacilityBooking = new com.toedter.calendar.JDateChooser();
        jTextFieldFacilityBookingHour = new javax.swing.JTextField();
        jLabelFacilityType = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAvailableFacilities = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBoxFacilityType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tennis", "Badminton", "Volleyball", "Handball", "Fitness" }));
        jComboBoxFacilityType.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxFacilityTypeActionPerformed(evt);
            }
        });

        jDateChooserFacilityBooking.setDateFormatString("dd-MM-yy");

        jTextFieldFacilityBookingHour.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextFieldFacilityBookingHourActionPerformed(evt);
            }
        });

        jLabelFacilityType.setText("Facility type");

        jLabel1.setText("Date");

        jLabel2.setText("Hour (8-20)");

        jListAvailableFacilities.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jListAvailableFacilities);

        jButton1.setText("Show");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxFacilityType, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFacilityType))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFacilityBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldFacilityBookingHour, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jButton1)))
                .addContainerGap(102, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFacilityType)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldFacilityBookingHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jDateChooserFacilityBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFacilityType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxFacilityTypeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxFacilityTypeActionPerformed
    {//GEN-HEADEREND:event_jComboBoxFacilityTypeActionPerformed
        // TODO add your handling code here:
        facilityType = jComboBoxFacilityType.getSelectedIndex();
        System.out.println(facilityType);
    }//GEN-LAST:event_jComboBoxFacilityTypeActionPerformed

    private void jTextFieldFacilityBookingHourActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldFacilityBookingHourActionPerformed
    {//GEN-HEADEREND:event_jTextFieldFacilityBookingHourActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFacilityBookingHourActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        model.clear();
        String type = null;
        int typeIndex = jComboBoxFacilityType.getSelectedIndex();
        System.out.println("typeindes = " + typeIndex);
        Date dd = jDateChooserFacilityBooking.getDate();
        //"Tennis", "Badminton", "Volleyball", "Handball", "Fitness"
        if (typeIndex == 0)
        {
            type = "tennis";
        }
        else if (typeIndex == 1)
        {
            type = "badminton";
        }
        else if (typeIndex == 2)
        {
            type = "volleyball";
        }
        else if (typeIndex == 3)
        {
            type = "handball";
        }
        else if (typeIndex == 4)
        {
            type = "fitness";
        }

        int hour = Integer.parseInt(jTextFieldFacilityBookingHour.getText());
        System.out.println("type=" + type);
        System.out.println(dd.toString());
        System.out.println(hour);
        ArrayList<Facility> facArray = control.getFacArrayForJlist(type, dd, hour);
        for (int i = 0; i < facArray.size(); i++)
        {
            Facility facility = facArray.get(i);
            String facstring = control.getString(facility);
            model.addElement(facstring);
        }
        jListAvailableFacilities.setModel(model);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(CasablancaFacilities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(CasablancaFacilities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(CasablancaFacilities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(CasablancaFacilities.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new CasablancaFacilities().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBoxFacilityType;
    private com.toedter.calendar.JDateChooser jDateChooserFacilityBooking;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFacilityType;
    private javax.swing.JList jListAvailableFacilities;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldFacilityBookingHour;
    // End of variables declaration//GEN-END:variables
}
