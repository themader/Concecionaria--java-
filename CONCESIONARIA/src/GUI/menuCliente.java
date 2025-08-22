package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import BLL.Cliente;
import BLL.CuentaD;
import DLL.DLLCuentaD;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class menuCliente extends JFrame {

	private JPanel contentPane;

	public void run(Cliente usuario) {
		try {
			menuCliente frame = new menuCliente(usuario);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public menuCliente(Cliente usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(244, 244, 244));
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("Menú del Cliente");
		lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(33, 37, 41));
		lblTitulo.setBounds(20, 10, 400, 40);
		contentPane.add(lblTitulo);

		JLabel lblBienvenido = new JLabel("Bienvenido/a, " + usuario.getNombre());
		lblBienvenido.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblBienvenido.setBounds(20, 60, 400, 30);
		contentPane.add(lblBienvenido);

		JLabel lblEmail = new JLabel("Email: " + usuario.getMail());
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(20, 90, 400, 20);
		contentPane.add(lblEmail);

		JLabel lblImagen = new JLabel();
		lblImagen.setBounds(600, 40, 200, 200);
		if (usuario.getImg() != null && usuario.getImg().length > 0) {
			ImageIcon icon = new ImageIcon(usuario.getImg());
			Image imgEscalada = icon.getImage().getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH);
			lblImagen.setIcon(new ImageIcon(imgEscalada));
		}
		contentPane.add(lblImagen);

		JButton btnCerrar = new JButton("Cerrar sesión");
		btnCerrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCerrar.setBounds(20, 490, 200, 40);
		btnCerrar.setBackground(Color.RED);
		btnCerrar.setForeground(Color.WHITE);
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnCerrar);

		JButton btnVehiculos = new JButton("Lista Vehículos");
		btnVehiculos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVehiculos.setBounds(20, 140, 250, 45);
		btnVehiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vistaVehiculoCliente vista = new vistaVehiculoCliente(usuario);
				vista.run(usuario);
			}
		});
		contentPane.add(btnVehiculos);

		JButton btnDeseados = new JButton("Deseados");
		btnDeseados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDeseados.setBounds(20, 200, 250, 45);
		btnDeseados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deseados vista = new deseados(usuario);
				vista.run(usuario);
			}
		});
		contentPane.add(btnDeseados);

		JButton btnCotizacion = new JButton("Cotización Vehículo");
		btnCotizacion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCotizacion.setBounds(20, 260, 250, 45);
		btnCotizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cotizacion vista = new cotizacion(usuario);
				vista.run(usuario);
			}
		});
		contentPane.add(btnCotizacion);

		JButton btnConsultas = new JButton("Consultas");
		btnConsultas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnConsultas.setBounds(20, 320, 250, 45);
		btnConsultas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultasClientes vista = new ConsultasClientes(usuario);
				vista.run(usuario);
			}
		});
		contentPane.add(btnConsultas);

		JButton btnVerCuenta = new JButton("Ver tu cuenta");
		btnVerCuenta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnVerCuenta.setBounds(20, 380, 250, 45);
		btnVerCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verCuenta vista = new verCuenta(usuario);
				vista.run(usuario);
			}
		});
		contentPane.add(btnVerCuenta);

		JButton btnRegistrarCuenta = new JButton("Registrar Cuenta");
		btnRegistrarCuenta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegistrarCuenta.setBounds(20, 440, 250, 40);
		btnRegistrarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LinkedList<CuentaD> cuentas = DLLCuentaD.obtenerCuentasPorIdUser(usuario.getId_user());
				if (cuentas.isEmpty()) {
					cargarCuentas vista = new cargarCuentas(usuario);
					vista.run(usuario);
				} else {
					JOptionPane.showMessageDialog(null, "Usted ya posee datos de su cuenta registrados.");
				}
			}
		});
		contentPane.add(btnRegistrarCuenta);
	}
}
