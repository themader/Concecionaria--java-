package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.User;
import BLL.Vehiculo;
import javax.swing.JTextField;

public class opcionesVenderVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(Vehiculo vehiculo, User usuario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					opcionesVenderVehiculo frame = new opcionesVenderVehiculo(vehiculo, usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param vehiculo 
	 */
	public opcionesVenderVehiculo(Vehiculo vehiculo, User usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		
		JButton btnVolver = new JButton("X");
		btnVolver.setForeground(new Color(240, 248, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVolver.setBackground(Color.RED);
		btnVolver.setBounds(0, 0, 56, 33);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnVolver);
		
		JButton btnNewButton = new JButton("Contado");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (usuario.getRol().equals("Cliente")){
					
					contadoCliente compra = new contadoCliente(vehiculo, usuario);
					compra.setVisible(true);
					
				}else{
					
					contadoPersonal compra = new contadoPersonal(vehiculo, usuario);
					compra.setVisible(true);
					
				}
				dispose(); 
			}
		});
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton.setBounds(43, 80, 162, 81);
		contentPane.add(btnNewButton);
		
		
		
		
		JButton btnNewButton1 = new JButton("Cuotas");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (usuario.getRol().equals("Cliente")){
					
					cuotasCliente compra = new cuotasCliente(vehiculo, usuario);
					compra.setVisible(true);
					
				}else {
					
					cuotasPersonal compra = new cuotasPersonal(vehiculo, usuario);
					compra.setVisible(true);
					
				}
			}
		});
		btnNewButton1.setFont(new Font("Arial Black", Font.BOLD, 15));
		btnNewButton1.setBounds(237, 80, 162, 81);
		contentPane.add(btnNewButton1);
		
		
		
	}
}