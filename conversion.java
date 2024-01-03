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

    private static double b_to_f(double b) {
        double sqrt_5_13 = Math.sqrt(5.0 / 13.0);
        if (b < 65) {
            return 50.0 - 10.0 * sqrt_5_13 * Math.sqrt(65.0 - b);
        }
        else {
            return 50.0 + 10.0 * sqrt_5_13 * Math.sqrt(b - 65.0);
        }
    }

    private static double f_to_b(double f) {
        return (7.0 / 500) * Math.abs(f - 50) * (f - 50) + 65;
    }

    private static double perch_to_sqm(double perch) {
        return 25.29285264 * perch;
    }

    private static double sq_mile_to_sqkm(double sq_mile) {
        return 2.58999 * sq_mile;
    }

    private static double sq_ft_to_sqm(double sq_ft) {
        return 0.09290304 * sq_ft;
    }

    private static double sq_i_to_sq_cm(double sq_i) {
        return 6.4516 * sq_i;
    }

    private static double imp_q_to_l(double imp_q) {
        return 1.1365225 * imp_q;
    }

    private static double us_lq_to_l(double us_lq) {
        return 0.946352946 * us_lq;
    }

    private static double us_dq_to_l(double us_dq) {
        return 1.101220942715 * us_dq;
    }

    private static double imp_c_to_l(double imp_c) {
        return 0.28413064262468  * imp_c;
    }

    private static double us_c_to_l(double us_c) {
        return 0.2365882365  * us_c;
    }

    private static double us_teas_to_ml(double us_teas) {
        return 4.9289215938 * us_teas;
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
            case "area":
                units = new String[]{"square inch", "square foot", "perch", "rood", "acre", 
                        "square mile"};
                break;
            case "volume":
                units = new String[]{"US teaspoon", "Imperial teaspoon", "US tablespoon", 
                        "Imperial tablespoon", "Imperial fluid ounce", "US fluid ounce", 
                        "US liquid pint", "US cup", "Imperial cup", "US dry pint", "Imperial pint", 
                        "US liquid quart", "US dry quart", "Imperial quart", "US liquid gallon",
                        "US dry gallon", "Imperial gallon"};
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
                            double of = f_to_b(u_value);
                            oc = (of - 32) * 5 / 9;
                        }
                        else oc = 0.0;

                        resultLabel.setText("Result: " + oc + " degrees Celsius");
                    }

                    else if ("area".equals(topValue[0])) {
                        double sqm;
                        if ("perch".equals(selectedValue)) {
                            sqm = perch_to_sqm(u_value);
                            resultLabel.setText("Result: " + sqm + " m^2");
                        }

                        else if ("rood".equals(selectedValue)) {
                            sqm = perch_to_sqm(u_value / 40);
                            resultLabel.setText("Result: " + sqm + " m^2");
                        }

                        else if ("acre".equals(selectedValue)) {
                            sqm = perch_to_sqm(u_value / 160);
                            resultLabel.setText("Result: " + sqm + " m^2");
                        }

                        else if ("square inch".equals(selectedValue)) {
                            double sqcm = sq_i_to_sq_cm(u_value);
                            resultLabel.setText("Result: " + sqcm + " cm^2");
                        }

                        else if ("square foot".equals(selectedValue)) {
                            sqm = sq_ft_to_sqm(u_value);
                            resultLabel.setText("Result: " + sqm + " m^2");
                        }

                        else if ("square mile".equals(selectedValue)) {
                            double sqkm = sq_mile_to_sqkm(u_value);
                            resultLabel.setText("Result: " + sqkm + " km^2");
                        }
                    }

                    else if ("volume".equals(topValue[0])) {
                        double l;
                        if ("Imperial fluid ounce".equals(selectedValue)) {
                            l = imp_q_to_l(u_value / 40);
                            double mL = l * 1000;
                            resultLabel.setText("Result: " + mL + " mL");
                        }

                        else if ("Imperial pint".equals(selectedValue)) {
                            l = imp_q_to_l(u_value / 2);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("Imperial quart".equals(selectedValue)) {
                            l = imp_q_to_l(u_value);
                            resultLabel.setText("Result: " + l + " L");                           
                        }

                        else if ("Imperial gallon".equals(selectedValue)) {
                            l = imp_q_to_l(u_value * 4);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("US fluid ounce".equals(selectedValue)) {
                            l = us_lq_to_l(u_value / 32);
                            double mL = l * 1000;
                            resultLabel.setText("Result: " + mL + " mL"); 
                        }

                        else if ("US lquid pint".equals(selectedValue)) {
                            l = us_lq_to_l(u_value / 2);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("US dry pint".equals(selectedValue)) {
                            l = us_dq_to_l(u_value / 2);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("US liquid quart".equals(selectedValue)) {
                            l = us_lq_to_l(u_value);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("US dry quart".equals(selectedValue)) {
                            l = us_dq_to_l(u_value);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("US liquid gallon".equals(selectedValue)) {
                            l = us_lq_to_l(u_value * 4);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("US dry gallon".equals(selectedValue)) {
                            l = us_dq_to_l(u_value * 4);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("Imperial teaspoon".equals(selectedValue)) {
                            l = imp_c_to_l(u_value / 48);
                            double mL = l * 1000;
                            resultLabel.setText("Result: " + mL + " mL");
                        }

                        else if ("Imperial tablespoon".equals(selectedValue)) {
                            l = imp_c_to_l(u_value / 16);
                            double mL = l * 1000;
                            resultLabel.setText("Result: " + mL + " mL");
                        }

                        else if ("Imperial cup".equals(selectedValue)) {
                            l = imp_c_to_l(u_value);
                            resultLabel.setText("Result: " + l + " L");
                        }

                        else if ("US teaspoon".equals(selectedValue)) {
                            double mL = us_teas_to_ml(u_value);
                            resultLabel.setText("Result: " + mL + " mL");
                        }

                        else if ("US tablespoon".equals(selectedValue)) {
                            double mL = us_teas_to_ml(u_value * 3);
                            resultLabel.setText("Result: " + mL + " mL");
                        }

                        else if ("US cup".equals(selectedValue)) {
                            l = us_c_to_l(u_value);
                            resultLabel.setText("Result: " + l + " L");
                        }
                    }
                    
                } catch (NumberFormatException ex) {
                    // Handle the case where the input is not a valid double
                    resultLabel.setText("Invalid input. Please enter a valid number.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.add(topbox); //topbox is the thing that lists the unit types like temp, length
        panel.add(comboBox); //comboBox is the thing that lists the measurement units
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

