/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;



import java.awt.Color;
import Classes.Brand;
import Classes.Booking;
import Classes.BookingHistory;
import Classes.Car;
import Classes.Customer;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.raven.chart.LegendItem;
import com.raven.chart.ModelChart;
import com.raven.chart.ModelLegend;
import com.raven.chart.LabelColor;
import com.raven.chart.blankchart.BlankPlotChart;
import com.raven.chart.blankchart.BlankPlotChatRender;
import com.raven.chart.blankchart.SeriesSize;
import fordesign.RoundedButton;
import fordesign.RoundedPanel;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import static java.lang.Short.decode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import javax.swing.table.JTableHeader;
 import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import google.chart.ModelPieChart;
import google.chart.WebPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;
import fordesign.RoundedCornerComboBoxTest;
import fordesign.RoundedTextField;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import raven.table.TableGradientCell;
/**  
 *
 * @author estan
 */
public class Home extends javax.swing.JFrame {
    Color DefaultColor, ClickedColor;
    
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255,255,255));
    Border default_border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(142,202,230));
    Border upper_border = BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(254, 79, 45));
    Border panel_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
    
    private javax.swing.JRadioButton jRadioButton_SelfDrive;
    private javax.swing.JRadioButton jRadioButton_WithDriver;
    private javax.swing.JPanel jPanel_Driver;
    private javax.swing.JComboBox<String> jComboBox_Driver;
    private javax.swing.JLabel jLabel_DriverFee;
    private static Object JTextField_customer;
    private static Object JTextField_customer_id;
    //private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    
    // ito ay para sa chart
    private List<ModelLegend> legends = new ArrayList<>();
    private List<ModelChart> model = new ArrayList<>();
    private final int seriesSize = 12;
    private final int seriesSpace = 6;
    private WebPanel web;
    /**
     * Creates new form Home
     */
    
    Classes.Car car = new Classes.Car();
    Brand brands = new Brand();
    ArrayList<Classes.Car> carsList = car.carsList();
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
    
    Classes.Booking booking = new Classes.Booking();
    ArrayList<Classes.Booking>  bookingList = booking.bookingList();
   ///ArrayList<Classes.Customer>  bookingList = booking.bookingList();
    //ArrayList<Customer> CustomerList()
    
    Classes.Drivers drivers = new Classes.Drivers();
    ArrayList<Classes.Drivers>driver_list = drivers.DriversList();
    int driverIndex = 0;
    
    long availableCarsCount = car.countAvailableCars();
    long maintenanceCarsCount = car.countMaintenanceCars();
    
    Booking book = new Booking();
    ArrayList<Classes.Booking> book_list = booking.bookingList();
    
    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(250, 250, 250));

        
        
        DefaultColor = new Color(142,202,230);
        ClickedColor = new Color(87, 180, 186);
        //Radio button
        ButtonGroup btn_group = new ButtonGroup();
        btn_group.add(jRadioButton_Automatic);
        btn_group.add(jRadioButton_Manual);
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
        populateTopCustomers();
        timeSet();
        //set border in bookcarpanel
        jLabel_select_car.setBorder(border);
        jLabel_select_customer.setBorder(border);
        jLabel_rent_details.setBorder(border);
        jLabel_dropoff.setBorder(border);
       
        
        
        // show counts                                                                                                                                                                                                                                                                                    
        jLabel_cars_count.setText(String.valueOf(carsList.size()));
        jLabel_customers_count.setText(String.valueOf(customer_list.size()));
        jLabel_book_count.setText(String.valueOf(bookingList.size()));
        jLabel_available_count.setText(String.valueOf(availableCarsCount));
        jLabel_maintenance_count.setText(String.valueOf(maintenanceCarsCount));

         HomeTabLabel.setBorder(border);
         jSpinner_damage_Fee.setVisible(false);
         jLabel51.setVisible(false);
         jRadioButton_with_Damage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Show damage fee spinner when 'With Damage' is selected
            jSpinner_damage_Fee.setEnabled(true);
            jLabel51.setVisible(true);
            }
        });

        jRadioButton_without_Damage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            // Hide damage fee spinner when 'Without Damage' is selected
            jSpinner_damage_Fee.setEnabled(false);
            jLabel51.setVisible(false);
            }
        });
        for (int year = 2020; year <= LocalDate.now().getYear(); year++) {
            jComboBox_Year.addItem(String.valueOf(year));
        }


