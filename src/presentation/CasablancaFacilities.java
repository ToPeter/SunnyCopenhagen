/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import domain.Booking;
import domain.Controller;
import domain.Facility;
import domain.Guest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

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
    private DefaultListModel model, infoOfBookingModel;
    private Controller control;
    private ArrayList<Facility> facArray;
    private ArrayList<Booking> bookingsarray;
    private ArrayList<Guest> waitingarray;
    private String type;
    private String username;
    private int bookingid;
    Date today, dd, weekfwd;
    Calendar c;
    private ArrayList<Booking> arrayBookingInfo;
    private boolean selected = false;

    public CasablancaFacilities() //should be updated and used only for employees
    {
        initComponents();
        jLayeredPane2.setVisible(false);
        jLayeredPaneBookingDetails.setVisible(false);
        model = new DefaultListModel();
        infoOfBookingModel = new DefaultListModel();
        control = new Controller();
        facArray = new ArrayList();
        bookingsarray = new ArrayList();
        waitingarray = new ArrayList();
        model.clear();
        type = null;
        c = Calendar.getInstance();
        // c.add(Calendar.DAY_OF_MONTH, -1);
        today = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, 7);
        weekfwd = c.getTime();
        jDateChooserFacilityBooking.setMinSelectableDate(today);
        jDateChooserFacilityBooking.requestFocusInWindow();
        jDateChooserFacilityBooking.setSelectableDateRange(today, weekfwd);
        jDateChooserFacilityBooking.setDate(today);
        populateComboBox();

    }

    public CasablancaFacilities(String user) //constructor for logging in as guest
    {
        initComponents();
        jLayeredPane2.setVisible(false);
        jLayeredPaneBookingDetails.setVisible(false);
        jMenuBar1.setVisible(false);
        model = new DefaultListModel();
        infoOfBookingModel = new DefaultListModel();
        control = new Controller();
        facArray = new ArrayList();
        bookingsarray = new ArrayList();
        waitingarray = new ArrayList();
        model.clear();
        type = null;
        username = user;
        c = Calendar.getInstance();
        today = c.getTime();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        c.add(Calendar.DAY_OF_MONTH, 7);
        weekfwd = c.getTime();
        jDateChooserFacilityBooking.setMinSelectableDate(today);
        jDateChooserFacilityBooking.requestFocusInWindow();
        jDateChooserFacilityBooking.setSelectableDateRange(today, weekfwd);
        jDateChooserFacilityBooking.setDate(today);
        populateComboBox();
    }

    public void populateComboBox()
    {
        jComboBoxBookingHour.removeAllItems();
//        int hour = c.get(c.HOUR_OF_DAY);
//        if (today.before(jDateChooserFacilityBooking.getDate() || today.equals(jDateChooserFacilityBooking.getDate())))
//        {
        for (int i = 8; i <= 20; i++)
        {

        //    jComboBoxBookingHour.addItem(i);
             jComboBoxBookingHour.addItem(i+":00-"+(i+1+":00"));
        }
//        }
//        else
//        {
//            for (int i = hour + 2; i <= 20; i++)
//            {
//                jComboBoxBookingHour.addItem(i);
//            }
//        }
    }
    
    public int getSelectedHour()
    {
        String hour = (String) jComboBoxBookingHour.getSelectedItem();
        int hourint = 0;
        if (hour.startsWith("1") || hour.startsWith("2"))
        {
            hourint = Integer.parseInt(hour.substring(0, 2));
        }
        else
        {
            hourint = Integer.parseInt(hour.substring(0, 1));
        }
        return hourint;
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

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jComboBoxFacilityType = new javax.swing.JComboBox();
        jDateChooserFacilityBooking = new com.toedter.calendar.JDateChooser();
        jLabelFacilityType = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAvailableFacilities = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBoxBookingHour = new javax.swing.JComboBox();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListSearchGuestFacilities = new javax.swing.JList();
        jTextFieldGuestIDSearchFacilities = new javax.swing.JTextField();
        jLabelGuestIDSearchFacilities = new javax.swing.JLabel();
        jButtonSearchGuestFacilities = new javax.swing.JButton();
        jLayeredPaneBookingDetails = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListShowInfoOnreservation = new javax.swing.JList();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLayeredPane1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter()
        {
            public void mouseMoved(java.awt.event.MouseEvent evt)
            {
                jLayeredPane1MouseMoved(evt);
            }
        });

        jComboBoxFacilityType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tennis", "Badminton", "Volleyball", "Handball", "Fitness" }));
        jComboBoxFacilityType.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxFacilityTypeActionPerformed(evt);
            }
        });

        jDateChooserFacilityBooking.setDateFormatString("dd-MM-yy");
        jDateChooserFacilityBooking.setFocusCycleRoot(true);

        jLabelFacilityType.setText("Facility type");

        jLabel1.setText("Date");

        jLabel2.setText("Hour");

        jListAvailableFacilities.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListAvailableFacilities.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jListAvailableFacilitiesMouseClicked(evt);
            }
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

        jButton2.setText("Book");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Waiting guest");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBoxBookingHour.setMinimumSize(new java.awt.Dimension(100, 25));
        jComboBoxBookingHour.setPreferredSize(new java.awt.Dimension(100, 25));
        jComboBoxBookingHour.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxBookingHourActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxFacilityType, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFacilityType))
                        .addGap(28, 28, 28)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFacilityBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxBookingHour, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(1250, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFacilityType)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jComboBoxBookingHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserFacilityBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFacilityType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jLayeredPane1.setLayer(jComboBoxFacilityType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jDateChooserFacilityBooking, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabelFacilityType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jComboBoxBookingHour, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jScrollPane2.setViewportView(jListSearchGuestFacilities);

        jLabelGuestIDSearchFacilities.setText("Guest ID");

        jButtonSearchGuestFacilities.setText("Search");
        jButtonSearchGuestFacilities.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonSearchGuestFacilitiesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addComponent(jTextFieldGuestIDSearchFacilities, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jButtonSearchGuestFacilities))
                    .addComponent(jLabelGuestIDSearchFacilities)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(1350, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelGuestIDSearchFacilities)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldGuestIDSearchFacilities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchGuestFacilities))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
        );
        jLayeredPane2.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jTextFieldGuestIDSearchFacilities, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabelGuestIDSearchFacilities, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jButtonSearchGuestFacilities, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jListShowInfoOnreservation.setModel(new javax.swing.AbstractListModel()
        {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListShowInfoOnreservation.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jListShowInfoOnreservationMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jListShowInfoOnreservation);

        jButton4.setText("Update List");
        jButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPaneBookingDetailsLayout = new javax.swing.GroupLayout(jLayeredPaneBookingDetails);
        jLayeredPaneBookingDetails.setLayout(jLayeredPaneBookingDetailsLayout);
        jLayeredPaneBookingDetailsLayout.setHorizontalGroup(
            jLayeredPaneBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBookingDetailsLayout.createSequentialGroup()
                .addGroup(jLayeredPaneBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1396, Short.MAX_VALUE))
        );
        jLayeredPaneBookingDetailsLayout.setVerticalGroup(
            jLayeredPaneBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBookingDetailsLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(312, Short.MAX_VALUE))
        );
        jLayeredPaneBookingDetails.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookingDetails.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu1.setText("File");

        jMenuItem1.setText("Search For Booked Facilities");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Book a Facility");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLayeredPaneBookingDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLayeredPaneBookingDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxFacilityTypeActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxFacilityTypeActionPerformed
    {//GEN-HEADEREND:event_jComboBoxFacilityTypeActionPerformed
        // TODO add your handling code here:
        facilityType = jComboBoxFacilityType.getSelectedIndex();
        System.out.println(facilityType);
    }//GEN-LAST:event_jComboBoxFacilityTypeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed

        model.clear();

        int typeIndex = jComboBoxFacilityType.getSelectedIndex();
        dd = jDateChooserFacilityBooking.getDate();

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

