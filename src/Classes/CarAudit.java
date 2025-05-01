/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import Classes.DB;
import com.sun.jdi.connect.spi.Connection;
import java.beans.Statement;
/**
 *
 * @author ANGEL SHANE
 */
public class CarAudit {
    private int auditId;
    private String action;
    private int carId;
    private String brand;
    private String model;
    private String fuel;
    private String color;
    private String plateNum;
    private int passengers;
    private String gearbox;
    private double price;
    private String airConditioning;
    private String airbag;
    private String sunroof;
    private String heatedSeats;
    private String navSystem;
    private String bluetooth;
    private String electricWindows;
    private String gps;
    private String status;
    private String actionTime;
    
    public CarAudit(){};
    
    public CarAudit(int auditId, String action, int carId, String brand, String model,
                String fuel, String color, String plateNum, int passengers, String gearbox,
                double price, String airConditioning, String airbag, String sunroof,
                String heatedSeats, String navSystem, String bluetooth, String electricWindows,
                String gps, String status, String actionTime) {
    this.auditId = auditId;
    this.action = action;
    this.carId = carId;
    this.brand = brand;
    this.model = model;
    this.fuel = fuel;
    this.color = color;
    this.plateNum = plateNum;
    this.passengers = passengers;
    this.gearbox = gearbox;
    this.price = price;
    this.airConditioning = airConditioning;
    this.airbag = airbag;
    this.sunroof = sunroof;
    this.heatedSeats = heatedSeats;
    this.navSystem = navSystem;
    this.bluetooth = bluetooth;
    this.electricWindows = electricWindows;
    this.gps = gps;
    this.status = status;
    this.actionTime = actionTime;
}

        
    // Getters and setters for all the fields
    public int getAuditId() { return auditId; }
    public void setAuditId(int auditId) { this.auditId = auditId; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getFuel() { return fuel; }
    public void setFuel(String fuel) { this.fuel = fuel; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getPlateNum() { return plateNum; }
    public void setPlateNum(String plateNum) { this.plateNum = plateNum; }

    public int getPassengers() { return passengers; }
    public void setPassengers(int passengers) { this.passengers = passengers; }

    public String getGearbox() { return gearbox; }
    public void setGearbox(String gearbox) { this.gearbox = gearbox; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getAirConditioning() { return airConditioning; }
    public void setAirConditioning(String airConditioning) { this.airConditioning = airConditioning; }

    public String getAirbag() { return airbag; }
    public void setAirbag(String airbag) { this.airbag = airbag; }

    public String getSunroof() { return sunroof; }
    public void setSunroof(String sunroof) { this.sunroof = sunroof; }

    public String getHeatedSeats() { return heatedSeats; }
    public void setHeatedSeats(String heatedSeats) { this.heatedSeats = heatedSeats; }

    public String getNavSystem() { return navSystem; }
    public void setNavSystem(String navSystem) { this.navSystem = navSystem; }

    public String getBluetooth() { return bluetooth; }
    public void setBluetooth(String bluetooth) { this.bluetooth = bluetooth; }

    public String getElectricWindows() { return electricWindows; }
    public void setElectricWindows(String electricWindows) { this.electricWindows = electricWindows; }

    public String getGps() { return gps; }
    public void setGps(String gps) { this.gps = gps; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getActionTime() { return actionTime; }
    public void setActionTime(String actionTime) { this.actionTime = actionTime; }
    
    
    public ArrayList<CarAudit> getCarAuditList() {
        ArrayList<CarAudit> carAuditList = new ArrayList<>();
        String query = "SELECT * FROM cars_audit"; // Your query to fetch all records from the audit table
        
        // Using try-with-resources to ensure automatic resource management
        try (java.sql.Connection conn = DB.getConnection(); 
             java.sql.Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(query)) {
            
            // Iterate through the ResultSet and create CarAudit objects
            while (rs.next()) {
                CarAudit audit = new CarAudit();
                
                // Setting the CarAudit object properties based on ResultSet data
                audit.setAuditId(rs.getInt("audit_id"));
                audit.setAction(rs.getString("action"));
                audit.setCarId(rs.getInt("id"));
                audit.setBrand(rs.getString("brand"));
                audit.setModel(rs.getString("model"));
                audit.setFuel(rs.getString("fuel"));
                audit.setColor(rs.getString("color"));
                audit.setPlateNum(rs.getString("plateNum"));
                audit.setPassengers(rs.getInt("passengers"));
                audit.setGearbox(rs.getString("gearbox"));
                audit.setPrice(rs.getDouble("price"));
                audit.setAirConditioning(rs.getString("air_conditioning"));
                audit.setAirbag(rs.getString("air_bag"));
                audit.setSunroof(rs.getString("sunroof"));
                audit.setHeatedSeats(rs.getString("heated_seats"));
                audit.setNavSystem(rs.getString("nav_system"));
                audit.setBluetooth(rs.getString("bluetooth"));
                audit.setElectricWindows(rs.getString("electric_windows"));
                audit.setGps(rs.getString("gps"));
                audit.setStatus(rs.getString("status"));
                audit.setActionTime(rs.getString("action_time"));
                
                // Add the CarAudit object to the list
                carAuditList.add(audit);
            }
            
        } catch (SQLException e) {
            // Log the exception or handle it accordingly
            e.printStackTrace();
        }
        
        return carAuditList;
    }


}