populateYearComboBox();
        
    jComboBox_Year.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String selectedYear = (String) jComboBox_Year.getSelectedItem();
        if (selectedYear != null && !selectedYear.equals("Select Year")) {
            updateChartByYear(Integer.parseInt(selectedYear));
        }
    }
});


    } // ITO ANG KATAPUSAN NG HOME BRACKET
                 
       public void updateChartByYear(int year) {
    chart.clearChart(); // Clear previous chart data

    ArrayList<Brand.BrandBookingCount> chartData = Brand.getTopNBookedBrandsFromHistory(3, year);

    if (chartData.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No booking data found for the year " + year, "No Data", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    Color[] topColors = {
        new Color(244, 67, 54),    // red
        new Color(33, 150, 243),   // blue
        new Color(76, 175, 80)     // green
    };

    int n = 3;
    for (int i = 0; i < n; i++) {
        if (i < chartData.size()) {
            chart.addLegend(chartData.get(i).brand, topColors[i]);
        } else {
            chart.addLegend("", topColors[i]);
        }
    }

    double[] bookingsArray = new double[n];
    for (int i = 0; i < n; i++) {
        bookingsArray[i] = (i < chartData.size()) ? chartData.get(i).count : 0;
    }

    chart.addData(new ModelChart("Top Brands in " + year, bookingsArray));
}





    
    
        public void populateYearComboBox() {
    jComboBox_Year.removeAllItems(); // Clear any previous items

    // Add placeholder
    jComboBox_Year.addItem("Select Year");

    // Get current year
    int currentYear = java.time.Year.now().getValue();

    // Add recent 10 years
    for (int i = 0; i < 10; i++) {
        jComboBox_Year.addItem(String.valueOf(currentYear - i));
    }

    // Optionally select placeholder by default
    jComboBox_Year.setSelectedIndex(0);
}

    
    public void clearChart() {
    model.clear();   // clear all model data
    legends.clear(); // clear legends
  // blankPlotChart.clear(); // if BlankPlotChart has its own clear method
    repaint();
    revalidate();
}

    
    
                public void timeSet() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy - hh:mm a");
                String formattedDateTime = now.format(formatter);
                SwingUtilities.invokeLater(() -> datetime.setText(formattedDateTime));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
                
                 public void populateTopCustomers() {
    // Get top customers
    ArrayList<BookingHistory.CustomerBookingCount> topCustomers = BookingHistory.getTop5Customers();

    if (topCustomers.size() > 0) {
        for (int i = 0; i < topCustomers.size(); i++) {
            BookingHistory.CustomerBookingCount customer = topCustomers.get(i);

            switch (i) {
                case 0:
                    nameLabel.setText(customer.customerName);
                    badge.setText(getBadge(customer.totalBookings));
                    badge.setBackground(getBadgeColor(customer.totalBookings));
                    count.setText(String.valueOf(customer.totalBookings));

                    break;
                case 1:
                    nameLabel1.setText(customer.customerName);
                    badge1.setText(getBadge(customer.totalBookings));
                    badge1.setBackground(getBadgeColor(customer.totalBookings));
                    count1.setText(String.valueOf(customer.totalBookings));
                    break;
                case 2:
                    nameLabel2.setText(customer.customerName);
                    badge2.setText(getBadge(customer.totalBookings));
                    badge2.setBackground(getBadgeColor(customer.totalBookings));
                    count2.setText(String.valueOf(customer.totalBookings));
                    break;
                case 3:
                    nameLabel3.setText(customer.customerName);
                    badge3.setText(getBadge(customer.totalBookings));
                    badge3.setBackground(getBadgeColor(customer.totalBookings));
                    count3.setText(String.valueOf(customer.totalBookings));
                    break;
                case 4:
                    nameLabel4.setText(customer.customerName);
                    badge4.setText(getBadge(customer.totalBookings));
                    badge4.setBackground(getBadgeColor(customer.totalBookings));
                    count4.setText(String.valueOf(customer.totalBookings));
                    break;
            }
        }
    } else {
        // If no customers, you can clear all labels
        nameLabel1.setText("No Data");
        badge1.setText("-");
        count1.setText("0");

        nameLabel2.setText("");
        badge2.setText("-");
        count2.setText("0");

        nameLabel3.setText("");
        badge3.setText("-");
        count3.setText("0");

        nameLabel4.setText("");
        badge4.setText("-");
        count4.setText("0");

        nameLabel.setText("");
        badge.setText("-");
        count.setText("0");
    }
}


        
                private String getBadge(int bookings) {
                if (bookings >= 10) {
                    return "VIP";
                } else if (bookings >= 5) {
                    return "Frequent";
                } else {
                    return "Regular";
                }
            }

            private Color getBadgeColor(int bookings) {
                if (bookings >= 10) {
                    return new Color(255, 193, 7); // Yellow
                } else if (bookings >= 5) {
                    return new Color(33, 150, 243); // Blue
                } else {
                    return new Color(76, 175, 80); // Green
                }
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
            jLabel_totalFee.setText("$" +price);
        }
    
    
    
    // pupulated the jComboBox_brands

    
    public void populateComboBoxBrands()
    {
        for ( String s : Brandmap.values())
        {
            jComboBox_brand.addItem(s);
            jComboBox_Brands_.addItem(s);
        }
    }
    
    private void populateDriverComboBox() {
    // Create an instance of the Drivers class
    Classes.Drivers drivers = new Classes.Drivers();
    ArrayList<Classes.Drivers> driverList = drivers.DriversList();

    // Check if the driver list is empty
    if (driverList == null || driverList.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No drivers available to display!");
        return;
    }

    // Add driver names to the ComboBox
    jComboBox_DriverList.removeAllItems(); // Clear any existing items
    for (Classes.Drivers driver : driverList) {
        jComboBox_DriverList.addItem(driver.getFullname());
    }
}

    
    
    
    public boolean carVerify()
    { 
        String model = jTextField_Model.getText();
        String _plateNum = jTextField_Model.getText();
        
        if(!model.trim().equals("") && !_plateNum.trim().equals(""))
        {
            return true;
        }
        else 
        {
            JOptionPane.showMessageDialog(null , "Enter a Valid Model and Plate Number" , "Invalid Info", 2);
             return false;
        }
    }
    
    // create resizable image to fit in jLabel
    public void displayImage(int width, int height, String image_path, JLabel label){

        // get the image
        var imageIco = new ImageIcon(image_path);
        // resize the icon
        Image image = imageIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set the image into JLabel
        label.setIcon(new ImageIcon(image));
    }
    
      // create resizable byte image to fit in jLabel
    public void displayByteImage(int width, int height, byte[] image_byte, JLabel label){

        // get the image
        var imageIco = new ImageIcon(image_byte);
        // resize the icon
        Image image = imageIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set the image into JLabel
        label.setIcon(new ImageIcon(image));
    }
    
    // create a function to select image
    // will return the image path
    // use images with a low size
    public String selectImage(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Picture");
        
        fileChooser.setCurrentDirectory(new File("c:\\images"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", ".png", ".jpg");
        fileChooser.addChoosableFileFilter(filter);
        
        int state = fileChooser.showSaveDialog(null);
        String path = "";
        
        if(state == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        
        
        return path;
    }
    
    // gagawa ng function para ipulate the jTable with brands (id & name )
    public void populateJtableWithBrands(){
        
       // Clear array List
       brands_list.clear();
       // populated arrayList
       brands_list = brands.brandsList();
       // JTable Columns
        String[] columnsName = {"ID", "Name"};
       // Rows
       Object[][] rows = new Object[brands_list.size()][columnsName.length];
       
       for (int i = 0; i < brands_list.size(); i++){
           
           rows[i][0] = brands_list.get(i).getId();
           rows[i][1] = brands_list.get(i).getName();
       }
       DefaultTableModel model = new DefaultTableModel(rows,columnsName);
       jTable_Brands.setModel(model);
       
    }
    
    public void displayBrand(){
        Classes.Brand selected_brand = brands_list.get(locationIndex);
        jSpinner_id.setValue(selected_brand.getId());
        jTextField_name.setText(selected_brand.getName());
        displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), selected_brand.getLogo(), jLabel_logo);
    }  

    
        // To Verify the empty fields
    public boolean driverVerify (String add_or_edit)
    {
        String fullname = jTextField_fullname.getText();
        String phone = jTextField_phone.getText();
        String email = jTextField_email.getText();
        String address = jTextArea_address.getText();
        boolean val = false;
        
        if(!fullname.trim().equals("") && !phone.trim().equals("") && !email.trim().equals("") && !address.trim().equals(""))
        {   val = true;    
        }
        else 
        {
            JOptionPane.showMessageDialog(null , "Enter Valid Driver info" , "Invalid Information", 2);
             val = false;
        }
        return val;
    }
    
    //create a function to display driver
    public void displayDriver()
    {
        Classes.Drivers selected_driver = driver_list.get(driverIndex);
        jSpinner_id.setValue(selected_driver.getId());
        jTextField_fullname.setText(selected_driver.getFullname());
        jTextField_phone.setText(selected_driver.getPhone());
        jTextField_email.setText(selected_driver.getEmail());
        jTextArea_address.setText(selected_driver.getAddress());
        
        Date bdate;  
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(selected_driver.getBirthdate());
            jDateChooser_Customer_birthDate.setDate(bdate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Form_Drivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // To Verify the empty fields
    public boolean brandVerify(String add_or_edit)
    {
        
        String name = jTextField_name.getText();
        String pic_path = jLabel_imagePath.getText();
        boolean val = false;
        
        if(!name.trim().equals(""))
        {
            if ("add".equals(add_or_edit))
            {
                
                if(pic_path.trim().equals(""))
                {
                    JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" , "Invalid Info", 2);
                    val = false;
                }
                else 
                {
                     val = true;    
                 }
            }
            if ("edit".equals(add_or_edit))
            {
                if (jLabel_logo.getIcon() == null)
                {
                    JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" , "Invalid Info", 2);
                    val = false;
                }
                else 
                {
                     val = true;    
                 }
                }
        }
        else 
        {
            JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" , "Invalid Info", 2);
             val = false;
        }
        return val;
    }
   
   
    // gagawa ng function para ipulate the jTable with brand (id & name )
    public void populateJtableWithCustomers(){
        
       // Clear array List
       customer_list.clear();
       // populated arrayList
       customer_list = customer.CustomerList();
       // JTable Columns
        String[] columnsName = {"ID", "full Name","BirthDate","Phone Number","Email","Address"};
       // Rows
       Object[][] rows = new Object[customer_list.size()][columnsName.length];
       
       for (int i = 0; i < customer_list.size(); i++){
           
           rows[i][0] = customer_list.get(i).getId();
           rows[i][1] = customer_list.get(i).getFullname();
           rows[i][2] = customer_list.get(i).getBirthdate();
           rows[i][3] = customer_list.get(i).getPhone();
           rows[i][4] = customer_list.get(i).getEmail();
           rows[i][5] = customer_list.get(i).getAddress();
       }
       DefaultTableModel customer = new DefaultTableModel(rows,columnsName);
       jTable_Customers_.setModel(customer);
       
    }
    
    
    
    // To Verify the empty fields
    public boolean customerVerify(String add_or_edit)
    {
        String fullname = jTextField_fullname.getText();
        String phone = jTextField_phone.getText();
        String email = jTextField_email.getText();
        String address = jTextArea_address.getText();
        boolean val = false;
        
        if(!fullname.trim().equals("") && !phone.trim().equals("") && !email.trim().equals("") && !address.trim().equals(""))
        {   val = true;    
        }
        else 
        {
            JOptionPane.showMessageDialog(null , "Enter Valid Customer info" , "Invalid Info", 2);
             val = false;
        }
        return val;
    }
    
    //create a function to display customer
    public void displayCustomer()
    {
        Classes.Customer selected_customer = customer_list.get(customerIndex);
        jSpinner_id.setValue(selected_customer.getId());
        jTextField_fullname.setText(selected_customer.getFullname());
        jTextField_phone.setText(selected_customer.getPhone());
        jTextField_email.setText(selected_customer.getEmail());
        jTextArea_address.setText(selected_customer.getAddress());
        
        Date bdate;  
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(selected_customer.getBirthdate());
            jDateChooser_Customer_birthDate.setDate(bdate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Form_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }
    
    public void filterCarTableByStatus(String status) {
    TableRowSorter<?> sorter = (TableRowSorter<?>) jTable_Cars.getRowSorter();
    if (status.equalsIgnoreCase("all")) {
        sorter.setRowFilter(null);  // Show all rows
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)^" + status + "$", 10)); // 10 is the index of the "Status" column
    }
}

    
     // gagawa ng function para ipulate the jTable with Vehicle
    public void populateJtableWithCars(){
        
      
       ArrayList<Car>cars_list = car.carsList();
       
        String[] columnsName = {"ID", "Brand","Model", "Type", "Fuel Type","Color", "Plate Number","Seats", "Transmission",
                                "Price", "Status"};

       // Rows
       Object[][] rows = new Object[cars_list.size()][columnsName.length];
       
       for (int i = 0; i < cars_list.size(); i++){
           
           rows[i][0] = cars_list.get(i).getId();
           rows[i][1] = cars_list.get(i).getBrand();
           rows[i][2] = cars_list.get(i).getModel();
           rows[i][3] = cars_list.get(i).getType();
           rows[i][4] = cars_list.get(i).getFuel();
           rows[i][5] = cars_list.get(i).getColor();
           rows[i][6] = cars_list.get(i).getplateNum_();
           rows[i][7] = cars_list.get(i).getPassengers();
           rows[i][8] = cars_list.get(i).getGearbox();
           rows[i][9] = cars_list.get(i).getPrice();
           rows[i][10] = cars_list.get(i).getStatus();
       }
       DefaultTableModel model = new DefaultTableModel(rows,columnsName);
        jTable_Cars.setModel(model);
        jTable_Cars.getColumnModel().getColumn(0).setMinWidth(0);
        jTable_Cars.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable_Cars.getColumnModel().getColumn(0).setWidth(0);
        jTable_Cars.getColumnModel().getColumn(0).setPreferredWidth(0);
        jTable_Cars.getColumnModel().getColumn(1).setPreferredWidth(125); // Brand
        jTable_Cars.getColumnModel().getColumn(2).setPreferredWidth(100); // Model
        jTable_Cars.getColumnModel().getColumn(3).setPreferredWidth(150); // Type
        jTable_Cars.getColumnModel().getColumn(4).setPreferredWidth(115); // Fuel Type
        jTable_Cars.getColumnModel().getColumn(5).setPreferredWidth(90); // Color
        jTable_Cars.getColumnModel().getColumn(6).setPreferredWidth(140);  // Plate number
        jTable_Cars.getColumnModel().getColumn(7).setPreferredWidth(70); // Seats
        jTable_Cars.getColumnModel().getColumn(8).setPreferredWidth(145); // Transmission
        jTable_Cars.getColumnModel().getColumn(9).setPreferredWidth(90); // Price
        jTable_Cars.getColumnModel().getColumn(10).setPreferredWidth(160); // Status
        
        jTable_Cars.setModel((TableModel) model);

        // Enable sorting and filtering
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable_Cars.setRowSorter(sorter);

        jComboBox_filter.addActionListener(e -> {
            String selectedStatus = jComboBox_filter.getSelectedItem().toString().toLowerCase();
            filterCarTableByStatus(selectedStatus.equals("all") ? "all" : selectedStatus);
        });
    }
    
        // gagawa ng function para ipulate the jTable with driver (id & name )
    public void populateJtableWithDrivers(){
        
       // Clear array List
       driver_list.clear();
       // populated arrayList
       driver_list = drivers.DriversList();
       // JTable Columns
        String[] columnsName = {"ID", "full Name","BirthDate","Phone Number","Email","Address"};
       // Rows
       Object[][] rows = new Object[driver_list.size()][columnsName.length];
       
       for (int i = 0; i < driver_list.size(); i++){
           
           rows[i][0] = driver_list.get(i).getId();
           rows[i][1] = driver_list.get(i).getFullname();
           rows[i][2] = driver_list.get(i).getBirthdate();
           rows[i][3] = driver_list.get(i).getPhone();
           rows[i][4] = driver_list.get(i).getEmail();
           rows[i][5] = driver_list.get(i).getAddress();
       }
       DefaultTableModel drivers = new DefaultTableModel(rows,columnsName);
       jTable_Drivers_.setModel(drivers);
       
    }
    
    
    public void populateJtableWithBooking() {
    // Get the list of bookings
    ArrayList<Classes.Booking> booking_list = booking.bookingList();

    String[] columnsName = {"car id","booking id", "Car Model", "Customer Name", "Start Date", "End Date", "Total Price", "Driver", "Driver Name", "Plate Number", "customer id"};

    // Prepare the rows for the table
    Object[][] rows = new Object[booking_list.size()][columnsName.length];
    for (int i = 0; i < booking_list.size(); i++) {
        Classes.Booking booking = booking_list.get(i);
        
        car = car.getCarById(booking.getCar_id());
        String carModel = car != null ? car.getModel() : "Unknown";
        String plateNumber = car != null ? car.getplateNum_() : "Unknown";
        
        Customer customer = new Customer();
        customer = customer.getCustomerById(booking.getCustomer_id());
        String customerName = customer != null ? customer.getFullname(): "Unknown";

        String driverName = booking.getDriverName();
        if (booking.getDriver().equals("With Driver") && driverName != null) {
            driverName = driverName;
        } else {
            driverName = "Self Drive";
        }

        rows[i][0] = car.getId();
        rows[i][1] = booking.getId();
        rows[i][2] = carModel;
        rows[i][3] = customerName;
        rows[i][4] = booking.getStart_date();
        rows[i][5] = booking.getEnd_date();
        rows[i][6] = booking.getTotal_price();
        rows[i][7] = booking.getDriver();
        rows[i][8] = driverName;
        rows[i][9] = plateNumber;
        rows[i][10] = customer.getId(); // customer id as hidden column
    }
    // Set table model with the updated rows
    jTable_Booked_vehicles.setModel(new DefaultTableModel(rows, columnsName));

    // Now, hide the 11th column (index 10)
    jTable_Booked_vehicles.getColumnModel().getColumn(10).setMinWidth(0);
    jTable_Booked_vehicles.getColumnModel().getColumn(10).setMaxWidth(0);
    jTable_Booked_vehicles.getColumnModel().getColumn(10).setWidth(0);
    jTable_Booked_vehicles.getColumnModel().getColumn(10).setPreferredWidth(0);
}
 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        homeTab = new javax.swing.JPanel();
        HomeTabLabel = new javax.swing.JLabel();
        vehicleTab = new javax.swing.JPanel();
        VehicleTabLabel = new javax.swing.JLabel();
        brandTab = new javax.swing.JPanel();
        BrandTabLabel = new javax.swing.JLabel();
        driverTab = new javax.swing.JPanel();
        DriversTabLabel = new javax.swing.JLabel();
        customerTab = new javax.swing.JPanel();
        CustomerTabLabel = new javax.swing.JLabel();
        bookCarTab = new javax.swing.JPanel();
        BookTabLabel = new javax.swing.JLabel();
        returnCarTab = new javax.swing.JPanel();
        ReturnTabLabel = new javax.swing.JLabel();
        datetime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jScrollPane10 = new raven.scroll.win11.ScrollPaneWin11();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new RoundedPanel(15);
        jLabel_book_count = new javax.swing.JLabel();
        jLabel_cars_logo3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel10 = new RoundedPanel(15);
        jLabel_cars_count = new javax.swing.JLabel();
        jLabel_cars_logo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel12 = new RoundedPanel(15);
        jLabel_customers_count = new javax.swing.JLabel();
        jLabel_cars_logo2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel14 = new RoundedPanel(15);
        jLabel_available_count = new javax.swing.JLabel();
        jLabel_cars_logo4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel16 = new RoundedPanel(15);
        jLabel_maintenance_count = new javax.swing.JLabel();
        jLabel_cars_logo5 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel8 = new RoundedPanel(15);
        chart = new com.raven.chart.Chart();
        jComboBox_Year = new javax.swing.JComboBox<>();
        jPanel9 = new RoundedPanel(15);
        jScrollPane3 = new raven.scroll.win11.ScrollPaneWin11();
        jTable_Cars = new javax.swing.JTable();
        jComboBox_filter = new javax.swing.JComboBox<>();
        panelTopCustomersList = new RoundedPanel(15);
        jLabel1 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        badge = new javax.swing.JLabel();
        nameLabel1 = new javax.swing.JLabel();
        badge1 = new javax.swing.JLabel();
        count1 = new javax.swing.JLabel();
        badge2 = new javax.swing.JLabel();
        nameLabel2 = new javax.swing.JLabel();
        count2 = new javax.swing.JLabel();
        count3 = new javax.swing.JLabel();
        nameLabel3 = new javax.swing.JLabel();
        badge3 = new javax.swing.JLabel();
        badge4 = new javax.swing.JLabel();
        count4 = new javax.swing.JLabel();
        nameLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel18 = new RoundedPanel(180);
        jPanel17 = new RoundedPanel(180);
        jPanel13 = new RoundedPanel(180);
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        brandPanel = new javax.swing.JPanel();
        jSpinner_id = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        jTextField_name = new RoundedTextField(15);
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel_logo = new javax.swing.JLabel();
        jButton_Edit = new javax.swing.JButton();
        jButton_Remove = new javax.swing.JButton();
        jButton_browse = new javax.swing.JButton();
        jButton_Refresh = new javax.swing.JButton();
        jButton_Clear = new javax.swing.JButton();
        jButton_First = new javax.swing.JButton();
        jButton_Next = new javax.swing.JButton();
        jButton_Previous = new javax.swing.JButton();
        jButton_Last = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Brands = new javax.swing.JTable();
        jButton_Add = new javax.swing.JButton();
        jLabel_imagePath = new javax.swing.JLabel();
        locationPanel = new javax.swing.JPanel();
        jSpinner_id3 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jTextField_fullname1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextField_phone1 = new javax.swing.JTextField();
        jTextField_email1 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea_address1 = new javax.swing.JTextArea();
        jLabel40 = new javax.swing.JLabel();
        jButton_Remove3 = new javax.swing.JButton();
        jButton_Edit3 = new javax.swing.JButton();
        jButton_Add3 = new javax.swing.JButton();
        jButton_Clear3 = new javax.swing.JButton();
        jButton_Refresh3 = new javax.swing.JButton();
        jButton_First3 = new javax.swing.JButton();
        jButton_Next3 = new javax.swing.JButton();
        jButton_Previous3 = new javax.swing.JButton();
        jButton_Last3 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_Drivers_ = new javax.swing.JTable();
        jDateChooser_Driver_birthDate = new com.toedter.calendar.JDateChooser();
        customerPanel = new javax.swing.JPanel();
        jButton_Last2 = new javax.swing.JButton();
        jButton_Previous2 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_Customers_ = new javax.swing.JTable();
        jButton_Next2 = new javax.swing.JButton();
        jButton_First2 = new javax.swing.JButton();
        jButton_Clear2 = new javax.swing.JButton();
        jButton_Refresh2 = new javax.swing.JButton();
        jButton_Edit2 = new javax.swing.JButton();
        jButton_Remove2 = new javax.swing.JButton();
        jButton_Add2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea_address = new javax.swing.JTextArea();
        jTextField_email = new javax.swing.JTextField();
        jTextField_phone = new javax.swing.JTextField();
        jTextField_fullname = new javax.swing.JTextField();
        jSpinner_id2 = new javax.swing.JSpinner();
        jLabel27 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jDateChooser_Customer_birthDate = new com.toedter.calendar.JDateChooser();
        bookCarPanel = new javax.swing.JPanel();
        jPanel_rent_details = new RoundedPanel(15);
        jLabel_rent_details = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel_dropoff = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel_dropoff1 = new javax.swing.JLabel();
        jButton_BookCar_ = new javax.swing.JButton();
        jLabel47 = new javax.swing.JLabel();
        jLabel_totalFee = new javax.swing.JLabel();
        jButton_BookingLIst_ = new javax.swing.JButton();
        jButton_Edit_Remove_Booking = new javax.swing.JButton();
        jButton_Edit_refresh_total_Price = new javax.swing.JButton();
        jButton_BookingLIst_1 = new javax.swing.JButton();
        jDateChooser_Pickup_Date = new com.toedter.calendar.JDateChooser();
        jDateChooser_dropoff = new com.toedter.calendar.JDateChooser();
        jPanel_select_car = new RoundedPanel(15);
        jLabel_select_car = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButton_select_car_ = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel_car_id = new javax.swing.JLabel();
        jLabel_car_model = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel_pricePerDay = new javax.swing.JLabel();
        jComboBox_Brands_ = new javax.swing.JComboBox<>();
        jPanel_select_customer = new RoundedPanel(15);
        jLabel_select_customer = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextField_customer = new RoundedTextField(15);
        jLabel_customer_id = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton_Select_Customer = new javax.swing.JButton();
        jRadioButton_withDriver = new javax.swing.JRadioButton();
        jRadioButton_selfDrive = new javax.swing.JRadioButton();
        jComboBox_DriverList = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        returnPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable_Booked_vehicles = new javax.swing.JTable();
        jButton_Return = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea_Vehicle_report = new javax.swing.JTextArea();
        jTextField_plateNumber = new javax.swing.JTextField();
        jSpinner_carId = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jDateChooser_retrunmentDate = new com.toedter.calendar.JDateChooser();
        jRadioButton_with_Damage = new javax.swing.JRadioButton();
        jRadioButton_without_Damage = new javax.swing.JRadioButton();
        jSpinner_damage_Fee = new javax.swing.JSpinner();
        jLabel51 = new javax.swing.JLabel();
        jSpinner_total_kilometer = new javax.swing.JSpinner();
        jLabel52 = new javax.swing.JLabel();
        vehiclePanel = new javax.swing.JPanel();
        jButton_Add_Car_ = new javax.swing.JButton();
        jButton_Add_Brands_List_ = new javax.swing.JButton();
        jButton_Reset_ = new javax.swing.JButton();
        jButton_Cars_List_ = new javax.swing.JButton();
        jButton_Remove_ = new javax.swing.JButton();
        jButton_Edit_ = new javax.swing.JButton();
        jButton_Cars_History_ = new javax.swing.JButton();
        jPanel5 = new RoundedPanel (15);
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSpinner_Id = new javax.swing.JSpinner();
        jComboBox_brand = new javax.swing.JComboBox<>();
        jTextField_Model = new RoundedTextField(15,15);
        jComboBox_VehicleType = new javax.swing.JComboBox<>();
        jComboBox_Fuel = new javax.swing.JComboBox<>();
        jComboBox_Color = new javax.swing.JComboBox<>();
        jButton_Search_ = new javax.swing.JButton();
        jLabel_Brand_Id = new javax.swing.JLabel();
        jPanel4 = new RoundedPanel(15);
        jRadioButton_Features_Aircond = new javax.swing.JRadioButton();
        jRadioButton_Features_AirBag = new javax.swing.JRadioButton();
        jRadioButton_Features_Sunroof = new javax.swing.JRadioButton();
        jRadioButton_Features_HeatedSeat = new javax.swing.JRadioButton();
        jRadioButton_Features_NavSys = new javax.swing.JRadioButton();
        jRadioButton_Features_Bluetooth = new javax.swing.JRadioButton();
        jRadioButton_Features_ElecWin = new javax.swing.JRadioButton();
        jRadioButton_Features_GPS = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new RoundedPanel(15);
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jRadioButton_Automatic = new javax.swing.JRadioButton();
        jRadioButton_Manual = new javax.swing.JRadioButton();
        int max = 10000;
        int min = 10;
        int step = 1;
        int i = 10;
        SpinnerModel spinner_model = new SpinnerNumberModel(i,min,max,step);
        jSpinner_Price = new javax.swing.JSpinner(spinner_model);
        int max_2 = 14;
        int min_2 = 2;
        int step_2 = 1;
        int i_2 = 2;
        SpinnerModel spinner_model_2 = new SpinnerNumberModel(i_2,min_2,max_2,step_2);
        jSpinner_Passengers = new javax.swing.JSpinner(spinner_model_2);
        jTextField_PlateNum = new RoundedTextField(15,15);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(238, 241, 249));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setLocationByPlatform(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(142, 202, 230));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 49));

        homeTab.setBackground(new java.awt.Color(142, 202, 230));
        homeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeTabMouseEntered(evt);
            }
        });

        HomeTabLabel.setBackground(new java.awt.Color(192, 192, 83));
        HomeTabLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        HomeTabLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HomeTabLabel.setText("HOME");

        javax.swing.GroupLayout homeTabLayout = new javax.swing.GroupLayout(homeTab);
        homeTab.setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HomeTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(HomeTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        vehicleTab.setBackground(new java.awt.Color(142, 202, 230));
        vehicleTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vehicleTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                vehicleTabMouseEntered(evt);
            }
        });

        VehicleTabLabel.setBackground(new java.awt.Color(192, 192, 83));
        VehicleTabLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        VehicleTabLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VehicleTabLabel.setText("VEHICLE");

        javax.swing.GroupLayout vehicleTabLayout = new javax.swing.GroupLayout(vehicleTab);
        vehicleTab.setLayout(vehicleTabLayout);
        vehicleTabLayout.setHorizontalGroup(
            vehicleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VehicleTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        vehicleTabLayout.setVerticalGroup(
            vehicleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(VehicleTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        brandTab.setBackground(new java.awt.Color(142, 202, 230));
        brandTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brandTabMouseClicked(evt);
            }
        });

        BrandTabLabel.setBackground(new java.awt.Color(192, 192, 83));
        BrandTabLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BrandTabLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BrandTabLabel.setText("BRAND");

        javax.swing.GroupLayout brandTabLayout = new javax.swing.GroupLayout(brandTab);
        brandTab.setLayout(brandTabLayout);
        brandTabLayout.setHorizontalGroup(
            brandTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BrandTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        brandTabLayout.setVerticalGroup(
            brandTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BrandTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        driverTab.setBackground(new java.awt.Color(142, 202, 230));
        driverTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                driverTabMouseClicked(evt);
            }
        });

        DriversTabLabel.setBackground(new java.awt.Color(192, 192, 83));
        DriversTabLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        DriversTabLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DriversTabLabel.setText("DRIVERS");

        javax.swing.GroupLayout driverTabLayout = new javax.swing.GroupLayout(driverTab);
        driverTab.setLayout(driverTabLayout);
        driverTabLayout.setHorizontalGroup(
            driverTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(driverTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DriversTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        driverTabLayout.setVerticalGroup(
            driverTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(driverTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(DriversTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        customerTab.setBackground(new java.awt.Color(142, 202, 230));
        customerTab.setForeground(new java.awt.Color(255, 255, 255));
        customerTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTabMouseClicked(evt);
            }
        });

        CustomerTabLabel.setBackground(new java.awt.Color(192, 192, 83));
        CustomerTabLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        CustomerTabLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CustomerTabLabel.setText("CUSTOMER");

        javax.swing.GroupLayout customerTabLayout = new javax.swing.GroupLayout(customerTab);
        customerTab.setLayout(customerTabLayout);
        customerTabLayout.setHorizontalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CustomerTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        customerTabLayout.setVerticalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CustomerTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        bookCarTab.setBackground(new java.awt.Color(142, 202, 230));
        bookCarTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookCarTabMouseClicked(evt);
            }
        });

        BookTabLabel.setBackground(new java.awt.Color(192, 192, 83));
        BookTabLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        BookTabLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        BookTabLabel.setText("BOOK CAR");

        javax.swing.GroupLayout bookCarTabLayout = new javax.swing.GroupLayout(bookCarTab);
        bookCarTab.setLayout(bookCarTabLayout);
        bookCarTabLayout.setHorizontalGroup(
            bookCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BookTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        bookCarTabLayout.setVerticalGroup(
            bookCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BookTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        returnCarTab.setBackground(new java.awt.Color(142, 202, 230));
        returnCarTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnCarTabMouseClicked(evt);
            }
        });

        ReturnTabLabel.setBackground(new java.awt.Color(192, 192, 83));
        ReturnTabLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ReturnTabLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ReturnTabLabel.setText("RETURN");

        javax.swing.GroupLayout returnCarTabLayout = new javax.swing.GroupLayout(returnCarTab);
        returnCarTab.setLayout(returnCarTabLayout);
        returnCarTabLayout.setHorizontalGroup(
            returnCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReturnTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                .addContainerGap())
        );
        returnCarTabLayout.setVerticalGroup(
            returnCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReturnTabLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        datetime.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        datetime.setText("jLabel55");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(datetime, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(homeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vehicleTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(driverTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookCarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(returnCarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(datetime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnCarTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookCarTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(driverTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brandTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vehicleTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(87, 180, 186));
        homePanel.setMinimumSize(new java.awt.Dimension(1250, 590));
        homePanel.setPreferredSize(new java.awt.Dimension(1250, 590));
        homePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane10.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel6.setBackground(new java.awt.Color(238, 241, 249));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel6.setPreferredSize(new java.awt.Dimension(1250, 1000));

        jPanel7.setBackground(new java.awt.Color(87, 180, 186));

        jLabel_book_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_book_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo3.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3.png"))); // NOI18N

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Booked");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel_book_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jLabel_cars_logo3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_cars_logo3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel_book_count, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(1, 85, 81));

        jLabel_cars_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_cars_count.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_cars_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1.png"))); // NOI18N

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vehicles");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_cars_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel_cars_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_cars_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel_cars_count, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel12.setBackground(new java.awt.Color(254, 79, 45));

        jLabel_customers_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_customers_count.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_customers_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2.png"))); // NOI18N

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Customers");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_customers_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_cars_logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_cars_logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_customers_count, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(2, 48, 71));

        jLabel_available_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_available_count.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_available_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo4.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/available.png"))); // NOI18N

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Available");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_available_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_cars_logo4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_cars_logo4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_available_count, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 183, 3));

        jLabel_maintenance_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_maintenance_count.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_maintenance_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo5.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maintenance.png"))); // NOI18N

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Maintenance");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_maintenance_count, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_cars_logo5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_cars_logo5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel_maintenance_count, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        chart.setBackground(new Color(255,255,255)); // Light gray

        jComboBox_Year.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2024", "2023", "2022", "2021" }));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jComboBox_Year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jTable_Cars.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_Cars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Cars.setToolTipText("");
        jTable_Cars.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Cars.setOpaque(false);
        jTable_Cars.setSelectionBackground(new java.awt.Color(102, 102, 102));
        jTable_Cars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CarsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable_Cars);
        //jTable_Cars.setBackground(new java.awt.Color(253, 251, 238 ));  // White background
        //jTable_Cars.setSelectionBackground(new java.awt.Color(204,204,204));  // Light blue selection
        //jTable_Cars.setSelectionForeground(new java.awt.Color(0,0,0));  // White text when selected
        //jTable_Cars.setGridColor(new java.awt.Color(204, 204, 204));  // Light gray grid lines
        //jTable_Cars.setForeground(new java.awt.Color(0, 0, 0));  // Black text for the table content
        //FlatLaf.registerCustomDefaultsSource("style");
        //FlatDarculaLaf.setup();
        jTable_Cars.setBackground(new Color(255,255,255));
        jTable_Cars.setBorder(null);

        jComboBox_filter.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jComboBox_filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Available", "Booked", "Maintenance" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox_filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        panelTopCustomersList.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Top Customers");

        nameLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel.setPreferredSize(new java.awt.Dimension(45, 16));

        count.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count.setPreferredSize(new java.awt.Dimension(45, 16));

        badge.setBackground(new java.awt.Color(255, 255, 255));
        badge.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        badge.setForeground(new java.awt.Color(255, 255, 255));
        badge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        badge.setOpaque(true);
        badge.setPreferredSize(new java.awt.Dimension(45, 16));

        nameLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nameLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel1.setPreferredSize(new java.awt.Dimension(45, 16));

        badge1.setBackground(new java.awt.Color(255, 255, 255));
        badge1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        badge1.setForeground(new java.awt.Color(255, 255, 255));
        badge1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        badge1.setOpaque(true);
        badge1.setPreferredSize(new java.awt.Dimension(45, 16));

        count1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        count1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count1.setPreferredSize(new java.awt.Dimension(45, 16));

        badge2.setBackground(new java.awt.Color(255, 255, 255));
        badge2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        badge2.setForeground(new java.awt.Color(255, 255, 255));
        badge2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        badge2.setOpaque(true);
        badge2.setPreferredSize(new java.awt.Dimension(45, 16));

        nameLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nameLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel2.setPreferredSize(new java.awt.Dimension(45, 16));

        count2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        count2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count2.setPreferredSize(new java.awt.Dimension(45, 16));

        count3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        count3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count3.setPreferredSize(new java.awt.Dimension(45, 16));

        nameLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nameLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel3.setPreferredSize(new java.awt.Dimension(45, 16));

        badge3.setBackground(new java.awt.Color(255, 255, 255));
        badge3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        badge3.setForeground(new java.awt.Color(255, 255, 255));
        badge3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        badge3.setOpaque(true);
        badge3.setPreferredSize(new java.awt.Dimension(45, 16));

        badge4.setBackground(new java.awt.Color(255, 255, 255));
        badge4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        badge4.setForeground(new java.awt.Color(255, 255, 255));
        badge4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        badge4.setOpaque(true);
        badge4.setPreferredSize(new java.awt.Dimension(45, 16));

        count4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        count4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        count4.setPreferredSize(new java.awt.Dimension(45, 16));

        nameLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nameLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameLabel4.setPreferredSize(new java.awt.Dimension(45, 16));

        jLabel2.setText("VIP");

        jLabel3.setText("Frequent");

        jLabel4.setText("Regular");

        jPanel18.setBackground(new java.awt.Color(255, 193, 7));
        jPanel18.setPreferredSize(new java.awt.Dimension(12, 12));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(33, 150, 243));
        jPanel17.setPreferredSize(new java.awt.Dimension(12, 12));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(76, 175, 80));
        jPanel13.setPreferredSize(new java.awt.Dimension(12, 12));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("NAME");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("BADGE");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("CARS");

        jButton2.setBackground(new java.awt.Color(33, 158, 188));
        jButton2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("View all customer");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTopCustomersListLayout = new javax.swing.GroupLayout(panelTopCustomersList);
        panelTopCustomersList.setLayout(panelTopCustomersListLayout);
        panelTopCustomersListLayout.setHorizontalGroup(
            panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                        .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                                        .addComponent(nameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(badge3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(count3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                                            .addComponent(nameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(badge2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(count2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTopCustomersListLayout.createSequentialGroup()
                                            .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(badge1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(count1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                                        .addComponent(nameLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(badge4, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(count4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopCustomersListLayout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(badge, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(count, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopCustomersListLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopCustomersListLayout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopCustomersListLayout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(75, 75, 75)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(70, 70, 70)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(39, 39, 39))))
        );
        panelTopCustomersListLayout.setVerticalGroup(
            panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopCustomersListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel54))
                .addGap(18, 18, 18)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(count, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(badge, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(count1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(badge1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(count2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(badge2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(count3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(badge3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(count4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(badge4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nameLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24)
                .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelTopCustomersListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelTopCustomersList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(99, 99, 99)
                                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(54, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelTopCustomersList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jScrollPane10.setViewportView(jPanel6);

        homePanel.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 590));

        jPanel2.add(homePanel, "card2");

        brandPanel.setBackground(new java.awt.Color(238, 241, 249));
        brandPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        brandPanel.setPreferredSize(new java.awt.Dimension(1250, 580));

        jSpinner_id.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel20.setText("ID:");

        jTextField_name.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nameActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel21.setText("Name:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel23.setText("Logo:");

        jLabel_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_logo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel_logo.setOpaque(true);

        jButton_Edit.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Edit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Edit.setText("Edit");
        jButton_Edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditActionPerformed(evt);
            }
        });

        jButton_Remove.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Remove.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Remove.setText("Remove");
        jButton_Remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RemoveActionPerformed(evt);
            }
        });

        jButton_browse.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jButton_browse.setText("Browse");
        jButton_browse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_browseActionPerformed(evt);
            }
        });

        jButton_Refresh.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Refresh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Refresh.setText("Refresh");
        jButton_Refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RefreshActionPerformed(evt);
            }
        });

        jButton_Clear.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Clear.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Clear.setText("Clear");
        jButton_Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
            }
        });

        jButton_First.setBackground(new java.awt.Color(254, 79, 45));
        jButton_First.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_First.setText("<<");
        jButton_First.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FirstActionPerformed(evt);
            }
        });

        jButton_Next.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Next.setText(">");
        jButton_Next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NextActionPerformed(evt);
            }
        });

        jButton_Previous.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Previous.setText("<");
        jButton_Previous.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PreviousActionPerformed(evt);
            }
        });

        jButton_Last.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Last.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Last.setText(">>");
        jButton_Last.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LastActionPerformed(evt);
            }
        });

        jTable_Brands.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Brands.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Brands.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_BrandsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Brands);
        jTable_Brands.setBackground(new java.awt.Color(255,255,255));  // White background
        jTable_Brands.setSelectionBackground(new java.awt.Color(102, 102, 255));  // Light blue selection
        jTable_Brands.setSelectionForeground(new java.awt.Color(255, 255, 255));  // White text when selected
        jTable_Brands.setGridColor(new java.awt.Color(204, 204, 204));  // Light gray grid lines
        jTable_Brands.setForeground(new java.awt.Color(0, 0, 0));  // Black text for the table content

        jButton_Add.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Add.setText("Add");
        jButton_Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout brandPanelLayout = new javax.swing.GroupLayout(brandPanel);
        brandPanel.setLayout(brandPanelLayout);
        brandPanelLayout.setHorizontalGroup(
            brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandPanelLayout.createSequentialGroup()
                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(151, 151, 151))
                    .addGroup(brandPanelLayout.createSequentialGroup()
                        .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(brandPanelLayout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel23))
                                .addGap(18, 18, 18)
                                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField_name, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_logo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(brandPanelLayout.createSequentialGroup()
                                .addGap(104, 104, 104)
                                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                                        .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(66, 66, 66))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                                        .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)))
                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(brandPanelLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150))))
        );
        brandPanelLayout.setVerticalGroup(
            brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(brandPanelLayout.createSequentialGroup()
                        .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(brandPanelLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jButton_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(brandPanelLayout.createSequentialGroup()
                                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_browse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(brandPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(140, 140, 140))
        );

        jPanel2.add(brandPanel, "card3");

        locationPanel.setBackground(new java.awt.Color(238, 241, 249));
        locationPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        locationPanel.setPreferredSize(new java.awt.Dimension(1250, 580));

        jSpinner_id3.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel7.setText("ID:");

        jLabel34.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel34.setText("Name:");

        jTextField_fullname1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel35.setText("Date of Birth:");

        jLabel37.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel37.setText("Phone:");

        jTextField_phone1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jTextField_email1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel39.setText("Email:");

        jTextArea_address1.setColumns(20);
        jTextArea_address1.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextArea_address1.setRows(5);
        jScrollPane2.setViewportView(jTextArea_address1);

        jLabel40.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel40.setText("Address:");

        jButton_Remove3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Remove3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Remove3.setText("Remove");
        jButton_Remove3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove3ActionPerformed(evt);
            }
        });

        jButton_Edit3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Edit3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Edit3.setText("Edit");
        jButton_Edit3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit3ActionPerformed(evt);
            }
        });

        jButton_Add3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Add3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Add3.setText("Add");
        jButton_Add3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add3ActionPerformed(evt);
            }
        });

        jButton_Clear3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Clear3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Clear3.setText("Clear");
        jButton_Clear3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Clear3ActionPerformed(evt);
            }
        });

        jButton_Refresh3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Refresh3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Refresh3.setText("Refresh");
        jButton_Refresh3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Refresh3ActionPerformed(evt);
            }
        });

        jButton_First3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_First3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_First3.setText("<<");
        jButton_First3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_First3ActionPerformed(evt);
            }
        });

        jButton_Next3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Next3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Next3.setText(">");
        jButton_Next3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Next3ActionPerformed(evt);
            }
        });

        jButton_Previous3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Previous3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Previous3.setText("<");
        jButton_Previous3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Previous3ActionPerformed(evt);
            }
        });

        jButton_Last3.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Last3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Last3.setText(">>");
        jButton_Last3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Last3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Last3ActionPerformed(evt);
            }
        });

        jTable_Drivers_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Drivers_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Drivers_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Drivers_MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable_Drivers_);
        jTable_Drivers_.setBackground(new java.awt.Color(255,255,255));  // White background
        jTable_Drivers_.setSelectionBackground(new java.awt.Color(102, 102, 255));  // Light blue selection
        jTable_Drivers_.setSelectionForeground(new java.awt.Color(255, 255, 255));  // White text when selected
        jTable_Drivers_.setGridColor(new java.awt.Color(204, 204, 204));  // Light gray grid lines
        jTable_Drivers_.setForeground(new java.awt.Color(0, 0, 0));  // Black text for the table content

        javax.swing.GroupLayout locationPanelLayout = new javax.swing.GroupLayout(locationPanel);
        locationPanel.setLayout(locationPanelLayout);
        locationPanelLayout.setHorizontalGroup(
            locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(locationPanelLayout.createSequentialGroup()
                        .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel35)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_fullname1)
                            .addGroup(locationPanelLayout.createSequentialGroup()
                                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner_id3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser_Driver_birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(locationPanelLayout.createSequentialGroup()
                        .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addGap(65, 65, 65)
                        .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                            .addComponent(jTextField_phone1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField_email1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(locationPanelLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jButton_Refresh3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Clear3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(locationPanelLayout.createSequentialGroup()
                                .addComponent(jButton_Remove3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Edit3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Add3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(744, 744, 744))
            .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locationPanelLayout.createSequentialGroup()
                    .addContainerGap(533, Short.MAX_VALUE)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(locationPanelLayout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, locationPanelLayout.createSequentialGroup()
                            .addComponent(jButton_First3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Next3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Previous3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jButton_Last3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)))
                    .addGap(91, 91, 91)))
        );
        locationPanelLayout.setVerticalGroup(
            locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationPanelLayout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_id3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_fullname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(locationPanelLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(13, 13, 13))
                    .addGroup(locationPanelLayout.createSequentialGroup()
                        .addComponent(jDateChooser_Driver_birthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField_phone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_email1)
                    .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Remove3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Edit3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Add3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Clear3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Refresh3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locationPanelLayout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Last3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Previous3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Next3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_First3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(122, 122, 122)))
        );

        jPanel2.add(locationPanel, "card4");

        customerPanel.setBackground(new java.awt.Color(238, 241, 249));
        customerPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        customerPanel.setPreferredSize(new java.awt.Dimension(1250, 580));

        jButton_Last2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Last2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Last2.setText(">>");
        jButton_Last2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Last2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Last2ActionPerformed(evt);
            }
        });

        jButton_Previous2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Previous2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Previous2.setText("<");
        jButton_Previous2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Previous2ActionPerformed(evt);
            }
        });

        jTable_Customers_.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Customers_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Customers_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Customers_MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_Customers_);
        jTable_Customers_.setBackground(new java.awt.Color(255,255,255));  // White background
        jTable_Customers_.setSelectionBackground(new java.awt.Color(102, 102, 255));  // Light blue selection
        jTable_Customers_.setSelectionForeground(new java.awt.Color(255, 255, 255));  // White text when selected
        jTable_Customers_.setGridColor(new java.awt.Color(204, 204, 204));  // Light gray grid lines
        jTable_Customers_.setForeground(new java.awt.Color(0, 0, 0));  // Black text for the table content

        jButton_Next2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Next2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Next2.setText(">");
        jButton_Next2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Next2ActionPerformed(evt);
            }
        });

        jButton_First2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_First2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_First2.setText("<<");
        jButton_First2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_First2ActionPerformed(evt);
            }
        });

        jButton_Clear2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Clear2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Clear2.setText("Clear");
        jButton_Clear2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Clear2ActionPerformed(evt);
            }
        });

        jButton_Refresh2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Refresh2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Refresh2.setText("Refresh");
        jButton_Refresh2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Refresh2ActionPerformed(evt);
            }
        });

        jButton_Edit2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Edit2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Edit2.setText("Edit");
        jButton_Edit2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit2ActionPerformed(evt);
            }
        });

        jButton_Remove2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Remove2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Remove2.setText("Remove");
        jButton_Remove2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove2ActionPerformed(evt);
            }
        });

        jButton_Add2.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Add2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Add2.setText("Add");
        jButton_Add2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add2ActionPerformed(evt);
            }
        });

        jTextArea_address.setColumns(20);
        jTextArea_address.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        jTextArea_address.setRows(5);
        jScrollPane5.setViewportView(jTextArea_address);

        jTextField_email.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N

        jTextField_phone.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jTextField_fullname.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jSpinner_id2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel27.setText("ID:");

        jLabel38.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel38.setText("Date of Birth:");

        jLabel43.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel43.setText("Name:");

        jLabel44.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel44.setText("Phone:");

        jLabel45.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel45.setText("Email:");

        jLabel46.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel46.setText("Address:");

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_First2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Next2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Previous2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Last2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(customerPanelLayout.createSequentialGroup()
                                .addComponent(jButton_Remove2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Edit2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton_Add2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                            .addComponent(jTextField_fullname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_phone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, customerPanelLayout.createSequentialGroup()
                                .addComponent(jSpinner_id2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 148, Short.MAX_VALUE)))
                        .addGap(752, 752, 752))
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(jDateChooser_Customer_birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jButton_Refresh2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customerPanelLayout.createSequentialGroup()
                    .addContainerGap(568, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(78, 78, 78)))
        );
        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_id2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13))
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(jDateChooser_Customer_birthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_phone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45))
                .addGap(14, 14, 14)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(18, 18, 18)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Remove2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Edit2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Add2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Refresh2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Last2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Previous2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Next2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_First2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
            .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customerPanelLayout.createSequentialGroup()
                    .addGap(31, 31, 31)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addGap(115, 115, 115)))
        );

        jPanel2.add(customerPanel, "card6");

        bookCarPanel.setBackground(new java.awt.Color(238, 241, 249));
        bookCarPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bookCarPanel.setPreferredSize(new java.awt.Dimension(1250, 580));

        jLabel_rent_details.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_rent_details.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_rent_details.setText("Rent Details");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Date:");

        jLabel_dropoff.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_dropoff.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_dropoff.setText("     Return Date");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Date:");

        jLabel_dropoff1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_dropoff1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel_dropoff1.setText("     Pick up Date");

        jButton_BookCar_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_BookCar_.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_BookCar_.setText("Book This Car");
        jButton_BookCar_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_BookCar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BookCar_ActionPerformed(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Total Price:");

        jLabel_totalFee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton_BookingLIst_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_BookingLIst_.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_BookingLIst_.setText("Booking List");
        jButton_BookingLIst_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_BookingLIst_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BookingLIst_ActionPerformed(evt);
            }
        });

        jButton_Edit_Remove_Booking.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Edit_Remove_Booking.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Edit_Remove_Booking.setText("Edit Booking");
        jButton_Edit_Remove_Booking.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit_Remove_Booking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit_Remove_BookingActionPerformed(evt);
            }
        });

        jButton_Edit_refresh_total_Price.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Edit_refresh_total_Price.setText("refresh");
        jButton_Edit_refresh_total_Price.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit_refresh_total_Price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit_refresh_total_PriceActionPerformed(evt);
            }
        });

        jButton_BookingLIst_1.setBackground(new java.awt.Color(254, 79, 45));
        jButton_BookingLIst_1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_BookingLIst_1.setText("Return List");
        jButton_BookingLIst_1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_BookingLIst_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BookingLIst_1ActionPerformed(evt);
            }
        });

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
                    .addComponent(jButton_Edit_refresh_total_Price)
                    .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooser_Pickup_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_totalFee, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                                .addComponent(jButton_Edit_Remove_Booking, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_BookingLIst_1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                                .addComponent(jButton_BookCar_, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jButton_BookingLIst_, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel_rent_detailsLayout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser_dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
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
                    .addComponent(jLabel36)
                    .addComponent(jDateChooser_Pickup_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel_dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel41)
                    .addComponent(jDateChooser_dropoff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jLabel_totalFee))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_Edit_refresh_total_Price)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_BookCar_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_BookingLIst_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_rent_detailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Edit_Remove_Booking, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_BookingLIst_1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jLabel_select_car.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_select_car.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_select_car.setText("Select a Car");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Vehicle:");

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

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Model:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Price/Day:");

        jLabel_pricePerDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jComboBox_Brands_.setToolTipText("");

        javax.swing.GroupLayout jPanel_select_carLayout = new javax.swing.GroupLayout(jPanel_select_car);
        jPanel_select_car.setLayout(jPanel_select_carLayout);
        jPanel_select_carLayout.setHorizontalGroup(
            jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_select_car, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_select_carLayout.createSequentialGroup()
                        .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel50)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_car_model, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_pricePerDay, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel_select_carLayout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                                .addComponent(jButton_select_car_)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_car_id))
                            .addComponent(jComboBox_Brands_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel_select_carLayout.setVerticalGroup(
            jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_select_car, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox_Brands_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jButton_select_car_)
                    .addComponent(jLabel30)
                    .addComponent(jLabel_car_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(jLabel_car_model))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(jLabel_pricePerDay))
                .addGap(26, 26, 26))
        );

        jLabel_select_customer.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel_select_customer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_select_customer.setText("Select Customer");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Cutomer Name:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
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

        buttonGroup1.add(jRadioButton_withDriver);
        jRadioButton_withDriver.setText("With Driver");
        jRadioButton_withDriver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_withDriverActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_selfDrive);
        jRadioButton_selfDrive.setText("Self Drive");
        jRadioButton_selfDrive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_selfDriveActionPerformed(evt);
            }
        });

        jComboBox_DriverList.setToolTipText("");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("Driver's  Name:");

        javax.swing.GroupLayout jPanel_select_customerLayout = new javax.swing.GroupLayout(jPanel_select_customer);
        jPanel_select_customer.setLayout(jPanel_select_customerLayout);
        jPanel_select_customerLayout.setHorizontalGroup(
            jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_select_customer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                        .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel_customer_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_DriverList, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton1)
                    .addComponent(jButton_Select_Customer)
                    .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jRadioButton_withDriver, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_selfDrive, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(202, Short.MAX_VALUE))
        );
        jPanel_select_customerLayout.setVerticalGroup(
            jPanel_select_customerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_select_customerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_select_customer, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                    .addComponent(jRadioButton_withDriver)
                    .addComponent(jRadioButton_selfDrive))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Select_Customer)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout bookCarPanelLayout = new javax.swing.GroupLayout(bookCarPanel);
        bookCarPanel.setLayout(bookCarPanelLayout);
        bookCarPanelLayout.setHorizontalGroup(
            bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarPanelLayout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel_select_car, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel_select_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jPanel_rent_details, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        bookCarPanelLayout.setVerticalGroup(
            bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel_rent_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bookCarPanelLayout.createSequentialGroup()
                        .addComponent(jPanel_select_car, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_select_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel2.add(bookCarPanel, "card7");

        returnPanel.setBackground(new java.awt.Color(238, 241, 249));
        returnPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        returnPanel.setPreferredSize(new java.awt.Dimension(1250, 580));

        jTable_Booked_vehicles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Booked_vehicles.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Booked_vehicles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Booked_vehiclesMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable_Booked_vehicles);
        jTable_Customers_.setBackground(new java.awt.Color(255,255,255));  // White background
        jTable_Customers_.setSelectionBackground(new java.awt.Color(102, 102, 255));  // Light blue selection
        jTable_Customers_.setSelectionForeground(new java.awt.Color(255, 255, 255));  // White text when selected
        jTable_Customers_.setGridColor(new java.awt.Color(204, 204, 204));  // Light gray grid lines
        jTable_Customers_.setForeground(new java.awt.Color(0, 0, 0));  // Black text for the table content

        jButton_Return.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Return.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Return.setText("Return");
        jButton_Return.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ReturnActionPerformed(evt);
            }
        });

        jTextArea_Vehicle_report.setColumns(20);
        jTextArea_Vehicle_report.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextArea_Vehicle_report.setRows(5);
        jScrollPane9.setViewportView(jTextArea_Vehicle_report);

        jTextField_plateNumber.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N

        jSpinner_carId.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel31.setText("ID:");

        jLabel42.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel42.setText("Drop off date:");

        jLabel48.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel48.setText("Plate Number:");

        jLabel53.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel53.setText("Vehicle Report:");

        buttonGroup1.add(jRadioButton_with_Damage);
        jRadioButton_with_Damage.setText("With Damage");

        buttonGroup1.add(jRadioButton_without_Damage);
        jRadioButton_without_Damage.setText("Without Damage");

        jSpinner_damage_Fee.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jSpinner_damage_Fee.setValue(1000);

        jLabel51.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel51.setText("Estimated Damage fee:");

        jSpinner_total_kilometer.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        jSpinner_total_kilometer.setValue(1000);

        jLabel52.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel52.setText("Total Kilometer:");

        javax.swing.GroupLayout returnPanelLayout = new javax.swing.GroupLayout(returnPanel);
        returnPanel.setLayout(returnPanelLayout);
        returnPanelLayout.setHorizontalGroup(
            returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(returnPanelLayout.createSequentialGroup()
                                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(returnPanelLayout.createSequentialGroup()
                                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jDateChooser_retrunmentDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField_plateNumber))
                                        .addGap(77, 77, 77))
                                    .addComponent(jSpinner_carId, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(returnPanelLayout.createSequentialGroup()
                                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(returnPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(jSpinner_total_kilometer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(returnPanelLayout.createSequentialGroup()
                                        .addComponent(jRadioButton_without_Damage)
                                        .addGap(46, 46, 46)
                                        .addComponent(jRadioButton_with_Damage)))
                                .addGap(158, 158, 158)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addGap(34, 34, 34)
                        .addComponent(jSpinner_damage_Fee, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(returnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Return, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9))
                .addGap(693, 693, 693))
        );
        returnPanelLayout.setVerticalGroup(
            returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, returnPanelLayout.createSequentialGroup()
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(returnPanelLayout.createSequentialGroup()
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(returnPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner_carId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                                    .addComponent(jTextField_plateNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel42))
                            .addGroup(returnPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jDateChooser_retrunmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner_total_kilometer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52))
                        .addGap(18, 18, 18)
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_with_Damage)
                            .addComponent(jRadioButton_without_Damage))
                        .addGap(31, 31, 31)
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(jSpinner_damage_Fee, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53))
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Return, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(105, 105, 105))
        );

        jPanel2.add(returnPanel, "card6");

        vehiclePanel.setBackground(new java.awt.Color(238, 241, 249));
        vehiclePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        vehiclePanel.setPreferredSize(new java.awt.Dimension(1250, 580));
        vehiclePanel.setRequestFocusEnabled(false);

        jButton_Add_Car_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Add_Car_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Car_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add_Car_.setText("Add");
        jButton_Add_Car_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Car_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Car_ActionPerformed(evt);
            }
        });

        jButton_Add_Brands_List_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Add_Brands_List_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Brands_List_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Add_Brands_List_.setText("Brands");
        jButton_Add_Brands_List_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Brands_List_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Brands_List_ActionPerformed(evt);
            }
        });

        jButton_Reset_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Reset_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Reset_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Reset_.setText("Reset");
        jButton_Reset_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Reset_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Reset_ActionPerformed(evt);
            }
        });

        jButton_Cars_List_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Cars_List_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Cars_List_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cars_List_.setText("Car List");
        jButton_Cars_List_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Cars_List_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cars_List_ActionPerformed(evt);
            }
        });

        jButton_Remove_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Remove_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Remove_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Remove_.setText("Remove");
        jButton_Remove_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove_.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_Remove_MouseClicked(evt);
            }
        });
        jButton_Remove_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove_ActionPerformed(evt);
            }
        });

        jButton_Edit_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Edit_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Edit_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Edit_.setText("Edit");
        jButton_Edit_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit_ActionPerformed(evt);
            }
        });

        jButton_Cars_History_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Cars_History_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Cars_History_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Cars_History_.setText("History");
        jButton_Cars_History_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Cars_History_.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jButton_Cars_History_ComponentAdded(evt);
            }
        });
        jButton_Cars_History_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cars_History_ActionPerformed(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("ID:");

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Brand:");

        jLabel26.setBackground(new java.awt.Color(102, 102, 102));
        jLabel26.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Type:");

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Model:");

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Fuel:");

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Color:");

        jSpinner_Id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Id.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBox_brand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_brand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_brandActionPerformed(evt);
            }
        });

        jTextField_Model.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Model.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_Model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ModelActionPerformed(evt);
            }
        });

        jComboBox_VehicleType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_VehicleType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sedan", "Hatchback", "SUV", "MPV", "Pickup", "Van", "Coupe", "Convertible", "Wagon", "Crossover", "Luxury", "Mini/Compact", "Sports Car", "Truck" }));
        jComboBox_VehicleType.setSelectedIndex(-1);
        jComboBox_VehicleType.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox_VehicleType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_VehicleTypeActionPerformed(evt);
            }
        });

        jComboBox_Fuel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Fuel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gas", "Diesel" }));
        jComboBox_Fuel.setSelectedIndex(-1);
        jComboBox_Fuel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBox_Color.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Color.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Blue", "Black", "White", "Silver", "Green", "Pink", "Purple", "Orange", "Brown", "Yellow" }));
        jComboBox_Color.setSelectedIndex(-1);
        jComboBox_Color.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton_Search_.setBackground(new java.awt.Color(254, 79, 45));
        jButton_Search_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Search_.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Search_.setText("Search");
        jButton_Search_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Search_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Search_ActionPerformed(evt);
            }
        });

        jLabel_Brand_Id.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel_Brand_Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Brand_Id.setText("#");
        jLabel_Brand_Id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jPanel4.setBackground(new java.awt.Color(1, 85, 81));

        jRadioButton_Features_Aircond.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_Aircond.setText("Air Conditioning");
        jRadioButton_Features_Aircond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_AircondActionPerformed(evt);
            }
        });

        jRadioButton_Features_AirBag.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_AirBag.setText("Air Bags");

        jRadioButton_Features_Sunroof.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_Sunroof.setText("Convertible");
        jRadioButton_Features_Sunroof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_SunroofActionPerformed(evt);
            }
        });

        jRadioButton_Features_HeatedSeat.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_HeatedSeat.setText("Heated Seats");

        jRadioButton_Features_NavSys.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_NavSys.setText("Navigation System");

        jRadioButton_Features_Bluetooth.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_Bluetooth.setText("Bluetooth");

        jRadioButton_Features_ElecWin.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_ElecWin.setText("Electric Windows");
        jRadioButton_Features_ElecWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_ElecWinActionPerformed(evt);
            }
        });

        jRadioButton_Features_GPS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton_Features_GPS.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton_Features_GPS.setText("GPS");
        jRadioButton_Features_GPS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Features_GPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_GPSActionPerformed(evt);
            }
        });

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 25)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(254, 79, 45));
        jLabel14.setText("Features");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_Features_AirBag, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_Features_Aircond, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_Features_Bluetooth, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_Features_NavSys))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_Features_HeatedSeat, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_Features_Sunroof, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton_Features_ElecWin, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton_Features_GPS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jRadioButton_Features_Aircond)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Features_AirBag))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Features_NavSys)
                            .addComponent(jRadioButton_Features_ElecWin)
                            .addComponent(jRadioButton_Features_HeatedSeat))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Features_Bluetooth)
                            .addComponent(jRadioButton_Features_GPS)
                            .addComponent(jRadioButton_Features_Sunroof))))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel12))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(75, 75, 75)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox_brand, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_VehicleType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_Model, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_Fuel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox_Color, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jSpinner_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton_Search_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jButton_Search_, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_VehicleType, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Color, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Plate Number:");

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Seats:");

        jLabel17.setBackground(new java.awt.Color(102, 102, 102));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Transmission:");

        jLabel18.setBackground(new java.awt.Color(102, 102, 102));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 23)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Price/Day:");

        jRadioButton_Automatic.setText("Automatic");
        jRadioButton_Automatic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Automatic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_AutomaticActionPerformed(evt);
            }
        });

        jRadioButton_Manual.setText("Manual");
        jRadioButton_Manual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Manual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_ManualActionPerformed(evt);
            }
        });

        jSpinner_Price.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Price.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinner_Passengers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Passengers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTextField_PlateNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_PlateNum.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_PlateNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_PlateNumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_PlateNum)
                    .addComponent(jSpinner_Passengers)
                    .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField_PlateNum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jSpinner_Passengers, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton_Manual)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(11, 11, 11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout vehiclePanelLayout = new javax.swing.GroupLayout(vehiclePanel);
        vehiclePanel.setLayout(vehiclePanelLayout);
        vehiclePanelLayout.setHorizontalGroup(
            vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton_Cars_History_, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton_Edit_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Add_Car_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                                        .addComponent(jButton_Remove_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton_Cars_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                                        .addComponent(jButton_Add_Brands_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton_Reset_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        vehiclePanelLayout.setVerticalGroup(
            vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_Add_Car_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Add_Brands_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_Reset_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Remove_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton_Edit_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton_Cars_List_, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Cars_History_, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(vehiclePanel, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1250, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTabMouseClicked
        homePanel.setVisible(true);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
        returnPanel.setVisible(false);
        
        HomeTabLabel.setBorder(border);
        BrandTabLabel.setBorder(default_border);
        VehicleTabLabel.setBorder(default_border);
        CustomerTabLabel.setBorder(default_border);
        DriversTabLabel.setBorder(default_border);
        BookTabLabel.setBorder(default_border);
        ReturnTabLabel.setBorder(default_border);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
        
        jLabel_cars_count.setText(String.valueOf(carsList.size()));
        jLabel_customers_count.setText(String.valueOf(customer_list.size()));
        jLabel_book_count.setText(String.valueOf(bookingList.size()));
        jLabel_available_count.setText(String.valueOf(availableCarsCount));
        jLabel_maintenance_count.setText(String.valueOf(maintenanceCarsCount));
    }//GEN-LAST:event_homeTabMouseClicked

    private void vehicleTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehicleTabMouseClicked
        vehiclePanel.setVisible(true);
        homePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
        returnPanel.setVisible(false);
        
        HomeTabLabel.setBorder(default_border);
        BrandTabLabel.setBorder(default_border);
        VehicleTabLabel.setBorder(border);
        CustomerTabLabel.setBorder(default_border);
        DriversTabLabel.setBorder(default_border);
        BookTabLabel.setBorder(default_border);
        ReturnTabLabel.setBorder(default_border);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
        
        jLabel_cars_count.setText(String.valueOf(carsList.size()));
        jLabel_customers_count.setText(String.valueOf(customer_list.size()));
        jLabel_book_count.setText(String.valueOf(bookingList.size()));
        jLabel_available_count.setText(String.valueOf(availableCarsCount));
        jLabel_maintenance_count.setText(String.valueOf(maintenanceCarsCount));
    }//GEN-LAST:event_vehicleTabMouseClicked

    private void brandTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brandTabMouseClicked
        brandPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
        returnPanel.setVisible(false);
        HomeTabLabel.setBorder(default_border);
        BrandTabLabel.setBorder(border);
        VehicleTabLabel.setBorder(default_border);
        CustomerTabLabel.setBorder(default_border);
        DriversTabLabel.setBorder(default_border);
        BookTabLabel.setBorder(default_border);
        ReturnTabLabel.setBorder(default_border);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
        
        jLabel_cars_count.setText(String.valueOf(carsList.size()));
        jLabel_customers_count.setText(String.valueOf(customer_list.size()));
        jLabel_book_count.setText(String.valueOf(bookingList.size()));
        jLabel_available_count.setText(String.valueOf(availableCarsCount));
        jLabel_maintenance_count.setText(String.valueOf(maintenanceCarsCount));
    }//GEN-LAST:event_brandTabMouseClicked

    private void driverTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_driverTabMouseClicked
        locationPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
        returnPanel.setVisible(false);
        
        HomeTabLabel.setBorder(default_border);
        BrandTabLabel.setBorder(default_border);
        VehicleTabLabel.setBorder(default_border);
        CustomerTabLabel.setBorder(default_border);
        DriversTabLabel.setBorder(border);
        BookTabLabel.setBorder(default_border);
        ReturnTabLabel.setBorder(default_border);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
        
        jLabel_cars_count.setText(String.valueOf(carsList.size()));
        jLabel_customers_count.setText(String.valueOf(customer_list.size()));
        jLabel_book_count.setText(String.valueOf(bookingList.size()));
        jLabel_available_count.setText(String.valueOf(availableCarsCount));
        jLabel_maintenance_count.setText(String.valueOf(maintenanceCarsCount));
    }//GEN-LAST:event_driverTabMouseClicked

    private void customerTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTabMouseClicked
        customerPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        bookCarPanel.setVisible(false);
        returnPanel.setVisible(false);
        
        HomeTabLabel.setBorder(default_border);
        BrandTabLabel.setBorder(default_border);
        VehicleTabLabel.setBorder(default_border);
        DriversTabLabel.setBorder(default_border);
        CustomerTabLabel.setBorder(border);
        BookTabLabel.setBorder(default_border);
        ReturnTabLabel.setBorder(default_border);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
        
        jLabel_cars_count.setText(String.valueOf(carsList.size()));
        jLabel_customers_count.setText(String.valueOf(customer_list.size()));
        jLabel_book_count.setText(String.valueOf(bookingList.size()));
        jLabel_available_count.setText(String.valueOf(availableCarsCount));
        jLabel_maintenance_count.setText(String.valueOf(maintenanceCarsCount));
    }//GEN-LAST:event_customerTabMouseClicked

    private void bookCarTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookCarTabMouseClicked
        bookCarPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        returnPanel.setVisible(false);
        
        HomeTabLabel.setBorder(default_border);
        BrandTabLabel.setBorder(default_border);
        VehicleTabLabel.setBorder(default_border);
        CustomerTabLabel.setBorder(default_border);
        DriversTabLabel.setBorder(default_border);
        BookTabLabel.setBorder(border);
        ReturnTabLabel.setBorder(default_border);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
    }//GEN-LAST:event_bookCarTabMouseClicked

    private void jComboBox_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_brandActionPerformed

        // Get the selected brands id
        // Initialize brand_id to a valid default value (or -1 to indicate no brand is selected).

        int brand_id = 0;
        for (Map.Entry<Integer, String> entry : Brandmap.entrySet())
        {
            if(entry.getValue().equals(jComboBox_brand.getSelectedItem()))
            {
                brand_id = entry.getKey();
            }
        }
        jLabel_Brand_Id.setText(String.valueOf(brand_id));
    }//GEN-LAST:event_jComboBox_brandActionPerformed

    private void jTextField_ModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ModelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ModelActionPerformed

    private void jRadioButton_Features_AircondActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_AircondActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_AircondActionPerformed

    private void jRadioButton_Features_SunroofActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_SunroofActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_SunroofActionPerformed

    private void jRadioButton_Features_ElecWinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_ElecWinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_ElecWinActionPerformed

    private void jRadioButton_Features_GPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_Features_GPSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_Features_GPSActionPerformed

    private void jButton_Add_Car_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_Car_ActionPerformed
        // TODO add your handling code here:
        // Add new Car
        // Car info
        String brand = jComboBox_brand.getSelectedItem().toString();
        String model = jTextField_Model.getText();
        String type = jComboBox_VehicleType.getSelectedItem().toString();
        String fuel = jComboBox_Fuel.getSelectedItem().toString();
        String color = jComboBox_Color.getSelectedItem().toString();
        String plateNum = jTextField_PlateNum.getText();
        int passengers = (int)jSpinner_Passengers.getValue();
        String gearbox = "automatic";
        //int price = Integer.valueOf(jSpinner_Price.getValue());
        int price = (int)(jSpinner_Price.getValue());

        // car features
        String air_cond = "no";
        String airbags = "no";
        String sunroof = "no";
        String heated_seats = "no";
        String nav_sys = "no";
        String bluetooth = "no";
        String elec_win = "no";
        String gps = "no";
        
        if (jRadioButton_Manual.isSelected()){gearbox = "manual";}

        if (jRadioButton_Features_AirBag.isSelected()){air_cond = "yes";}
        if (jRadioButton_Features_AirBag.isSelected()){airbags = "yes";}
        if (jRadioButton_Features_Sunroof.isSelected()){sunroof = "yes";}
        if (jRadioButton_Features_HeatedSeat.isSelected()){heated_seats = "yes";}
        if (jRadioButton_Features_Bluetooth.isSelected()){bluetooth = "yes";}
        if (jRadioButton_Features_NavSys.isSelected()){nav_sys = "yes";}
        if (jRadioButton_Features_ElecWin.isSelected()){elec_win = "yes";}
        if (jRadioButton_Features_GPS.isSelected()){gps = "yes";}

        /*
        */
        if (carVerify()){
            car.addCar(brand, model, type, fuel, color, plateNum, passengers, gearbox, price, air_cond,
                airbags, sunroof, heated_seats, nav_sys, bluetooth, elec_win, gps);
        }
    }//GEN-LAST:event_jButton_Add_Car_ActionPerformed

    private void jButton_Add_Brands_List_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_Brands_List_ActionPerformed
        // TODO add your handling code here:
        Form_BrandsList form_brdlist = new Form_BrandsList();
        form_brdlist.setVisible(true);
    }//GEN-LAST:event_jButton_Add_Brands_List_ActionPerformed

    private void jButton_Reset_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Reset_ActionPerformed
        // TODO add your handling code here:
        // To reset all fields

        jTextField_PlateNum.setText("");
        jTextField_Model.setText("");
        jSpinner_Price.setValue(100);
        jLabel_Brand_Id.setText("#");
        jComboBox_VehicleType.setSelectedIndex(-1);
        jSpinner_Passengers.setValue(-1);
        jComboBox_Fuel.setSelectedIndex(-1);
        jComboBox_Color.setSelectedIndex(-1);
        jComboBox_brand.setSelectedIndex(-1);
        jRadioButton_Automatic.setSelected(true);

        jRadioButton_Features_AirBag.setSelected(false);
        jRadioButton_Features_Aircond.setSelected(false);
        jRadioButton_Features_Bluetooth.setSelected(false);
        jRadioButton_Features_ElecWin.setSelected(false);
        jRadioButton_Features_GPS.setSelected(false);
        jRadioButton_Features_HeatedSeat.setSelected(false);
        jRadioButton_Features_NavSys.setSelected(false);
        jRadioButton_Features_Sunroof.setSelected(false);

    }//GEN-LAST:event_jButton_Reset_ActionPerformed

    private void jButton_Cars_List_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cars_List_ActionPerformed
        Form_CarsList form_crslst = new Form_CarsList();
        form_crslst.setVisible(true);
    }//GEN-LAST:event_jButton_Cars_List_ActionPerformed

    private void jButton_Remove_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_Remove_MouseClicked
        // Delete Car
        int id = (int)jSpinner_Id.getValue();
    }//GEN-LAST:event_jButton_Remove_MouseClicked

    private void jButton_Remove_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Remove_ActionPerformed
        // Delete Car

        int id = (int)jSpinner_Id.getValue();
        car.removeCar(id);
    }//GEN-LAST:event_jButton_Remove_ActionPerformed

    private void jButton_Edit_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit_ActionPerformed
        // Edit Car info
        // Car info
        int id = (int)jSpinner_Id.getValue();
        String brand = jComboBox_brand.getSelectedItem().toString();
        String model = jTextField_Model.getText();
        String type = jComboBox_VehicleType.getSelectedItem().toString();
        String fuel = jComboBox_Fuel.getSelectedItem().toString();
        String color = jComboBox_Color.getSelectedItem().toString();
        String plateNum = jTextField_PlateNum.getText();
        int passengers = (int)jSpinner_Passengers .getValue();
        String gearbox = "automatic";
        int price = (int)(jSpinner_Price.getValue());
        // car features
        String air_cond = "no";
        String airbags = "no";
        String sunroof = "no";
        String heated_seats = "no";
        String nav_sys = "no";
        String bluetooth = "no";
        String elec_win = "no";
        String gps = "no";

        if (jRadioButton_Manual.isSelected()){gearbox = "manual";}

        if (jRadioButton_Features_AirBag.isSelected()){air_cond = "yes";}
        if (jRadioButton_Features_AirBag.isSelected()){airbags = "yes";}
        if (jRadioButton_Features_Sunroof.isSelected()){sunroof = "yes";}
        if (jRadioButton_Features_HeatedSeat.isSelected()){heated_seats = "yes";}
        if (jRadioButton_Features_Bluetooth.isSelected()){bluetooth = "yes";}
        if (jRadioButton_Features_NavSys.isSelected()){nav_sys = "yes";}
        if (jRadioButton_Features_ElecWin.isSelected()){elec_win = "yes";}
        if (jRadioButton_Features_GPS.isSelected()){gps = "yes";}

        /*
        */
        if (carVerify()){
            car.editCar(id, brand, model, type, fuel, color, plateNum, passengers, gearbox, price, air_cond,
                airbags, sunroof, heated_seats, nav_sys, bluetooth, elec_win, gps);
        }
    }//GEN-LAST:event_jButton_Edit_ActionPerformed

    private void jTextField_PlateNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_PlateNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_PlateNumActionPerformed

    private void jRadioButton_AutomaticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_AutomaticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_AutomaticActionPerformed

    private void jRadioButton_ManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_ManualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton_ManualActionPerformed

    private void jButton_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EditActionPerformed
        // edit brands info

        try {
            int id = (int) jSpinner_id.getValue();
            String name = jTextField_name.getText();
            byte[] logo;

            if (jLabel_imagePath.getText().trim().equals("")){
                logo = brands.getBrandById(id).getLogo();
            }
            else {
                logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            }
            if (brandVerify("edit")){
                brands.editBrand(id, name,logo);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null , "Enter Valid Brand Data" + ex.getMessage() , "Invalid Data", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);;
        }
    }//GEN-LAST:event_jButton_EditActionPerformed

    private void jButton_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RemoveActionPerformed
        // remove brands

        int id = (int) jSpinner_id.getValue();
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this brand?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            brands.removeBrand(id);
        }

    }//GEN-LAST:event_jButton_RemoveActionPerformed

    private void jButton_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_browseActionPerformed
        // Browse and display image
        String imagePath = selectImage();
        displayImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), imagePath, jLabel_logo);
        // Display image path
        jLabel_imagePath.setText(imagePath);

    }//GEN-LAST:event_jButton_browseActionPerformed

    private void jButton_RefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RefreshActionPerformed
            // TODO add your handling code here:
        // refresh jTable
        populateJtableWithBrands();
    }//GEN-LAST:event_jButton_RefreshActionPerformed

    private void jButton_ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ClearActionPerformed
        // TODO add your handling code here:
        // clear fields
        jSpinner_id.setValue(0);
        jTextField_name.setText("");
        jLabel_logo.setIcon(null);
        jLabel_imagePath.setText("");
    }//GEN-LAST:event_jButton_ClearActionPerformed

    private void jButton_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FirstActionPerformed
        // TODO add your handling code here:
        //  Button first
        locationIndex = 0;
        displayBrand();

    }//GEN-LAST:event_jButton_FirstActionPerformed

    private void jButton_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NextActionPerformed
        // TODO add your handling code here:
        // Button Next
        locationIndex ++;
        if(locationIndex > brands_list.size()-1){ locationIndex = brands_list.size()-1;  }
        displayBrand();

    }//GEN-LAST:event_jButton_NextActionPerformed

    private void jButton_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_PreviousActionPerformed
        // TODO add your handling code here:
        // Button previous
        locationIndex --;
        if(locationIndex < 0){ locationIndex = 0; }
        displayBrand();
    }//GEN-LAST:event_jButton_PreviousActionPerformed

    private void jButton_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LastActionPerformed
        // TODO add your handling code here:
        // button last
        locationIndex = brands_list.size()-1;
        displayBrand();
    }//GEN-LAST:event_jButton_LastActionPerformed

    private void jTable_BrandsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_BrandsMouseClicked
        // get the selected brands
        int index = jTable_Brands.getSelectedRow();
        int id = Integer.valueOf(jTable_Brands.getValueAt(index,0).toString());
        Classes.Brand brd = brands.getBrandById(id);
        jSpinner_id.setValue(brd.getId());
        jTextField_name.setText(brd.getName());
        displayByteImage(jLabel_logo.getWidth(), jLabel_logo.getHeight(), brd.getLogo(), jLabel_logo);
    }//GEN-LAST:event_jTable_BrandsMouseClicked

    private void jButton_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AddActionPerformed
        // add new brands

        try {
            String name = jTextField_name.getText();
            byte[] logo = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));

            if (brandVerify("add")){

                //if (name == )
                brands.addBrand(name,logo);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null , "Select Valid Logo" + ex.getMessage() , "Invalid Logo", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton_AddActionPerformed

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
        Form_CustomersList frm_cst_list = new Form_CustomersList("add");
        frm_cst_list.setVisible(true);

    }//GEN-LAST:event_jButton_Select_CustomerActionPerformed

    private void jButton_Last2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Last2ActionPerformed
        customerIndex = customer_list.size()-1;
        displayCustomer();
    }//GEN-LAST:event_jButton_Last2ActionPerformed

    private void jButton_Previous2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Previous2ActionPerformed
        // TODO add your handling code here:
        // Button previous
        customerIndex --;
        if(customerIndex < 0){ customerIndex = 0; }
        displayCustomer();
    }//GEN-LAST:event_jButton_Previous2ActionPerformed

    private void jTable_Customers_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Customers_MouseClicked
        // get the selected customer
        int index = jTable_Customers_.getSelectedRow();
        int id = Integer.valueOf(jTable_Customers_.getValueAt(index, 0).toString());
        String fullname = jTable_Customers_.getValueAt(index, 1).toString();
        String phone = jTable_Customers_.getValueAt(index, 3).toString();
        String email = jTable_Customers_.getValueAt(index, 4).toString();
        String address = jTable_Customers_.getValueAt(index, 5).toString();

        jSpinner_id2.setValue(id);
        jTextField_fullname.setText(fullname);
        jTextField_phone.setText(phone);
        jTextField_email.setText(email);
        jTextArea_address.setText(address);

        Date bdate;
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(jTable_Customers_.getValueAt(index, 2).toString());
            jDateChooser_Customer_birthDate.setDate(bdate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Form_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable_Customers_MouseClicked

    private void jButton_Next2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Next2ActionPerformed
        // TODO add your handling code here:
        // Button Next
        customerIndex ++;
        if(customerIndex > customer_list.size()-1){ customerIndex = customer_list.size()-1;  }
        displayCustomer();
    }//GEN-LAST:event_jButton_Next2ActionPerformed

    private void jButton_First2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_First2ActionPerformed
        //  Button first
        customerIndex = 0;
        displayCustomer();
    }//GEN-LAST:event_jButton_First2ActionPerformed

    private void jButton_Clear2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Clear2ActionPerformed
        // TODO add your handling code here:
        // clear fields
        jSpinner_id.setValue(0);
        jTextField_fullname.setText("");
        jTextField_email.setText("");
        jTextField_phone.setText("");
        jTextArea_address.setText("");
        jDateChooser_Customer_birthDate.setDate(null);
        //        jLabel_logo.setIcon(null);
        //        jLabel_imagePath.setText("");
    }//GEN-LAST:event_jButton_Clear2ActionPerformed

    private void jButton_Refresh2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Refresh2ActionPerformed
        // TODO add your handling code here:
        // refresh jTable
        populateJtableWithCustomers();
    }//GEN-LAST:event_jButton_Refresh2ActionPerformed

    private void jButton_Edit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit2ActionPerformed
        // edit customer info

        try
        {
            int id = (int) jSpinner_id.getValue();
            String name = jTextField_fullname.getText();
            String phone = jTextField_phone.getText();
            String email = jTextField_email.getText();
            String address = jTextArea_address.getText();

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String birthdate = dateformat.format(jDateChooser_Customer_birthDate.getDate());

            if (customerVerify("edit"))
            {
                customer.editCustomer(id, name, birthdate, phone, email, address);
            }

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null , ex.getMessage(), "Error", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);;
        }
    }//GEN-LAST:event_jButton_Edit2ActionPerformed

    private void jButton_Remove2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Remove2ActionPerformed
        // remove Customer

        int id = (int) jSpinner_id.getValue();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this Customer?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION)
        {
            customer.removeCustomer(id);
        }
    }//GEN-LAST:event_jButton_Remove2ActionPerformed

    private void jButton_Add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add2ActionPerformed
        // add new brand

        try {
            String fullname = jTextField_fullname.getText();
            String phone = jTextField_phone.getText();
            String email = jTextField_email.getText();
            String address = jTextArea_address.getText();

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String birthDate = dateformat.format(jDateChooser_Customer_birthDate.getDate());

            if (customerVerify("add"))
            {
                // addCustomer (String _fullname, String _birthdate, String _phone, String _email, String _address)
                customer.addCustomer(fullname,birthDate ,phone, email, address);
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null , ex.getMessage() , "Error", 2);
        }
    }//GEN-LAST:event_jButton_Add2ActionPerformed

    private void jRadioButton_selfDriveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_selfDriveActionPerformed
        // TODO add your handling code here:
         jComboBox_DriverList.setEnabled(false); // Disable driver list
        jComboBox_DriverList.setVisible(false); 
    }//GEN-LAST:event_jRadioButton_selfDriveActionPerformed

    private void jTextField_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nameActionPerformed

    private void jButton_select_car_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_select_car_ActionPerformed
       
        // get the brand id
        String brand_id = (jLabel_Brand_Id.getText());
        
        // show the cars list form
        Form_CarsListByBrand frm_cars_brand = new Form_CarsListByBrand();
        frm_cars_brand.setVisible(true);
    }//GEN-LAST:event_jButton_select_car_ActionPerformed

    private void jRadioButton_withDriverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton_withDriverActionPerformed
        // TODO add your handling code here:
        
        jComboBox_DriverList.setEnabled(true);  // Enable driver list
    jComboBox_DriverList.setVisible(true);
        
    }//GEN-LAST:event_jRadioButton_withDriverActionPerformed

    private void jButton_BookCar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BookCar_ActionPerformed
