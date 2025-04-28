/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ANGEL SHANE
 */
public class BookingHistory {
    private int id;
    private int carId;
    private int customerId;
    private String startDate;
    private String endDate;
    private double totalPrice;
    private String driver;
    private String driverName;
    private int totalKM;
    
    public BookingHistory() {}
    
    // Constructor
    public BookingHistory(int id, int carId, int customerId, String startDate, String endDate,
                          double totalPrice, String driver, String driverName, int totalKM) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.driver = driver;
        this.driverName = driverName;
        this.totalKM = totalKM;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public int getTotalKM() {
        return totalKM;
    }

    public void setTotalKM(int totalKM) {
        this.totalKM = totalKM;
    }

    public ResultSet getData(String query){
        PreparedStatement ps;
        ResultSet rs = null;
        try {
            ps = DB.getConnection().prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BookingHistory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    public void addHistory(int carId, int customerId, String startDate, String endDate, double totalPrice,
                           String driver, String driverName, int totalKM) {

        String insertQuery = "INSERT INTO `history`(`car_id`, `customer_id`, `start_date`, `end_date`, `total_price`, `driver`, `driverName`, `Total_KM`) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = null;
        try {
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setInt(1, carId);
            ps.setInt(2, customerId);
            ps.setString(3, startDate);
            ps.setString(4, endDate);
            ps.setDouble(5, totalPrice);
            ps.setString(6, driver);
            ps.setString(7, driverName);
            ps.setInt(8, totalKM);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("History record added successfully.");
                // Optional: Uncomment for UI feedback
                // JOptionPane.showMessageDialog(null, "History record added!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("Failed to add history record.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingHistory.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Class to store Customer Booking Count
public static class CustomerBookingCount {
    public String customerName;
    public int totalBookings;

    public CustomerBookingCount(String customerName, int totalBookings) {
        this.customerName = customerName;
        this.totalBookings = totalBookings;
    }
}

// Function to get Top 5 Customers
public static ArrayList<CustomerBookingCount> getTop5Customers() {
    ArrayList<CustomerBookingCount> topCustomers = new ArrayList<>();
    String sql = "SELECT cu.fullname, COUNT(h.id) AS total_bookings " +
                 "FROM history h " +
                 "JOIN customers cu ON h.customer_id = cu.id " +
                 "GROUP BY h.customer_id " +
                 "ORDER BY total_bookings DESC " +
                 "LIMIT 5";

    try (PreparedStatement ps = DB.getConnection().prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String customerName = rs.getString("fullname");
            int totalBookings = rs.getInt("total_bookings");
            topCustomers.add(new CustomerBookingCount(customerName, totalBookings));
        }
    } catch (SQLException ex) {
        Logger.getLogger(BookingHistory.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return topCustomers;
}

    
    
}
