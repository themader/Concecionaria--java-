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
import DLL.DLLConsulta;
import DLL.DLLMovimiento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class ResponderConsulta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField respuesta;

	/**
	 * Launch the application.
	 */

			public void run(User usuario, Consulta consulta) {
				try {
					ResponderConsulta frame = new ResponderConsulta(usuario, consulta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


	/**
	 * Create the frame.
	 */
	public ResponderConsulta(User usuario, Consulta consulta) {
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
		
		JLabel lblNewLabel = new JLabel("Responder la consulta:");
		lblNewLabel.setBounds(89, 69, 269, 19);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(consulta.getDetalle());
		lblNewLabel_1.setBounds(10, 118, 414, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 8));
		contentPane.add(lblNewLabel_1);
		
		respuesta = new JTextField();
		respuesta.setBounds(114, 176, 216, 50);
		contentPane.add(respuesta);
		respuesta.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Respuestas");
		lblNewLabel_2.setBounds(114, 151, 97, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Subir respuesta");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(114, 180, 216, 38);
		contentPane.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				dispose();
			}
		});
		
		
		
		JButton btnEditar = new JButton("Subir respuesta");
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnEditar.setBounds(114, 237, 216, 40);
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String Respuesta = respuesta.getText();
            	
                if (Respuesta.isEmpty()) {
                	JOptionPane.showMessageDialog(null, "Escribir una respuesta.");

                } else {
                    
                	String detalle = "El usuario: "+usuario.getNombre()+" Le respondio al usuario con id: "+consulta.getId_cliente();
			        Date fecha = new Date(System.currentTimeMillis());; 
			  DLLMovimiento.agregarMovimiento(detalle, fecha);
                	DLLConsulta.cargarRespuesta(consulta.getId_consulta(), consulta.getDetalle(), Respuesta);
                	dispose();
                	
                }
            }
        });
        contentPane.add(btnEditar);
		
		
		

	}
}