//        // Add new Booking

 try {
    
    int car_id = Integer.valueOf(jLabel_car_id.getText());
    int customer_id = Integer.valueOf(jLabel_customer_id.getText());

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String pickup_date = dateFormat.format(jDateChooser_Pickup_Date.getDate());
    String dropoff_date = dateFormat.format(jDateChooser_dropoff.getDate());

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
        int addFee = 2500;      
       totalPrice += addFee;
        driverName = jComboBox_DriverList.getSelectedItem().toString();
            JOptionPane.showMessageDialog(null, "An additional fee of 2500 will be charged for renting with a driver.", 
                                  "Driver Fee", JOptionPane.INFORMATION_MESSAGE);
        
        
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

boolean isAvailable = car.isCarAvailable(car_id, pickup_date, dropoff_date);
if (!isAvailable) {
    JOptionPane.showMessageDialog(null, "The selected car is not available for the selected dates.");
    return;
}

    booking.addNewBooking(
        car_id,              
        customer_id,         
        pickup_date,         
        dropoff_date,       
        totalPrice,        
        driver,            
        driverName          
    );
    BookingHistory history = new BookingHistory();

}    catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
}

    }//GEN-LAST:event_jButton_BookCar_ActionPerformed

    private void jButton_select_car_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_select_car_MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_select_car_MouseClicked

    private void jButton_BookingLIst_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BookingLIst_ActionPerformed
        // display  the  booking  list
        Form_BookingList bookinglist = new Form_BookingList();
        bookinglist.setVisible(true);
    }//GEN-LAST:event_jButton_BookingLIst_ActionPerformed

    private void jButton_Edit_Remove_BookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit_Remove_BookingActionPerformed
        Form_Booking_Edit_Remove frm_edt_rmv = new Form_Booking_Edit_Remove();
        frm_edt_rmv.setVisible(true);
    }//GEN-LAST:event_jButton_Edit_Remove_BookingActionPerformed

    private void jButton_Edit_refresh_total_PriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit_refresh_total_PriceActionPerformed
        try {
        // Get the selected pickup and drop-off dates from the date pickers
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String pickup_date = dateFormat.format(jDateChooser_Pickup_Date.getDate());
        String dropoff_date = dateFormat.format(jDateChooser_dropoff.getDate());

        Date pickupDate = dateFormat.parse(pickup_date);
        Date dropoffDate = dateFormat.parse(dropoff_date);

        // Calculate the difference in milliseconds
        long diffInMillis = dropoffDate.getTime() - pickupDate.getTime();
        
        // Convert milliseconds to days
        long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        
        // Validate the dates
        if (diffInDays <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid rental period. Drop-off date must be after the pick-up date.");
            return;
        }

        // Get the car price per day (ensure you already have car details, such as the price)
        int car_id = Integer.valueOf(jLabel_car_id.getText());  // Example: get car id from a label
        Car car = new Car();
        car = car.getCarById(car_id);  // Get the car object by ID
        
        int pricePerDay = car.getPrice();  // Get the price per day of the car

        if (pricePerDay <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid price per day for the selected car.");
            return;
        }

        // Calculate the total price for the selected period
        int totalPrice = (int) diffInDays * pricePerDay;

        // Update the total price label or field
        jLabel_totalFee.setText("$" + totalPrice);  // Assuming you have a label to show the total price

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton_Edit_refresh_total_PriceActionPerformed

    private void jButton_Search_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Search_ActionPerformed
        // Search by ID
        int car_id = (int) jSpinner_Id.getValue();
        Car new_car = car.getCarById(car_id);

        jButton_Reset_ActionPerformed(null);

        if (new_car != null)
        {
            jTextField_PlateNum.setText(new_car.getplateNum_());
            jTextField_Model.setText(new_car.getModel());
            jSpinner_Passengers.setValue(new_car.getPassengers());
            jSpinner_Price.setValue(new_car.getPrice());
            jComboBox_VehicleType.setSelectedItem(new_car.getType());
            jComboBox_brand.setSelectedItem(new_car.getBrand());
            // jComboBox_Cars_.setSelectedItem(Vehiclemap.get(new_car.getModel()));
            jComboBox_Fuel.setSelectedItem(new_car.getFuel());
            jComboBox_Color.setSelectedItem(new_car.getColor());

            if (new_car.getGearbox().equals("manual")){jRadioButton_Manual.setSelected(true);}
            if (new_car.getAir_cond().equals("yes")){jRadioButton_Features_Aircond.setSelected(true);}
            if (new_car.getAirbag().equals("yes")){jRadioButton_Features_AirBag.setSelected(true);}
            if (new_car.getElec_window().equals("yes")){jRadioButton_Features_ElecWin.setSelected(true);}
            if (new_car.getGps().equals("yes")){jRadioButton_Features_GPS.setSelected(true);}
            if (new_car.getHeated_seats().equals("yes")){jRadioButton_Features_HeatedSeat.setSelected(true);}
            if (new_car.getNav_sys().equals("yes")){jRadioButton_Features_NavSys.setSelected(true);}
            if (new_car.getSunroof().equals("yes")){jRadioButton_Features_Sunroof.setSelected(true);}

        }
    }//GEN-LAST:event_jButton_Search_ActionPerformed

    private void jButton_BookingLIst_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BookingLIst_1ActionPerformed
        // TODO add your handling code here:
        Form_ReturnCars bookingList = new Form_ReturnCars();
        bookingList.setVisible(true);
    }//GEN-LAST:event_jButton_BookingLIst_1ActionPerformed

    private void jTable_CarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CarsMouseClicked
        // get the selected car image

        //         int index = jTable_Cars.getSelectedRow();
        //         int id = Integer.valueOf(jTable_Cars.getValueAt(index, 0).toString());
        //         ArrayList<Car.CarImage> images = car.carImagesList(id);

        int index = jTable_Cars.getSelectedRow();
        int id = Integer.valueOf(jTable_Cars.getValueAt(index,0).toString());
        ArrayList<Car.CarImage> images = car.carImagesList(id);
        //   displayByteImage(jLabel_Car_Image.getWidth(), jLabel_Car_Image.getHeight(), images.get(id).getCar_img(), jLabel_Car_Image);
    }//GEN-LAST:event_jTable_CarsMouseClicked

    private void jButton_Remove3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Remove3ActionPerformed
        // remove Driver

        int id = (int) jSpinner_id.getValue();

        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure You want to delete this Driver?" , "Confirm" , JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION)
        {
            drivers.removeDriver(id);
        }
    }//GEN-LAST:event_jButton_Remove3ActionPerformed

    private void jButton_Edit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Edit3ActionPerformed
        // edit driver info

        try
        {
            int id = (int) jSpinner_id.getValue();
            String name = jTextField_fullname.getText();
            String phone = jTextField_phone.getText();
            String email = jTextField_email.getText();
            String address = jTextArea_address.getText();

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String birthdate = dateformat.format(jDateChooser_Customer_birthDate.getDate());

            if (driverVerify("edit"))
            {
                drivers.editDriver(id, name, birthdate, phone, email, address);
            }

        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null , ex.getMessage(), "Error", 2);
            //Logger.getLogger(Form_Brands.class.getName()).log(Level.SEVERE, null, ex);;
        }
    }//GEN-LAST:event_jButton_Edit3ActionPerformed

    private void jButton_Add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add3ActionPerformed
        // add new brand

        try {
            String fullname = jTextField_fullname.getText();
            String phone = jTextField_phone.getText();
            String email = jTextField_email.getText();
            String address = jTextArea_address.getText();

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            String birthDate = dateformat.format(jDateChooser_Driver_birthDate.getDate());

            if (driverVerify("add"))
            {
                // addDriver (String _fullname, String _birthdate, String _phone, String _email, String _address)
                drivers.addDriver(fullname,birthDate ,phone, email, address);
            }
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null , ex.getMessage() , "Error", 2);
        }
    }//GEN-LAST:event_jButton_Add3ActionPerformed

    private void jButton_Clear3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Clear3ActionPerformed
        // TODO add your handling code here:
        // clear fields
        jSpinner_id.setValue(0);
        jTextField_fullname.setText("");
        jTextField_email.setText("");
        jTextField_phone.setText("");
        jTextArea_address.setText("");
        jDateChooser_Customer_birthDate.setDate(null);
        //        jLabel_logo.setIcon(null);
        //        jLabel_imagePath.setText("");
    }//GEN-LAST:event_jButton_Clear3ActionPerformed

    private void jButton_Refresh3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Refresh3ActionPerformed
        // TODO add your handling code here:
        // refresh jTable
        populateJtableWithDrivers();
    }//GEN-LAST:event_jButton_Refresh3ActionPerformed

    private void jButton_First3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_First3ActionPerformed
        //  Button first
        index = 0;
        displayDriver();
    }//GEN-LAST:event_jButton_First3ActionPerformed

    private void jButton_Next3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Next3ActionPerformed
        // TODO add your handling code here:
        // Button Next
        index ++;
        if(index > driver_list.size()-1){ index = driver_list.size()-1;  }
        displayDriver();
    }//GEN-LAST:event_jButton_Next3ActionPerformed

    private void jButton_Previous3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Previous3ActionPerformed
        // TODO add your handling code here:
        // Button previous
        index --;
        if(index < 0){ index = 0; }
        displayDriver();
    }//GEN-LAST:event_jButton_Previous3ActionPerformed

    private void jButton_Last3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Last3ActionPerformed
        index = driver_list.size()-1;
        displayDriver();
    }//GEN-LAST:event_jButton_Last3ActionPerformed

    private void jTable_Drivers_MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Drivers_MouseClicked
        // get the selected driver
        int index = jTable_Drivers_.getSelectedRow();
        int id = Integer.valueOf(jTable_Drivers_.getValueAt(index, 0).toString());
        String fullname = jTable_Drivers_.getValueAt(index, 1).toString();
        String phone = jTable_Drivers_.getValueAt(index, 3).toString();
        String email = jTable_Drivers_.getValueAt(index, 4).toString();
        String address = jTable_Drivers_.getValueAt(index, 5).toString();

        jSpinner_id3.setValue(id);
        jTextField_fullname1.setText(fullname);
        jTextField_phone1.setText(phone);
        jTextField_email1.setText(email);
        jTextArea_address1.setText(address);

        Date bdate;
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(jTable_Drivers_.getValueAt(index, 2).toString());
            jDateChooser_Driver_birthDate.setDate(bdate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Form_Drivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable_Drivers_MouseClicked

    private void jButton_Cars_History_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Cars_History_ActionPerformed
        // TODO add your handling code here:
         new Form_VehicleHistory().setVisible(true);
    }//GEN-LAST:event_jButton_Cars_History_ActionPerformed

    private void jButton_Cars_History_ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jButton_Cars_History_ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Cars_History_ComponentAdded

    private void returnCarTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnCarTabMouseClicked
        // TODO add your handling code here:
        
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
        returnPanel.setVisible(true);
        
        HomeTabLabel.setBorder(default_border);
        BrandTabLabel.setBorder(default_border);
        VehicleTabLabel.setBorder(default_border);
        CustomerTabLabel.setBorder(default_border);
        DriversTabLabel.setBorder(default_border);
        BookTabLabel.setBorder(default_border);
        ReturnTabLabel.setBorder(border);
        
        populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        populateJtableWithBooking();
    }//GEN-LAST:event_returnCarTabMouseClicked

    private void jTable_Booked_vehiclesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Booked_vehiclesMouseClicked
        // TODO add your handling code here:
        
       int index = jTable_Booked_vehicles.getSelectedRow();
        
        String PlateNumber = jTable_Booked_vehicles.getValueAt(index, 9).toString();
        
        
        int id = Integer.valueOf(jTable_Booked_vehicles.getValueAt(index, 1).toString());
        jSpinner_carId.setValue(id);
        
        jTextField_plateNumber.setText(PlateNumber);
        
        Date bdate;  
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(jTable_Booked_vehicles.getValueAt(index, 4).toString());
            jDateChooser_retrunmentDate.setDate(bdate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Form_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }                                               
    }//GEN-LAST:event_jTable_Booked_vehiclesMouseClicked

    private void jButton_ReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ReturnActionPerformed
        // TODO add your handling code here:
        
        //String insertQuery = "INSERT INTO `history`(`car_id`, `customer_id`, `start_date`, `end_date`, `total_price`, `driver`, `driverName`, `Total_KM`) " +
        //                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
            int selectedRow = jTable_Booked_vehicles.getSelectedRow();

