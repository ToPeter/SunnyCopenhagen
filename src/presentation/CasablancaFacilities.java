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
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    private ArrayList<Booking> bookingsArrayForInstructor;
    private ArrayList<Guest> waitingarray;
    ArrayList<Booking> arr;
    private String type;
    private String username;
    Date minDate, dd, weekfwd, today;
    Calendar c;
    JLayeredPane currentPane;

    public CasablancaFacilities() //should be updated and used only for employees
    {
        initComponents();
        setupPanes();
        model = new DefaultListModel();
        infoOfBookingModel = new DefaultListModel();
        control = new Controller();
        facArray = new ArrayList();
        bookingsArrayForInstructor = new ArrayList();
        bookingsarray = new ArrayList();
        waitingarray = new ArrayList();
        model.clear();
        type = null;
        c = Calendar.getInstance();
        today = c.getTime();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        // c.add(Calendar.DAY_OF_MONTH, -1);
        minDate = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, 7);
        weekfwd = c.getTime();
        jDateChooserFacilityBooking.setMinSelectableDate(today);
        jDateChooserFacilityBooking.requestFocusInWindow();
        jDateChooserFacilityBooking.setSelectableDateRange(today, weekfwd);
        jDateChooserFacilityBooking.setDate(today);
        username = "10000-1"; // used for testing when running file directly in netbeans
        populateComboBox();
        populateAnotherComboBoxBcuzWhyNot();

    }

    public CasablancaFacilities(String user) //constructor for logging in as guest
    {
        initComponents();
        setupPanes();

        jMenuAdmin.setEnabled(false);
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
        minDate = c.getTime();
        c.add(Calendar.DAY_OF_MONTH, 7);
        weekfwd = c.getTime();
        jDateChooserFacilityBooking.setMinSelectableDate(today);
        jDateChooserFacilityBooking.requestFocusInWindow();
        jDateChooserFacilityBooking.setSelectableDateRange(today, weekfwd);
        jDateChooserFacilityBooking.setDate(today);
    }

    public void populateAnotherComboBoxBcuzWhyNot()
    {
        jComboBoxAddFacility.removeAllItems();
        jComboBoxChoseTypeInstructor.removeAllItems();
        for (int i = 0; i < control.getFacilityTypes().size(); i++)
        {
            jComboBoxAddFacility.addItem(control.getFacilityTypes().get(i));
            jComboBoxChoseTypeInstructor.addItem(control.getFacilityTypes().get(i));
        }
    }

    public void populateComboBox()
    {
        jComboBoxBookingHour.removeAllItems();
        int hour = c.get(c.HOUR_OF_DAY);
        if (today.before(jDateChooserFacilityBooking.getDate()))
        {
            for (int i = 8; i <= 20; i++)
            {

                jComboBoxBookingHour.addItem(i + ":00-" + (i + 1 + ":00"));
            }
        }
        else
        {
            for (int i = hour + 2; i <= 20; i++)
            {
                jComboBoxBookingHour.addItem(i + ":00-" + (i + 1 + ":00"));
            }
        }
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

    public void setupPanes()
    {
        jLayeredPaneSearchFacility.setVisible(false);
        jLayeredPaneBookInstructor.setVisible(false);
        jLayeredPaneAdmin.setVisible(false);
        jLayeredPaneBookingDetails.setVisible(false);
        currentPane = jLayeredPaneBookFacility;
    }

    public void swicthPane(JLayeredPane newPane, JLayeredPane oldPane)
    {
        oldPane.setVisible(false);
        currentPane = newPane;
        newPane.setVisible(true);
    }

    public void updateJtable(String username)
    {
        String[] nameOFcullums =
        {
            "bookingID", "Type", "Inno", "booking Date", "Booking Time"
        };
        arr = control.getBookingList(username);
        Object[][] data = new Object[arr.size()][5];

        for (int i = 0; i < arr.size(); i++)
        {
            Booking booking = arr.get(i);

            data[i][0] = booking.getBookingId();
            data[i][1] = booking.getType();
            data[i][2] = booking.getInno();
            data[i][3] = booking.getBookingdate();
            data[i][4] = booking.getBookingtime();

        }
        DefaultTableModel tm = new DefaultTableModel(data, nameOFcullums);
        jTableShowBookings.setModel(tm);

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

        jLayeredPaneBookFacility = new javax.swing.JLayeredPane();
        jComboBoxFacilityType = new javax.swing.JComboBox();
        jDateChooserFacilityBooking = new com.toedter.calendar.JDateChooser();
        jLabelFacilityType = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListAvailableFacilities = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBoxBookingHour = new javax.swing.JComboBox();
        jLayeredPaneSearchFacility = new javax.swing.JLayeredPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListSearchGuestFacilities = new javax.swing.JList();
        jTextFieldGuestIDSearchFacilities = new javax.swing.JTextField();
        jLabelGuestIDSearchFacilities = new javax.swing.JLabel();
        jButtonSearchGuestFacilities = new javax.swing.JButton();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLayeredPaneBookInstructor = new javax.swing.JLayeredPane();
        jButtonRemoveIN = new javax.swing.JButton();
        jButtonBookIN = new javax.swing.JButton();
        jTextFieldGuestNameInstructor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonBookIN1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableShowBookings = new javax.swing.JTable();
        jLayeredPaneAdmin = new javax.swing.JLayeredPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxAddFacility = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldInstructorName = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jComboBoxChoseTypeInstructor = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLayeredPaneBookingDetails = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableBookingDetails = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuBookInstructor = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuAdmin = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuTest = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

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
        jDateChooserFacilityBooking.setFocusCycleRoot(true);
        jDateChooserFacilityBooking.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                jDateChooserFacilityBookingPropertyChange(evt);
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

        jButton3.setText("Waiting guest");
        jButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Book FACILITY");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPaneBookFacilityLayout = new javax.swing.GroupLayout(jLayeredPaneBookFacility);
        jLayeredPaneBookFacility.setLayout(jLayeredPaneBookFacilityLayout);
        jLayeredPaneBookFacilityLayout.setHorizontalGroup(
            jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBookFacilityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneBookFacilityLayout.createSequentialGroup()
                        .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxFacilityType, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelFacilityType))
                        .addGap(28, 28, 28)
                        .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooserFacilityBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxBookingHour, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneBookFacilityLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton1))
                    .addGroup(jLayeredPaneBookFacilityLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPaneBookFacilityLayout.setVerticalGroup(
            jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBookFacilityLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelFacilityType)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jComboBoxBookingHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserFacilityBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxFacilityType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPaneBookFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPaneBookFacilityLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton2)
                        .addGap(27, 27, 27)
                        .addComponent(jButton3)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jLayeredPaneBookFacility.setLayer(jComboBoxFacilityType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jDateChooserFacilityBooking, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jLabelFacilityType, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookFacility.setLayer(jComboBoxBookingHour, javax.swing.JLayeredPane.DEFAULT_LAYER);

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

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 226, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jLayeredPaneSearchFacilityLayout = new javax.swing.GroupLayout(jLayeredPaneSearchFacility);
        jLayeredPaneSearchFacility.setLayout(jLayeredPaneSearchFacilityLayout);
        jLayeredPaneSearchFacilityLayout.setHorizontalGroup(
            jLayeredPaneSearchFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneSearchFacilityLayout.createSequentialGroup()
                .addGroup(jLayeredPaneSearchFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPaneSearchFacilityLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPaneSearchFacilityLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPaneSearchFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPaneSearchFacilityLayout.createSequentialGroup()
                                .addComponent(jTextFieldGuestIDSearchFacilities, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(jButtonSearchGuestFacilities))
                            .addComponent(jLabelGuestIDSearchFacilities)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 1353, Short.MAX_VALUE))
        );
        jLayeredPaneSearchFacilityLayout.setVerticalGroup(
            jLayeredPaneSearchFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneSearchFacilityLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelGuestIDSearchFacilities)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPaneSearchFacilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldGuestIDSearchFacilities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchGuestFacilities))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jLayeredPaneSearchFacility.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneSearchFacility.setLayer(jTextFieldGuestIDSearchFacilities, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneSearchFacility.setLayer(jLabelGuestIDSearchFacilities, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneSearchFacility.setLayer(jButtonSearchGuestFacilities, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneSearchFacility.setLayer(jLayeredPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButtonRemoveIN.setText("Remove INSTRUCTOR");
        jButtonRemoveIN.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonRemoveINActionPerformed(evt);
            }
        });

        jButtonBookIN.setText("Book INSTRUCTOR");
        jButtonBookIN.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonBookINActionPerformed(evt);
            }
        });

        jTextFieldGuestNameInstructor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextFieldGuestNameInstructorActionPerformed(evt);
            }
        });

        jLabel4.setText("Guest Number");

        jButtonBookIN1.setText("Search");
        jButtonBookIN1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonBookIN1ActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(jTableShowBookings);

        javax.swing.GroupLayout jLayeredPaneBookInstructorLayout = new javax.swing.GroupLayout(jLayeredPaneBookInstructor);
        jLayeredPaneBookInstructor.setLayout(jLayeredPaneBookInstructorLayout);
        jLayeredPaneBookInstructorLayout.setHorizontalGroup(
            jLayeredPaneBookInstructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBookInstructorLayout.createSequentialGroup()
                .addGroup(jLayeredPaneBookInstructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneBookInstructorLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addComponent(jTextFieldGuestNameInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonBookIN1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPaneBookInstructorLayout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPaneBookInstructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonBookIN, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRemoveIN, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(395, Short.MAX_VALUE))
        );
        jLayeredPaneBookInstructorLayout.setVerticalGroup(
            jLayeredPaneBookInstructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneBookInstructorLayout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jLayeredPaneBookInstructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldGuestNameInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBookIN1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPaneBookInstructorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneBookInstructorLayout.createSequentialGroup()
                        .addComponent(jButtonBookIN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveIN))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(178, 178, 178))
        );
        jLayeredPaneBookInstructor.setLayer(jButtonRemoveIN, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookInstructor.setLayer(jButtonBookIN, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookInstructor.setLayer(jTextFieldGuestNameInstructor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookInstructor.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookInstructor.setLayer(jButtonBookIN1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookInstructor.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jButton5.setText("Add Facility");
        jButton5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Facility type");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(jComboBoxAddFacility, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAddFacility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jLabel3))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Manage Facility", jPanel2);

        jLabel5.setText("Name");

        jButton6.setText("Add instructor");
        jButton6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInstructorName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxChoseTypeInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldInstructorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxChoseTypeInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Instructor", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 145, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jLayeredPaneAdminLayout = new javax.swing.GroupLayout(jLayeredPaneAdmin);
        jLayeredPaneAdmin.setLayout(jLayeredPaneAdminLayout);
        jLayeredPaneAdminLayout.setHorizontalGroup(
            jLayeredPaneAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneAdminLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPaneAdminLayout.setVerticalGroup(
            jLayeredPaneAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPaneAdminLayout.createSequentialGroup()
                .addGroup(jLayeredPaneAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPaneAdminLayout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPaneAdminLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jLayeredPaneAdmin.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneAdmin.setLayer(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTableBookingDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String []
            {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableBookingDetails.setEnabled(false);
        jScrollPane3.setViewportView(jTableBookingDetails);

        jButton4.setText("Back");
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
                .addGap(26, 26, 26)
                .addGroup(jLayeredPaneBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jLayeredPaneBookingDetailsLayout.setVerticalGroup(
            jLayeredPaneBookingDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPaneBookingDetailsLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(177, 177, 177))
        );
        jLayeredPaneBookingDetails.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPaneBookingDetails.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenuBookInstructor.setText("Guest");

        jMenuItem2.setText("Book a Facility");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenuBookInstructor.add(jMenuItem2);

        jMenuBar.add(jMenuBookInstructor);

        jMenuAdmin.setText("Admin");

        jMenuItem4.setText("Add instructor/facility");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuAdmin.add(jMenuItem4);

        jMenuItem3.setText("Book an Instructor");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenuAdmin.add(jMenuItem3);

        jMenuBar.add(jMenuAdmin);

        jMenuTest.setText("test");

        jMenuItem1.setText("Search For Booked Facilities");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuTest.add(jMenuItem1);

        jMenuBar.add(jMenuTest);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLayeredPaneBookFacility, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1304, Short.MAX_VALUE))
                    .addComponent(jLayeredPaneSearchFacility))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(212, 212, 212)
                    .addComponent(jLayeredPaneBookInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(489, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(206, Short.MAX_VALUE)
                    .addComponent(jLayeredPaneAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(743, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLayeredPaneBookingDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLayeredPaneBookFacility, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPaneSearchFacility))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(136, 136, 136)
                    .addComponent(jLayeredPaneBookInstructor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(391, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(101, Short.MAX_VALUE)
                    .addComponent(jLayeredPaneAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(428, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLayeredPaneBookingDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

        facArray = control.getFacArrayForJlist(type, dd, getSelectedHour());

        for (int i = 0; i < facArray.size(); i++)
        {
            Facility facility = facArray.get(i);
            String facstring = control.getString(facility);
            model.addElement(facstring);
        }
        jListAvailableFacilities.setModel(model);
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

        control.createFacilityBooking(fac, type, username, dd, getSelectedHour(), 0);

// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
    {//GEN-HEADEREND:event_jButton3ActionPerformed

        int typeIndex = jListAvailableFacilities.getSelectedIndex();
        Facility fac = facArray.get(typeIndex);
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

        swicthPane(jLayeredPaneBookFacility, currentPane);
// TODO add your handling code here:


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        swicthPane(jLayeredPaneSearchFacility, currentPane);
        // TODO add your handling code here:

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

            int bookingid = control.getBookingno(facid.getFacID(), jDateChooserFacilityBooking.getDate(), getSelectedHour());
            System.out.println("bookingid: " + bookingid);
            ArrayList<Booking> arrayBookingInfo = control.getBookingDetails(bookingid);

            System.out.println(arrayBookingInfo.size());

            Object[][] twoArray = new Object[arrayBookingInfo.size()][3];
            String[] names =
            {
                "GuestNO", "WaitingPosition", "Instructor"
            };
            for (int i = 0; i < arrayBookingInfo.size(); i++)
            {

                Booking booking = arrayBookingInfo.get(i);
                twoArray[i][0] = booking.getGuestno();
                twoArray[i][1] = booking.getWaitingpos();
                twoArray[i][2] = booking.getInno();

//                String resutl = "" + booking.getGuestno() + " Waiting: " + booking.getWaitingpos() + " instructor: " + booking.getInno();
//                System.out.println(resutl);
//                infoOfBookingModel.addElement(resutl);
            }
            DefaultTableModel tm = new DefaultTableModel(twoArray, names);
            jTableBookingDetails.setModel(tm);
            swicthPane(jLayeredPaneBookingDetails, currentPane);
        }


    }//GEN-LAST:event_jListAvailableFacilitiesMouseClicked


    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem3ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem3ActionPerformed

        swicthPane(jLayeredPaneBookInstructor, currentPane);
//        jLayeredPane1.setVisible(false);
//        jLayeredPane2.setVisible(false);
//        jLayeredPaneBookingDetails.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jButtonBookIN1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBookIN1ActionPerformed
    {//GEN-HEADEREND:event_jButtonBookIN1ActionPerformed
        model.clear();
        updateJtable(jTextFieldGuestNameInstructor.getText());

        bookingsarray = control.getBookingList(jTextFieldGuestNameInstructor.getText());
        for (int i = 0; i < bookingsarray.size(); i++)
        {
            Booking booking = bookingsarray.get(i);
            //   String facstring = control.getString(booking);
            model.addElement(booking.getBookingId() + " " + booking.getType() + " " + booking.getInno());
        }
//        jListShowInfoOnreservation.setModel(model);

    }//GEN-LAST:event_jButtonBookIN1ActionPerformed

    private void jTextFieldGuestNameInstructorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextFieldGuestNameInstructorActionPerformed
    {//GEN-HEADEREND:event_jTextFieldGuestNameInstructorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldGuestNameInstructorActionPerformed

    private void jButtonBookINActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonBookINActionPerformed
    {//GEN-HEADEREND:event_jButtonBookINActionPerformed

        int index = jTableShowBookings.getSelectedRow();

        Booking booking = arr.get(index);

        System.out.println(booking.toString());

        boolean hasInstructor = control.saveInstructorBooking(booking, jTextFieldGuestNameInstructor.getText());
        bookingsArrayForInstructor = control.getFacArrayForShowingAvailableInstructor(booking.getType(), booking.getBookingdate(), booking.getBookingtime(), username);

        //--CHECK IF THERE IS OR IS NOT INSTRUCTOR ALREAADY
        // boolean checkResult = control.checkInstructorAlreadyThere(dd, hour, username);
        if (bookingsArrayForInstructor.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "There is NO AVAILABLE INSTRUCTOR ");
        }
        else if (hasInstructor != true)
        {
            JOptionPane.showMessageDialog(null, "You have INSTRUCTOR already");
        }
        else if (hasInstructor == true)
        {
            JOptionPane.showMessageDialog(null, "Instructor booked");
            updateJtable(jTextFieldGuestNameInstructor.getText());

        }

//        if (!bookingsArrayForInstructor.isEmpty())
//        {
//            System.out.println("Gettin 0: " + bookingsArrayForInstructor.get(0));
//
//            //    Booking booking = bookingsArrayForInstructor.get(0); 
//            //booking = bookingsarray.get(0);
////        for (int i = 0; i < bookingsArrayForInstructor.size(); i++)
////        {
//            Booking tempBooking = bookingsArrayForInstructor.get(0);
//            int tempBookingInno = tempBooking.getInno();
//            //String user = tempBooking.getGuestno();
//            int tempBookingID = 0;
//            for (int j = 0; j < bookingsarray.size(); j++)
//            {
//                Booking tempBooking1 = bookingsarray.get(j);
//                tempBookingID = tempBooking1.getBookingId();
//            }
//
//            Booking booking = new Booking(tempBookingID, tempBookingInno, username);
//        }
//            control.saveInstructorBooking(booking);
        System.out.println("DONE");


    }//GEN-LAST:event_jButtonBookINActionPerformed

    private void jButtonRemoveINActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonRemoveINActionPerformed
    {//GEN-HEADEREND:event_jButtonRemoveINActionPerformed
        int index = jTableShowBookings.getSelectedRow();
        Booking booking = arr.get(index);

        boolean instructorRemoved = control.removeInstructor(booking.getBookingId(), jTextFieldGuestNameInstructor.getText());
        if (instructorRemoved)
        {
            JOptionPane.showMessageDialog(null, "Instructor succesfully removed");
            updateJtable(jTextFieldGuestNameInstructor.getText());
        }
    }//GEN-LAST:event_jButtonRemoveINActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton6ActionPerformed
    {//GEN-HEADEREND:event_jButton6ActionPerformed
        control.addInstructor(jTextFieldInstructorName.getText(), jComboBoxChoseTypeInstructor.getSelectedItem().toString());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton5ActionPerformed
    {//GEN-HEADEREND:event_jButton5ActionPerformed
        control.createNewFacility(jComboBoxAddFacility.getSelectedItem().toString());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jDateChooserFacilityBookingPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_jDateChooserFacilityBookingPropertyChange
    {//GEN-HEADEREND:event_jDateChooserFacilityBookingPropertyChange
        if (jDateChooserFacilityBooking.getDate() != null)
        {
            populateComboBox();
        }
    }//GEN-LAST:event_jDateChooserFacilityBookingPropertyChange

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem4ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem4ActionPerformed
        swicthPane(jLayeredPaneAdmin, currentPane);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
    {//GEN-HEADEREND:event_jButton4ActionPerformed
        swicthPane(jLayeredPaneBookFacility, currentPane);
    }//GEN-LAST:event_jButton4ActionPerformed

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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonBookIN;
    private javax.swing.JButton jButtonBookIN1;
    private javax.swing.JButton jButtonRemoveIN;
    private javax.swing.JButton jButtonSearchGuestFacilities;
    private javax.swing.JComboBox jComboBoxAddFacility;
    private javax.swing.JComboBox jComboBoxBookingHour;
    private javax.swing.JComboBox jComboBoxChoseTypeInstructor;
    private javax.swing.JComboBox jComboBoxFacilityType;
    private com.toedter.calendar.JDateChooser jDateChooserFacilityBooking;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelFacilityType;
    private javax.swing.JLabel jLabelGuestIDSearchFacilities;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPaneAdmin;
    private javax.swing.JLayeredPane jLayeredPaneBookFacility;
    private javax.swing.JLayeredPane jLayeredPaneBookInstructor;
    private javax.swing.JLayeredPane jLayeredPaneBookingDetails;
    private javax.swing.JLayeredPane jLayeredPaneSearchFacility;
    private javax.swing.JList jListAvailableFacilities;
    private javax.swing.JList jListSearchGuestFacilities;
    private javax.swing.JMenu jMenuAdmin;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuBookInstructor;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenu jMenuTest;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableBookingDetails;
    private javax.swing.JTable jTableShowBookings;
    private javax.swing.JTextField jTextFieldGuestIDSearchFacilities;
    private javax.swing.JTextField jTextFieldGuestNameInstructor;
    private javax.swing.JTextField jTextFieldInstructorName;
    // End of variables declaration//GEN-END:variables

}
