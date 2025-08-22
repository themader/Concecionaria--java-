package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLMovimiento;
import DLL.DLLUser;
import DLL.DLLVehiculo;
import DLL.DLLCuentaD;
import BLL.CuentaD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.LinkedList;

public class cuotasPersonal extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public void run(Vehiculo vehiculo, User usuario) {
        try {
            cuotasPersonal frame = new cuotasPersonal(vehiculo, usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public cuotasPersonal(Vehiculo vehiculo, User usuario) {
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

        JLabel lblNewLabel = new JLabel("Cantidad de cuotas");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(109, 60, 188, 14);
        contentPane.add(lblNewLabel);

        int[] cuotas = {3, 6, 9, 12, 15, 18};
        JComboBox<Integer> cuota = new JComboBox<>();
        for (int c : cuotas) {
            cuota.addItem(c);
        }
        cuota.setBackground(new Color(255, 255, 255));
        cuota.setBounds(109, 85, 188, 55);
        contentPane.add(cuota);

        LinkedList<User> listaUsuarios = DLLUser.mostrarUsuario();

        JLabel lblQuienCompro = new JLabel("Seleccione al comprador del vehículo");
        lblQuienCompro.setHorizontalAlignment(SwingConstants.LEFT);
        lblQuienCompro.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblQuienCompro.setBounds(80, 152, 269, 26);
        contentPane.add(lblQuienCompro);

        JComboBox<User> comboBoxComprador = new JComboBox<>();
        for (User user : listaUsuarios) {
            comboBoxComprador.addItem(user);
        }
        comboBoxComprador.setBounds(109, 179, 188, 55);
        contentPane.add(comboBoxComprador);

        JButton btnEditar = new JButton("Vender");
        btnEditar.setForeground(new Color(240, 248, 255));
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnEditar.setBackground(Color.CYAN);
        btnEditar.setBounds(109, 246, 188, 40);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User comprador = (User) comboBoxComprador.getSelectedItem();

                if (comprador != null) {
                    int idComprador = comprador.getId_user();

                    int cuotasSeleccionadas = (int) cuota.getSelectedItem();  
                    double precioCuota = vehiculo.getPrecio() / cuotasSeleccionadas;

                    JOptionPane.showMessageDialog(null, "Debe tener un sueldo que el 50% pueda cubrir la cuota del vehículo de la cual es $" + precioCuota);

                    LinkedList<CuentaD> cuentas = DLLCuentaD.obtenerCuentasPorIdUser(idComprador);

                    for (CuentaD cuenta : cuentas) {
                        double mitadSueldo = cuenta.getSueldo() / 2;

                        if (mitadSueldo >= precioCuota) {
                            Vehiculo car = new Vehiculo(usuario.getId_user(), idComprador, vehiculo.getIdVehiculo());
                            DLLVehiculo.editar_venta(car);

                            String detalle = "El personal: " + usuario.getMail() +
                                    " vendió el vehículo " + vehiculo.getMarca() + " " + vehiculo.getNombre() +
                                    " al cliente: " + comprador.getNombre() +
                                    " con pago en " + cuotasSeleccionadas + " de $"+precioCuota;
                            Date fecha = new Date(System.currentTimeMillis());
                            DLLMovimiento.agregarMovimiento(detalle, fecha);

                            JOptionPane.showMessageDialog(null, "Venta realizada con éxito");
                            dispose();
                            return;
                        }
                    }

                    JOptionPane.showMessageDialog(null, "No alcanza el salario para pagar.");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar un comprador.");
                }
            }
        });

        contentPane.add(btnEditar);
    }
}





