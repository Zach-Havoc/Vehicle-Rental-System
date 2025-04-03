/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Car;
import Classes.Customer;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author estan
 */
public class Form_DamagedCars extends javax.swing.JFrame {

    /**
     * Creates new form Form_DamagedCars
     */
    
    Classes.Booking booking = new Classes.Booking();
    ArrayList<Classes.Booking> book_list = booking.bookingList();
    public Form_DamagedCars() {
        initComponents();
    }
    
    
    public void populateJtableWithBooking() {
    // Get the list of bookings
    ArrayList<Classes.Booking> booking_list = booking.bookingList();

    // Debugging: print the number of bookings fetched
    System.out.println("Number of bookings: " + booking_list.size());

    

    // If there are no bookings, show a message and clear the table
    if (booking_list.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No bookings to display!", "Information", JOptionPane.INFORMATION_MESSAGE);
        //jTable_booking_.setModel(new DefaultTableModel(new Object[0][columnsName.length], columnsName));
        return;
    }
    String[] columnsName = {"Car Model", "Customer Name", "Start Date", "End Date", "Total Price", "Driver", "Driver Name", "Plate Number"};
    // Prepare the rows for the table
    Object[][] rows = new Object[booking_list.size()][columnsName.length];
    for (int i = 0; i < booking_list.size(); i++) {
        Classes.Booking booking = booking_list.get(i);
        
        // Get Car Model (assuming you have a method to fetch the car by ID)
        Car car = new Car();
        car = car.getCarById(booking.getCar_id());  // Assuming the booking has car_id
        String carModel = car != null ? car.getModel() : "Unknown";
        String plateNumber = car != null ? car.getplateNum_() : "Unknown";
        // Get Customer Name (assuming you have a method to fetch the customer by ID)
        Customer customer = new Customer();
        customer = customer.getCustomerById(booking.getCustomer_id());  // Assuming the booking has customer_id
        String customerName = customer != null ? customer.getFullname(): "Unknown";

        // Get Driver Name (assuming the driver is a reference or ID that you can fetch)
        String driverName = booking.getDriverName();
        if (booking.getDriver().equals("With Driver") && driverName != null) {
            driverName = driverName; // use driverName if available
        } else {
            driverName = "Self Drive";
        }

        // Populate rows for the table
        rows[i][0] = carModel;           // Car Model
        rows[i][1] = customerName;       // Customer Name
        rows[i][2] = booking.getStart_date();   // Start Date
        rows[i][3] = booking.getEnd_date();     // End Date
        rows[i][4] = booking.getTotal_price();  // Total Price
        rows[i][5] = booking.getDriver();       // Driver (Self Drive / With Driver)
        rows[i][6] = driverName;   // Driver Name
        rows[i][7] = plateNumber;
    }

    // Set table model with the updated rows
    //jTable_booking_.setModel(new DefaultTableModel(rows, columnsName));
}
    
