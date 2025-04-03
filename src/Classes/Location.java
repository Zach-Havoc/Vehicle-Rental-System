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
public class Location {


    private int id;
    private String city;
    private String address;
    
    
    public Location() {}
    
    public Location(int _id, String _city, String _address) {
        this.id = _id;
        this.city = _city;
        this.address = _address;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addLocation (String _city, String _address){
        
        String insertQuery = "INSERT INTO `locations`(`city`, `location`) VALUES (?,?)";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _city);
            ps.setString(2, _address);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "Location has been Added" , "Add Location", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Location Not been Added" , "Add Location", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // create a function to edit location
    public void editLocation(int _id, String _city, String _address){
        
         String editQuery = "UPDATE `locations` SET `city`=?,`location`=? WHERE `id`=?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _city);
            ps.setString(2, _address);
            ps.setInt(3, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Location has been edited" , "Edit Location", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Location not been edited" , "Edit Location", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // create a function to remove location
    public void removeLocation(int _id){
        
        String removeQuery = "DELETE FROM `locations` WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(removeQuery);
            
            ps.setInt(1, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Location has been Removed" , "Remove Location", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Location Not been Removed" , "Remove Location", 2);
                //System.out.println("Brand Not Removed!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
         }
         return rs;
     }
    
    
    //function to get all Locations, return in array an list
     public ArrayList<Location> locationList(){
         ArrayList<Location> locList = new ArrayList<>();
         
         ResultSet rs = getData("SELECT * FROM `locations`");
         
         try {
             while(rs.next()){
                 System.out.println(rs.getInt(1));
                 System.out.println(rs.getString(2));
                 Location location = new Location(rs.getInt(1),rs.getString(2),rs.getString(3));
                 locList.add(location);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
         }
         return locList;
     }
    
     //function to get all Locations, return in array an list
     public ArrayList<Location> locationListByCity(String city){
         ArrayList<Location> locList = new ArrayList<>();
         
         ResultSet rs = getData("SELECT * FROM `locations` WHERE `city` " + city);
         
         try {
             while(rs.next()){
                 System.out.println(rs.getInt(1));
                 System.out.println(rs.getString(2));
                 Location location = new Location(rs.getInt(1),rs.getString(2),rs.getString(3));
                 locList.add(location);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Location.class.getName()).log(Level.SEVERE, null, ex);
         }
         return locList;
     }
  
}
