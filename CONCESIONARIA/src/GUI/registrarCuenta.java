package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import DLL.DLLUser;
import BLL.User;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.awt.Color;

public class registrarCuenta extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JPanel contentPane;
    private JTextField nombre;
    private JTextField email;
    private JTextField contrasena;
    private JButton btnNewButtonCrearCuenta;
    private JButton btnNewButton_1;
    private JButton btnSeleccionarFoto;
    private byte[] fotoPerfil;
    
    /**
     * Launch the application.
     */
    public void run(User usuario) {
        try {
            registrarCuenta frame = new registrarCuenta();
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public registrarCuenta() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 750);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        
        nombre = new JTextField();
        nombre.setBounds(50, 133, 350, 40);
        contentPane.add(nombre);
        nombre.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNombre.setBounds(50, 100, 350, 30);
        contentPane.add(lblNombre);

       
        email = new JTextField();
        email.setBounds(50, 210, 350, 40);
        contentPane.add(email);
        email.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblEmail.setBounds(50, 180, 350, 30);
        contentPane.add(lblEmail);

       
        contrasena = new JTextField();
        contrasena.setColumns(10);
        contrasena.setBounds(50, 290, 350, 40);
        contentPane.add(contrasena);

        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblContrasena.setBounds(50, 260, 350, 30);
        contentPane.add(lblContrasena);

       
        btnSeleccionarFoto = new JButton("Seleccionar Foto");
        btnSeleccionarFoto.setFont(new Font("Tahoma", Font.PLAIN, 13));
        btnSeleccionarFoto.setBounds(82, 374, 278, 30);
        contentPane.add(btnSeleccionarFoto);
        
    
        btnNewButtonCrearCuenta = new JButton("Crear cuenta");
        btnNewButtonCrearCuenta.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnNewButtonCrearCuenta.setBounds(82, 415, 278, 67);
        contentPane.add(btnNewButtonCrearCuenta);
        
      
        btnNewButton_1 = new JButton("X");
        btnNewButton_1.setBackground(new Color(255, 0, 0));
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 25));
        btnNewButton_1.setBounds(0, 11, 101, 54);
        contentPane.add(btnNewButton_1);

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
                        fotoPerfil = Files.readAllBytes(selectedFile.toPath());
                        JOptionPane.showMessageDialog(null, "Imagen cargada correctamente.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al leer la imagen.");
                    }
                }
            }
        });

       
        btnNewButtonCrearCuenta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = nombre.getText();
                String emailUsuario = email.getText();
                String contrasenaUsuario = contrasena.getText();
                String rol = "Cliente";

              
                if (nombreUsuario.isEmpty() || emailUsuario.isEmpty() || contrasenaUsuario.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }else {
                	if (fotoPerfil == null) {
                        JOptionPane.showMessageDialog(null, "Por favor, seleccione una foto de perfil.");
                        return;
                    }else {
                	 BLL.Cliente.crearCuenta(fotoPerfil, nombreUsuario, emailUsuario, contrasenaUsuario, rol);

                     dispose();
                }
                }
                
               
            }
        });
    }
}