    public void addToDamagedCarsTable(int carId, String plateNumber, int damageFee) {
    // Assuming you have a model for the damaged cars table, you can add a new row here
    DefaultTableModel damagedTableModel = (DefaultTableModel) jTable_Damaged_vehicle.getModel();
    
    
    
    
//    String[] columnsName = {"Car Model", "Customer Name", "Start Date", "End Date", "Total Price", "Driver", "Driver Name", "Plate Number"};
//    // Prepare the rows for the table
//    Object[][] rows = new Object[booking_list.size()][columnsName.length];
//    for (int i = 0; i < booking_list.size(); i++) {
//        Classes.Booking booking = booking_list.get(i);
//        
//        // Get Car Model (assuming you have a method to fetch the car by ID)
//        Car car = new Car();
//        car = car.getCarById(booking.getCar_id());  // Assuming the booking has car_id
//        String carModel = car != null ? car.getModel() : "Unknown";
//        String plateNumber = car != null ? car.getplateNum_() : "Unknown";
//        // Get Customer Name (assuming you have a method to fetch the customer by ID)
//        Customer customer = new Customer();
//        customer = customer.getCustomerById(booking.getCustomer_id());  // Assuming the booking has customer_id
//        String customerName = customer != null ? customer.getFullname(): "Unknown";
//
//        // Get Driver Name (assuming the driver is a reference or ID that you can fetch)
//        String driverName = booking.getDriverName();
//        if (booking.getDriver().equals("With Driver") && driverName != null) {
//            driverName = driverName; // use driverName if available
//        } else {
//            driverName = "Self Drive";
//        }
//
//        // Populate rows for the table
//        rows[i][0] = carModel;           // Car Model
//        rows[i][1] = customerName;       // Customer Name
//        rows[i][2] = booking.getStart_date();   // Start Date
//        rows[i][3] = booking.getEnd_date();     // End Date
//        rows[i][4] = booking.getTotal_price();  // Total Price
//        rows[i][5] = booking.getDriver();       // Driver (Self Drive / With Driver)
//        rows[i][6] = driverName;   // Driver Name
//        rows[i][7] = plateNumber;
//    }
    
    
    
    
    // Add the car to the damaged cars table with the damage fee and status
    Object[] row = new Object[4]; // Assuming 4 columns: ID, Plate Number, Damage Fee, Status
    row[0] = carId;
    row[1] = plateNumber;
    row[2] = damageFee;
    row[3] = "Damaged"; // Tag as damaged
    
    damagedTableModel.addRow(row);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_brands_logo = new javax.swing.JLabel();
        jLabel_close1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Damaged_vehicle = new javax.swing.JTable();
        jTextField_searchCustomer = new javax.swing.JTextField();
        jButton_Search_customerName = new javax.swing.JButton();
        jButton_resetTable = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1142, 592));

        jPanel3.setBackground(new java.awt.Color(255, 212, 60));

        jLabel4.setBackground(java.awt.Color.black);
        jLabel4.setFont(new java.awt.Font("Arial", 1, 34)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Damaged Vehicle");

        jLabel_brands_logo.setBackground(java.awt.Color.black);

        jLabel_close1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel_close1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_close1.setText("X");
        jLabel_close1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_close1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(254, 254, 254)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTable_Damaged_vehicle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable_Damaged_vehicle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Damaged_vehicle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Damaged_vehicleMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Damaged_vehicle);

        jButton_Search_customerName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_Search_customerName.setText("Search ");
        jButton_Search_customerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Search_customerNameActionPerformed(evt);
            }
        });

        jButton_resetTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton_resetTable.setText("Reset");
        jButton_resetTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_resetTableMouseClicked(evt);
            }
        });
        jButton_resetTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_resetTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 908, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField_searchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton_Search_customerName)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_resetTable)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_searchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Search_customerName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton_resetTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 964, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_close1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabel_close1MouseClicked

    private void jTable_Damaged_vehicleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Damaged_vehicleMouseClicked
        // get the selected customer
    }//GEN-LAST:event_jTable_Damaged_vehicleMouseClicked

    private void jButton_Search_customerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Search_customerNameActionPerformed
        // TODO add your handling code here:

        // Get the search term from the text field
        String searchName = jTextField_searchCustomer.getText().trim();

//        if (searchName.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "Please enter a name to search.", "Search Error", JOptionPane.ERROR_MESSAGE);
//            return; // Exit the function if the input is empty
//        }
//
//        // Call the function to search for customers by name and display the results
//        searchCustomerByName(searchName);

    }//GEN-LAST:event_jButton_Search_customerNameActionPerformed

    private void jButton_resetTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_resetTableMouseClicked
        // TODO add your handling code here:
       // populateJtableWithBooking();
    }//GEN-LAST:event_jButton_resetTableMouseClicked

    private void jButton_resetTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_resetTableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_resetTableActionPerformed

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
            java.util.logging.Logger.getLogger(Form_DamagedCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_DamagedCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_DamagedCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_DamagedCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_DamagedCars().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Search_customerName;
    private javax.swing.JButton jButton_resetTable;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_brands_logo;
    private javax.swing.JLabel jLabel_close1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Damaged_vehicle;
    private javax.swing.JTextField jTextField_searchCustomer;
    // End of variables declaration//GEN-END:variables
}
