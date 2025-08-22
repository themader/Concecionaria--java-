package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.CuentaD;
import BLL.User;
import BLL.Vehiculo;
import DLL.DLLCuentaD;
import DLL.DLLMovimiento;
import DLL.DLLVehiculo;

public class contadoCliente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public void run(Vehiculo vehiculo, User usuario) {
        try {
            contadoCliente frame = new contadoCliente(vehiculo, usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public contadoCliente(Vehiculo vehiculo, User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 390, 250);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(0, 0, 53, 33);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnVolver);

        JLabel lblInfo = new JLabel("¿Desea comprar el vehículo al contado?");
        lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblInfo.setBounds(60, 70, 300, 30);
        contentPane.add(lblInfo);

        JButton btnComprar = new JButton("Comprar");
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnComprar.setBackground(Color.CYAN);
        btnComprar.setBounds(90, 130, 180, 40);
        btnComprar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idComprador = usuario.getId_user();
                double precioVehiculo = vehiculo.getPrecio();

                
                LinkedList<CuentaD> cuentas = DLLCuentaD.obtenerCuentasPorIdUser(idComprador);
                
                double totalCaja = 0;
                for (CuentaD cuenta : cuentas) {
                    if (cuenta.getCaja() >= precioVehiculo) {
                        double nuevaCaja = cuenta.getCaja() - precioVehiculo;

                     
                        Vehiculo car = new Vehiculo(usuario.getId_user(),  vehiculo.getIdVehiculo());
                        DLLVehiculo.compraCliente(car);

                        String detalle = "El cliente " + usuario.getMail() + " compró el vehículo " +
                                         vehiculo.getMarca() + " " + vehiculo.getNombre() + " al contado.";
                        Date fecha = new Date(System.currentTimeMillis());

                        DLLMovimiento.agregarMovimiento(detalle, fecha);

                        CuentaD cuentaActualizada = new CuentaD(cuenta.getId_cuenta(), usuario.getId_user(), cuenta.getSueldo(), nuevaCaja);
                        DLLCuentaD.modificarCaja(cuentaActualizada);

                        JOptionPane.showMessageDialog(null, "Compra realizada con éxito");
                        dispose();
                        return;
                    }
                }

                
                JOptionPane.showMessageDialog(null, "Ninguna de tus cuentas tiene suficiente dinero para cubrir la compra.");


            }
        });

        contentPane.add(btnComprar);
    }
}

