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

import BLL.CuentaD;
import BLL.User;
import BLL.Vehiculo;
import DLL.DLLCuentaD;
import DLL.DLLMovimiento;
import DLL.DLLUser;
import DLL.DLLVehiculo;

public class contadoPersonal extends JFrame {

	
	private JPanel contentPane;

	public void run(Vehiculo vehiculo, User usuario) {
		try {
			contadoPersonal frame = new contadoPersonal(vehiculo, usuario);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public contadoPersonal(Vehiculo vehiculo, User usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 300);

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

		LinkedList<User> listaUsuarios = DLLUser.mostrarUsuario();

		JLabel lblQuienCompro = new JLabel("Seleccione al comprador del vehículo");
		lblQuienCompro.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuienCompro.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQuienCompro.setBounds(74, 64, 222, 26);
		contentPane.add(lblQuienCompro);

		JComboBox<User> comboBoxComprador = new JComboBox<>();
		for (User user : listaUsuarios) {
			comboBoxComprador.addItem(user);
		}
		comboBoxComprador.setBounds(94, 91, 177, 55);
		contentPane.add(comboBoxComprador);

		JButton btnEditar = new JButton("Vender");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEditar.setBackground(Color.CYAN);
		btnEditar.setBounds(94, 157, 177, 33);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User comprador = (User) comboBoxComprador.getSelectedItem();

				if (comprador != null) {
					int idComprador = comprador.getId_user();
					double precioVehiculo = vehiculo.getPrecio();

					LinkedList<CuentaD> cuentas = DLLCuentaD.obtenerCuentasPorIdUser(idComprador);

					for (CuentaD cuenta : cuentas) {
						if (cuenta.getCaja() >= precioVehiculo) {
							double nuevaCaja = cuenta.getCaja() - precioVehiculo;

							
							Vehiculo car = new Vehiculo(usuario.getId_user(), idComprador, vehiculo.getIdVehiculo());
							DLLVehiculo.editar_venta(car);

							String detalle = "El personal: " + usuario.getMail() +
									" vendió el vehículo " + vehiculo.getMarca() + " " + vehiculo.getNombre() +
									" al cliente: " + comprador.getNombre();
							Date fecha = new Date(System.currentTimeMillis());
							DLLMovimiento.agregarMovimiento(detalle, fecha);

							CuentaD cuentaActualizada = new CuentaD(cuenta.getId_cuenta(), comprador.getId_user(), cuenta.getSueldo(), nuevaCaja);
							DLLCuentaD.modificarCaja(cuentaActualizada);

							JOptionPane.showMessageDialog(null, "Venta realizada con éxito");
							dispose();
							return;
						}
					}

					JOptionPane.showMessageDialog(null, "El cliente no tiene suficiente dinero en ninguna cuenta para comprar este vehículo.");
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un comprador.");
				}
			}
		});

		contentPane.add(btnEditar);
	}
}
