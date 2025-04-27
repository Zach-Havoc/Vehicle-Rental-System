/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fordesign;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedPasswordField extends JPasswordField {
    private int cornerRadius = 20; // Default corner radius

    public RoundedPasswordField(int columns, int radius) {
        super(columns);
        this.cornerRadius = radius;
        setOpaque(false); // Needed for custom painting
        setBorder(new EmptyBorder(8, 12, 8, 12)); // Padding
    }

    public RoundedPasswordField(int columns) {
        this(columns, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
        // Border
        g2.setColor(getForeground().darker());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);
        g2.dispose();
        super.paintComponent(g);
    }
}

