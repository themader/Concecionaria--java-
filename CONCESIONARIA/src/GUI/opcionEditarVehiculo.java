package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.User;
import BLL.Vehiculo;

public class opcionEditarVehiculo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(Vehiculo vehiculo, User usuario) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    opcionEditarVehiculo frame = new opcionEditarVehiculo(vehiculo, usuario);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public opcionEditarVehiculo(Vehiculo vehiculo, User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

       
        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(new Color(240, 248, 255));
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(0, 0, 89, 33);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnVolver);

        
        JButton btnEditarVehiculo = new JButton("Editar vehículo");
        btnEditarVehiculo.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEditarVehiculo.setBounds(108, 65, 203, 67);
        btnEditarVehiculo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarVehiculo editar = new editarVehiculo(vehiculo, usuario);
                editar.setVisible(true);
            }
        });
        contentPane.add(btnEditarVehiculo);

        
        JButton btnEditarVenta = new JButton("Editar venta");
        btnEditarVenta.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEditarVenta.setBounds(108, 158, 203, 67);
        btnEditarVenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarVenta editar = new editarVenta(vehiculo, usuario);
                editar.setVisible(true);
            }
        });
        contentPane.add(btnEditarVenta);
    }
}





