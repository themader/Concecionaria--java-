package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BLL.Personal;
import BLL.User;
import BLL.Vehiculo;
import DLL.DLLMovimiento;
import DLL.DLLUser;
import DLL.DLLVehiculo;

public class editarUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;
	private JTextField mail;
	private JTextField contrasena;
	private byte[] fotoUser;
	private JButton btnSeleccionarFoto;
	
	public void run( User usuario, User user) {
		try {
			editarUser frame = new editarUser( usuario, user);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public editarUser( User usuario, User user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LinkedList<User> listaUsuarios = DLLUser.mostrarUsuario();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setForeground(new Color(240, 248, 255));
		btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnVolver.setBackground(Color.RED);
		btnVolver.setBounds(10, 8, 115, 30);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnVolver);

		
		JLabel titulo = new JLabel("Ingrese los nuevos datos de: "
				+ user.getNombre());
		titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
		titulo.setBounds(45, 50, 270, 30);
		contentPane.add(titulo);

		// MARCA
		JLabel lbl1 = new JLabel("Nombre:");
		lbl1.setBounds(78, 90, 188, 14);
		contentPane.add(lbl1);
		nombre = new JTextField(user.getNombre());
		nombre.setBounds(78, 109, 188, 46);
		contentPane.add(nombre);
		nombre.setColumns(10);

		// NOMBRE
		JLabel lbl2 = new JLabel("Mail:");
		lbl2.setBounds(78, 173, 188, 14);
		contentPane.add(lbl2);
		mail = new JTextField(user.getMail());
		mail.setColumns(10);
		mail.setBounds(78, 192, 188, 46);
		contentPane.add(mail);


		String[] roles = { "Jefe", "Cliente" };
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(78, 260, 188, 14);
		contentPane.add(lblRol);
		JComboBox rol = new JComboBox(roles);
		rol.setBackground(new Color(255, 255, 255));
		rol.setBounds(78, 285, 188, 46);
		contentPane.add(rol);
		
		
		// AÑO DEL VEHICULO
		JLabel lbl4 = new JLabel("Contraseña:");
		lbl4.setBounds(78, 342, 188, 14);
		contentPane.add(lbl4);
		contrasena = new JTextField(user.getContrasena());
		contrasena.setColumns(10);
		contrasena.setBounds(78, 367, 188, 46);
		contentPane.add(contrasena);

		
		
		
		//SELECCIONAR FOTO:
		btnSeleccionarFoto = new JButton("Seleccionar Foto");
        btnSeleccionarFoto.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeleccionarFoto.setBounds(78, 424, 188, 30);
        contentPane.add(btnSeleccionarFoto);
		btnSeleccionarFoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();

                
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Imágenes (JPG, PNG, JPEG)", "jpg", "jpeg", "png");
                chooser.setFileFilter(filter);

                int option = chooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();

                
                    String nombreArchivo = selectedFile.getName().toLowerCase();
                    if (!(nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".jpeg") || nombreArchivo.endsWith(".png"))) {
                        JOptionPane.showMessageDialog(null, "Solo se permiten archivos JPG, JPEG o PNG.");
                        return;
                    }

                    try {
                        fotoUser = Files.readAllBytes(selectedFile.toPath());
                        JOptionPane.showMessageDialog(null, "Imagen cargada correctamente.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al leer la imagen.");
                    }
                }
            }
        });
		
		

		// BOTÓN MODIFICAR
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setBackground(new Color(173, 255, 47));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(78, 465, 188, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombreUsuario = nombre.getText().trim();
		        String mailUsuario = mail.getText().trim();
		        String contrasenaUsuario = contrasena.getText().trim();

				String rolSeleccionado = rol.getSelectedItem().toString();
				
				
			
		        
		        String detalle = "El usuario: "+user.id_user + ": "+ user.getNombre()+" fue modificado correctamente por: "+usuario.getMail();
		        Date fecha = new Date(System.currentTimeMillis());; 
		       
		        
		        
		        
		        
		        if (nombreUsuario.isEmpty() || mailUsuario.isEmpty() || contrasenaUsuario.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
		            return;
		        }else{
		        	   if (fotoUser == null) {
				            JOptionPane.showMessageDialog(null, "Por favor, seleccione una imagen del usuario.");
				            return;
				        }else{
				        	
				        	 User usuarioM = new User(fotoUser, nombre.getText(), mail.getText(), contrasena.getText(), rolSeleccionado, user.getId_user());


							  DLLUser.modificar_user(usuarioM);
							  
						        DLLMovimiento.agregarMovimiento(detalle, fecha);
				        	 dispose();
				        }
		        	
		           
		        }

		        
		       
			}
		});
		contentPane.add(btnNewButton);
	}
}