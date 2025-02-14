import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginn extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private Color primaryColor = new Color(0, 67, 123);
    private Color secondaryColor = new Color(25, 25, 25);
    private Color backgroundColor = new Color(240, 245, 255);
    private Color buttonColor = new Color(0, 102, 204);
    private Color buttonHoverColor = new Color(0, 82, 164);

    public loginn() {
        setTitle("SHS Enrollment System");
        setLayout(new BorderLayout(0, 10));
        getContentPane().setBackground(backgroundColor);

        // Top Panel for Logo and Texts
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(backgroundColor);

        // Load and add logo with proper error handling
        try {
            String logoPath = "c:/Users/Administrator/Desktop/shs_enrollment/src/resources/shslogo.png";
            ImageIcon logoIcon = new ImageIcon(logoPath);
            
            if (logoIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image scaledImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
                logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                topPanel.add(logoLabel);
                topPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            } else {
                throw new Exception("Failed to load logo image");
            }
        } catch (Exception e) {
            System.err.println("Error loading logo: " + e.getMessage());
            JLabel placeholder = new JLabel("SHS LOGO");
            placeholder.setFont(new Font("Arial", Font.BOLD, 28));
            placeholder.setAlignmentX(Component.CENTER_ALIGNMENT);
            topPanel.add(placeholder);
        }

        // Add NextGen text
        JLabel nextGenLabel = new JLabel("NextGen");
        nextGenLabel.setFont(new Font("Arial", Font.BOLD, 32));
        nextGenLabel.setForeground(primaryColor);
        nextGenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(nextGenLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Add Welcome text
        JLabel welcomeLabel = new JLabel("WELCOME BACK STUDENT");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 22));
        welcomeLabel.setForeground(secondaryColor);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(welcomeLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        add(topPanel, BorderLayout.NORTH);

        // Create main panel for login components
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Email field
        emailField = new JTextField(20);
        emailField.setPreferredSize(new Dimension(320, 45));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        emailField.setText("Enter your email");
        emailField.setForeground(Color.GRAY);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        setupPlaceholder(emailField, "Enter your email");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(emailField, gbc);

        // Password field
        passwordField = new JPasswordField(20);
        passwordField.setPreferredSize(new Dimension(320, 45));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(primaryColor, 2),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        passwordField.setEchoChar((char)0);
        passwordField.setText("Enter your password");
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        setupPasswordPlaceholder(passwordField);
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 10, 25, 10);
        loginPanel.add(passwordField, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        buttonPanel.setBackground(backgroundColor);

        // Login button
        JButton loginButton = new JButton("Login");
        styleButton(loginButton, 160, 50);
        loginButton.addActionListener(e -> handleLogin());

        // Create Account button
        JButton createAccountButton = new JButton("Create Account");
        styleButton(createAccountButton, 160, 50);
        createAccountButton.addActionListener(e -> handleCreateAccount());

        buttonPanel.add(loginButton);
        buttonPanel.add(createAccountButton);

        gbc.gridy = 2;
        loginPanel.add(buttonPanel, gbc);

        add(loginPanel, BorderLayout.CENTER);

        // Window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void styleButton(JButton button, int width, int height) {
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(buttonColor);
        button.setForeground(Color.lightGray);  // Changed to white for better visibility
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 18));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createRaisedBevelBorder(),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(buttonHoverColor);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(buttonColor);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    private void setupPlaceholder(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void setupPasswordPlaceholder(JPasswordField field) {
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(field.getPassword()).equals("Enter your password")) {
                    field.setText("");
                    field.setEchoChar('●');
                    field.setForeground(Color.BLACK);
                }
            }
            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setText("Enter your password");
                    field.setEchoChar((char)0);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        
        if (email.equals("Enter your email") || password.equals("Enter your password")) {
            JOptionPane.showMessageDialog(this,
                "Please enter both email and password",
                "Login Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Add your login authentication logic here
        JOptionPane.showMessageDialog(this,
            "Login attempt with:\nEmail: " + email,
            "Login",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleCreateAccount() {
        SwingUtilities.invokeLater(() -> {
            createAccount signUpForm = new createAccount();
            signUpForm.setVisible(true);
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new loginn().setVisible(true));
    }
}