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
 * @author PC
 */
public class Drivers {
    
    private int id;
    private String fullname;
    private String birthdate;      
    private String phone;
    private String email;
    private String address;

    public Drivers() {
    }

    public Drivers(int id, String fullname, String birthdate, String phone, String email, String address) {
        this.id = id;
        this.fullname = fullname;
        this.birthdate = birthdate;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    // create a function to add a driver
    public void addDriver (String _fullname, String _birthdate, String _phone, String _email, String _address){
        
        String insertQuery = "INSERT INTO `drivers`(`fullname`, `birth_date`, `phone`, `email`, `address`) VALUES (?,?,?,?,?)";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _fullname);
            ps.setString(2, _birthdate);
            ps.setString(3, _phone);
            ps.setString(4, _email);
            ps.setString(5, _address);
           
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "New Driver has been Added" , "Add Driver", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Driver Not Added" , "Add Driver", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Drivers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // create a function to edit driver
    public void editDriver(int _id, String _fullname, String _birthdate, String _phone, String _email, String _address){
                             
         String editQuery = "UPDATE `drivers` SET `fullname`=?,`birth_date`=?,`phone`=?,`email`=?,`address`=? WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _fullname);
            ps.setString(2, _birthdate);
            ps.setString(3, _phone);
            ps.setString(4, _email);
            ps.setString(5, _address);
            ps.setInt(6, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "Driver's information has been edited" , "Edit Driver", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Driver's information remain unchanged" , "Edit Driver", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Drivers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // create a function to remove driver
    public void removeDriver(int _id){
        
        String removeQuery = "DELETE FROM `drivers` WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(removeQuery);
            
            ps.setInt(1, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "Driver has been Removed" , "Remove Driver", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Driver Not been Removed" , "Remove Driver", 2);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Drivers.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        // function to return a restulset
     public ResultSet getData(String query){
         
         PreparedStatement ps;
         ResultSet rs = null;
         try {
             
             
             ps = DB.getConnection().prepareStatement(query);
             rs = ps.executeQuery();
            } catch (SQLException ex) {
             Logger.getLogger(Drivers.class.getName()).log(Level.SEVERE, null, ex);
         }
         return rs;
     }
    
    
    //function to get all Drivers and return an array list
     public ArrayList<Drivers> DriversList(){
         ArrayList<Drivers> DriversList = new ArrayList<>();
         
         ResultSet rs = getData("SELECT * FROM view_all_drivers;");
         
         try {
             while(rs.next()){
                 rs.getInt(1);
                 rs.getString(2);
                 rs.getString(3);
                 rs.getString(4);
                 rs.getString(5);
                 rs.getString(6);
                 Drivers  driver = new Drivers(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
                 DriversList.add(driver);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Drivers.class.getName()).log(Level.SEVERE, null, ex);
         }
         return DriversList;
     }
    
  
}