/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Classes.Car;
import java.awt.Image;
import java.io.File;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC
 */
public class Form_CarImages extends javax.swing.JFrame {

    /**
     * Creates new form Form_CarImages
     */
    
    Car car = new Car();
    ArrayList<Car>carList = car.carsList();
    ArrayList<Car.CarImage> carImages;
    
    public Form_CarImages() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        populateJtableWithCars();
    }
    
            // create resizable image to fit in jLabel
    public void displayImage(int width, int height, String image_path, JLabel label){

        // get the image
        var imageIco = new ImageIcon(image_path);
        // resize the icon
        Image image = imageIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set the image into JLabel
        label.setIcon(new ImageIcon(image));
    }
    
    // create resizable byte image to fit in jLabel
    public void displayByteImage(int width, int height, byte[] image_byte, JLabel label){

        // get the image
        ImageIcon imageIco = new ImageIcon(image_byte);
        // resize the image
        Image image = imageIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // set the image into JLabel
        label.setIcon(new ImageIcon(image));
    }
    
        // use images with a low size
    public String selectImage(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Picture");
        
        fileChooser.setCurrentDirectory(new File("c:\\images"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image", ".png", ".jpg");
        fileChooser.addChoosableFileFilter(filter);
        
        int state = fileChooser.showSaveDialog(null);
        String path = "";
        
        if(state == JFileChooser.APPROVE_OPTION){
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        
        
        return path;
    }
    
    
        // gagawa ng function para ipulate the jTable with cars (id & name )
        public void populateJtableWithCars(){
       // JTable Columns
        String[] columnsName = {"ID", "Model", "Plate Number"};
       // Rows
       Object[][] rows = new Object[carList.size()][columnsName.length];
       
       for (int i = 0; i < carList.size(); i++){
           
           rows[i][0] = carList.get(i).getId();
           rows[i][1] = carList.get(i).getModel();
           rows[i][2] = carList.get(i).getplateNum_();
       }
       DefaultTableModel model = new DefaultTableModel(rows,columnsName);
       jTable_Cars.setModel(model);
       
    }
        
            
        // gagawa ng function para ipulate the jTable with car images (id )
        public void populateJtableWithCarImages(int car_id) 
        {
            ArrayList<Car.CarImage> imageList = car.carImagesList(car_id);

            // JTable Columns
            String[] columnsName = {"Image ID", "Image"};
            
            
            Object[][] rows = new Object[imageList.size()][columnsName.length];

                for (int i = 0; i < imageList.size(); i++) 
                {
                    rows[i][0] = imageList.get(i).getImg_id();

                    // Convert the byte array to an ImageIcon for rendering
                    byte[] imgData = imageList.get(i).getCar_img();
                    if (imgData != null) 
                    {
                        ImageIcon imgIcon = new ImageIcon(new ImageIcon(imgData).getImage()
                        .getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                        rows[i][1] = imgIcon;
                    } 
                    else 
                    {
                        rows[i][1] = "No Image";
                    }
            }

    // Use a custom DefaultTableModel
    DefaultTableModel model = new DefaultTableModel(rows, columnsName) {
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 1 ? ImageIcon.class : Object.class; // Render the second column as ImageIcon
        }
    };

    jTable_CarImages.setModel(model);
    jTable_CarImages.setRowHeight(100); // Adjust row height for images
}

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_imagePath = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_brands_logo = new javax.swing.JLabel();
        jLabel_close1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Cars = new javax.swing.JTable();
        jLabel_CarImage = new javax.swing.JLabel();
        jButton_Select_Image_ = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_CarImages = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_Remove_Image_ = new javax.swing.JButton();
        jButton_Image_Slider_ = new javax.swing.JButton();
        jButton_Add_Image_ = new javax.swing.JButton();

        jLabel_imagePath.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 212, 60));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Car images");

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
                .addGap(394, 394, 394)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jLabel_brands_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel_close1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTable_Cars.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_Cars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_Cars.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_Cars.setRowHeight(50);
        jTable_Cars.setSelectionBackground(new java.awt.Color(102, 102, 102));
        jTable_Cars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CarsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Cars);

        jLabel_CarImage.setBackground(new java.awt.Color(204, 204, 204));
        jLabel_CarImage.setOpaque(true);

        jButton_Select_Image_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Select_Image_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Select_Image_.setText("Select image");
        jButton_Select_Image_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Select_Image_ActionPerformed(evt);
            }
        });

        jTable_CarImages.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_CarImages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable_CarImages.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable_CarImages.setSelectionBackground(new java.awt.Color(102, 102, 102));
        jTable_CarImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_CarImagesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable_CarImages);

        jLabel1.setFont(new java.awt.Font("Verdana", 2, 24)); // NOI18N
        jLabel1.setText("Cars");

        jLabel2.setFont(new java.awt.Font("Verdana", 2, 24)); // NOI18N
        jLabel2.setText("Selected Car Images");

        jButton_Remove_Image_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Remove_Image_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Remove_Image_.setText("Remove Image");
        jButton_Remove_Image_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Remove_Image_ActionPerformed(evt);
            }
        });

        jButton_Image_Slider_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Image_Slider_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Image_Slider_.setText("Images Slider");
        jButton_Image_Slider_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Image_Slider_ActionPerformed(evt);
            }
        });

        jButton_Add_Image_.setBackground(new java.awt.Color(255, 212, 60));
        jButton_Add_Image_.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton_Add_Image_.setText("Add image");
        jButton_Add_Image_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Add_Image_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton_Select_Image_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton_Remove_Image_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jButton_Image_Slider_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton_Add_Image_, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_CarImage, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel_CarImage, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Select_Image_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton_Add_Image_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Remove_Image_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Image_Slider_, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_close1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//GEN-LAST:event_jLabel_close1MouseClicked

    private void jTable_CarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CarsMouseClicked
        // get the selected brand
        int index = jTable_Cars.getSelectedRow();
        int car_id = Integer.valueOf(jTable_Cars.getValueAt(index, 0).toString());
        //System.out.println(id);
        populateJtableWithCarImages(car_id);
        carImages = car.carImagesList(car_id);
    }//GEN-LAST:event_jTable_CarsMouseClicked

    private void jTable_CarImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_CarImagesMouseClicked
        // display the selected image
        // get image from jtable
        int index = jTable_Cars.getSelectedRow();
        System.out.println("index --> " + index);
        int image_id = Integer.valueOf(jTable_CarImages.getValueAt(index, 0).toString());
        System.out.println("index --> " + index);
        
        byte[] img = null;
        
        for (Car.CarImage cimg : carImages){
            if(cimg.getImg_id () == image_id){
                img = cimg.getCar_img();
            }
        }
        
        displayByteImage(jLabel_CarImage.getWidth(), jLabel_CarImage.getHeight(), img , jLabel_CarImage);
    }//GEN-LAST:event_jTable_CarImagesMouseClicked

    private void jButton_Select_Image_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Select_Image_ActionPerformed
        // TODO add your handling code here:
        // Browse and display image
         String imagePath = selectImage();
         displayImage(jLabel_CarImage.getWidth(), jLabel_CarImage.getHeight(), imagePath, jLabel_CarImage);
         // Display image path
          jLabel_imagePath.setText(imagePath);
    }//GEN-LAST:event_jButton_Select_Image_ActionPerformed

    private void jButton_Add_Image_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Add_Image_ActionPerformed
        // Add image
         try 
         {
             // get car id from jtable
            int index = jTable_Cars.getSelectedRow();
            int car_id = Integer.valueOf(jTable_Cars.getValueAt(index, 0).toString());
            byte[] image = Files.readAllBytes(Paths.get(jLabel_imagePath.getText()));
            
            System.out.println(car_id);
            car.addCarImage(car_id,image );
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null , "Select an image [" + ex.getMessage() + "]", "Car Image", 2);
           // Logger.get Logger(Form_CarImages.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton_Add_Image_ActionPerformed

    private void jButton_Remove_Image_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Remove_Image_ActionPerformed
        //  image
        
    }//GEN-LAST:event_jButton_Remove_Image_ActionPerformed

    private void jButton_Image_Slider_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Image_Slider_ActionPerformed
        // show images slider
        // get the selected car image
        
        int index = jTable_Cars.getSelectedRow();
        int id = Integer.valueOf(jTable_Cars.getValueAt(index,0).toString()); //  car id
        Form_CarImages_Slider slider = new Form_CarImages_Slider(id);
        slider.setVisible(true);
    }//GEN-LAST:event_jButton_Image_Slider_ActionPerformed

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
            java.util.logging.Logger.getLogger(Form_CarImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_CarImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_CarImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_CarImages.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_CarImages().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Add_Image_;
    private javax.swing.JButton jButton_Image_Slider_;
    private javax.swing.JButton jButton_Remove_Image_;
    private javax.swing.JButton jButton_Select_Image_;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_CarImage;
    private javax.swing.JLabel jLabel_brands_logo;
    private javax.swing.JLabel jLabel_close1;
    private javax.swing.JLabel jLabel_imagePath;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_CarImages;
    private javax.swing.JTable jTable_Cars;
    // End of variables declaration//GEN-END:variables
}
