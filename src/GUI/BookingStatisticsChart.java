/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Classes.Booking;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BookingStatisticsChart extends JFrame {
    public BookingStatisticsChart() {
        setTitle("Car Booking Statistics");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Chart panels
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new ChartPanel(createDailyBookingsChart()));
        panel.add(new ChartPanel(createMonthlySalesChart()));

        setContentPane(panel);
    }

    private JFreeChart createDailyBookingsChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> dailyBookings = new TreeMap<>();

        for (Booking booking : new Booking().bookingList()) {
            String date = booking.getStart_date(); // or use booking.getEnd_date()
            dailyBookings.put(date, dailyBookings.getOrDefault(date, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : dailyBookings.entrySet()) {
            dataset.addValue(entry.getValue(), "Bookings", entry.getKey());
        }

        return ChartFactory.createBarChart(
                "Number of Cars Booked Per Day",
                "Date",
                "Number of Bookings",
                dataset
        );
    }

    private JFreeChart createMonthlySalesChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> monthlySales = new TreeMap<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");

        for (Booking booking : new Booking().bookingList()) {
            try {
                Date startDate = sdf.parse(booking.getStart_date());
                String month = monthFormat.format(startDate);
                int total = booking.getTotal_price();
                monthlySales.put(month, monthlySales.getOrDefault(month, 0) + total);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<String, Integer> entry : monthlySales.entrySet()) {
            dataset.addValue(entry.getValue(), "Sales", entry.getKey());
        }

        return ChartFactory.createBarChart(
                "Total Monthly Sales",
                "Month",
                "Total Sales (₱)",
                dataset
        );
    }

    // To test the charts
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BookingStatisticsChart chart = new BookingStatisticsChart();
            chart.setVisible(true);
        });
    }
}