// Validate: must select a row first
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(null, "Please select a car to return!", "Error", JOptionPane.ERROR_MESSAGE);
    return;
}

// Validate: Plate Number
String plateNumber = jTextField_plateNumber.getText().trim();
if (plateNumber.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please enter the plate number.", "Missing Field", JOptionPane.WARNING_MESSAGE);
    return;
}

// Validate: Drop off date
Date returnDate = jDateChooser_retrunmentDate.getDate();
if (returnDate == null) {
    JOptionPane.showMessageDialog(null, "Please select the drop off date.", "Missing Field", JOptionPane.WARNING_MESSAGE);
    return;
}

// Validate: Total Kilometer
int totalKM = (Integer) jSpinner_total_kilometer.getValue();
if (totalKM <= 0) {
    JOptionPane.showMessageDialog(null, "Please enter total kilometer driven.", "Missing Field", JOptionPane.WARNING_MESSAGE);
    return;
}

// Validate: Report Field (must be manually entered)
String vehicleReport = jTextArea_Vehicle_report.getText().trim();
if (vehicleReport.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please write a vehicle report.", "Missing Field", JOptionPane.WARNING_MESSAGE);
    return;
}

// Validate: Radio button
if (!jRadioButton_with_Damage.isSelected() && !jRadioButton_without_Damage.isSelected()) {
    JOptionPane.showMessageDialog(null, "Please select return condition (With or Without Damage).", "Missing Field", JOptionPane.WARNING_MESSAGE);
    return;
}

