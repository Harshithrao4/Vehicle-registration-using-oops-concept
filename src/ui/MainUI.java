package ui;
import models.*;
import services.RentalService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainUI {
    private RentalService rentalService;

    public MainUI() {
        rentalService = new RentalService();
        rentalService.addVehicle(new Car("Toyota", "Innova", 1500, 7));
        rentalService.addVehicle(new Car("Honda", "City", 1200, 5));
        rentalService.addVehicle(new Bike("Yamaha", "FZ", 500, 150));
        rentalService.addVehicle(new Bike("Royal Enfield", "Classic 350", 800, 350));

        JFrame frame = new JFrame("XYZ Vehicle Rentals");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel companyLabel = new JLabel("Welcome to HARSHITH Vehicle Rentals", SwingConstants.CENTER);
        companyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(companyLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        frame.add(panel, BorderLayout.CENTER);

        JLabel choiceLabel = new JLabel("Would you like to rent a Car or a Bike?");
        panel.add(choiceLabel);

        String[] vehicleTypes = {"Car", "Bike"};
        JComboBox<String> vehicleTypeBox = new JComboBox<>(vehicleTypes);
        panel.add(vehicleTypeBox);

        JLabel modelLabel = new JLabel("Select a Model:");
        panel.add(modelLabel);

        JComboBox<String> modelBox = new JComboBox<>();
        panel.add(modelBox);

        JLabel hoursLabel = new JLabel("Enter number of days:");
        JTextField hoursField = new JTextField();
        panel.add(hoursLabel);
        panel.add(hoursField);

        JButton rentButton = new JButton("Rent Now");
        JButton resetButton = new JButton("Reset");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rentButton);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        vehicleTypeBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modelBox.removeAllItems();
                String selectedType = (String) vehicleTypeBox.getSelectedItem();
                List<Vehicle> availableVehicles = rentalService.getAvailableVehicles();
                for (Vehicle v : availableVehicles) {
                    if (v.getType().equalsIgnoreCase(selectedType)) {
                        modelBox.addItem(v.toString());
                    }
                }
            }
        });

        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedVehicle = (String) modelBox.getSelectedItem();
                String hoursText = hoursField.getText();

                if (selectedVehicle == null || hoursText.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select a model and enter hours.");
                    return;
                }

                try {
                    int hours = Integer.parseInt(hoursText);
                    double price = 0;
                    for (Vehicle v : rentalService.getAvailableVehicles()) {
                        if (v.toString().equals(selectedVehicle)) {
                            price = v.rentalPrice * hours;
                            v.rentVehicle();
                            break;
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Total Price: ₹" + price + "\nThank you for renting with XYZ Vehicle Rentals!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter a valid number for hours.");
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vehicleTypeBox.setSelectedIndex(0);
                modelBox.removeAllItems();
                hoursField.setText("");
            }
        });

        frame.setVisible(true);
    }
} 
