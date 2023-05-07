import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JTextField inputField;
    private JButton convertButton;
    private JLabel resultLabel;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel(new GridLayout(4, 2));
        inputField = new JTextField("");
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        resultLabel = new JLabel("");
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        fromComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        toComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        mainPanel.add(new JLabel("Temperature"));
        mainPanel.add(inputField);
        mainPanel.add(new JLabel("From"));
        mainPanel.add(fromComboBox);
        mainPanel.add(new JLabel("To"));
        mainPanel.add(toComboBox);
        mainPanel.add(convertButton);
        mainPanel.add(resultLabel);
        add(mainPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double temp = Double.parseDouble(inputField.getText());
            double result;

            String fromScale = (String) fromComboBox.getSelectedItem();
            String toScale = (String) toComboBox.getSelectedItem();

            if (fromScale.equals("Celsius")) {
                if (toScale.equals("Fahrenheit")) {
                    result = (temp * 9 / 5) + 32;
                } else {
                    result = temp + 273.15;
                }
            } else if (fromScale.equals("Fahrenheit")) {
                if (toScale.equals("Celsius")) {
                    result = (temp - 32) * 5 / 9;
                } else {
                    result = (temp + 459.67) * 5 / 9;
                }
            } else {
                if (toScale.equals("Celsius")) {
                    result = temp - 273.15;
                } else {
                    result = temp * 9 / 5 - 459.67;
                }
            }

            resultLabel.setText(String.format("%.2f", result) + " " + toScale);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}