// Validate: Damage fee if "With Damage"
int damageFee = 0;
if (jRadioButton_with_Damage.isSelected()) {
    damageFee = (Integer) jSpinner_damage_Fee.getValue();
    if (damageFee <= 0) {
        JOptionPane.showMessageDialog(null, "Please enter estimated damage fee.", "Missing Field", JOptionPane.WARNING_MESSAGE);
        return;
    }
}

// Retrieve data from table
int carId = Integer.parseInt(jTable_Booked_vehicles.getValueAt(selectedRow, 0).toString());
int bookid = Integer.parseInt(jTable_Booked_vehicles.getValueAt(selectedRow, 1).toString());
String startDate = jTable_Booked_vehicles.getValueAt(selectedRow, 4).toString();
String endDateStr = new SimpleDateFormat("yyyy-MM-dd").format(returnDate);
int customerId = Integer.parseInt(jTable_Booked_vehicles.getValueAt(selectedRow, 10).toString());
String driver = jTable_Booked_vehicles.getValueAt(selectedRow, 7).toString();
String driverName = jTable_Booked_vehicles.getValueAt(selectedRow, 8).toString();
double totalPrice = Double.parseDouble(jTable_Booked_vehicles.getValueAt(selectedRow, 6).toString());

// Save to history and process logic
BookingHistory history = new BookingHistory();

