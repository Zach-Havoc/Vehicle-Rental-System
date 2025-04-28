/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ANGEL SHANE
 */
import Classes.BookingHistory;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TopCustomersPanel extends JPanel {

    public TopCustomersPanel(ArrayList<BookingHistory.CustomerBookingCount> topCustomers) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel title = new JLabel("Top Customers");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        for (BookingHistory.CustomerBookingCount customer : topCustomers) {
            JPanel customerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            customerPanel.setBackground(Color.WHITE);

            JLabel nameLabel = new JLabel(customer.customerName);
            nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
            nameLabel.setPreferredSize(new Dimension(150, 30));

            JLabel badge = new JLabel(getBadge(customer.totalBookings));
            badge.setFont(new Font("SansSerif", Font.BOLD, 12));
            badge.setOpaque(true);
            badge.setForeground(Color.WHITE);
            badge.setBackground(getBadgeColor(customer.totalBookings));
            badge.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JLabel count = new JLabel("+" + customer.totalBookings + " cars");
            count.setFont(new Font("SansSerif", Font.BOLD, 16));

            customerPanel.add(nameLabel);
            customerPanel.add(badge);
            customerPanel.add(count);

            listPanel.add(customerPanel);
        }

        add(new JScrollPane(listPanel), BorderLayout.CENTER);

        JButton viewAll = new JButton("View all customers");
        viewAll.setBackground(new Color(0, 153, 204));
        viewAll.setForeground(Color.WHITE);
        viewAll.setFocusPainted(false);
        viewAll.setFont(new Font("SansSerif", Font.BOLD, 16));
        viewAll.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        viewAll.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(viewAll);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private String getBadge(int bookings) {
        if (bookings >= 10) {
            return "VIP";
        } else if (bookings >= 5) {
            return "Frequent";
        } else {
            return "Regular";
        }
    }

    private Color getBadgeColor(int bookings) {
        if (bookings >= 10) {
            return new Color(255, 193, 7); // Yellow
        } else if (bookings >= 5) {
            return new Color(33, 150, 243); // Blue
        } else {
            return new Color(76, 175, 80); // Green
        }
    }
}
