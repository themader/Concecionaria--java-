package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLVehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class vistaVehiculo extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Vehiculo vehiculoSeleccionado;
    private JTextField inpFiltro;

    public static void main(User usuario) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    vistaVehiculo frame = new vistaVehiculo(usuario);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public vistaVehiculo(User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 934, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 56, 760, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(new String[]{"ID", "IMG", "Nombre", "Marca", "Categoria", "Precio", "Comprador"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 87, 898, 200);
        contentPane.add(scrollPane);

        

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(758, 360, 150, 40);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vehiculoSeleccionado != null) {
                    opcionEditarVehiculo cuenta = new opcionEditarVehiculo(vehiculoSeleccionado, usuario);
                    cuenta.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
                }
            }
        });
        contentPane.add(btnEditar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(672, 411, 150, 40);
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vehiculoSeleccionado != null) {
                    JOptionPane.showMessageDialog(null, "Función de eliminación aún no implementada.");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
                }
            }
        });
        contentPane.add(btnEliminar);

        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(new Color(240, 248, 255));
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(0, 0, 66, 45);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnVolver);

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
                    vehiculoSeleccionado.setIdQuienCompro((int) model.getValueAt(row, 6));

                    lblSeleccionado.setText("Seleccionado: ID=" + vehiculoSeleccionado.getIdVehiculo()
                            + ", Nombre=" + vehiculoSeleccionado.getNombre()
                            + ", Marca=" + vehiculoSeleccionado.getMarca()
                            + ", Categoria=" + vehiculoSeleccionado.getCategoria()
                            + ", Precio=" + vehiculoSeleccionado.getPrecio());
                }
            }
        });

        inpFiltro = new JTextField();
        inpFiltro.setBounds(10, 321, 118, 40);
        contentPane.add(inpFiltro);
        inpFiltro.setColumns(10);

        JButton btnFiltroGeneral = new JButton("Filtrar nombre");
        btnFiltroGeneral.setBounds(10, 370, 118, 51);
        btnFiltroGeneral.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarTablaFiltradaStream(inpFiltro.getText());
            }
        });
        contentPane.add(btnFiltroGeneral);

        JLabel lblNewLabel = new JLabel("Filtro");
        lblNewLabel.setBounds(10, 309, 117, 14);
        contentPane.add(lblNewLabel);

        JButton btnVer = new JButton("Ver");
        btnVer.setBounds(575, 309, 150, 40);
        btnVer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vehiculoSeleccionado != null) {
                    verVehiculo cuenta = new verVehiculo(vehiculoSeleccionado, usuario);
                    cuenta.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
                }
            }
        });
        contentPane.add(btnVer);

        JButton btnVender = new JButton("Vender");
        btnVender.setBounds(575, 360, 150, 40);
        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (vehiculoSeleccionado != null) {
                    if (vehiculoSeleccionado.getIdQuienCompro() > 0) {
                        JOptionPane.showMessageDialog(null, "El vehículo ya se encuentra vendido.");
                    } else {
                        opcionesVenderVehiculo cuenta = new opcionesVenderVehiculo(vehiculoSeleccionado, usuario);
                        cuenta.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un vehículo.");
                }
            }
        });
        contentPane.add(btnVender);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(758, 309, 150, 40);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarVehiculo cuenta = new agregarVehiculo(vehiculoSeleccionado, usuario);
                cuenta.setVisible(true);
            }
        });
        contentPane.add(btnAgregar);

        cargarTabla();
    }

    private void cargarTabla() {
        LinkedList<Vehiculo> vehiculos = DLLVehiculo.mostrarVehiculo();
        model.setRowCount(0);
        for (Vehiculo v : vehiculos) {
            model.addRow(new Object[]{
                v.getIdVehiculo(),
                v.getImg(),
                v.getNombre(),
                v.getMarca(),
                v.getCategoria(),
                v.getPrecio(),
                v.getIdQuienCompro()
            });
        }
    }

    private void cargarTablaFiltradaStream(String filtro) {
        LinkedList<Vehiculo> vehiculosFiltrados = DLLVehiculo.mostrarVehiculo().stream()
                .filter(v -> v.getNombre().toLowerCase().startsWith(filtro.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));

        model.setRowCount(0);
        for (Vehiculo v : vehiculosFiltrados) {
            model.addRow(new Object[]{
                v.getIdVehiculo(),
                v.getImg(),
                v.getNombre(),
                v.getMarca(),
                v.getCategoria(),
                v.getPrecio(),
                v.getIdQuienCompro()
            });
        }
    }
}

