import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class createAccount extends JFrame {
    private Color primaryColor = new Color(0, 67, 123);
    private Color secondaryColor = new Color(25, 25, 25);
    private Color backgroundColor = new Color(240, 245, 255);
    private Color buttonColor = new Color(255, 223, 0); // Yellow
    private Color buttonHoverColor = new Color(255, 200, 0);
    private JTextField[] textFields;
    private JComboBox<String> gradeCombo, strandCombo;
    private JPasswordField passwordField;

    public createAccount() {
        setTitle("Student Sign Up");
        setLayout(new BorderLayout(0, 10));
        getContentPane().setBackground(backgroundColor);

        // Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(backgroundColor);

        // Logo
        try {
            String logoPath = "c:/Users/Administrator/Desktop/shs_enrollment/src/resources/shslogo.png";
            ImageIcon logoIcon = new ImageIcon(logoPath);
            
            if (logoIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image scaledImage = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
                logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                topPanel.add(logoLabel);
                topPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        } catch (Exception e) {
            System.err.println("Error loading logo: " + e.getMessage());
        }

        // NextGen Label
        JLabel nextGenLabel = new JLabel("NextGen");
        nextGenLabel.setFont(new Font("Arial", Font.BOLD, 32));
        nextGenLabel.setForeground(primaryColor);
        nextGenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(nextGenLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Sign Up Label
        JLabel signUpLabel = new JLabel("STUDENT SIGN UP");
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 24));
        signUpLabel.setForeground(secondaryColor);
        signUpLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(signUpLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Credentials Label
        JLabel credentialsLabel = new JLabel("PLEASE ENTER YOUR CREDENTIALS");
        credentialsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        credentialsLabel.setForeground(secondaryColor);
        credentialsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(credentialsLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        add(topPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 10, 8, 10);

        // Create form fields with placeholders
        String[] placeholders = {
            "Enter Student ID", "Enter First Name", "Enter Last Name", 
            "Enter Contact Number", "Enter Email Address", "Enter Password"
        };

        textFields = new JTextField[placeholders.length - 1]; // Exclude password field

        for (int i = 0; i < placeholders.length; i++) {
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.gridwidth = 2; // Make fields span both columns

            if (i == placeholders.length - 1) { // Password field
                passwordField = new JPasswordField(20);
                setupPasswordField(passwordField, placeholders[i]);
                formPanel.add(passwordField, gbc);
            } else {
                JTextField field = new JTextField(20);
                setupTextField(field, placeholders[i]);
                textFields[i] = field;
                formPanel.add(field, gbc);
            }
        }

        // Grade Level Combo Box
        gbc.gridy++;
        gradeCombo = new JComboBox<>(new String[]{"Select Grade Level", "Grade 11", "Grade 12"});
        styleComboBox(gradeCombo);
        formPanel.add(gradeCombo, gbc);

        // Strand Combo Box
        gbc.gridy++;
        String[] strands = {
            "Select Strand", "STEM", "HUMSS", "ABM", "Arts and Design", 
            "Sports Track", "TVL - Automotive", "TVL - ICT", "TVL - Cookery"
        };
        strandCombo = new JComboBox<>(strands);
        styleComboBox(strandCombo);
        formPanel.add(strandCombo, gbc);

        // Submit Button
        gbc.gridy++;
        gbc.insets = new Insets(25, 10, 10, 10);
        JButton submitButton = new JButton("Submit");
        styleButton(submitButton);
        submitButton.addActionListener(e -> handleSubmit());
        formPanel.add(submitButton, gbc);

        // Add form panel to scroll pane
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Window settings
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 750);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void setupTextField(JTextField field, String placeholder) {
        field.setPreferredSize(new Dimension(320, 35));
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void setupPasswordField(JPasswordField field, String placeholder) {
        field.setPreferredSize(new Dimension(320, 35));
        field.setEchoChar((char)0);
        field.setText(placeholder);
        field.setForeground(Color.GRAY);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setEchoChar('●');
                    field.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setEchoChar((char)0);
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setPreferredSize(new Dimension(320, 35));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        comboBox.setBackground(Color.WHITE);
        comboBox.setForeground(Color.GRAY);
        ((JLabel)comboBox.getRenderer()).setHorizontalAlignment(SwingConstants.LEFT);
    }

    private void styleButton(JButton button) {
        button.setPreferredSize(new Dimension(320, 45));
        button.setBackground(buttonColor);
        button.setForeground(Color.BLACK); // Changed to black
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        
        // Create a custom border with legend effect
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(buttonColor.darker(), 2),
            BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(buttonColor.darker());
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(buttonColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void handleSubmit() {
        // Validate fields
        for (JTextField field : textFields) {
            if (field.getForeground() == Color.GRAY || field.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please fill in all fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        if (gradeCombo.getSelectedIndex() == 0 || strandCombo.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this,
                "Please select both Grade Level and Strand",
                "Validation Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add your registration logic here
        JOptionPane.showMessageDialog(this,
            "Account created successfully!",
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
        dispose(); // Close the window after successful registration
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new createAccount().setVisible(true));
    }

    
}