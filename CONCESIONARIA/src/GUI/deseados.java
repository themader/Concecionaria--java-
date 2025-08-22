package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLVehiculo;
import DLL.DLLDeseados;

import java.awt.*;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class deseados extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Vehiculo vehiculoSeleccionado;
    private JTextField inpFiltro;

    public static void run(User usuario) {
        EventQueue.invokeLater(() -> {
            try {
                deseados frame = new deseados(usuario);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public deseados(User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 934, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 56, 760, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(new String[]{"ID",  "Nombre", "Marca", "Categoria", "Precio","img"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 87, 898, 200);
        contentPane.add(scrollPane);

        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(735, 309, 150, 40);
        btnEliminar.addActionListener(e -> {
            
        });
        contentPane.add(btnEliminar);

      
        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(new Color(240, 248, 255));
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(0, 0, 66, 45);
        btnVolver.addActionListener(e -> dispose());
        contentPane.add(btnVolver);

        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    vehiculoSeleccionado = new Vehiculo(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (String) model.getValueAt(row, 2),
                        (String) model.getValueAt(row, 3),
                        (double) model.getValueAt(row, 4),
                        (byte[]) model.getValueAt(row, 5)
                    );

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

        
        JButton btnVer = new JButton("Ver");
        btnVer.setBounds(575, 309, 150, 40);
        btnVer.addActionListener(e -> {
            if (vehiculoSeleccionado != null) {
            	
                verVehiculo cuenta = new verVehiculo(vehiculoSeleccionado, usuario);
                cuenta.setVisible(true);
                
            }
        });
        contentPane.add(btnVer);

       
        JButton btnVender = new JButton("Comprar");
        btnVender.setBounds(413, 309, 150, 40);
        btnVender.addActionListener(e -> {
            if (vehiculoSeleccionado != null) {
            	
                opcionesVenderVehiculo cuenta = new opcionesVenderVehiculo(vehiculoSeleccionado, usuario);
                cuenta.setVisible(true);
                
            }
        });
        contentPane.add(btnVender);

        cargarTabla(usuario);  
    }

    private void cargarTabla(User usuario) {
        LinkedList<Vehiculo> vehiculosFavoritos = DLL.DLLDeseados.VerFavoritos(usuario.getId_user());

        if (vehiculosFavoritos != null) {
            model.setRowCount(0);  
            for (Vehiculo v : vehiculosFavoritos) {
                model.addRow(new Object[]{
                        v.getIdVehiculo(),
                        v.getNombre(),
                        v.getMarca(),
                        v.getCategoria(),
                        v.getPrecio(),
                        v.getImg()
                     
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "No tienes vehículos favoritos.");
        }
    }
}
