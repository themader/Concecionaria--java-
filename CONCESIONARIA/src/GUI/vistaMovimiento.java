package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BLL.Movimiento;
import DLL.DLLMovimiento;
import BLL.User;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class vistaMovimiento extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel model;
    private Movimiento movimientoSeleccionado;
    private JTextField inpFiltro;

    public  void run(User usuario) {
        EventQueue.invokeLater(() -> {
            try {
                vistaMovimiento frame = new vistaMovimiento(usuario);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public vistaMovimiento(User usuario) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 450);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblSeleccionado = new JLabel("Seleccionado:");
        lblSeleccionado.setBounds(10, 56, 650, 20);
        contentPane.add(lblSeleccionado);

        model = new DefaultTableModel(new String[]{"ID", "Detalle", "Fecha"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 87, 660, 200);
        contentPane.add(scrollPane);

        
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    movimientoSeleccionado = new Movimiento(
                        (int) model.getValueAt(row, 0),
                        (String) model.getValueAt(row, 1),
                        (Date) model.getValueAt(row, 2)
                    );
                    lblSeleccionado.setText("Seleccionado: ID=" + movimientoSeleccionado.getID()
                            + ", Detalle=" + movimientoSeleccionado.getDetalle()
                            + ", Fecha=" + movimientoSeleccionado.getFecha());
                }
            }
        });

        inpFiltro = new JTextField();
        inpFiltro.setBounds(10, 321, 200, 30);
        contentPane.add(inpFiltro);
        inpFiltro.setColumns(10);

        JButton btnFiltrar = new JButton("Filtrar detalle");
        btnFiltrar.setBounds(220, 320, 130, 35);
        btnFiltrar.addActionListener(e -> cargarTablaFiltrada(inpFiltro.getText()));
        contentPane.add(btnFiltrar);

       

       

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(510, 316, 150, 40);
        btnEliminar.addActionListener(e -> eliminarMovimiento());
        contentPane.add(btnEliminar);

        JButton btnVolver = new JButton("X");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnVolver.setBackground(Color.RED);
        btnVolver.setBounds(0, 0, 66, 45);
        btnVolver.addActionListener(e -> dispose());
        contentPane.add(btnVolver);

        cargarTabla();
    }

    private void cargarTabla() {
        LinkedList<Movimiento> movimientos = DLLMovimiento.mostrarMovimiento();
        model.setRowCount(0);
        for (Movimiento m : movimientos) {
            model.addRow(new Object[]{
                    m.getID(),
                    m.getDetalle(),
                    m.getFecha()
            });
        }
    }

    private void cargarTablaFiltrada(String filtro) {
        LinkedList<Movimiento> filtrados = DLLMovimiento.mostrarMovimiento().stream()
                .filter(m -> m.getDetalle().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toCollection(LinkedList::new));

        model.setRowCount(0);
        for (Movimiento m : filtrados) {
            model.addRow(new Object[]{
                    m.getID(),
                    m.getDetalle(),
                    m.getFecha()
            });
        }
    }


    private void eliminarMovimiento() {
        if (movimientoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Seleccione un movimiento para eliminar.");
            return;
        }else {
            JOptionPane.showMessageDialog(null, "Función de eliminar no implementada.");
        }
    }

	
}
