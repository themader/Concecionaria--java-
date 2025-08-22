package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

import BLL.User;
import BLL.Vehiculo;

public class verVehiculo extends JFrame {

    private JPanel contentPane;

    public void run(Vehiculo vehiculo, User usuario) {
        try {
            verVehiculo frame = new verVehiculo(vehiculo, usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public verVehiculo(Vehiculo vehiculo, User usuario) {
        setTitle("Detalles del Vehículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 380);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(Color.RED);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBounds(10, 10, 100, 35);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> dispose());
        contentPane.add(btnVolver);

        // Imagen del vehículo
        JLabel lblImagenVehiculo = new JLabel();
        lblImagenVehiculo.setBounds(10, 55, 420, 290);
        if (vehiculo.getImg() != null) {
            lblImagenVehiculo.setIcon(new ImageIcon(vehiculo.getImg()));
        } else {
            lblImagenVehiculo.setText("Sin imagen disponible");
            lblImagenVehiculo.setHorizontalAlignment(SwingConstants.CENTER);
        }
        contentPane.add(lblImagenVehiculo);

        // Datos del vehículo
        JLabel lblNombre = new JLabel(vehiculo.getMarca() + " " + vehiculo.getNombre());
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNombre.setBounds(450, 80, 270, 30);
        contentPane.add(lblNombre);

        JLabel lblCategoria = new JLabel("Categoría: " + vehiculo.getCategoria());
        lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCategoria.setBounds(450, 140, 270, 30);
        contentPane.add(lblCategoria);

        JLabel lblPrecio = new JLabel(String.format("Precio: $%.2f", vehiculo.getPrecio()));
        lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblPrecio.setBounds(450, 200, 270, 30);
        contentPane.add(lblPrecio);
    }
}
