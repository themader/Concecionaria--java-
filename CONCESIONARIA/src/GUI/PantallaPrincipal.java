package GUI;

import BLL.Concesionaria;
import BLL.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField emailField;
    private JPasswordField passwordField;
    private Concesionaria condoleoAutos = new Concesionaria();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                PantallaPrincipal frame = new PantallaPrincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public PantallaPrincipal() {
        setTitle("Condoleo Autos - Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        setLocationRelativeTo(null); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon("src/img/logo.png")); 
        logoLabel.setBounds(30, 30, 400, 400);
        contentPane.add(logoLabel);

     
        JLabel titleLabel = new JLabel("Inicio de Sesión");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setBounds(500, 30, 400, 40);
        contentPane.add(titleLabel);

 
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        emailLabel.setBounds(500, 100, 250, 25);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        emailField.setBounds(500, 130, 300, 35);
        contentPane.add(emailField);

  
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        passwordLabel.setBounds(500, 180, 250, 25);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        passwordField.setBounds(500, 210, 300, 35);
        contentPane.add(passwordField);

   
        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(500, 270, 300, 40);
        loginButton.addActionListener((ActionEvent e) -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            User usuario = User.verificarCuenta(condoleoAutos, email, password);
            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "¡Bienvenido, " + usuario.getNombre() + "!");
        
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        contentPane.add(loginButton);


        JButton registerButton = new JButton("Registrarse");
        registerButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        registerButton.setBackground(new Color(60, 179, 113));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBounds(500, 320, 145, 40);
        registerButton.addActionListener(e -> {
            registrarCuenta registro = new registrarCuenta();
            registro.setVisible(true);
        });
        contentPane.add(registerButton);

 
        JButton closeButton = new JButton("Cerrar");
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setBounds(655, 320, 145, 40);
        closeButton.addActionListener(e -> System.exit(0));
        contentPane.add(closeButton);
    }
}
