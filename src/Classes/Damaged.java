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
 * @author estan
 */
public class Damaged {
    
    private int id;
    private int booking_id;
    private String booking_info;
    private String vahicle_status;
    private int total_cost;

    public Damaged() {
    }

    public Damaged(int id, int booking_id, String booking_info, String vahicle_status, int total_cost) {
        this.id = id;
        this.booking_id = booking_id;
        this.booking_info = booking_info;
        this.vahicle_status = vahicle_status;
        this.total_cost = total_cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getBooking_info() {
        return booking_info;
    }

    public void setBooking_info(String booking_info) {
        this.booking_info = booking_info;
    }

    public String getVahicle_status() {
        return vahicle_status;
    }

    public void setVahicle_status(String vahicle_status) {
        this.vahicle_status = vahicle_status;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }
    
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
     
    
    
    public ArrayList<Damaged> damagedList() {
        ArrayList<Damaged> damagedList = new ArrayList<>();
        ResultSet rs = getData("SELECT * FROM `reservation`");

        try {
            if (rs == null) {
                System.out.println("No data returned from the query.");
                return damagedList;
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
       //         bookList.add(booking);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, "Error while retrieving bookings", ex);
            JOptionPane.showMessageDialog(null, "Error retrieving bookings: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return damagedList;
    }
    
    
    
}
