
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Car {
    
    private int id;
    private String brand;
    private String model;
    private String type;
    private String fuel;
    private String color;
    private String plateNum;
    private int passengers;
    private String gearbox;
    private int price;
    private String air_cond;
    private String airbag;
    private String sunroof;
    private String heated_seats;
    private String nav_sys;
    private String bluetooth;
    private String elec_window;
    private String gps;
    private String status;

    public Car() {
    }

    public Car(int id, String brand, String model, String type, String fuel, String color, String plateNum, int passengers, String gearbox, int price, String air_cond, String airbag, String sunroof, String heated_seats, String nav_sys, String bluetooth, String elec_window, String gps, String status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.fuel = fuel;
        this.color = color;
        this.plateNum = plateNum;
        this.passengers = passengers;
        this.gearbox = gearbox;
        this.price = price;
        this.air_cond = air_cond;
        this.airbag = airbag;
        this.sunroof = sunroof;
        this.heated_seats = heated_seats;
        this.nav_sys = nav_sys;
        this.bluetooth = bluetooth;
        this.elec_window = elec_window;
        this.gps = gps;
        this.status = status; // Initialize status
    }
    
     public String getType(){
         return type;
     }
     
     public void setType(String type){
         this.type = type;
     }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getplateNum_() {
        return plateNum;
    }

    public void setplateNum_(String plateNum) {
        this.plateNum = plateNum;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAir_cond() {
        return air_cond;
    }

    public void setAir_cond(String air_cond) {
        this.air_cond = air_cond;
    }

    public String getAirbag() {
        return airbag;
    }

    public void setAirbag(String airbag) {
        this.airbag = airbag;
    }

    public String getSunroof() {
        return sunroof;
    }

    public void setSunroof(String sunroof) {
        this.sunroof = sunroof;
    }

    public String getHeated_seats() {
        return heated_seats;
    }

    public void setHeated_seats(String heated_seats) {
        this.heated_seats = heated_seats;
    }

    public String getNav_sys() {
        return nav_sys;
    }

    public void setNav_sys(String nav_sys) {
        this.nav_sys = nav_sys;
    }

    public String getBluetooth() {
        return bluetooth;
    }

    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getElec_window() {
        return elec_window;
    }

    public void setElec_window(String elec_window) {
        this.elec_window = elec_window;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }
    
         // START Class for image
        public class CarImage{  
        private int img_id;
        private int car_id;
        private byte[] car_img; // Store binary image data
         
        public CarImage(){}   

        public CarImage(int img_id, int car_id, byte[] car_img){
            this.img_id = img_id;
            this.car_id = car_id;
            this.car_img = car_img;
        }
        
            // Getters and setters
            public int getImg_id() 
                {
                return img_id;
                }
                public void setImg_id(int img_id) 
                {
                this.img_id = img_id;
                }
                public int getCar_id() 
                {
                return car_id;
                }

            public void setCar_id(int car_id) 
                {
                this.car_id = car_id;
                }

            public byte[] getCar_img() 
            {
                return car_img;
            }

            public void setCar_img(byte[] car_img) 
            {
                this.car_img = car_img;
            }
    }
    // END Class for image
    
    
    
         
     // function to return a restulset
     public ResultSet getData(String query){
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = DB.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
     
    
    
         //function to get all cars, return in array an list
        public ArrayList<Car> carsList() {
        ArrayList<Car> carsList = new ArrayList<>();
        ResultSet rs = getData("SELECT * FROM `cars`");

        try {
            while (rs.next()) {
                Car car = new Car(
                    rs.getInt(1), // id
                    rs.getString(2), // brand
                    rs.getString(3), // model
                    rs.getString(4),
                    rs.getString(5), // fuel
                    rs.getString(6), // color
                    rs.getString(7), // plateNum
                    rs.getInt(8), // passengers
                    rs.getString(9), // gearbox
                    rs.getInt(10), // price
                    rs.getString(11), // air_cond
                    rs.getString(12), // airbag
                    rs.getString(13), // sunroof
                    rs.getString(14), // heated_seats
                    rs.getString(15), // nav_sys
                    rs.getString(16), // bluetooth
                    rs.getString(17), // elec_window
                    rs.getString(18), // gps
                    rs.getString(19) 
                );

                carsList.add(car);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carsList;
    }
  
public void addCar(String _brand, String _model, String _type, String _fuel, String _color, String _plateNum, int _passengers, 
                   String _gearbox, int _price, String _air_cond, String _airbag, String _sunroof,
                   String _heated_seats, String _nav_sys, String _bluetooth, String _elec_window, String _gps) {
    
    String insertQuery = "INSERT INTO `cars`(`brand`, `model`, `car_type`, `fuel`, `color`, `plateNum`, `passengers`, `gearbox`, `price`, `air_conditioning`, `air_bag`, `sunroof`, `heated_seats`, `nav_system`, `bluetooth`, `electric_windows`, `gps`, `status`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    PreparedStatement ps;

    try {
        ps = DB.getConnection().prepareStatement(insertQuery);
        ps.setString(1, _brand);         // 1
        ps.setString(2, _model);         // 2
        ps.setString(3, _type);          // 3
        ps.setString(4, _fuel);          // 4
        ps.setString(5, _color);         // 5
        ps.setString(6, _plateNum);      // 6
        ps.setInt(7, _passengers);       // 7
        ps.setString(8, _gearbox);       // 8
        ps.setInt(9, _price);            // 9
        ps.setString(10, _air_cond);     // 10
        ps.setString(11, _airbag);       // 11
        ps.setString(12, _sunroof);      // 12
        ps.setString(13, _heated_seats); // 13
        ps.setString(14, _nav_sys);      // 14
        ps.setString(15, _bluetooth);    // 15
        ps.setString(16, _elec_window);  // 16
        ps.setString(17, _gps);          // 17
        ps.setString(18, "available");   // 18


        if (ps.executeUpdate() != 0) {
            JOptionPane.showMessageDialog(null, "The new Vehicle has been Added", "Add Vehicle", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Vehicle Not been Added", "Add Vehicle", 2);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    // add car images
     public void addCarImage(int car_id, byte[] car_image){
        String insertQuery = "INSERT INTO `car_images`(`car_id`, `c_image`) VALUES (?,?)";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setInt(1, car_id);
            ps.setBytes(2, car_image);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The New Image has been Added" , "Add Image", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "(Image Not been Added" , "Add Image", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

     
     // function to edit brand
     public void editCar(int _id, String _brand, String _model, String _type, String _fuel, String _color, String _plateNum, int _passengers, 
                        String _gearbox, int _price, String _air_cond, String _airbag, String _sunroof,
                        String _heated_seats, String _nav_sys, String _bluetooth, String _elec_window, String _gps){
        String editQuery = "UPDATE `cars` SET `brand`=?,`model`=?,`fuel`=?,`color`=?,`plateNum`=?,`passengers`=?,`gearbox`=?,`price`=?,`air_conditioning`=?,`air_bag`=?,`sunroof`=?,`heated_seats`=?,`nav_system`=?,`bluetooth`=?,`electric_windows`=?,`gps`=? WHERE `id` =?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _brand);          // 1
            ps.setString(2, _model);          // 2
            ps.setString(3, _type);           // 3
            ps.setString(4, _fuel);           // 4
            ps.setString(5, _color);          // 5
            ps.setString(6, _plateNum);       // 6
            ps.setInt(7, _passengers);        // 7
            ps.setString(8, _gearbox);        // 8
            ps.setInt(9, _price);             // 9
            ps.setString(10, _air_cond);      // 10
            ps.setString(11, _airbag);        // 11
            ps.setString(12, _sunroof);       // 12
            ps.setString(13, _heated_seats);  // 13
            ps.setString(14, _nav_sys);       // 14
            ps.setString(15, _bluetooth);     // 15
            ps.setString(16, _elec_window);   // 16
            ps.setString(17, _gps);           // 17
            ps.setInt(18, _id);               // 18

            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Vehicle has been edited" , "Edit Vehicle info", 1);
            }
            else{
                JOptionPane.showMessageDialog(null , "Vehicle not been edited" , "Edit Vehicle info", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     // function to remove car
     public void removeCar(int _id){
        String removeQuery = "DELETE FROM `cars` WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(removeQuery);
            
            ps.setInt(1, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Vehicle has been Removed" , "Remove Vehicle", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Vehicle Not been Removed" , "Remove Vehicle", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
      //function to get car images
            public ArrayList<CarImage> carImagesList(int car_id) 
                {
                ArrayList<CarImage> images = new ArrayList<>();
                ResultSet rs = getData("SELECT `id`, `car_id`, `c_image` FROM `car_images` WHERE `car_id` =" + car_id);
                CarImage car_image;

                    try 
                    {
                        while (rs.next()) 
                        {
                        car_image = new CarImage();
                        car_image.setImg_id(rs.getInt(1)); // Image ID
                        car_image.setCar_id(rs.getInt(2)); // Car ID
                        // Retrieve the image data as a byte array
                        car_image.setCar_img(rs.getBytes(3)); 
                        images.add(car_image);
                        //car_image.setCar_img(imageData); // Set the image data in CarImage object
                        
                        }
                    } 
                    catch (SQLException ex) 
                    {
                        Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return images;
                    }
        
        //function to search Id
        public Car getCarById(int car_id){
         String query = "SELECT * FROM `cars` WHERE `id` = "+ car_id ;
         ResultSet rs = getData(query);
         Car car = null;
         try {
             if (rs.next())
             {

             car = new Car(
                            rs.getInt(1),      // id
                            rs.getString(2),   // brand
                            rs.getString(3),   // model
                            rs.getString(4),   // type
                            rs.getString(5),   // fuel
                            rs.getString(6),   // color
                            rs.getString(7),   // plateNum
                            rs.getInt(8),      // passengers
                            rs.getString(9),   // gearbox
                            rs.getInt(10),     // price
                            rs.getString(11),  // air_conditioning
                            rs.getString(12),  // air_bag
                            rs.getString(13),  // sunroof
                            rs.getString(14),  // heated_seats
                            rs.getString(15),  // nav_system
                            rs.getString(16),  // bluetooth
                            rs.getString(17),  // electric_windows
                            rs.getString(18),  // gps
                            rs.getString(19)   // status
                        );

             }
             else
             {
                 //JOptionPane.showMessageDialog(null , "ID not found!" , "Invalid ID", 2);
             }
         } catch (SQLException ex) {
             Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
         }
         return car;
     }
        
        
        public HashMap<Integer ,String> VehicleHashMap()
     {
         HashMap<Integer ,String> vehicle_map =  new  HashMap <Integer ,String>();
         
         ResultSet rs = getData("SELECT * FROM `cars`");
         
         
         try {
             while (rs.next())
             {
                 vehicle_map.put( rs.getInt(1), rs.getString(2));
             }
         } catch (SQLException ex) {
             Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
         }

         return vehicle_map;
     }
    
        //function to get cars by id
        public ArrayList<Car> carsByBrandList(int brand_id)
        {
         ArrayList<Car> carsList = new ArrayList<>();
         
         ResultSet rs = getData("SELECT * FROM `cars` WHERE brand = " + brand_id);
         
         try {
             while(rs.next())
             {
                 
               Car  car = new Car(
                        rs.getInt(1),      // id
                        rs.getString(2),   // brand
                        rs.getString(3),   // model
                        rs.getString(4),   // type
                        rs.getString(5),   // fuel
                        rs.getString(6),   // color
                        rs.getString(7),   // plateNum
                        rs.getInt(8),      // passengers
                        rs.getString(9),   // gearbox
                        rs.getInt(10),     // price
                        rs.getString(11),  // air_conditioning
                        rs.getString(12),  // air_bag
                        rs.getString(13),  // sunroof
                        rs.getString(14),  // heated_seats
                        rs.getString(15),  // nav_system
                        rs.getString(16),  // bluetooth
                        rs.getString(17),  // electric_windows
                        rs.getString(18),  // gps
                        rs.getString(19)   // status
                    );

                 
                 carsList.add(car);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
         }
         return carsList;
     }
        
        public boolean isCarAvailable(int carId, String startDate, String endDate) {
    String query = "SELECT * FROM `reservation` r "
                 + "WHERE r.car_id = ? AND ("
                 + "(? BETWEEN r.start_date AND r.end_date) OR "
                 + "(? BETWEEN r.start_date AND r.end_date) OR "
                 + "(r.start_date BETWEEN ? AND ?) OR "
                 + "(r.end_date BETWEEN ? AND ?))";
    
    try (PreparedStatement ps = DB.getConnection().prepareStatement(query)) {
        ps.setInt(1, carId);  // Set the car ID parameter
        ps.setString(2, startDate);  // Set the start date
        ps.setString(3, endDate);  // Set the end date
        ps.setString(4, startDate);  // Check if the start date overlaps
        ps.setString(5, endDate);  // Check if the start date overlaps
        ps.setString(6, startDate);  // Check if the end date overlaps
        ps.setString(7, endDate);  // Check if the end date overlaps
        
        ResultSet rs = ps.executeQuery();
        
        // If no rows are returned, it means there is no overlap, so the car is available
        return !rs.next();
    } catch (SQLException ex) {
        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return false;
}

                           // ito ay sa pag update ng kotse
        public void updateCarStatus(int carId, String status) {
    String updateCarQuery = "UPDATE `cars` SET `status` = ? WHERE `id` = ?";

    try (PreparedStatement psCar = DB.getConnection().prepareStatement(updateCarQuery);
         PreparedStatement ps = DB.getConnection().prepareStatement(updateCarQuery)) {

        // Update the car's status to available (true) or unavailable (false)
        psCar.setString(1, status);  
        psCar.setInt(2, carId);
        psCar.executeUpdate();


    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error)
        Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
    }
}

public long countAvailableCars() {
    ArrayList<Car> carsList = carsList();  // Fetch the list of cars from the database

    // Use Java Streams to filter the cars with status 'available' and count them
    long availableCarsCount = carsList.stream()
                                      .filter(car -> car.getStatus().equalsIgnoreCase("available"))
                                      .count();

    return availableCarsCount;  // Return the count of available cars
}
public long countMaintenanceCars() {
    ArrayList<Car> carsList = carsList();  // Fetch the list of cars from the database

    // Use Java Streams to filter the cars with status 'available' and count them
    long maintenanceCarsCount = carsList.stream()
                                      .filter(car -> car.getStatus().equalsIgnoreCase("maintenance"))
                                      .count();

    return maintenanceCarsCount;  // Return the count of available cars
}

}
