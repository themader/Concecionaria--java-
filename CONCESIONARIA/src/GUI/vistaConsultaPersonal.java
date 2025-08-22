package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Consulta;
import BLL.User;
import DLL.DLLConsulta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class vistaConsultaPersonal extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Consulta consultaSeleccionada;
    private User usuario;

    public void run(User usuario) {
        try {
            vistaConsultaPersonal frame = new vistaConsultaPersonal(usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public vistaConsultaPersonal(User usuario) {
        this.usuario = usuario;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Consulta seleccionada:");
        lblSeleccionado.setBounds(10, 56, 860, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(new String[]{"ID", "ID Cliente", "Detalle", "Respuesta"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 87, 860, 200);
        contentPane.add(scrollPane);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    consultaSeleccionada = new Consulta(
                        (int) model.getValueAt(row, 0),             
                        (int) model.getValueAt(row, 1),              
                        (String) model.getValueAt(row, 2),           
                        (String) model.getValueAt(row, 3)           
                    );

                    lblSeleccionado.setText("Consulta seleccionada: ID=" + consultaSeleccionada.getId_consulta() +
                            ", Cliente ID=" + consultaSeleccionada.getId_cliente() +
                            ", Detalle=" + consultaSeleccionada.getDetalle() +
                            ", Respuesta=" + consultaSeleccionada.getRespuesta());
                }
            }
        });

    
        JButton btnResponder = new JButton("Responder");
        btnResponder.setBounds(710, 310, 160, 40);
        btnResponder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (consultaSeleccionada != null) {
                    ResponderConsulta cuenta = new ResponderConsulta(usuario, consultaSeleccionada);
                    cuenta.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una consulta.");
                }
            }
        });
        contentPane.add(btnResponder);


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

        cargarTabla();
    }

    private void cargarTabla() {
        LinkedList<Consulta> consultas = DLLConsulta.mostrarConsultas();
        model.setRowCount(0); 
        for (Consulta c : consultas) {
            model.addRow(new Object[]{
                c.getId_consulta(),
                c.getId_cliente(),
                c.getDetalle(),
                c.getRespuesta()
            });
        }
    }
}

