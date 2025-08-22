package GUI;

import BLL.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

public class verCuenta extends JFrame {

	
	private JPanel contentPane;

	
	public void run(User usuario) {
		try {
			verCuenta frame = new verCuenta(usuario);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public verCuenta(User usuario) {
		setTitle("Cuenta de Usuaro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 884, 502);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

	
		JLabel lblImagenUsuario = new JLabel();
		lblImagenUsuario.setBackground(UIManager.getColor("CheckBox.foreground"));
		lblImagenUsuario.setIcon(new ImageIcon(User.getImg()));
		lblImagenUsuario.setBounds(10, 94, 310, 249);
		contentPane.add(lblImagenUsuario);

	
		JButton btnVolver = new JButton("X");
		btnVolver.setForeground(new Color(240, 248, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVolver.setBackground(Color.RED);
		btnVolver.setBounds(10, 11, 139, 70);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("Nombre: " +usuario.getNombre());
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(330, 128, 251, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblMail = new JLabel("Mail: " +usuario.getMail());
		lblMail.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMail.setBounds(330, 197, 251, 58);
		contentPane.add(lblMail);
		
		JLabel lblContrasea = new JLabel("Contraseña: "+usuario.getContrasena());
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContrasea.setBounds(330, 285, 251, 58);
		contentPane.add(lblContrasea);
	}
}
