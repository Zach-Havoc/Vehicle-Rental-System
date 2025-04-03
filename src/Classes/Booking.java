package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Booking {

    private int id;
    private int car_id;
    private int customer_id;
    private String start_date;
    private String end_date;
    private int total_price;
    private String driver;
    private String driverName;

    // Constructor
    public Booking(){
    }

   public Booking(int id, int car_id, int customer_id, String start_date, String end_date, int total_price, String driver, String driverName){
    this.id = id;
    this.car_id = car_id;
    this.customer_id = customer_id;
    this.start_date = start_date;
    this.end_date = end_date;
    this.total_price = total_price;
    this.driver = driver;
    this.driverName = driverName;
   }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    
    
    
    public void addNewBooking(int carId, int customerId, String startDate, String endDate, int totalPrice, 
                          String driver, String driverName) {
    boolean isAvailable = isCarAvailable(carId, startDate, endDate);
    if (!isAvailable) {
        JOptionPane.showMessageDialog(null, "Car is not available for the selected dates!");
        return;
    }

    String insertQuery = "INSERT INTO `reservation`(`car_id`, `customer_id`, `start_date`, `end_date`, `total_price`, `driver`, `driverName`) "
                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery)) {
        ps.setInt(1, carId);
        ps.setInt(2, customerId);
        ps.setString(3, startDate);
        ps.setString(4, endDate);
        ps.setInt(5, totalPrice);
        ps.setString(6, driver);
        ps.setString(7, driverName);

        if (ps.executeUpdate() != 0) {
            JOptionPane.showMessageDialog(null, "Booking added successfully!", "Add Booking", JOptionPane.INFORMATION_MESSAGE);

            // Update the label for available cars
            Car car = new Car();
            car.updateCarStatus(carId, "booked");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add the booking. Please try again.", "Add Booking", JOptionPane.ERROR_MESSAGE);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
    
    
    
    
// OKAY NA TO
//    public void addNewBooking(int carId, int customerId, String startDate, String endDate, int totalPrice, 
//                          String driver, String driverName) {
//    // Check car availability
//    boolean isAvailable = isCarAvailable(carId, startDate, endDate);
//    if (!isAvailable) {
//        JOptionPane.showMessageDialog(null, "Car is not available for the selected dates!");
//        return;
//    }
//
//    String insertQuery = "INSERT INTO `reservation`(`car_id`, `customer_id`, `start_date`, `end_date`, `total_price`, `driver`, `driverName`) "
//                       + "VALUES (?, ?, ?, ?, ?, ?, ?)";
//    try (PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery)) {
//        ps.setInt(1, carId);
//        ps.setInt(2, customerId);
//        ps.setString(3, startDate);
//        ps.setString(4, endDate);
//        ps.setInt(5, totalPrice);
//        ps.setString(6, driver);
//        ps.setString(7, driverName);
//
//        if (ps.executeUpdate() != 0) {
//            JOptionPane.showMessageDialog(null, "Booking added successfully!", "Add Booking", JOptionPane.INFORMATION_MESSAGE);
//
//            // Update car status to unavailable
//            Car car = new Car();
//            car.updateCarStatus(carId, false);
//        } else {
//            JOptionPane.showMessageDialog(null, "Failed to add the booking. Please try again.", "Add Booking", JOptionPane.ERROR_MESSAGE);
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
//    }
//}


//     
//     public void updateCarStatus(int carId, boolean status) {
//    String updateQuery = "UPDATE `cars` SET `status` = ? WHERE `id` = ?";
//    PreparedStatement ps;
//
//    try {
//        ps = DB.getConnection().prepareStatement(updateQuery);
//        ps.setBoolean(1, status); // Set the status (true = available, false = not available)
//        ps.setInt(2, carId); // Set the car ID
//        
//        if (ps.executeUpdate() != 0) {
//            System.out.println("Car status updated successfully.");
//        } else {
//            JOptionPane.showMessageDialog(null, "Failed to update car status.", "Update Status", JOptionPane.ERROR_MESSAGE);
//        }
//    } catch (SQLException ex) {
//        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
//    }
//}

    
    
    public void editBooking(int id, int carId, int customerId, String startDate, String endDate, int totalPrice, 
                          String driver, String driverName) {
    // SQL query to insert booking data
    String editQuery = "UPDATE `reservation` SET `car_id`= ?,`customer_id`= ?,`start_date`= ?,`end_date`= ?,`total_price`= ?,`driver`= ?,`driverName`= ? WHERE `id`= ?";
    PreparedStatement ps;

    try {
        // Get database connection
        ps = DB.getConnection().prepareStatement(editQuery);

        // Set the parameters in the query
        ps.setInt(1, carId);
        ps.setInt(2, customerId);
        ps.setString(3, startDate);  
        ps.setString(4, endDate);    
        ps.setInt(5, totalPrice);   
        ps.setString(6, driver);     
        ps.setString(7, driverName); 
        ps.setInt(8, id); 

        // Execute the query and check the result
        if (ps.executeUpdate() != 0) {
            JOptionPane.showMessageDialog(null, "Edited", "Booking", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Unable To Edit", "Booking", JOptionPane.ERROR_MESSAGE);
        }

    } catch (SQLException ex) {
        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "An error occurred while adding the booking: " + ex.getMessage(), "Add Booking", JOptionPane.ERROR_MESSAGE);
    }
}

         // function to return a resultset
     
     public ResultSet getData(String query){
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = DB.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
     
    // Function to get all bookings and return an array list
     public ArrayList<Booking> bookingList() {
        ArrayList<Booking> bookList = new ArrayList<>();
        ResultSet rs = getData("SELECT * FROM `reservation`");

        try {
            if (rs == null) {
                System.out.println("No data returned from the query.");
                return bookList;
            }

            while (rs.next()) {
                int id = rs.getInt("id"); 
                int car_id = rs.getInt("car_id");
                int customer_id = rs.getInt("customer_id");
                String start_date = rs.getString("start_date");
                String end_date = rs.getString("end_date");
                int total_price = rs.getInt("total_price");
                String driver = rs.getString("driver");
                String driverName = rs.getString("driverName");

                // Create and add booking to the list
                Booking booking = new Booking(id, car_id, customer_id, start_date, end_date, total_price, driver, driverName);
                bookList.add(booking);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, "Error while retrieving bookings", ex);
            JOptionPane.showMessageDialog(null, "Error retrieving bookings: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return bookList;
    }


     


    
    public boolean isCarAvailable(int carId, String startDate, String endDate) {
    String query = "SELECT * FROM `reservation` WHERE `car_id` = ? AND ("
                 + "(? BETWEEN `start_date` AND `end_date`) OR "
                 + "(? BETWEEN `start_date` AND `end_date`) OR "
                 + "(`start_date` BETWEEN ? AND ?) OR "
                 + "(`end_date` BETWEEN ? AND ?))";
    try (PreparedStatement ps = DB.getConnection().prepareStatement(query)) {
        ps.setInt(1, carId);
        ps.setString(2, startDate);
        ps.setString(3, endDate);
        ps.setString(4, startDate);
        ps.setString(5, endDate);
        ps.setString(6, startDate);
        ps.setString(7, endDate);
        ResultSet rs = ps.executeQuery();
        return !rs.next(); // If no overlapping reservation exists, car is available
    } catch (SQLException ex) {
        Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
    
    public void updateBookStatus(int id) {
 
    String deleteReservationQuery = "DELETE FROM `reservation` WHERE `id` = ?";  // Delete the reservation linked to the car

    try (PreparedStatement psCar = DB.getConnection().prepareStatement(deleteReservationQuery);
         PreparedStatement ps = DB.getConnection().prepareStatement(deleteReservationQuery)) {

        // Update the car's status to available (true) or unavailable (false)
        

        // Remove the reservation associated with this car
        ps.setInt(1, id); //ITO ANG BABAGUHIN
        ps.executeUpdate();  // Remove the reservation

        // Optional: log success or display a message
        System.out.println("Book status updated and reservation removed successfully.");

    } catch (SQLException ex) {
        // Handle exceptions (e.g., log the error)
        Logger.getLogger(Car.class.getName()).log(Level.SEVERE, null, ex);
    }
}

  

}