//        String hour = (String) jComboBoxBookingHour.getSelectedItem();
//        int hourint = 0;
//        if (hour.startsWith("1") || hour.startsWith("2"))
//        {
//            hourint = Integer.parseInt(hour.substring(0, 2));
//        }
//        else
//        {
//            hourint = Integer.parseInt(hour.substring(0, 1));
//        }
//        
//            
//                System.out.println("printing hourint " + hourint);
//                System.out.println("type=" + type);
//                System.out.println(dd.toString());
                facArray = control.getFacArrayForJlist(type, dd, getSelectedHour());

                for (int i = 0; i < facArray.size(); i++)
                {
                    Facility facility = facArray.get(i);
                    String facstring = control.getString(facility);
                    model.addElement(facstring);
                }
                jListAvailableFacilities.setModel(model);
                selected = false;
            
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        int listIndex = jListAvailableFacilities.getSelectedIndex();
        Facility fac = facArray.get(listIndex);

        int typeIndex = jComboBoxFacilityType.getSelectedIndex();
        dd = jDateChooserFacilityBooking.getDate();

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

       // int hour = (Integer) jComboBoxBookingHour.getSelectedItem();
        if (!control.fourBookingPerDay(username, dd))
        {
            control.createFacilityBooking(fac, type, username, dd, getSelectedHour(), 0);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "You can only have 4 booking per day", "Error", JOptionPane.OK_OPTION);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed

        int typeIndex = jListAvailableFacilities.getSelectedIndex();
        Facility fac = facArray.get(typeIndex);
    //    int hour = (Integer) jComboBoxBookingHour.getSelectedItem();
        waitingarray = control.getWaitingList(fac.getFacID(), jDateChooserFacilityBooking.getDate(), getSelectedHour());
        System.out.println("waitingarray size " + waitingarray.size());
        model.clear();
        for (int i = 0; i < waitingarray.size(); i++)
        {
            Guest guest = waitingarray.get(i);
            model.addElement(guest.stringForWaitingList());
        }

        jListAvailableFacilities.setModel(model);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem2ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        jLayeredPane1.setVisible(true);
        jLayeredPane2.setVisible(false);
        jLayeredPaneBookingDetails.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        jLayeredPane1.setVisible(false);
        jLayeredPane2.setVisible(true);
        jLayeredPaneBookingDetails.setVisible(false);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButtonSearchGuestFacilitiesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonSearchGuestFacilitiesActionPerformed
    {//GEN-HEADEREND:event_jButtonSearchGuestFacilitiesActionPerformed
        // TODO add your handling code here:
        model.clear();
        bookingsarray = control.getBookingList(jTextFieldGuestIDSearchFacilities.getText());
        for (int i = 0; i < bookingsarray.size(); i++)
        {
            Booking booking = bookingsarray.get(i);
            String bookingString = control.getBookingListString(booking);
            model.addElement(bookingString);
        }
        jListSearchGuestFacilities.setModel(model);
    }//GEN-LAST:event_jButtonSearchGuestFacilitiesActionPerformed

    private void jListAvailableFacilitiesMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jListAvailableFacilitiesMouseClicked
    {//GEN-HEADEREND:event_jListAvailableFacilitiesMouseClicked
        if (evt.getClickCount() == 2)
        {
            int jListSelectedIndex = jListAvailableFacilities.getSelectedIndex();
            Facility facid = facArray.get(jListSelectedIndex);
            int hour = (Integer) jComboBoxBookingHour.getSelectedItem();
            bookingid = control.getBookingno(facid.getFacID(), jDateChooserFacilityBooking.getDate(), getSelectedHour());
            System.out.println("bookingid: " + bookingid);
            arrayBookingInfo = control.getBookingDetails(bookingid);

            System.out.println(arrayBookingInfo.size());
            for (int i = 0; i < arrayBookingInfo.size(); i++)
            {
                Booking booking = arrayBookingInfo.get(i);
                String resutl = "" + booking.getGuestno() + " Waiting: " + booking.getWaitingpos() + " instructor: " + booking.getInno();
                System.out.println(resutl);

                infoOfBookingModel.addElement(resutl);

            }
            jListShowInfoOnreservation.setModel(infoOfBookingModel);
            jLayeredPane1.setVisible(false);
            jLayeredPaneBookingDetails.setVisible(true);
        }


    }//GEN-LAST:event_jListAvailableFacilitiesMouseClicked

    private void jListShowInfoOnreservationMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jListShowInfoOnreservationMouseClicked
    {//GEN-HEADEREND:event_jListShowInfoOnreservationMouseClicked
//        if (evt.getClickCount() == 2)
//        {
//            int jListSelectedIndex = jListShowInfoOnreservation.getSelectedIndex();
//
//            System.out.println("index: "+jListSelectedIndex);
//           Booking booking = arrayBookingInfo.get(jListSelectedIndex);
//            System.out.println(booking.toString());
//           control.updateWaitingPos(bookingid, booking.getGuestno());
//           control.commit();
//           infoOfBookingModel.clear();
//            for (int i = 0; i < arrayBookingInfo.size(); i++)
//            {
//                Booking book = arrayBookingInfo.get(i);
//                String resutl = "" + book.getGuestno() + " Waiting: " + book.getWaitingpos() + " instructor: " + book.getInno();
//                System.out.println(resutl);
//
//                infoOfBookingModel.addElement(resutl);
//
//            }
//            jListShowInfoOnreservation.setModel(infoOfBookingModel);
//            
//        }
    }//GEN-LAST:event_jListShowInfoOnreservationMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed

        JOptionPane.showConfirmDialog(this, "Do you wanto remove the person?", "Confirm", 0, 3);
       // JOptionPane.showMessageDialog(null, "Do you wanto remove the person?", "Confirm", JOptionPane.QUESTION_MESSAGE, null);
        //JOptionPane.showMessageDialog(null, "You can only have 4 booking per day", "Error", JOptionPane.OK_OPTION);

        int jListSelectedIndex = jListShowInfoOnreservation.getSelectedIndex();

        System.out.println("index: " + jListSelectedIndex);
        Booking booking = arrayBookingInfo.get(jListSelectedIndex);
        System.out.println(booking.toString());
        control.updateWaitingPos(bookingid, booking.getGuestno());
        infoOfBookingModel.clear();
        for (int i = 0; i < arrayBookingInfo.size(); i++)
        {
            Booking book = arrayBookingInfo.get(i);
            String resutl = "" + book.getGuestno() + " Waiting: " + book.getWaitingpos() + " instructor: " + book.getInno();
            System.out.println(resutl);

            infoOfBookingModel.addElement(resutl);

        }
        jListShowInfoOnreservation.setModel(infoOfBookingModel);
        control.commitFac();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLayeredPane1MouseMoved(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLayeredPane1MouseMoved
    {//GEN-HEADEREND:event_jLayeredPane1MouseMoved
        // TODO add your handling code here:
//        if (!selected)
//        populateComboBox();
    }//GEN-LAST:event_jLayeredPane1MouseMoved

    private void jComboBoxBookingHourActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxBookingHourActionPerformed
    {//GEN-HEADEREND:event_jComboBoxBookingHourActionPerformed
        // TODO add your handling code here:
        selected = true;
    }//GEN-LAST:event_jComboBoxBookingHourActionPerformed

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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonSearchGuestFacilities;
    private javax.swing.JComboBox jComboBoxBookingHour;
    private javax.swing.JComboBox jComboBoxFacilityType;
    private com.toedter.calendar.JDateChooser jDateChooserFacilityBooking;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelFacilityType;
    private javax.swing.JLabel jLabelGuestIDSearchFacilities;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPaneBookingDetails;
    private javax.swing.JList jListAvailableFacilities;
    private javax.swing.JList jListSearchGuestFacilities;
    private javax.swing.JList jListShowInfoOnreservation;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextFieldGuestIDSearchFacilities;
    // End of variables declaration//GEN-END:variables
}
