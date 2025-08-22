package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.User;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPersonal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public void run(User usuario) {
        try {
            MenuPersonal frame = new MenuPersonal(usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MenuPersonal(User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 650);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(244, 244, 244));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(null);
        setContentPane(contentPane);

    
        JButton btnCerrarX = new JButton("X");
        btnCerrarX.setBounds(860, 10, 30, 30);
        btnCerrarX.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnCerrarX.setForeground(Color.WHITE);
        btnCerrarX.setBackground(new Color(220, 53, 69));
        btnCerrarX.setFocusPainted(false);
        btnCerrarX.setBorderPainted(false);
        btnCerrarX.setContentAreaFilled(true);
        btnCerrarX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCerrarX.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "¿Cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
        btnCerrarX.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnCerrarX.setBackground(new Color(200, 40, 50));
            }
            public void mouseExited(MouseEvent e) {
                btnCerrarX.setBackground(new Color(220, 53, 69));
            }
        });
        contentPane.add(btnCerrarX);

        JLabel lblTitulo = new JLabel("Menú del Personal");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setForeground(new Color(51, 51, 51));
        lblTitulo.setBounds(20, 10, 400, 40);
        contentPane.add(lblTitulo);

        JLabel lblBienvenida = new JLabel("Bienvenido/a, " + usuario.getNombre());
        lblBienvenida.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblBienvenida.setForeground(new Color(80, 80, 80));
        lblBienvenida.setBounds(20, 60, 400, 30);
        contentPane.add(lblBienvenida);

        JLabel lblEmail = new JLabel("Email: " + usuario.getMail());
        lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblEmail.setForeground(new Color(100, 100, 100));
        lblEmail.setBounds(20, 95, 400, 20);
        contentPane.add(lblEmail);

        JLabel lblImagen = new JLabel();
        lblImagen.setBounds(600, 40, 250, 250);
        if (usuario.getImg() != null && usuario.getImg().length > 0) {
            ImageIcon icon = new ImageIcon(usuario.getImg());
            Image imgEscalada = icon.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(imgEscalada));
        } else {
            lblImagen.setIcon(new ImageIcon("src/img/default_user.png"));
        }
        contentPane.add(lblImagen);

      
        JButton btnVehiculos = new JButton("Lista Vehículos");
        btnVehiculos.setBounds(20, 140, 250, 50);
        btnVehiculos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnVehiculos.setBackground(new Color(0, 123, 255));
        btnVehiculos.setForeground(Color.WHITE);
        btnVehiculos.setFocusPainted(false);
        btnVehiculos.setBorderPainted(false);
        btnVehiculos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVehiculos.addActionListener(e -> new vistaVehiculo(usuario).setVisible(true));
        contentPane.add(btnVehiculos);

        JButton btnClientes = new JButton("Lista Clientes");
        btnClientes.setBounds(20, 200, 250, 50);
        btnClientes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnClientes.setBackground(new Color(0, 123, 255));
        btnClientes.setForeground(Color.WHITE);
        btnClientes.setFocusPainted(false);
        btnClientes.setBorderPainted(false);
        btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnClientes.addActionListener(e -> new vistaCliente(usuario).run(usuario));
        contentPane.add(btnClientes);

        JButton btnPersonal = new JButton("Lista Personal");
        btnPersonal.setBounds(20, 260, 250, 50);
        btnPersonal.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnPersonal.setBackground(new Color(0, 123, 255));
        btnPersonal.setForeground(Color.WHITE);
        btnPersonal.setFocusPainted(false);
        btnPersonal.setBorderPainted(false);
        btnPersonal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPersonal.addActionListener(e -> new vistaPersonal(usuario).run(usuario));
        contentPane.add(btnPersonal);

        JButton btnMovimientos = new JButton("Movimientos");
        btnMovimientos.setBounds(20, 320, 250, 50);
        btnMovimientos.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnMovimientos.setBackground(new Color(0, 123, 255));
        btnMovimientos.setForeground(Color.WHITE);
        btnMovimientos.setFocusPainted(false);
        btnMovimientos.setBorderPainted(false);
        btnMovimientos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMovimientos.addActionListener(e -> new vistaMovimiento(usuario).run(usuario));
        contentPane.add(btnMovimientos);

        JButton btnConsultas = new JButton("Consultas");
        btnConsultas.setBounds(20, 380, 250, 50);
        btnConsultas.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnConsultas.setBackground(new Color(0, 123, 255));
        btnConsultas.setForeground(Color.WHITE);
        btnConsultas.setFocusPainted(false);
        btnConsultas.setBorderPainted(false);
        btnConsultas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnConsultas.addActionListener(e -> new vistaConsultaPersonal(usuario).run(usuario));
        contentPane.add(btnConsultas);

        JButton btnMiCuenta = new JButton("Mi Cuenta");
        btnMiCuenta.setBounds(20, 440, 250, 50);
        btnMiCuenta.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnMiCuenta.setBackground(new Color(0, 123, 255));
        btnMiCuenta.setForeground(Color.WHITE);
        btnMiCuenta.setFocusPainted(false);
        btnMiCuenta.setBorderPainted(false);
        btnMiCuenta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMiCuenta.addActionListener(e -> new verCuenta(usuario).run(usuario));
        contentPane.add(btnMiCuenta);
    }
}



