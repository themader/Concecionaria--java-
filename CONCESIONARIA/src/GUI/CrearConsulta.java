package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Consulta;
import BLL.User;
import BLL.Vehiculo;
import DLL.DLLConsulta;
import DLL.DLLMovimiento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class CrearConsulta extends JFrame {

	private JPanel contentPane;
	private JTextField detalle;

	/**
	 * Launch the application.
	 */

			public void run(User usuario, Vehiculo vehiculo) {
				try {
					CrearConsulta frame = new CrearConsulta(usuario, vehiculo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the frame.
	 */
	public CrearConsulta(User usuario, Vehiculo vehiculo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton cerrarSesion = new JButton("X");
		cerrarSesion.setBounds(10, 11, 90, 33);
		cerrarSesion.setForeground(SystemColor.text);
		contentPane.add(cerrarSesion);
		cerrarSesion.setBackground(Color.RED);
		cerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  dispose();
				
			}
		});
		cerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblNewLabel = new JLabel("Hcaer una consulta:");
		lblNewLabel.setBounds(89, 69, 269, 19);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		
		
		detalle = new JTextField();
		detalle.setBounds(114, 142, 216, 50);
		contentPane.add(detalle);
		detalle.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("¿Que consulta desea hacer?");
		lblNewLabel_2.setBounds(114, 117, 229, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel_2);
		
		
		
		
		JButton btnEditar = new JButton("Subir consulta");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnEditar.setBounds(114, 203, 216, 40);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String Respuesta = detalle.getText();
            	
                if (Respuesta.isEmpty()) {
                	JOptionPane.showMessageDialog(null, "Escribir una consulta.");

                } else {
                    
                	int id = usuario.getId_user();
                	
                	
                	String detalle = "El usuario: "+usuario.getNombre()+" hizo una consulta sobre: "+vehiculo.getMarca()+": "+vehiculo.getNombre();
			        Date fecha = new Date(System.currentTimeMillis());; 
			  DLLMovimiento.agregarMovimiento(detalle, fecha);
                	
                	DLLConsulta.AgregarConsulta(Respuesta, id);;
                	dispose();
                	
                }
            }
        });
        contentPane.add(btnEditar);
		
		
		

	}
}
