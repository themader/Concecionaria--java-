package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLMovimiento;
import DLL.DLLVehiculo;
import DLL.DLLCuentaD;
import BLL.CuentaD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.LinkedList;

public class cuotasCliente extends JFrame {

    private JPanel contentPane;

    public void run(Vehiculo vehiculo, User usuario) {
        try {
            cuotasCliente frame = new cuotasCliente(vehiculo, usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public cuotasCliente(Vehiculo vehiculo, User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 346);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(new Color(240, 248, 255));
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(0, 0, 53, 33);
        btnVolver.addActionListener(e -> dispose());
        contentPane.add(btnVolver);

        JLabel lblTitulo = new JLabel("Seleccione cantidad de cuotas");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setBounds(95, 60, 260, 14);
        contentPane.add(lblTitulo);

        int[] cuotas = {3, 6, 9, 12, 15, 18};
        JComboBox<Integer> comboCuotas = new JComboBox<>();
        for (int c : cuotas) {
            comboCuotas.addItem(c);
        }
        comboCuotas.setBackground(new Color(255, 255, 255));
        comboCuotas.setBounds(109, 85, 188, 55);
        contentPane.add(comboCuotas);

        JButton btnComprar = new JButton("Comprar");
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnComprar.setBackground(new Color(0, 128, 0));
        btnComprar.setBounds(109, 180, 188, 40);
        btnComprar.addActionListener((ActionEvent e) -> {
            int cuotasSeleccionadas = (int) comboCuotas.getSelectedItem();
            double precioCuota = vehiculo.getPrecio() / cuotasSeleccionadas;

            LinkedList<CuentaD> cuentas = DLLCuentaD.obtenerCuentasPorIdUser(usuario.getId_user());

            for (CuentaD cuenta : cuentas) {
                double mitadSueldo = cuenta.getSueldo() / 2;

                if (mitadSueldo >= precioCuota) {
                    
                    Vehiculo car = new Vehiculo(usuario.getId_user(), vehiculo.getIdVehiculo());
                    DLLVehiculo.compraCliente(car);

                    String detalle = "El cliente: " + usuario.getMail() +
                            " compró el vehículo " + vehiculo.getMarca() + " " + vehiculo.getNombre() +
                            " en " + cuotasSeleccionadas + " cuotas de $" + precioCuota;
                    Date fecha = new Date(System.currentTimeMillis());
                    DLLMovimiento.agregarMovimiento(detalle, fecha);

                    JOptionPane.showMessageDialog(null, "Compra realizada con éxito");
                    dispose();
                    return;
                }
            }

            JOptionPane.showMessageDialog(null, "Tu sueldo no alcanza: necesitás que el 50% cubra una cuota de $" + precioCuota);
        });

        contentPane.add(btnComprar);
    }
}
