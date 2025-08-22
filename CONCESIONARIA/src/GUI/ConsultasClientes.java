package GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Consulta;
import BLL.User;
import DLL.DLLConsulta;

public class ConsultasClientes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Consulta consultaSeleccionada;

 
    public static void run(User usuario) {
        try {
            ConsultasClientes frame = new ConsultasClientes(usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ConsultasClientes(User usuario) {
        setTitle("Mis Consultas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Consulta seleccionada:");
        lblSeleccionado.setBounds(135, 31, 650, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(new String[]{"ID", "Detalle", "Respuesta"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 62, 660, 200);
        contentPane.add(scrollPane);

        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(10, 11, 50, 30);
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(btnVolver);

       
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    consultaSeleccionada = new Consulta(
                            (int) model.getValueAt(row, 0),
                            usuario.getId_user(),
                            (String) model.getValueAt(row, 1),
                            (String) model.getValueAt(row, 2)
                    );

                    lblSeleccionado.setText("Seleccionada: ID=" + consultaSeleccionada.getId_consulta() +
                            ", Detalle=" + consultaSeleccionada.getDetalle() +
                            ", Respuesta=" + consultaSeleccionada.getRespuesta());
                }
            }
        });

        cargarConsultas(usuario);
    }

    
    private void cargarConsultas(User usuario) {
        LinkedList<Consulta> consultas = DLLConsulta.VerConsultasPorIdUsuario(usuario.getId_user());

        if (consultas != null && !consultas.isEmpty()) {
            model.setRowCount(0);
            for (Consulta c : consultas) {
                model.addRow(new Object[]{
                        c.getId_consulta(),
                        c.getDetalle(),
                        (c.getRespuesta() != null ? c.getRespuesta() : "Sin respuesta")
                });
            }
        } else {
            JOptionPane.showMessageDialog(null, "No realizaste consultas todavía.");
        }
    }
}
