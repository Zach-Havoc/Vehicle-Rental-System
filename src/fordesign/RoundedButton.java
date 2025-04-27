package fordesign;

import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {
    private int cornerRadius = 20; // Default value

    public RoundedButton(int radius) {
        super();
        this.cornerRadius = radius;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    public RoundedButton() {
        this(20); // Default 20px radius
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(getBackground().brighter());
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.setColor(getForeground().darker());
        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, cornerRadius, cornerRadius);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    public void paintBorder(Graphics g) {
        // Border painted in paintComponent for consistency
    }
}
