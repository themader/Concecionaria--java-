package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.User;
import DLL.DLLConsulta;
import DLL.DLLMovimiento;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class cotizacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField marca;
	private JTextField nombre;
	private JTextField ano;
	private JTextField kilometraje;

	/**
	 * Launch the application.
	 */

			public void run(User usuario) {
				try {
					cotizacion frame = new cotizacion(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	/**
	 * Create the frame.
	 */
	public cotizacion(User usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		JButton btnVolver = new JButton("X");
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVolver.setBackground(Color.RED);
		btnVolver.setBounds(10, 11, 55, 33);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("Ingrese los datos de su vehiculo");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(44, 25, 355, 45);
		contentPane.add(lblNewLabel);
		
		marca = new JTextField();
		marca.setBounds(67, 111, 319, 27);
		contentPane.add(marca);
		marca.setColumns(10);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(67, 163, 319, 27);
		contentPane.add(nombre);
		
		ano = new JTextField();
		ano.setColumns(10);
		ano.setBounds(67, 215, 319, 27);
		contentPane.add(ano);
		
		JLabel lblNewLabel_1 = new JLabel("Marca");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(67, 98, 45, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre del modelo");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1.setBounds(67, 149, 138, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Año");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(67, 201, 138, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblYNuestroPersonal = new JLabel("Y nuestro personal le responderan");
		lblYNuestroPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		lblYNuestroPersonal.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblYNuestroPersonal.setBounds(44, 55, 355, 45);
		contentPane.add(lblYNuestroPersonal);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Kilometraje");
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1_1.setBounds(67, 253, 138, 14);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		kilometraje = new JTextField();
		kilometraje.setColumns(10);
		kilometraje.setBounds(67, 268, 319, 27);
		contentPane.add(kilometraje);
		
		JButton btnNewButton = new JButton("Solicitar cotizacion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			String MARCA = marca.getText();
			String NOMBRE = nombre.getText();
			String KM = kilometraje.getText();
			String ANO = ano.getText();
			
			
			
			
			String detalle = "El usuario: "+usuario.getNombre()+" Solicita una cotizacion de "+MARCA+" "+NOMBRE+ " KM: "+KM+ ", Año: "+ANO;
	        Date fecha = new Date(System.currentTimeMillis());; 
	        DLLMovimiento.agregarMovimiento(detalle, fecha);
			
	        DLLConsulta.AgregarConsulta(detalle, usuario.getId_user());;
	        dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(67, 306, 319, 33);
		contentPane.add(btnNewButton);
		
		
		
		
		
	}
}






