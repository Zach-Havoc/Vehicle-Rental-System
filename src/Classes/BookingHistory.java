/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    public void addHistory(int carId, int customerId, String startDate, String endDate, double totalPrice,
                       String driver, String driverName, int totalKM) {

    String insertQuery = "INSERT INTO `history`(`car_id`, `customer_id`, `start_date`, `end_date`, `total_price`, `driver`, `driverName`, `Total_KM`) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    PreparedStatement ps;

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

    } catch (SQLException ex) {
        Logger.getLogger(BookingHistory.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    
}
