package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import BLL.User;
import DLL.DLLCuentaD;
import DLL.DLLMovimiento;
import DLL.DLLUser;

import javax.swing.SwingConstants;

public class cargarCuentas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField sueldo;
	private JTextField ahorro;

	/**
	 * Launch the application.
	 */
	
			public void run(User usuario) {
				try {
					cargarCuentas frame = new cargarCuentas(usuario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	

	/**
	 * Create the frame.
	 */
	public cargarCuentas(User usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		
		
		
		
		JButton btnVolver = new JButton("X");
		btnVolver.setBounds(17, 10, 45, 33);
		btnVolver.setForeground(new Color(240, 248, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVolver.setBackground(Color.RED);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnVolver);
		
		
		
		JLabel titulo = new JLabel("Ingrese los siguientes datos");
		titulo.setBounds(45, 34, 353, 57);
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("SansSerif", Font.BOLD, 25));
		contentPane.add(titulo);

		
		JLabel lbl1 = new JLabel("Caja/ahorros");
		lbl1.setBounds(109, 172, 174, 14);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("Sueldo");
		lbl2.setBounds(109, 91, 67, 14);
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lbl2);
		
		
		
		
		sueldo = new JTextField();
		sueldo.setBounds(106, 110, 210, 31);
		contentPane.add(sueldo);
		sueldo.setColumns(10);
		
		ahorro = new JTextField();
		ahorro.setBounds(109, 197, 210, 31);
		ahorro.setColumns(10);
		contentPane.add(ahorro);
		
		
		
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.setBounds(109, 239, 207, 50);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String sueldoText = sueldo.getText();
		        String ahorroText = ahorro.getText();
	            
	            if(sueldoText.isEmpty() || ahorroText.isEmpty()) {
	                JOptionPane.showMessageDialog(null, "Debe llenar las casillas con un valor");	
	            }else {
	            	double Sueldo= Double.parseDouble(sueldo.getText());
		            double caja = Double.parseDouble(ahorro.getText());


	                DLLCuentaD.agregar_cuenta(usuario.getId_user(), Sueldo, caja);;
			        dispose();
	                
	                
	            }
			}
		});
		contentPane.add(btnNewButton);
		
		
		
	}
}