if (jRadioButton_without_Damage.isSelected()) {
    Booking book = new Booking();
    car.updateCarStatus(carId, "available");
    book.updateBookStatus(bookid);
    history.addHistory(carId, customerId, startDate, endDateStr, totalPrice, driver, driverName, totalKM);
} else {
    double totalPriceWithDamage = totalPrice + damageFee;
    car.updateCarStatus(carId, "maintenance");
    history.addHistory(carId, customerId, startDate, endDateStr, totalPriceWithDamage, driver, driverName, totalKM);

    Form_DamagedCars dmgcrs = new Form_DamagedCars();
    dmgcrs.addToDamagedCarsTable(carId, plateNumber, damageFee);
}

// Clear fields after return
jTextField_plateNumber.setText("");
jDateChooser_retrunmentDate.setDate(null);
jSpinner_total_kilometer.setValue(1000);
jSpinner_damage_Fee.setValue(1000);
jTextArea_Vehicle_report.setText("");  // Clear report
populateJtableWithBooking();

     
    }//GEN-LAST:event_jButton_ReturnActionPerformed

    private void homeTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeTabMouseEntered
//        homeTab.setBackground(ClickedColor);
//        vehicleTab.setBackground(DefaultColor);
    }//GEN-LAST:event_homeTabMouseEntered

    private void vehicleTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehicleTabMouseEntered
        // TODO add your handling code here:
