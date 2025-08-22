package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.User;
import BLL.Personal;
import DLL.DLLUser;

import java.awt.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class vistaPersonal extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Personal personalSeleccionado;
    private JTextField inpFiltro;

    public void run(User usuario) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    vistaPersonal frame = new vistaPersonal(usuario);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public vistaPersonal(User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 56, 760, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(new String[]{"ID", "Nombre", "Mail", "Rol"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 87, 760, 200);
        contentPane.add(scrollPane);

      
        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(600, 300, 150, 40);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (personalSeleccionado != null) {
                    JOptionPane.showMessageDialog(null, "Editar Personal: " + personalSeleccionado.getNombre());
                    editarUser cuenta = new editarUser(usuario, personalSeleccionado);
                    cuenta.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un personal.");
                }
            }
        });
        contentPane.add(btnEditar);

  
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(430, 300, 150, 40);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (personalSeleccionado != null) {
                    JOptionPane.showMessageDialog(null, "Función de eliminación aún no implementada.");
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un personal.");
                }
            }
        });
        contentPane.add(btnEliminar);

     
        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(new Color(240, 248, 255));
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(0, 0, 66, 45);
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnVolver);

     
        table.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        int id = (int) model.getValueAt(row, 0);
                        String nombre = (String) model.getValueAt(row, 1);
                        String mail = (String) model.getValueAt(row, 2);
                        String rol = (String) model.getValueAt(row, 3);

                        personalSeleccionado = new Personal();
                        personalSeleccionado.setId_user(id);
                        personalSeleccionado.setNombre(nombre);
                        personalSeleccionado.setMail(mail);
                        personalSeleccionado.setRol(rol);

                        lblSeleccionado.setText("Seleccionado: ID=" + id + ", Nombre=" + nombre + ", Mail=" + mail + ", Rol=" + rol);
                    }
                }
            }
        });

       
        inpFiltro = new JTextField();
        inpFiltro.setBounds(10, 300, 200, 40);
        contentPane.add(inpFiltro);

        
        JButton btnFiltro = new JButton("Filtrar nombre");
        btnFiltro.setBounds(220, 300, 150, 40);
        btnFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cargarTablaFiltrada(inpFiltro.getText());
            }
        });
        contentPane.add(btnFiltro);

        cargarTabla();
    }

    private void cargarTabla() {
        LinkedList<User> usuarios = DLLUser.mostrarUsuario();
        model.setRowCount(0);

        LinkedList<User> personal = usuarios.stream()
                .filter(u -> u.getRol() != null && u.getRol().equalsIgnoreCase("Jefe"))
                .collect(Collectors.toCollection(LinkedList::new));

        for (User p : personal) {
            model.addRow(new Object[]{
                    p.getId_user(),
                    p.getNombre(),
                    p.getMail(),
                    p.getRol()
            });
        }
    }

    private void cargarTablaFiltrada(String filtro) {
        LinkedList<User> usuarios = DLLUser.mostrarUsuario();

        LinkedList<User> personalFiltrado = usuarios.stream()
                .filter(u -> u.getRol().equalsIgnoreCase("Personal"))
                .filter(u -> u.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));

        model.setRowCount(0);

        for (User p : personalFiltrado) {
            model.addRow(new Object[]{
                    p.getId_user(),
                    p.getNombre(),
                    p.getMail(),
                    p.getRol()
            });
        }
    }
}

