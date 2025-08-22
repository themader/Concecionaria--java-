package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLCuentaD;
import DLL.DLLDeseados;
import DLL.DLLVehiculo;

import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class vistaVehiculoCliente extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Vehiculo vehiculoSeleccionado;
    private JTextField inpFiltro;
    private JLabel lblSeleccionado;

    public void run(User usuario) {
        EventQueue.invokeLater(() -> {
            try {
                vistaVehiculoCliente frame = new vistaVehiculoCliente(usuario);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public vistaVehiculoCliente(User usuario) {
        setTitle("Vehículos Disponibles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 940, 510);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(null);
        setContentPane(contentPane);

      
        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setBackground(Color.RED);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBounds(0, 0, 66, 45);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> dispose());
        contentPane.add(btnVolver);

      
        lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(80, 50, 830, 20);
        lblSeleccionado.setFont(new Font("Tahoma", Font.PLAIN, 14));
        contentPane.add(lblSeleccionado);


        model = new DefaultTableModel(new String[]{"ID", "IMG", "Nombre", "Marca", "Categoría", "Precio"}, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 80, 900, 210);
        contentPane.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    vehiculoSeleccionado = new Vehiculo(
                        (int) model.getValueAt(row, 0),
                        (byte[]) model.getValueAt(row, 1),
                        (String) model.getValueAt(row, 2),
                        (String) model.getValueAt(row, 3),
                        (String) model.getValueAt(row, 4),
                        (double) model.getValueAt(row, 5)
                    );

                    lblSeleccionado.setText(String.format("Seleccionado: ID=%d, Nombre=%s, Marca=%s, Categoría=%s, Precio=%.2f",
                        vehiculoSeleccionado.getIdVehiculo(),
                        vehiculoSeleccionado.getNombre(),
                        vehiculoSeleccionado.getMarca(),
                        vehiculoSeleccionado.getCategoria(),
                        vehiculoSeleccionado.getPrecio()));
                }
            }
        });

        JLabel lblFiltro = new JLabel("Filtro (nombre):");
        lblFiltro.setBounds(10, 310, 120, 20);
        contentPane.add(lblFiltro);

        inpFiltro = new JTextField();
        inpFiltro.setBounds(10, 335, 120, 35);
        contentPane.add(inpFiltro);

        JButton btnFiltroGeneral = new JButton("Filtrar");
        btnFiltroGeneral.setBounds(10, 380, 120, 40);
        btnFiltroGeneral.setFocusPainted(false);
        btnFiltroGeneral.addActionListener(e -> cargarTablaFiltrada(inpFiltro.getText()));
        contentPane.add(btnFiltroGeneral);

       
        JButton btnVender = new JButton("Compra");
        btnVender.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnVender.setBackground(new Color(154, 205, 50));
        btnVender.setBounds(310, 310, 180, 60);
        btnVender.setFocusPainted(false);
        btnVender.addActionListener(e -> {
            if (vehiculoSeleccionado == null) {
                JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
                return;
            }
            LinkedList<?> cuentas = DLLCuentaD.obtenerCuentasPorIdUser(usuario.getId_user());
            if (cuentas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No posee datos de cuenta registrados. Complete los datos.");
                cargarCuentas cuentaa = new cargarCuentas(usuario);
                cuentaa.run(usuario);
            } else {
                opcionesVenderVehiculo cuenta = new opcionesVenderVehiculo(vehiculoSeleccionado, usuario);
                cuenta.setVisible(true);
            }
        });
        contentPane.add(btnVender);

      
        JButton btnDeseado = new JButton("Agregar a deseados");
        btnDeseado.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDeseado.setBounds(560, 310, 210, 60);
        btnDeseado.setFocusPainted(false);
        btnDeseado.addActionListener(e -> {
            if (vehiculoSeleccionado != null) {
                DLLDeseados.agregar_deseado(usuario.getId_user(), vehiculoSeleccionado.getIdVehiculo());
                JOptionPane.showMessageDialog(null, "Vehículo agregado a deseados.");
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
            }
        });
        contentPane.add(btnDeseado);

        JButton btnVer = new JButton("Ver");
        btnVer.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnVer.setBounds(310, 380, 180, 60);
        btnVer.setFocusPainted(false);
        btnVer.addActionListener(e -> {
            if (vehiculoSeleccionado != null) {
                verVehiculo ver = new verVehiculo(vehiculoSeleccionado, usuario);
                ver.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
            }
        });
        contentPane.add(btnVer);

  
        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnConsultar.setBounds(560, 380, 210, 60);
        btnConsultar.setFocusPainted(false);
        btnConsultar.addActionListener(e -> {
            if (vehiculoSeleccionado != null) {
                CrearConsulta consulta = new CrearConsulta(usuario, vehiculoSeleccionado);
                consulta.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
            }
        });
        contentPane.add(btnConsultar);

        cargarTabla();
    }

    private void cargarTabla() {
        LinkedList<Vehiculo> vehiculos = DLLVehiculo.mostrarVehiculo();
        LinkedList<Vehiculo> noVendidos = vehiculos.stream()
                .filter(v -> v.getIdQuienCompro() == 0 && v.getIdQuienVendio() == 0)
                .collect(Collectors.toCollection(LinkedList::new));

        model.setRowCount(0);

        for (Vehiculo v : noVendidos) {
            model.addRow(new Object[]{
                    v.getIdVehiculo(),
                    v.getImg(),
                    v.getNombre(),
                    v.getMarca(),
                    v.getCategoria(),
                    v.getPrecio()
            });
        }
    }

    private void cargarTablaFiltrada(String filtro) {
        LinkedList<Vehiculo> vehiculos = DLLVehiculo.mostrarVehiculo();
        LinkedList<Vehiculo> filtrados = vehiculos.stream()
                .filter(v -> v.getIdQuienCompro() == 0 && v.getIdQuienVendio() == 0)
                .filter(v -> v.getNombre().toLowerCase().startsWith(filtro.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));

        model.setRowCount(0);

        for (Vehiculo v : filtrados) {
            model.addRow(new Object[]{
                    v.getIdVehiculo(),
                    v.getImg(),
                    v.getNombre(),
                    v.getMarca(),
                    v.getCategoria(),
                    v.getPrecio()
            });
        }
    }
}