//        homeTab.setBackground(DefaultColor);
//        vehicleTab.setBackground(ClickedColor);
    }//GEN-LAST:event_vehicleTabMouseEntered

    private void jComboBox_VehicleTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_VehicleTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_VehicleTypeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setFont(new Font("SansSerif", Font.BOLD, 16));
        BorderFactory.createEmptyBorder(10, 30, 10, 30);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        Form_Viewcustomer vcustomer = new Form_Viewcustomer();
        vcustomer.setVisible(true);
//  
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
//        FlatLaf.registerCustomDefaultsSource("raven.table");
//        FlatMacDarkLaf.setup();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BookTabLabel;
    private javax.swing.JLabel BrandTabLabel;
    private javax.swing.JLabel CustomerTabLabel;
    private javax.swing.JLabel DriversTabLabel;
    private javax.swing.JLabel HomeTabLabel;
    private javax.swing.JLabel ReturnTabLabel;
    private javax.swing.JLabel VehicleTabLabel;
    private javax.swing.JLabel badge;
    private javax.swing.JLabel badge1;
    private javax.swing.JLabel badge2;
    private javax.swing.JLabel badge3;
    private javax.swing.JLabel badge4;
    private javax.swing.JPanel bookCarPanel;
    private javax.swing.JPanel bookCarTab;
    private javax.swing.JPanel brandPanel;
    private javax.swing.JPanel brandTab;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.raven.chart.Chart chart;
    private javax.swing.JLabel count;
    private javax.swing.JLabel count1;
    private javax.swing.JLabel count2;
    private javax.swing.JLabel count3;
    private javax.swing.JLabel count4;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JPanel customerTab;
    private javax.swing.JLabel datetime;
    private javax.swing.JPanel driverTab;
    private javax.swing.JPanel homePanel;
    private javax.swing.JPanel homeTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Add2;
    private javax.swing.JButton jButton_Add3;
    private javax.swing.JButton jButton_Add_Brands_List_;
    private javax.swing.JButton jButton_Add_Car_;
    private javax.swing.JButton jButton_BookCar_;
    private javax.swing.JButton jButton_BookingLIst_;
    private javax.swing.JButton jButton_BookingLIst_1;
    private javax.swing.JButton jButton_Cars_History_;
    private javax.swing.JButton jButton_Cars_List_;
    private javax.swing.JButton jButton_Clear;
    private javax.swing.JButton jButton_Clear2;
    private javax.swing.JButton jButton_Clear3;
    private javax.swing.JButton jButton_Edit;
    private javax.swing.JButton jButton_Edit2;
    private javax.swing.JButton jButton_Edit3;
    private javax.swing.JButton jButton_Edit_;
    private javax.swing.JButton jButton_Edit_Remove_Booking;
    private javax.swing.JButton jButton_Edit_refresh_total_Price;
    private javax.swing.JButton jButton_First;
    private javax.swing.JButton jButton_First2;
    private javax.swing.JButton jButton_First3;
    private javax.swing.JButton jButton_Last;
    private javax.swing.JButton jButton_Last2;
    private javax.swing.JButton jButton_Last3;
    private javax.swing.JButton jButton_Next;
    private javax.swing.JButton jButton_Next2;
    private javax.swing.JButton jButton_Next3;
    private javax.swing.JButton jButton_Previous;
    private javax.swing.JButton jButton_Previous2;
    private javax.swing.JButton jButton_Previous3;
    private javax.swing.JButton jButton_Refresh;
    private javax.swing.JButton jButton_Refresh2;
    private javax.swing.JButton jButton_Refresh3;
    private javax.swing.JButton jButton_Remove;
    private javax.swing.JButton jButton_Remove2;
    private javax.swing.JButton jButton_Remove3;
    private javax.swing.JButton jButton_Remove_;
    private javax.swing.JButton jButton_Reset_;
    private javax.swing.JButton jButton_Return;
    private javax.swing.JButton jButton_Search_;
    private javax.swing.JButton jButton_Select_Customer;
    private javax.swing.JButton jButton_browse;
    private javax.swing.JButton jButton_select_car_;
    private javax.swing.JComboBox<String> jComboBox_Brands_;
    private javax.swing.JComboBox<String> jComboBox_Color;
    private javax.swing.JComboBox<String> jComboBox_DriverList;
    private javax.swing.JComboBox<String> jComboBox_Fuel;
    private javax.swing.JComboBox<String> jComboBox_VehicleType;
    private javax.swing.JComboBox<String> jComboBox_Year;
    private javax.swing.JComboBox<String> jComboBox_brand;
    private javax.swing.JComboBox<String> jComboBox_filter;
    private com.toedter.calendar.JDateChooser jDateChooser_Customer_birthDate;
    private com.toedter.calendar.JDateChooser jDateChooser_Driver_birthDate;
    private com.toedter.calendar.JDateChooser jDateChooser_Pickup_Date;
    private com.toedter.calendar.JDateChooser jDateChooser_dropoff;
    private com.toedter.calendar.JDateChooser jDateChooser_retrunmentDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_Brand_Id;
    private javax.swing.JLabel jLabel_available_count;
    private javax.swing.JLabel jLabel_book_count;
    private static javax.swing.JLabel jLabel_car_id;
    private static javax.swing.JLabel jLabel_car_model;
    private javax.swing.JLabel jLabel_cars_count;
    private javax.swing.JLabel jLabel_cars_logo;
    private javax.swing.JLabel jLabel_cars_logo2;
    private javax.swing.JLabel jLabel_cars_logo3;
    private javax.swing.JLabel jLabel_cars_logo4;
    private javax.swing.JLabel jLabel_cars_logo5;
    private static javax.swing.JLabel jLabel_customer_id;
    private javax.swing.JLabel jLabel_customers_count;
    private javax.swing.JLabel jLabel_dropoff;
    private javax.swing.JLabel jLabel_dropoff1;
    private javax.swing.JLabel jLabel_imagePath;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JLabel jLabel_maintenance_count;
    private static javax.swing.JLabel jLabel_pricePerDay;
    private javax.swing.JLabel jLabel_rent_details;
    private javax.swing.JLabel jLabel_select_car;
    private javax.swing.JLabel jLabel_select_customer;
    private static javax.swing.JLabel jLabel_totalFee;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanel_rent_details;
    private javax.swing.JPanel jPanel_select_car;
    private javax.swing.JPanel jPanel_select_customer;
    private javax.swing.JRadioButton jRadioButton_Automatic;
    private javax.swing.JRadioButton jRadioButton_Features_AirBag;
    private javax.swing.JRadioButton jRadioButton_Features_Aircond;
    private javax.swing.JRadioButton jRadioButton_Features_Bluetooth;
    private javax.swing.JRadioButton jRadioButton_Features_ElecWin;
    private javax.swing.JRadioButton jRadioButton_Features_GPS;
    private javax.swing.JRadioButton jRadioButton_Features_HeatedSeat;
    private javax.swing.JRadioButton jRadioButton_Features_NavSys;
    private javax.swing.JRadioButton jRadioButton_Features_Sunroof;
    private javax.swing.JRadioButton jRadioButton_Manual;
    private javax.swing.JRadioButton jRadioButton_selfDrive;
    private javax.swing.JRadioButton jRadioButton_withDriver;
    private javax.swing.JRadioButton jRadioButton_with_Damage;
    private javax.swing.JRadioButton jRadioButton_without_Damage;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner_Id;
    public javax.swing.JSpinner jSpinner_Passengers;
    private javax.swing.JSpinner jSpinner_Price;
    private javax.swing.JSpinner jSpinner_carId;
    private javax.swing.JSpinner jSpinner_damage_Fee;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JSpinner jSpinner_id2;
    private javax.swing.JSpinner jSpinner_id3;
    private javax.swing.JSpinner jSpinner_total_kilometer;
    public javax.swing.JTable jTable_Booked_vehicles;
    public javax.swing.JTable jTable_Brands;
    public javax.swing.JTable jTable_Cars;
    public javax.swing.JTable jTable_Customers_;
    public javax.swing.JTable jTable_Drivers_;
    private javax.swing.JTextArea jTextArea_Vehicle_report;
    private javax.swing.JTextArea jTextArea_address;
    private javax.swing.JTextArea jTextArea_address1;
    private javax.swing.JTextField jTextField_Model;
    private javax.swing.JTextField jTextField_PlateNum;
    private static javax.swing.JTextField jTextField_customer;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_email1;
    private javax.swing.JTextField jTextField_fullname;
    private javax.swing.JTextField jTextField_fullname1;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_phone;
    private javax.swing.JTextField jTextField_phone1;
    private javax.swing.JTextField jTextField_plateNumber;
    private javax.swing.JPanel locationPanel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JLabel nameLabel3;
    private javax.swing.JLabel nameLabel4;
    private javax.swing.JPanel panelTopCustomersList;
    private javax.swing.JPanel returnCarTab;
    private javax.swing.JPanel returnPanel;
    private javax.swing.JPanel vehiclePanel;
    private javax.swing.JPanel vehicleTab;
    // End of variables declaration//GEN-END:variables
}
