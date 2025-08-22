package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLMovimiento;
import DLL.DLLUser;
import DLL.DLLVehiculo;

public class editarVenta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public void run(Vehiculo vehiculo, User usuario) {
		try {
			editarVenta frame = new editarVenta(vehiculo, usuario);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public editarVenta(Vehiculo vehiculo, User usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		LinkedList<User> listaUsuarios = DLLUser.mostrarUsuario();

		
		
		
		JLabel lblQuienCompro = new JLabel("Seleccione quien compró el auto");
		lblQuienCompro.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuienCompro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuienCompro.setBounds(94, 24, 222, 26);
		contentPane.add(lblQuienCompro);
		JComboBox<User> comboBoxComprador = new JComboBox<>();
		for (User user : listaUsuarios) {
			comboBoxComprador.addItem(user);
		}
		comboBoxComprador.setBounds(94, 48, 177, 55);
		contentPane.add(comboBoxComprador);
		
		
		
		JLabel lblSeleccioneQuienVendio = new JLabel("Seleccione quien vendió");
		lblSeleccioneQuienVendio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSeleccioneQuienVendio.setBounds(94, 111, 222, 26);
		contentPane.add(lblSeleccioneQuienVendio);
		JComboBox<User> comboBoxVendedor = new JComboBox<>();
		for (User user : listaUsuarios) {
			comboBoxVendedor.addItem(user);
		}
		comboBoxVendedor.setBounds(94, 133, 177, 55);
		contentPane.add(comboBoxVendedor);
		
		JButton btnVolver = new JButton("X");
		btnVolver.setForeground(new Color(240, 248, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVolver.setBackground(Color.RED);
		btnVolver.setBounds(0, 0, 53, 33);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);

		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setForeground(new Color(240, 248, 255));
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBackground(Color.CYAN);
		btnEditar.setBounds(94, 199, 177, 33);
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        User comprador = (User) comboBoxComprador.getSelectedItem();
		        User vendedor = (User) comboBoxVendedor.getSelectedItem();

		        if (comprador != null && vendedor != null) {
		            int idComprador = comprador.getId_user(); 
		            int idVendedor = vendedor.getId_user();
		            
		            String detalle = "Los datos de la venta del vehiculo "+vehiculo.getIdVehiculo()+": "+vehiculo.getMarca()+" "+ vehiculo.getNombre();
			        Date fecha = new Date(System.currentTimeMillis());; 
			        DLLMovimiento.agregarMovimiento(detalle, fecha);

		            Vehiculo car = new Vehiculo(idVendedor, idComprador, vehiculo.getIdVehiculo());
		            DLLVehiculo.editar_venta(car);

		            dispose(); 
		        } else {
		            JOptionPane.showMessageDialog(null, "Debe seleccionar comprador y vendedor.");
		        }
		    }
		});

		contentPane.add(btnEditar);
	}
}


