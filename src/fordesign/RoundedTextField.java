/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fordesign;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedTextField extends JTextField {
    private int cornerRadius = 20; // You can change this for more/less rounding

    public RoundedTextField(int columns, int radius) {
        super(columns);
        this.cornerRadius = radius;
        setOpaque(false); // So we can paint a rounded background
        setBorder(new EmptyBorder(8, 12, 8, 12)); // Padding inside
    }
//8, 12, 8, 12
    public RoundedTextField(int columns) {
        this(columns, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        // Optional: Border
        g2.setColor(getForeground().darker());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
        g2.dispose();
        super.paintComponent(g);
    }
}

