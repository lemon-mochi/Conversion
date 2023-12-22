import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math; 

public class conversion {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static double lb_to_kg(double lbs) {
        return lbs * 0.45359237;
    }

    private static double ft_to_m(double ft) {
        return ft * 0.3048;
    }

    private static double fathom_to_m(double fathom) {
        return fathom * 1.852;
    }

    private static double f_to_b(double b) {
        double sqrt_5_13 = Math.sqrt(5.0 / 13.0);
        if (b < 65) {
            return 50.0 - 10.0 * sqrt_5_13 * Math.sqrt(65.0 - b);
        }
        else {
            return 50.0 + 10.0 * sqrt_5_13 * Math.sqrt(b - 65.0);
        }
    }

    private static double b_to_f(double f) {
        return (7.0 / 500) * Math.abs(f - 50) * (f - 50) + 65;
    }

    private static void updateComboBox(JComboBox<String> topbox, JComboBox<String> comboBox) {
        String selectedValue = (String) topbox.getSelectedItem();
        String[] units;

        switch (selectedValue) {
            case "weight":
                units = new String[]{"grain", "drachm", "ounce", "pound", "stone", "quarter",
                        "Imperial hundredweight", "US hundredweight", "Imperial ton", "US ton"};
                break;
            case "length":
                units = new String[]{"twip", "thou", "barleycorn", "inch", "hand", "foot", "yard",
                        "chain", "furlong", "mile", "league", "fathom", "cable", "nautical mile",
                        "link", "rod"};
                break;
            case "temperature":
                units = new String[]{"Kelvin", "Fahrenheit", "Blaine"};
                break;
            default:
                units = new String[]{};
        }

        // Update the items in comboBox
        comboBox.setModel(new DefaultComboBoxModel<>(units));
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Convert");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField textField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        String[] items = {"weight", "length", "area", "volume", "temperature"};
        JComboBox<String> topbox = new JComboBox<>(items);
        JComboBox<String> comboBox = new JComboBox<>();

        final String[] topValue = { (String) topbox.getSelectedItem() };;

        topbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topValue[0] = (String) topbox.getSelectedItem();
                updateComboBox(topbox, comboBox);
            }
        });

        JLabel resultLabel = new JLabel("Result: ");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedValue = (String) comboBox.getSelectedItem();
                    
                    // Get the text from the JTextField
                    String userInput = textField.getText();
                    //convert user input to double
                    double u_value = Double.parseDouble(userInput);

                    if ("weight".equals(topValue[0])) {
                        double kilograms;

                        if ("grain".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value / 700);
                            double mg = kilograms * 100000;
                            resultLabel.setText("Result: " + mg + "mg");
                        }

                        else if ("drachm".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value / 256);
                            double g = kilograms * 1000;
                            resultLabel.setText("Result: " + g + "g");
                        }
                        
                        else if ("ounce".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value / 16);
                            double g = kilograms * 1000;
                            resultLabel.setText("Result: " + g + "g");                            
                        }
                            
                        else if ("pound".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value);
                            resultLabel.setText("Result: " + kilograms + "kg");
                        }

                        else if ("stone".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value * 14);
                            resultLabel.setText("Result: " + kilograms + "kg");
                        }
                        
                        else if ("quarter".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value * 28);
                            resultLabel.setText("Result: " + kilograms + "kg");
                        }
                            
                        else if ("Imperial hundredweight".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value * 112);
                            resultLabel.setText("Result: " + kilograms + "kg");
                        }
                        
                        else if ("US hundredweight".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value * 100);
                            resultLabel.setText("Result: " + kilograms + "kg");
                        }
                        
                        else if ("Imperial ton".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value * 2240);
                            double tonne = kilograms / 1000;
                            resultLabel.setText("Result: " + tonne + "tonnes");
                        }   

                        else if ("US ton".equals(selectedValue)) {
                            kilograms = lb_to_kg(u_value * 2000);
                            double tonne = kilograms / 1000;
                            resultLabel.setText("Result: " + tonne + "tonnes");
                        }
                        
                        else
                            kilograms = 0.0;
                    }

                    else if ("length".equals(topValue[0])) {
                        double metres;

                        if ("twip".equals(selectedValue)) {
                            metres = ft_to_m(u_value / 17280);
                            double micro = metres * 1000000;
                            resultLabel.setText("Result: " + micro + "micrometres");
                        }
                            
                        else if ("thou".equals(selectedValue)) {
                            metres = ft_to_m(u_value / 12000);
                            double micro = metres * 1000000;
                            resultLabel.setText("Result: " + micro + "micrometres");
                        }
                            
                        else if ("barleycorn".equals(selectedValue)) {
                            metres = ft_to_m(u_value / 36);
                            double mm = metres * 1000;
                            resultLabel.setText("Result: " + mm + "mm");
                        }
                        
                        else if ("inch".equals(selectedValue)) {
                            metres = ft_to_m(u_value / 12);
                            double cm = metres * 100;
                            resultLabel.setText("Result: " + cm + "cm");
                        }

                        else if ("hand".equals(selectedValue)) {
                            metres = ft_to_m(u_value / 3);
                            double cm = metres * 100;
                            resultLabel.setText("Result: " + cm + "cm");
                        }
                        
                        else if ("foot".equals(selectedValue)) {
                            metres = ft_to_m(u_value);
                            resultLabel.setText("Result: " + metres + "m");
                        }
                        
                        else if ("yard".equals(selectedValue)) {
                            metres = ft_to_m(u_value * 3);
                            resultLabel.setText("Result: " + metres + "m");
                        }
                            
                        else if ("chain".equals(selectedValue)) {
                            metres = ft_to_m(u_value * 66);
                            resultLabel.setText("Result: " + metres + "m");
                        }
                        
                        else if ("furlong".equals(selectedValue)) {
                            metres = ft_to_m(u_value * 660);
                            resultLabel.setText("Result: " + metres + "m");
                        }
                            
                        else if ("mile".equals(selectedValue)) {
                            metres = ft_to_m(u_value * 5280);
                            double km = metres / 1000;
                            resultLabel.setText("Result: " + km + "km");
                        }
                            
                        else if ("league".equals(selectedValue)) {
                            metres = ft_to_m(u_value * 15840);
                            double km = metres / 1000;
                            resultLabel.setText("Result: " + km + "km");
                        }
                            
                        else if ("fathom".equals(selectedValue)) {
                            metres = fathom_to_m(u_value);
                            double km = metres / 1000;
                            resultLabel.setText("Result: " + km + "km");                            
                        }
                        
                        else if ("cable".equals(selectedValue)) {
                            metres = fathom_to_m(u_value * 100);
                            double km = metres / 1000;
                            resultLabel.setText("Result: " + km + "km");                           
                        }
                            
                        else if ("nautical mile".equals(selectedValue)) {
                            metres = fathom_to_m(u_value * 1000);
                            double km = metres / 1000;
                            resultLabel.setText("Result: " + km + "km");                            
                        }

                        else if ("link".equals(selectedValue)) {
                            metres = ft_to_m(u_value * 66 / 100);
                            double cm = metres * 100;
                            resultLabel.setText("Result: " + cm + "cm");
                        }
                            
                        
                        else if ("rod".equals(selectedValue)) {
                            metres = ft_to_m(u_value * 33 / 2);
                            resultLabel.setText("Result: " + metres + "m");
                        }

                        else
                            metres = 0.0;
                    }

                    else if ("temperature".equals(topValue[0])) {
                        double oc;
                        if ("Kelvin".equals(selectedValue)) {
                            oc = u_value - 273.15;
                        }

                        else if ("Fahrenheit".equals(selectedValue)) {
                            oc = (u_value - 32) * 5 / 9;
                            
                        }

                        else if ("Blaine".equals(selectedValue)) {
                            double of = b_to_f(u_value);
                            oc = (of - 32) * 5 / 9;
                        }
                        else oc = 0.0;

                        resultLabel.setText("Result: " + oc + " degrees Celsius");
                    }
                    
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid double
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(topbox);
        panel.add(comboBox);
        panel.add(new JLabel("Enter Value: "));
        panel.add(textField);
        panel.add(submitButton);

        panel.add(resultLabel);

        frame.getContentPane().add(panel);

        frame.setSize(450, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

