/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Brand;
import Classes.Booking;
import Classes.Car;
import Classes.Customer;
import java.awt.Color;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author estan
 */
public class Form_Booking_Edit_Remove extends javax.swing.JFrame {
    
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255,212,60));
    Border upper_border = BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(255,212,60));
    Border panel_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);

    /**
     * Creates new form Form_Booking_Edit_Remove
     */
    
    Car car = new Car();
    Brand brands = new Brand();
    HashMap<Integer ,String> Brandmap = brands.brandsHashMap();
    HashMap<Integer ,String> Vehiclemap = car.VehicleHashMap();
    
    Classes.Brand brand = new Classes.Brand();
    ArrayList<Classes.Brand> brands_list = brands.brandsList();
    int locationIndex = 0;
     
    Classes.Location location = new Classes.Location();
    ArrayList<Classes.Location>locations_list = location.locationList();
    int customerIndex = 0;
    
    Classes.Customer customer = new Classes.Customer();
    ArrayList<Classes.Customer>customer_list = customer.CustomerList();
    int index = 0;
    
    Booking booking = new Booking();
    
    public Form_Booking_Edit_Remove() {
        initComponents();
        
        //set border in bookcarpanel
        jLabel_select_car.setBorder(border);
        jLabel_select_customer.setBorder(border);
        jLabel_rent_details.setBorder(border);
        jLabel_dropoff.setBorder(upper_border);
        
        jPanel_select_customer.setBorder(panel_border);
        jPanel_select_car.setBorder(panel_border);
        jPanel_rent_details.setBorder(panel_border);
        
        this.setLocationRelativeTo(null);
        populateComboBoxBrands();
    }
    
    // create a static function to display customer id and name
        public static void displayCustomer(String id, String name)
        {
            jTextField_customer.setText(name);
            jLabel_customer_id.setText(id);
        }
        // create a static function to display customer id and name
        public static void displayCarInfo(String id, String model)
        {
        jLabel_car_id.setText(id);
        jLabel_car_model.setText(model);
        }
        
        public static void displayCarInfo(String id, String model, String price ){
            jLabel_car_id.setText(id);
            jLabel_car_model.setText(model);
            jLabel_pricePerDay.setText(price);
            jLabel_totalFee.setText(price);
        }
        
        public static void displayBooking(String id, String car_id, String customer_id, String start_date, String end_date, String total_price, String driver, String namedriverName)
        {
            /*
            id, car_id, customer_id, start_date, end_date, total_price, driver, driverName
            */
            jTextField_customer.setText(new Customer().getCustomerById(Integer.valueOf(customer_id)).getFullname());
            jLabel_booking_id.setText(car_id);
            jLabel_car_id.setText(car_id);
            
            jLabel_car_model.setText(new Car().getCarById(Integer.valueOf(car_id)).getModel());
            jLabel_pricePerDay.setText(String.valueOf(new Car().getCarById(Integer.valueOf(car_id)).getPrice()));
            jLabel_Brand_Id.setText(String.valueOf(new Car().getCarById(Integer.valueOf(car_id)).getBrand()));
            
           //jComboBox_Brands_.setSelectedItem(new Brand().getBrandById(new Car().getCarById(Integer.valueOf(car_id)).getBrand()));

            
            jLabel_customer_id.setText(customer_id);
            jLabel_totalFee.setText(total_price);
            
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            jDateChooser_Start_Date.setDate(dateformat.parse(start_date));
            jDateChooser_End_Date.setDate(dateformat.parse(end_date));
        } catch (ParseException ex) {
            Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
    
    
    // pupulated the jComboBox_brands

    
    public void populateComboBoxBrands()
    {
        for ( String s : Brandmap.values())
        {
            jComboBox_Brands_.addItem(s);
            
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookCarPanel = new javax.swing.JPanel();
        jPanel_rent_details = new javax.swing.JPanel();
        jLabel_rent_details = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jDateChooser_Start_Date = new com.toedter.calendar.JDateChooser();
        jLabel_dropoff = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jDateChooser_End_Date = new com.toedter.calendar.JDateChooser();
        jLabel_dropoff1 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel_totalFee = new javax.swing.JLabel();
        jPanel_select_customer = new javax.swing.JPanel();
        jLabel_select_customer = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField_customer = new javax.swing.JTextField();
        jLabel_customer_id = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton_Select_Customer = new javax.swing.JButton();
        jRadioButton_withDriver = new javax.swing.JRadioButton();
        jRadioButton_selfDrive = new javax.swing.JRadioButton();
        jComboBox_DriverList = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jButton_RemoveBooking_ = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_brands_logo = new javax.swing.JLabel();
        jLabel_close1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton_BookingLIst_ = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel_booking_id = new javax.swing.JLabel();
        jPanel_select_car = new javax.swing.JPanel();
        jLabel_select_car = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboBox_Brands_ = new javax.swing.JComboBox<>();
        jButton_select_car_ = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel_car_id = new javax.swing.JLabel();
        jLabel_car_model = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel_pricePerDay = new javax.swing.JLabel();
        jLabel_Brand_Id = new javax.swing.JLabel();
        jButton_EditBooking_ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bookCarPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel_rent_details.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_rent_details.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_rent_details.setText("Rent Details");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Date:");

        jLabel_dropoff.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_dropoff.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_dropoff.setText("     End of Rent");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Date:");

        jLabel_dropoff1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_dropoff1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_dropoff1.setText("     Start of Rent");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Total Price:");

        jLabel_totalFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_totalFee.setText("###");

        javax.swing.GroupLayout jPanel_rent_detailsLayout = new javax.swing.GroupLayout(jPanel_rent_details);
        jPanel_rent_details.setLayout(jPanel_rent_detailsLayout);
        jPanel_rent_detailsLayout.setHorizontalGroup(
            jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_rent_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_dropoff1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel_dropoff, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jDateChooser_Start_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                        .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser_End_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_totalFee, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel_rent_detailsLayout.setVerticalGroup(
            jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel_rent_details, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_dropoff1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser_Start_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addComponent(jLabel_dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel41)
                    .addComponent(jDateChooser_End_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel_totalFee))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel_select_customer.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_select_customer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_select_customer.setText("Select Customer");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Cutomer Name:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Customer ID:");

        jTextField_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_customerActionPerformed(evt);
            }
        });

        jLabel_customer_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_customer_id.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_customer_id.setText("000");

        jButton1.setText("Enter Customer Details");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton_Select_Customer.setText("Select Customer");
        jButton_Select_Customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Select_CustomerActionPerformed(evt);
            }
        });

        jRadioButton_withDriver.setText("With Driver");
        jRadioButton_withDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_withDriverActionPerformed(evt);
            }
        });

        jRadioButton_selfDrive.setText("Self Drive");
        jRadioButton_selfDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_selfDriveActionPerformed(evt);
            }
        });

        jComboBox_DriverList.setToolTipText("");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Driver's  Name:");

        javax.swing.GroupLayout jPanel_select_customerLayout = new javax.swing.GroupLayout(jPanel_select_customer);
        jPanel_select_customer.setLayout(jPanel_select_customerLayout);
        jPanel_select_customerLayout.setHorizontalGroup(
            jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                        .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton_Select_Customer))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_selfDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_withDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                        .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_customer_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_DriverList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_select_customerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_select_customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel_select_customerLayout.setVerticalGroup(
            jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_select_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel_customer_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_DriverList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jRadioButton_withDriver))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Select_Customer)
                    .addComponent(jRadioButton_selfDrive))
                .addGap(17, 17, 17))
        );

        jButton_RemoveBooking_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_RemoveBooking_.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_RemoveBooking_.setText("Remove/Cancel Booking");
        jButton_RemoveBooking_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_RemoveBooking_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveBooking_ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 212, 60));

        jLabel4.setBackground(new java.awt.Color(255, 212, 60));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Booking Edit/ Remove");

        jLabel_close1.setBackground(new java.awt.Color(255, 212, 60));
        jLabel_close1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_close1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close1.setText("X");
        jLabel_close1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_close1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Select Booking:");

        jButton_BookingLIst_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_BookingLIst_.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_BookingLIst_.setText("Booking List");
        jButton_BookingLIst_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_BookingLIst_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BookingLIst_ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel34.setText("ID:");

        jLabel_booking_id.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel_booking_id.setText("00");

        jLabel_select_car.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_select_car.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_select_car.setText("Select a Car");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Brand:");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Vehicle:");

        jComboBox_Brands_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_Brands_ActionPerformed(evt);
            }
        });

        jButton_select_car_.setText("Select Car");
        jButton_select_car_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_select_car_MouseClicked(evt);
            }
        });
        jButton_select_car_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_select_car_ActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("ID:");

        jLabel_car_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_car_id.setText("000");

        jLabel_car_model.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_car_model.setText("###");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Model:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Price/Day:");

        jLabel_pricePerDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_pricePerDay.setText("###");

        jLabel_Brand_Id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_Brand_Id.setText("###");

        javax.swing.GroupLayout jPanel_select_carLayout = new javax.swing.GroupLayout(jPanel_select_car);
        jPanel_select_car.setLayout(jPanel_select_carLayout);
        jPanel_select_carLayout.setHorizontalGroup(
            jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_select_car, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_select_carLayout.createSequentialGroup()
                        .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_select_carLayout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(14, 14, 14)
                                .addComponent(jButton_select_car_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_car_id)
                                .addGap(83, 83, 83))
                            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_Brands_, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_select_carLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_car_model, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_pricePerDay, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel_select_carLayout.setVerticalGroup(
            jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_select_car, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Brands_, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Brand_Id)
                    .addComponent(jLabel31))
                .addGap(20, 20, 20)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jButton_select_car_)
                    .addComponent(jLabel30)
                    .addComponent(jLabel_car_id))
                .addGap(2, 2, 2)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel_car_model))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel_pricePerDay))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jButton_BookingLIst_, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_booking_id)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel_select_car, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jButton_BookingLIst_)
                    .addComponent(jLabel34)
                    .addComponent(jLabel_booking_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_select_car, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jButton_EditBooking_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_EditBooking_.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_EditBooking_.setText("Edit Booking");
        jButton_EditBooking_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_EditBooking_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditBooking_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bookCarPanelLayout = new javax.swing.GroupLayout(bookCarPanel);
        bookCarPanel.setLayout(bookCarPanelLayout);
        bookCarPanelLayout.setHorizontalGroup(
            bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(bookCarPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_select_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_rent_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(bookCarPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_EditBooking_, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_RemoveBooking_, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bookCarPanelLayout.setVerticalGroup(
            bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookCarPanelLayout.createSequentialGroup()
                        .addComponent(jPanel_rent_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_RemoveBooking_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_EditBooking_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookCarPanelLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel_select_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookCarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bookCarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RemoveBooking_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveBooking_ActionPerformed
        // remove / cancel booking
        // booking id 
        int id = Integer.valueOf(jLabel_booking_id.getText());
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this booking?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        
//        if (confirm == JOptionPane.YES_OPTION)
//        {
//           booking.removeBooking(id);
//        }
    }//GEN-LAST:event_jButton_RemoveBooking_ActionPerformed

    private void jButton_BookingLIst_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BookingLIst_ActionPerformed
        // display  the  booking  list
        Form_BookingList bookinglist = new Form_BookingList();
        bookinglist.setVisible(true);
    }//GEN-LAST:event_jButton_BookingLIst_ActionPerformed

    private void jComboBox_Brands_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_Brands_ActionPerformed
        // Get the selected brands id
        int brands_id = 0;
        for (Map.Entry<Integer, String> entry : Brandmap.entrySet())
        {
            if(entry.getValue().equals(jComboBox_Brands_.getSelectedItem()))
            {
                brands_id = entry.getKey();
            }
        }
        jLabel_Brand_Id.setText(String.valueOf(brands_id));
    }//GEN-LAST:event_jComboBox_Brands_ActionPerformed

    private void jButton_select_car_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_select_car_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_select_car_MouseClicked

    private void jButton_select_car_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_select_car_ActionPerformed

        // get the brand id
        int brand_id = Integer.valueOf(jLabel_Brand_Id.getText());

        // show the cars list form
//        Form_CarsListByBrand frm_cars_brand = new Form_CarsListByBrand(brand_id, "edit");
     //   frm_cars_brand.setVisible(true);
    }//GEN-LAST:event_jButton_select_car_ActionPerformed

    private void jTextField_customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_customerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_customerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // will show the Form_Customer so that
        Form_Customer form_cstmr = new Form_Customer();
        form_cstmr.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_Select_CustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Select_CustomerActionPerformed
        // show customers list
        Form_CustomersList frm_cst_list = new Form_CustomersList("edit");
        frm_cst_list.setVisible(true);
    }//GEN-LAST:event_jButton_Select_CustomerActionPerformed

    private void jRadioButton_withDriverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_withDriverActionPerformed
        // TODO add your handling code here:

        jComboBox_DriverList.setEnabled(true);  // Enable driver list
        jComboBox_DriverList.setVisible(true);

    }//GEN-LAST:event_jRadioButton_withDriverActionPerformed

    private void jRadioButton_selfDriveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_selfDriveActionPerformed
        // TODO add your handling code here:
        jComboBox_DriverList.setEnabled(false); // Disable driver list
        jComboBox_DriverList.setVisible(false);
    }//GEN-LAST:event_jRadioButton_selfDriveActionPerformed

    private void jLabel_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_close1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabel_close1MouseClicked

    private void jButton_EditBooking_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditBooking_ActionPerformed
        // edit booking

        try {
            // booking id 
            int id = Integer.valueOf(jLabel_booking_id.getText());
            int car_id = Integer.valueOf(jLabel_car_id.getText());
            int customer_id = Integer.valueOf(jLabel_customer_id.getText());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String pickup_date = dateFormat.format(jDateChooser_Start_Date.getDate());
            String dropoff_date = dateFormat.format(jDateChooser_End_Date.getDate());

            Date pickupDate = dateFormat.parse(pickup_date);
            Date dropoffDate = dateFormat.parse(dropoff_date);

            long diffInMillis = dropoffDate.getTime() - pickupDate.getTime();
            long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
            if (diffInDays <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid rental period. Drop-off date must be after the pick-up date.");
                return;
            }
            Car car = new Car();
            car = car.getCarById(car_id);
            int pricePerDay = car.getPrice();
            if (pricePerDay <= 0) {
                JOptionPane.showMessageDialog(null, "Invalid price per day for the selected car.");
                return;
            }
            jLabel_pricePerDay.setText("" + pricePerDay);
            int totalPrice = (int) diffInDays * pricePerDay;
            Customer customer = new Customer();
            ArrayList<Customer> customerList = customer.CustomerList();
            boolean validCustomer = customerList.stream().anyMatch(c -> c.getId() == customer_id);
            if (!validCustomer) {
                JOptionPane.showMessageDialog(null, "Invalid Customer ID!");
                return;
            }
            String driver = "";
            String driverName = "";

            if (jRadioButton_withDriver.isSelected()) {
                driver = "With Driver";
                driverName = jComboBox_DriverList.getSelectedItem().toString();

                // Check if the driver name is valid
                Form_Drivers driverForm = new Form_Drivers();
                ArrayList<Classes.Drivers> driverList = driverForm.driver_list;
                if (driverList == null || driverList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Driver list is empty or not loaded!");
                    return;
                }
            } else if (jRadioButton_selfDrive.isSelected()) {
                driver = "Self Drive";
                driverName = "";
            }
            Booking booking = new Booking();
            booking.addNewBooking(
                car_id,
                customer_id,
                pickup_date,
                dropoff_date,
                totalPrice,
                driver,
                driverName
            );

            JOptionPane.showMessageDialog(null, "Edited Successfully! Total Price: $" + totalPrice);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton_EditBooking_ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Booking_Edit_Remove.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Booking_Edit_Remove().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookCarPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_BookingLIst_;
    private javax.swing.JButton jButton_EditBooking_;
    private javax.swing.JButton jButton_RemoveBooking_;
    private javax.swing.JButton jButton_Select_Customer;
    private javax.swing.JButton jButton_select_car_;
    public static javax.swing.JComboBox<String> jComboBox_Brands_;
    private javax.swing.JComboBox<String> jComboBox_DriverList;
    public static com.toedter.calendar.JDateChooser jDateChooser_End_Date;
    public static com.toedter.calendar.JDateChooser jDateChooser_Start_Date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private static javax.swing.JLabel jLabel_Brand_Id;
    private static javax.swing.JLabel jLabel_booking_id;
    private javax.swing.JLabel jLabel_brands_logo;
    private static javax.swing.JLabel jLabel_car_id;
    private static javax.swing.JLabel jLabel_car_model;
    private javax.swing.JLabel jLabel_close1;
    private static javax.swing.JLabel jLabel_customer_id;
    private javax.swing.JLabel jLabel_dropoff;
    private javax.swing.JLabel jLabel_dropoff1;
    private static javax.swing.JLabel jLabel_pricePerDay;
    private javax.swing.JLabel jLabel_rent_details;
    private javax.swing.JLabel jLabel_select_car;
    private javax.swing.JLabel jLabel_select_customer;
    private static javax.swing.JLabel jLabel_totalFee;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_rent_details;
    private javax.swing.JPanel jPanel_select_car;
    private javax.swing.JPanel jPanel_select_customer;
    private javax.swing.JRadioButton jRadioButton_selfDrive;
    private javax.swing.JRadioButton jRadioButton_withDriver;
    private static javax.swing.JTextField jTextField_customer;
    // End of variables declaration//GEN-END:variables
}
