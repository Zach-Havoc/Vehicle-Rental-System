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
public class Brand {
    
     private int id;
    private String name;
    private byte[] logo;
    
    public Brand() {
        
    }
    
    public Brand(int _id, String _name, byte[] _logo) {
        this.id = _id;
        this.name = _name;
        this.logo = _logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }
    
    public void addBrand(String brandName, byte[] brandLogo) {
        // Query to check if the brand already exists in the database
        String checkQuery = "SELECT COUNT(*) FROM brands WHERE name = ?";

        try (PreparedStatement checkStmt = DB.getConnection().prepareStatement(checkQuery)) {
            checkStmt.setString(1, brandName);  // Set the brand name parameter
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // If the brand already exists, show an error message
                JOptionPane.showMessageDialog(null, "Brand already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // If the brand doesn't exist, proceed with adding the brand
                String insertQuery = "INSERT INTO brands (name, logo) VALUES (?, ?)";
                try (PreparedStatement insertStmt = DB.getConnection().prepareStatement(insertQuery)) {
                    insertStmt.setString(1, brandName);  // Set brand name
                    insertStmt.setBytes(2, brandLogo);   // Set brand logo

                    int rowsAffected = insertStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Brand added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add brand.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    
    // function to edit brand
     public void editBrand(int _id, String _name, byte[] _logo){
        String editQuery = "UPDATE `brands` SET `name`= ? ,`logo`= ? WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(editQuery);
            ps.setString(1, _name);
            ps.setBytes(2, _logo);
            ps.setInt(3, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Vehicle brand has been edited" , "Edit Vehicle brand", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Vehicle Brand not been edited" , "Edit Vehicle brand", 2);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     
     // function to remove brand
     public void removeBrand(int _id){
        String removeQuery = "DELETE FROM `brands` WHERE `id` = ?";
        PreparedStatement ps;
        
        try {
            
            ps = DB.getConnection().prepareStatement(removeQuery);
            
            ps.setInt(1, _id);
            
            if(ps.executeUpdate()!=0){
                JOptionPane.showMessageDialog(null , "The Vehicle brand has been Removed" , "Remove Vehicle brand", 1);
                
            }
            else{
                JOptionPane.showMessageDialog(null , "Vehicle Brand Not been Removed" , "Remove Vehicle brand", 2);
                //System.out.println("Brand Not Removed!");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
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
             Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
         }
         return rs;
     }
     
     //function to get all brand, return in array an list
     public ArrayList<Brand> brandsList(){
         ArrayList<Brand> brdList = new ArrayList<>();
         
         ResultSet rs = getData("SELECT * FROM `brands`");
         
         try {
             while(rs.next()){
                 Brand brand = new Brand(rs.getInt(1),rs.getString(2),rs.getBytes(3));
                 brdList.add(brand);
                 
             }
         } catch (SQLException ex) {
             Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
         }
         return brdList;
     }
     
     // function to get brand ID
     
     public Brand getBrandById(int brand_id){
         String query = "SELECT * FROM `brands` WHERE `id` = "+ brand_id ;
         ResultSet rs = getData(query);
         Brand brand = null;
         try {
             rs.next();
             brand = new Brand(rs.getInt(1), rs.getString(2), rs.getBytes(3));
         } catch (SQLException ex) {
             Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
         }
         return brand;
     }
     
     // function to populate a hashmap with brands 
     public HashMap<Integer, String> brandsHashMap() {
    HashMap<Integer, String> brand_map = new HashMap<Integer, String>();
    ResultSet rs = getData("SELECT * FROM `brands`");

    try {
        while (rs.next()) {
            int brandId = rs.getInt(1);  
            String brandName = rs.getString(2);  
            brand_map.put(brandId, brandName);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
    }

    return brand_map;
}

     
     public static class BrandBookingCount {
    public String brand;
    public int count;
    public BrandBookingCount(String brand, int count) {
        this.brand = brand;
        this.count = count;
    }
}

    
    public static ArrayList<BrandBookingCount> getTopNBookedBrands(int limit) {
    ArrayList<BrandBookingCount> topBrands = new ArrayList<>();
    String sql = "SELECT c.brand, COUNT(*) AS times_booked " +
                 "FROM reservation r " +
                 "JOIN cars c ON r.car_id = c.id " +
                 "GROUP BY c.brand " +
                 "ORDER BY times_booked DESC " +
                 "LIMIT " + limit;
    try (PreparedStatement ps = DB.getConnection().prepareStatement(sql)) {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            topBrands.add(new BrandBookingCount(rs.getString("brand"), rs.getInt("times_booked")));
        }
    } catch (SQLException ex) {
        Logger.getLogger(Brand.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    return topBrands;
}

     
}
