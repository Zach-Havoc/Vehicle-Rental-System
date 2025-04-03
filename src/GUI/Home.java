/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;



import java.awt.Color;
import Classes.Brand;
import Classes.Booking;
import Classes.Car;
import Classes.Customer;
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

/**  
 *
 * @author estan
 */
public class Home extends javax.swing.JFrame {
    
    Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(255,212,60));
    Border upper_border = BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(255,212,60));
    Border panel_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black);
    
    private javax.swing.JRadioButton jRadioButton_SelfDrive;
    private javax.swing.JRadioButton jRadioButton_WithDriver;
    private javax.swing.JPanel jPanel_Driver;
    private javax.swing.JComboBox<String> jComboBox_Driver;
    private javax.swing.JLabel jLabel_DriverFee;
    private static Object JTextField_customer;
    private static Object JTextField_customer_id;

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
    
    public Home() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        //Radio button
        
        ButtonGroup btn_group = new ButtonGroup();
        btn_group.add(jRadioButton_Automatic);
        btn_group.add(jRadioButton_Manual);
        
        //populateComboBoxBrands();
        populateJtableWithBrands();
        populateJtableWithCustomers();
        populateDriverComboBox();
        populateJtableWithCars();
        populateJtableWithDrivers();
        
        //set border in bookcarpanel
        jLabel_select_car.setBorder(border);
        jLabel_select_customer.setBorder(border);
        jLabel_rent_details.setBorder(border);
        jLabel_dropoff.setBorder(upper_border);
        
        jPanel_select_customer.setBorder(panel_border);
        jPanel_select_car.setBorder(panel_border);
        jPanel_rent_details.setBorder(panel_border);
        
        // show counts                                                                                                                                                                                                                                                                                    
        jLabel_cars_count.setText(String.valueOf(carsList.size()));
        jLabel_customers_count.setText(String.valueOf(customer_list.size()));
        jLabel_book_count.setText(String.valueOf(bookingList.size()));
        jLabel_available_count.setText(String.valueOf(availableCarsCount));
        jLabel_maintenance_count.setText(String.valueOf(maintenanceCarsCount));


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

    
//    public void populateComboBoxBrands()
//    {
//        for ( String s : Brandmap.values())
//        {
//            jComboBox_brand.addItem(s);
//            jComboBox_Brands_.addItem(s);
//            
//        }
//    }
    
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
            jDateChooser_birthDate.setDate(bdate);
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
            jDateChooser_birthDate.setDate(bdate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Form_Customer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }
    
    
    
     // gagawa ng function para ipulate the jTable with Vehicle
    public void populateJtableWithCars(){
        
       // Clear array List
       //brands_list.clear();
       // populated arrayList
       ArrayList<Car>cars_list = car.carsList();
       // JTable Columns
       
       /*
       `id`, `brand`, `model`, `fuel`, `color`, `plateNum`, `passengers`, 
       `gearbox`, `price`, `air_conditioning`, `air_bag`, `sunroof`, 
       `heated_seats`, `nav_system`, `bluetooth`, `electric_windows`, `gps`
       */
        String[] columnsName = {"ID", "brand","model", "fuel","color", "plateNum","passengers", "gearbox",
                                "price", "air_conditioning","air_bag", "sunroof","heated_seats", 
                                "nav_system","bluetooth", "electric_windows","gps", "Status"};

       // Rows
       
       Object[][] rows = new Object[cars_list.size()][columnsName.length];
       
       for (int i = 0; i < cars_list.size(); i++){
           
           rows[i][0] = cars_list.get(i).getId();
           rows[i][1] = cars_list.get(i).getBrand();
           rows[i][2] = cars_list.get(i).getModel();
           rows[i][3] = cars_list.get(i).getFuel();
           rows[i][4] = cars_list.get(i).getColor();
           rows[i][5] = cars_list.get(i).getplateNum_();
           rows[i][6] = cars_list.get(i).getPassengers();
           rows[i][7] = cars_list.get(i).getGearbox();
           rows[i][8] = cars_list.get(i).getPrice();
           rows[i][9] = cars_list.get(i).getAir_cond();
           rows[i][10] = cars_list.get(i).getAirbag();
           rows[i][11] = cars_list.get(i).getSunroof();
           rows[i][12] = cars_list.get(i).getHeated_seats();
           rows[i][13] = cars_list.get(i).getNav_sys();
           rows[i][14] = cars_list.get(i).getBluetooth();
           rows[i][15] = cars_list.get(i).getElec_window();
           rows[i][16] = cars_list.get(i).getGps();
           rows[i][17] = cars_list.get(i).getStatus();
       }
       DefaultTableModel model = new DefaultTableModel(rows,columnsName);
       jTable_Cars.setModel(model);
       
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel15 = new javax.swing.JPanel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        homeTab = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        vehicleTab = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        brandTab = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        locationTab = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        customerTab = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        bookCarTab = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel_book_count = new javax.swing.JLabel();
        jLabel_cars_logo3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel_cars_count = new javax.swing.JLabel();
        jLabel_cars_logo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel_customers_count = new javax.swing.JLabel();
        jLabel_cars_logo2 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_Cars = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jLabel_available_count = new javax.swing.JLabel();
        jLabel_cars_logo4 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel_maintenance_count = new javax.swing.JLabel();
        jLabel_cars_logo5 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        vehiclePanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner_Id = new javax.swing.JSpinner();
        jButton_Search_ = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_brand = new javax.swing.JComboBox<>();
        jLabel_Brand_Id = new javax.swing.JLabel();
        jTextField_Model = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_Fuel = new javax.swing.JComboBox<>();
        jComboBox_Color = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton_Features_Aircond = new javax.swing.JRadioButton();
        jRadioButton_Features_AirBag = new javax.swing.JRadioButton();
        jRadioButton_Features_Sunroof = new javax.swing.JRadioButton();
        jRadioButton_Features_HeatedSeat = new javax.swing.JRadioButton();
        jRadioButton_Features_NavSys = new javax.swing.JRadioButton();
        jRadioButton_Features_Bluetooth = new javax.swing.JRadioButton();
        jRadioButton_Features_ElecWin = new javax.swing.JRadioButton();
        jRadioButton_Features_GPS = new javax.swing.JRadioButton();
        jButton_Add_Car_ = new javax.swing.JButton();
        jButton_Add_Brands_List_ = new javax.swing.JButton();
        jButton_Reset_ = new javax.swing.JButton();
        jButton_Cars_List_ = new javax.swing.JButton();
        jButton_Remove_ = new javax.swing.JButton();
        jButton_Edit_ = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField_PlateNum = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        int max_2 = 14;
        int min_2 = 2;
        int step_2 = 1;
        int i_2 = 2;
        SpinnerModel spinner_model_2 = new SpinnerNumberModel(i_2,min_2,max_2,step_2);
        jSpinner_Passengers = new javax.swing.JSpinner(spinner_model_2);
        jLabel17 = new javax.swing.JLabel();
        jRadioButton_Automatic = new javax.swing.JRadioButton();
        jRadioButton_Manual = new javax.swing.JRadioButton();
        int max = 10000;
        int min = 10;
        int step = 1;
        int i = 10;
        SpinnerModel spinner_model = new SpinnerNumberModel(i,min,max,step);
        jSpinner_Price = new javax.swing.JSpinner(spinner_model);
        jLabel18 = new javax.swing.JLabel();
        brandPanel = new javax.swing.JPanel();
        jSpinner_id = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
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
        jDateChooser_birthDate = new com.toedter.calendar.JDateChooser();
        bookCarPanel = new javax.swing.JPanel();
        jPanel_rent_details = new javax.swing.JPanel();
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
        jPanel_select_car = new javax.swing.JPanel();
        jLabel_select_car = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButton_select_car_ = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        jLabel_car_id = new javax.swing.JLabel();
        jLabel_car_model = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel_pricePerDay = new javax.swing.JLabel();
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
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 212, 60));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        homeTab.setOpaque(false);
        homeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeTabMouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(192, 192, 83));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("HOME");

        javax.swing.GroupLayout homeTabLayout = new javax.swing.GroupLayout(homeTab);
        homeTab.setLayout(homeTabLayout);
        homeTabLayout.setHorizontalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        homeTabLayout.setVerticalGroup(
            homeTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        vehicleTab.setOpaque(false);
        vehicleTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vehicleTabMouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(192, 192, 83));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("VEHICLE");

        javax.swing.GroupLayout vehicleTabLayout = new javax.swing.GroupLayout(vehicleTab);
        vehicleTab.setLayout(vehicleTabLayout);
        vehicleTabLayout.setHorizontalGroup(
            vehicleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        vehicleTabLayout.setVerticalGroup(
            vehicleTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehicleTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        brandTab.setOpaque(false);
        brandTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brandTabMouseClicked(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(192, 192, 83));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("BRAND");

        javax.swing.GroupLayout brandTabLayout = new javax.swing.GroupLayout(brandTab);
        brandTab.setLayout(brandTabLayout);
        brandTabLayout.setHorizontalGroup(
            brandTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        brandTabLayout.setVerticalGroup(
            brandTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(brandTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        locationTab.setOpaque(false);
        locationTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                locationTabMouseClicked(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(192, 192, 83));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DRIVERS");

        javax.swing.GroupLayout locationTabLayout = new javax.swing.GroupLayout(locationTab);
        locationTab.setLayout(locationTabLayout);
        locationTabLayout.setHorizontalGroup(
            locationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        locationTabLayout.setVerticalGroup(
            locationTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        customerTab.setOpaque(false);
        customerTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTabMouseClicked(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(192, 192, 83));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CUSTOMER");

        javax.swing.GroupLayout customerTabLayout = new javax.swing.GroupLayout(customerTab);
        customerTab.setLayout(customerTabLayout);
        customerTabLayout.setHorizontalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        customerTabLayout.setVerticalGroup(
            customerTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        bookCarTab.setOpaque(false);
        bookCarTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookCarTabMouseClicked(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(192, 192, 83));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("BOOK CAR");

        javax.swing.GroupLayout bookCarTabLayout = new javax.swing.GroupLayout(bookCarTab);
        bookCarTab.setLayout(bookCarTabLayout);
        bookCarTabLayout.setHorizontalGroup(
            bookCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        bookCarTabLayout.setVerticalGroup(
            bookCarTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(homeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vehicleTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brandTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(locationTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookCarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bookCarTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(customerTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(locationTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vehicleTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homeTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.CardLayout());

        homePanel.setBackground(new java.awt.Color(255, 255, 255));
        homePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 212, 60), 2, true));
        jPanel7.setOpaque(false);

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

        homePanel.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 220, 170));

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 212, 60), 2, true));
        jPanel10.setOpaque(false);

        jLabel_cars_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_cars_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/1.png"))); // NOI18N

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
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

        homePanel.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 220, 170));

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));
        jPanel12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 212, 60), 2, true));
        jPanel12.setOpaque(false);

        jLabel_customers_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_customers_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo2.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2.png"))); // NOI18N

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Customers");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_customers_count, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_cars_logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
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

        homePanel.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 220, 170));

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
        jTable_Cars.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Cars.setSelectionBackground(new java.awt.Color(102, 102, 102));
        jTable_Cars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CarsMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable_Cars);
        jTable_Cars.setBackground(new java.awt.Color(255, 255, 255));  // White background
        jTable_Cars.setSelectionBackground(new java.awt.Color(102, 102, 255));  // Light blue selection
        jTable_Cars.setSelectionForeground(new java.awt.Color(255, 255, 255));  // White text when selected
        jTable_Cars.setGridColor(new java.awt.Color(204, 204, 204));  // Light gray grid lines
        jTable_Cars.setForeground(new java.awt.Color(0, 0, 0));  // Black text for the table content

        homePanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 1190, 330));

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 212, 60), 2, true));
        jPanel14.setOpaque(false);

        jLabel_available_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_available_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo4.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/available.png"))); // NOI18N

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
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

        homePanel.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 30, 220, 170));

        jPanel16.setBackground(new java.awt.Color(0, 0, 0));
        jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 212, 60), 2, true));
        jPanel16.setOpaque(false);

        jLabel_maintenance_count.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        jLabel_maintenance_count.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel_cars_logo5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_cars_logo5.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_cars_logo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_cars_logo5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/maintenance.png"))); // NOI18N

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N
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

        homePanel.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 30, 220, 170));

        jPanel2.add(homePanel, "card2");

        vehiclePanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setBackground(new java.awt.Color(102, 102, 102));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setText("ID:");

        jSpinner_Id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Id.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton_Search_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Search_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Search_.setText("Search");
        jButton_Search_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Search_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Search_ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(102, 102, 102));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Brand:");

        jComboBox_brand.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_brand.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_brandActionPerformed(evt);
            }
        });

        jLabel_Brand_Id.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel_Brand_Id.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Brand_Id.setText("#");
        jLabel_Brand_Id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTextField_Model.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Model.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_Model.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ModelActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(102, 102, 102));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setText("Model:");

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 51, 51));
        jLabel11.setText("Fuel:");

        jComboBox_Fuel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Fuel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gas", "Diesel" }));
        jComboBox_Fuel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jComboBox_Color.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Color.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Red", "Blue", "Black", "White", "Silver", "Green", "Pink", "Purple", "Orange", "Brown", "Yellow" }));
        jComboBox_Color.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel13.setBackground(new java.awt.Color(102, 102, 102));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 51));
        jLabel13.setText("Color:");

        jLabel14.setBackground(new java.awt.Color(102, 102, 102));
        jLabel14.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setText("Features");

        jRadioButton_Features_Aircond.setText("Air Conditioning");
        jRadioButton_Features_Aircond.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_AircondActionPerformed(evt);
            }
        });

        jRadioButton_Features_AirBag.setText("Air Bags");

        jRadioButton_Features_Sunroof.setText("Convertible");
        jRadioButton_Features_Sunroof.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_SunroofActionPerformed(evt);
            }
        });

        jRadioButton_Features_HeatedSeat.setText("Heated Seats");

        jRadioButton_Features_NavSys.setText("Navigation System");

        jRadioButton_Features_Bluetooth.setText("Bluetooth");

        jRadioButton_Features_ElecWin.setText("Electric Windows");
        jRadioButton_Features_ElecWin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_ElecWinActionPerformed(evt);
            }
        });

        jRadioButton_Features_GPS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton_Features_GPS.setText("GPS");
        jRadioButton_Features_GPS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRadioButton_Features_GPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton_Features_GPSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(jRadioButton_Features_GPS, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
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
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jButton_Add_Car_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Add_Car_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Car_.setText("Add");
        jButton_Add_Car_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Car_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Car_ActionPerformed(evt);
            }
        });

        jButton_Add_Brands_List_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Add_Brands_List_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Brands_List_.setText("Brands");
        jButton_Add_Brands_List_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add_Brands_List_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Brands_List_ActionPerformed(evt);
            }
        });

        jButton_Reset_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Reset_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Reset_.setText("Reset");
        jButton_Reset_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Reset_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Reset_ActionPerformed(evt);
            }
        });

        jButton_Cars_List_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Cars_List_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Cars_List_.setText("Car List");
        jButton_Cars_List_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Cars_List_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Cars_List_ActionPerformed(evt);
            }
        });

        jButton_Remove_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Remove_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        jButton_Edit_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Edit_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Edit_.setText("Edit");
        jButton_Edit_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit_ActionPerformed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(102, 102, 102));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Plate Number:");

        jTextField_PlateNum.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_PlateNum.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField_PlateNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_PlateNumActionPerformed(evt);
            }
        });

        jLabel16.setBackground(new java.awt.Color(102, 102, 102));
        jLabel16.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setText("Seats:");

        jSpinner_Passengers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinner_Passengers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel17.setBackground(new java.awt.Color(102, 102, 102));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 51, 51));
        jLabel17.setText("Transmission:");

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

        jLabel18.setBackground(new java.awt.Color(102, 102, 102));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("Price/Day:");

        javax.swing.GroupLayout vehiclePanelLayout = new javax.swing.GroupLayout(vehiclePanel);
        vehiclePanel.setLayout(vehiclePanelLayout);
        vehiclePanelLayout.setHorizontalGroup(
            vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
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
                                        .addComponent(jButton_Reset_, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13))
                                .addGap(25, 25, 25)
                                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                                        .addComponent(jSpinner_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton_Search_, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox_Fuel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox_Color, 0, 180, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jLabel16)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(vehiclePanelLayout.createSequentialGroup()
                                .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton_Manual, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jSpinner_Passengers, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_PlateNum, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(136, 136, 136))))
        );
        vehiclePanelLayout.setVerticalGroup(
            vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jButton_Search_, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehiclePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jSpinner_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Brand_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox_brand, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_Model, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehiclePanelLayout.createSequentialGroup()
                        .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_Fuel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox_Color, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)))
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
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
                .addGap(53, 53, 53))
            .addGroup(vehiclePanelLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField_PlateNum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSpinner_Passengers, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vehiclePanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jRadioButton_Automatic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton_Manual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(vehiclePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jSpinner_Price, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.add(vehiclePanel, "card5");

        brandPanel.setBackground(new java.awt.Color(255, 255, 255));

        jSpinner_id.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel20.setText("ID:");

        jTextField_name.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jTextField_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nameActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel21.setText("Name:");

        jLabel23.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel23.setText("Logo:");

        jLabel_logo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_logo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel_logo.setOpaque(true);

        jButton_Edit.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Edit.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton_Edit.setText("Edit");
        jButton_Edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EditActionPerformed(evt);
            }
        });

        jButton_Remove.setBackground(new java.awt.Color(255, 212, 60));
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

        jButton_Refresh.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Refresh.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Refresh.setText("Refresh");
        jButton_Refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RefreshActionPerformed(evt);
            }
        });

        jButton_Clear.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Clear.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Clear.setText("Clear");
        jButton_Clear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ClearActionPerformed(evt);
            }
        });

        jButton_First.setBackground(new java.awt.Color(255, 212, 60));
        jButton_First.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_First.setText("<<");
        jButton_First.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FirstActionPerformed(evt);
            }
        });

        jButton_Next.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Next.setText(">");
        jButton_Next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NextActionPerformed(evt);
            }
        });

        jButton_Previous.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton_Previous.setText("<");
        jButton_Previous.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_PreviousActionPerformed(evt);
            }
        });

        jButton_Last.setBackground(new java.awt.Color(255, 212, 60));
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

        jButton_Add.setBackground(new java.awt.Color(255, 212, 60));
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
                            .addComponent(jButton_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                                .addComponent(jLabel_imagePath, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                                .addComponent(jButton_browse, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(174, 174, 174))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Clear, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)))
                .addGroup(brandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                        .addComponent(jButton_First, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(167, 167, 167))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, brandPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))))
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

        locationPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        jButton_Remove3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Remove3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Remove3.setText("Remove");
        jButton_Remove3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove3ActionPerformed(evt);
            }
        });

        jButton_Edit3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Edit3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Edit3.setText("Edit");
        jButton_Edit3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit3ActionPerformed(evt);
            }
        });

        jButton_Add3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Add3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Add3.setText("Add");
        jButton_Add3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add3ActionPerformed(evt);
            }
        });

        jButton_Clear3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Clear3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Clear3.setText("Clear");
        jButton_Clear3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Clear3ActionPerformed(evt);
            }
        });

        jButton_Refresh3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Refresh3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Refresh3.setText("Refresh");
        jButton_Refresh3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Refresh3ActionPerformed(evt);
            }
        });

        jButton_First3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_First3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_First3.setText("<<");
        jButton_First3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_First3ActionPerformed(evt);
            }
        });

        jButton_Next3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Next3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Next3.setText(">");
        jButton_Next3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Next3ActionPerformed(evt);
            }
        });

        jButton_Previous3.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Previous3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_Previous3.setText("<");
        jButton_Previous3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Previous3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Previous3ActionPerformed(evt);
            }
        });

        jButton_Last3.setBackground(new java.awt.Color(255, 212, 60));
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
                                .addComponent(jSpinner_id3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 119, Short.MAX_VALUE))))
                    .addGroup(locationPanelLayout.createSequentialGroup()
                        .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37)
                            .addComponent(jLabel39)
                            .addComponent(jLabel40))
                        .addGap(65, 65, 65)
                        .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
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
                .addGap(756, 756, 756))
            .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locationPanelLayout.createSequentialGroup()
                    .addContainerGap(529, Short.MAX_VALUE)
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
                .addGap(63, 63, 63)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner_id3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_fullname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addGap(13, 13, 13)
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
                .addContainerGap(83, Short.MAX_VALUE))
            .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(locationPanelLayout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                    .addGroup(locationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton_Last3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Previous3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_Next3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton_First3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(122, 122, 122)))
        );

        jPanel2.add(locationPanel, "card4");

        customerPanel.setBackground(new java.awt.Color(255, 255, 255));

        jButton_Last2.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Last2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Last2.setText(">>");
        jButton_Last2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Last2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Last2ActionPerformed(evt);
            }
        });

        jButton_Previous2.setBackground(new java.awt.Color(255, 212, 60));
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

        jButton_Next2.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Next2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Next2.setText(">");
        jButton_Next2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Next2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Next2ActionPerformed(evt);
            }
        });

        jButton_First2.setBackground(new java.awt.Color(255, 212, 60));
        jButton_First2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_First2.setText("<<");
        jButton_First2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_First2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_First2ActionPerformed(evt);
            }
        });

        jButton_Clear2.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Clear2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Clear2.setText("Clear");
        jButton_Clear2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Clear2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Clear2ActionPerformed(evt);
            }
        });

        jButton_Refresh2.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Refresh2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Refresh2.setText("Refresh");
        jButton_Refresh2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Refresh2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Refresh2ActionPerformed(evt);
            }
        });

        jButton_Edit2.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Edit2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Edit2.setText("Edit");
        jButton_Edit2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Edit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Edit2ActionPerformed(evt);
            }
        });

        jButton_Remove2.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Remove2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton_Remove2.setText("Remove");
        jButton_Remove2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Remove2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove2ActionPerformed(evt);
            }
        });

        jButton_Add2.setBackground(new java.awt.Color(255, 212, 60));
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
                                .addComponent(jButton_Add2, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addComponent(jTextField_fullname, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_phone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, customerPanelLayout.createSequentialGroup()
                                .addComponent(jSpinner_id2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 146, Short.MAX_VALUE)))
                        .addGap(752, 752, 752))
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(jDateChooser_birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jButton_Refresh2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_Clear2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(customerPanelLayout.createSequentialGroup()
                    .addContainerGap(564, Short.MAX_VALUE)
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
                        .addComponent(jDateChooser_birthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                    .addGap(115, 115, 115)))
        );

        jPanel2.add(customerPanel, "card6");

        bookCarPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        jButton_BookCar_.setBackground(new java.awt.Color(255, 212, 60));
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
        jLabel_totalFee.setText("###");

        jButton_BookingLIst_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_BookingLIst_.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_BookingLIst_.setText("Booking List");
        jButton_BookingLIst_.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_BookingLIst_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BookingLIst_ActionPerformed(evt);
            }
        });

        jButton_Edit_Remove_Booking.setBackground(new java.awt.Color(255, 212, 60));
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

        jButton_BookingLIst_1.setBackground(new java.awt.Color(255, 212, 60));
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
        jLabel_car_model.setText("###");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Model:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Price/Day:");

        jLabel_pricePerDay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel_pricePerDay.setText("###");

        javax.swing.GroupLayout jPanel_select_carLayout = new javax.swing.GroupLayout(jPanel_select_car);
        jPanel_select_car.setLayout(jPanel_select_carLayout);
        jPanel_select_carLayout.setHorizontalGroup(
            jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_select_car, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                .addGap(65, 65, 65)
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
                        .addComponent(jButton_select_car_)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_car_id)
                        .addGap(85, 85, 85)))
                .addGap(142, 142, 142))
        );
        jPanel_select_carLayout.setVerticalGroup(
            jPanel_select_carLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_select_carLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_select_car, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
                .addContainerGap(54, Short.MAX_VALUE))
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
                .addContainerGap(135, Short.MAX_VALUE))
        );
        bookCarPanelLayout.setVerticalGroup(
            bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookCarPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(bookCarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel_rent_details, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bookCarPanelLayout.createSequentialGroup()
                        .addComponent(jPanel_select_car, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel_select_customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jPanel2.add(bookCarPanel, "card7");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        populateJtableWithCars();
    }//GEN-LAST:event_homeTabMouseClicked

    private void vehicleTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vehicleTabMouseClicked
        vehiclePanel.setVisible(true);
        homePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_vehicleTabMouseClicked

    private void brandTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brandTabMouseClicked
        brandPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_brandTabMouseClicked

    private void locationTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_locationTabMouseClicked
        locationPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        customerPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_locationTabMouseClicked

    private void customerTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerTabMouseClicked
        customerPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        bookCarPanel.setVisible(false);
    }//GEN-LAST:event_customerTabMouseClicked

    private void bookCarTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookCarTabMouseClicked
        bookCarPanel.setVisible(true);
        homePanel.setVisible(false);
        vehiclePanel.setVisible(false);
        brandPanel.setVisible(false);
        locationPanel.setVisible(false);
        customerPanel.setVisible(false);
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
            car.addCar(brand, model, fuel, color, plateNum, passengers, gearbox, price, air_cond,
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
        jSpinner_Price.setValue(10);
        jLabel_Brand_Id.setText("#");
        jSpinner_Passengers.setValue(0);
        jComboBox_Fuel.setSelectedIndex(0);
        jComboBox_Color.setSelectedIndex(0);
        jComboBox_brand.setSelectedIndex(0);
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
        String fuel = jComboBox_Fuel.getSelectedItem().toString();
        String color = jComboBox_Color.getSelectedItem().toString();
        String plateNum = jTextField_PlateNum.getText();
        int passengers = (int)jSpinner_Passengers .getValue();
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
            car.editCar(id, brand, model, fuel, color, plateNum, passengers, gearbox, price, air_cond,
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

        jSpinner_id.setValue(id);
        jTextField_fullname.setText(fullname);
        jTextField_phone.setText(phone);
        jTextField_email.setText(email);
        jTextArea_address.setText(address);

        Date bdate;
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(jTable_Customers_.getValueAt(index, 2).toString());
            jDateChooser_birthDate.setDate(bdate);
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
        jDateChooser_birthDate.setDate(null);
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
            String birthdate = dateformat.format(jDateChooser_birthDate.getDate());

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
            String birthDate = dateformat.format(jDateChooser_birthDate.getDate());

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
            String birthdate = dateformat.format(jDateChooser_birthDate.getDate());

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
            String birthDate = dateformat.format(jDateChooser_birthDate.getDate());

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
        jDateChooser_birthDate.setDate(null);
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

        jSpinner_id.setValue(id);
        jTextField_fullname.setText(fullname);
        jTextField_phone.setText(phone);
        jTextField_email.setText(email);
        jTextArea_address.setText(address);

        Date bdate;
        try {
            bdate = new SimpleDateFormat("yyyy-MM-dd").parse(jTable_Drivers_.getValueAt(index, 2).toString());
            jDateChooser_birthDate.setDate(bdate);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(Form_Drivers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable_Drivers_MouseClicked

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookCarPanel;
    private javax.swing.JPanel bookCarTab;
    private javax.swing.JPanel brandPanel;
    private javax.swing.JPanel brandTab;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JPanel customerTab;
    private javax.swing.JPanel homePanel;
    private javax.swing.JPanel homeTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Add;
    private javax.swing.JButton jButton_Add2;
    private javax.swing.JButton jButton_Add3;
    private javax.swing.JButton jButton_Add_Brands_List_;
    private javax.swing.JButton jButton_Add_Car_;
    private javax.swing.JButton jButton_BookCar_;
    private javax.swing.JButton jButton_BookingLIst_;
    private javax.swing.JButton jButton_BookingLIst_1;
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
    private javax.swing.JButton jButton_Search_;
    private javax.swing.JButton jButton_Select_Customer;
    private javax.swing.JButton jButton_browse;
    private javax.swing.JButton jButton_select_car_;
    private javax.swing.JComboBox<String> jComboBox_Color;
    private javax.swing.JComboBox<String> jComboBox_DriverList;
    private javax.swing.JComboBox<String> jComboBox_Fuel;
    private javax.swing.JComboBox<String> jComboBox_brand;
    private com.toedter.calendar.JDateChooser jDateChooser_Pickup_Date;
    private com.toedter.calendar.JDateChooser jDateChooser_birthDate;
    private com.toedter.calendar.JDateChooser jDateChooser_dropoff;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSpinner jSpinner_Id;
    private javax.swing.JSpinner jSpinner_Passengers;
    private javax.swing.JSpinner jSpinner_Price;
    private javax.swing.JSpinner jSpinner_id;
    private javax.swing.JSpinner jSpinner_id2;
    private javax.swing.JSpinner jSpinner_id3;
    private javax.swing.JTable jTable_Brands;
    public javax.swing.JTable jTable_Cars;
    private javax.swing.JTable jTable_Customers_;
    private javax.swing.JTable jTable_Drivers_;
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
    private javax.swing.JPanel locationPanel;
    private javax.swing.JPanel locationTab;
    private javax.swing.JPanel vehiclePanel;
    private javax.swing.JPanel vehicleTab;
    // End of variables declaration//GEN-END:variables
}
