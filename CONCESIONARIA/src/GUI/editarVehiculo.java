package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLMovimiento;
import DLL.DLLUser;
import DLL.DLLVehiculo;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.util.LinkedList;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class editarVehiculo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField marca;
	private JTextField nombre;
	private JTextField ano;
	private JTextField precio;
	private JTextField km;
	private JTextField chasis;
	private JTextField patente;
	
	private JButton btnSeleccionarFoto;
    private byte[] fotoVehiculo;

	public void run(Vehiculo vehiculo, User usuario) {
		try {
			editarVehiculo frame = new editarVehiculo(vehiculo, usuario);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public editarVehiculo(Vehiculo vehiculo, User usuario) {

		LinkedList<User> listaUsuarios = DLLUser.mostrarUsuario();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 1070);
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

		
		JLabel titulo = new JLabel("Ingrese los datos del vehículo");
		titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
		titulo.setBounds(45, 50, 270, 30);
		contentPane.add(titulo);

		// MARCA
		JLabel lbl1 = new JLabel("Marca:");
		lbl1.setBounds(78, 90, 188, 14);
		contentPane.add(lbl1);
		marca = new JTextField(vehiculo.getMarca());
		marca.setBounds(78, 109, 188, 46);
		contentPane.add(marca);
		marca.setColumns(10);

		// NOMBRE
		JLabel lbl2 = new JLabel("Nombre:");
		lbl2.setBounds(78, 173, 188, 14);
		contentPane.add(lbl2);
		nombre = new JTextField(vehiculo.getNombre());
		nombre.setColumns(10);
		nombre.setBounds(78, 192, 188, 46);
		contentPane.add(nombre);

		// CATEGORIA
		String[] categorias = { "Sedan", "Hatchback", "SUV", "Camioneta", "Moto", "Pick up" };
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(78, 255, 188, 14);
		contentPane.add(lblCategoria);
		JComboBox categoria = new JComboBox(categorias);
		categoria.setBackground(new Color(255, 255, 255));
		categoria.setBounds(78, 280, 188, 46);
		contentPane.add(categoria);

		// TRANSMISION
		String[] transmisiones = { "Manual", "Automatico" };
		JLabel lblTransmision = new JLabel("Transmisión:");
		lblTransmision.setBounds(78, 340, 188, 14);
		contentPane.add(lblTransmision);
		JComboBox Transmision = new JComboBox(transmisiones);
		Transmision.setBackground(new Color(255, 255, 255));
		Transmision.setBounds(78, 365, 188, 46);
		contentPane.add(Transmision);

		// AÑO DEL VEHICULO
		JLabel lbl4 = new JLabel("Año:");
		lbl4.setBounds(78, 425, 188, 14);
		contentPane.add(lbl4);
		ano = new JTextField(vehiculo.getAno());
		ano.setColumns(10);
		ano.setBounds(78, 450, 188, 46);
		contentPane.add(ano);

		// PRECIO
		
		JLabel lbl5 = new JLabel("Precio: $");
		lbl5.setBounds(78, 507, 188, 14);
		contentPane.add(lbl5);
		precio = new JTextField();
		precio.setColumns(10);
		precio.setBounds(78, 532, 188, 46);
		contentPane.add(precio);

		// KILOMETRAJE
		JLabel lbl6 = new JLabel("KM:");
		lbl6.setBounds(78, 592, 188, 14);
		contentPane.add(lbl6);
		km = new JTextField(vehiculo.getKm());
		km.setColumns(10);
		km.setBounds(78, 617, 188, 46);
		contentPane.add(km);

		// Numero de chasis
		JLabel lbl7 = new JLabel("Numero de chasis:");
		lbl7.setBounds(78, 678, 188, 14);
		contentPane.add(lbl7);
		chasis = new JTextField(vehiculo.getnChasis());
		chasis.setColumns(10);
		chasis.setBounds(78, 703, 188, 46);
		contentPane.add(chasis);

		// PATENTE
		JLabel lbl8 = new JLabel("Patente:");
		lbl8.setBounds(78, 763, 188, 14);
		contentPane.add(lbl8);
		patente = new JTextField(vehiculo.getPatente());
		patente.setColumns(10);
		patente.setBounds(78, 788, 188, 46);
		contentPane.add(patente);

		// QUIEN PUBLICO
		JLabel lbl9 = new JLabel("Quien publico:");
		lbl9.setBounds(78, 850, 188, 14);
		contentPane.add(lbl9);
		JComboBox<User> comboBoxPublico = new JComboBox<>();
		for (User user : listaUsuarios) {
			comboBoxPublico.addItem(user);
		}
		comboBoxPublico.setBounds(78, 875, 188, 46);
		contentPane.add(comboBoxPublico);
		
		
		//SELECCIONAR FOTO:
		btnSeleccionarFoto = new JButton("Seleccionar Foto");
        btnSeleccionarFoto.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSeleccionarFoto.setBounds(78, 930, 188, 30);
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
                        fotoVehiculo = Files.readAllBytes(selectedFile.toPath());
                        JOptionPane.showMessageDialog(null, "Imagen cargada correctamente.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error al leer la imagen.");
                    }
                }
            }
        });
		
		

	
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setBackground(new Color(173, 255, 47));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(78, 970, 188, 50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String marcaVehiculo = marca.getText();
		        String nombreVehiculo = nombre.getText();
		        String anoVehiculo = ano.getText();
		        String precioVehiculo = precio.getText();
		        String kmVehiculo = km.getText().trim();
		        String chasisVehiculo = chasis.getText();
		        String patenteVehiculo = patente.getText();
				 User IDpublicador = (User) comboBoxPublico.getSelectedItem();

			     int id_user = IDpublicador.getId_user(); 
				
			     
		        
		        String detalle = "Vehiculo "+vehiculo.getIdVehiculo() + ": "+ vehiculo.getMarca()+" " +vehiculo.getNombre() +" fue modificado correctamente a: "+marca+nombre;
		        Date fecha = new Date(System.currentTimeMillis());; 
		        
		       
		        
		        
		        
		        
		        
		        if (marcaVehiculo.isEmpty() || nombreVehiculo.isEmpty() || anoVehiculo.isEmpty()
		                || precioVehiculo.isEmpty() || kmVehiculo.isEmpty() || chasisVehiculo.isEmpty()
		                || patenteVehiculo.isEmpty()) {

		            JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
		            return;

		        } else {
		            if (fotoVehiculo == null) {
		                JOptionPane.showMessageDialog(null, "Por favor, seleccione una imagen del vehículo.");
		                return;

		            } else {
		            	Vehiculo car = new Vehiculo(fotoVehiculo, nombre.getText(), marca.getText(),
				    		    categoria.getSelectedItem().toString(),Transmision.getSelectedItem().toString(),
				    		    Integer.parseInt(ano.getText()), Double.parseDouble(precio.getText()),
				    		    Integer.parseInt(km.getText()), Integer.parseInt(chasis.getText()),
				    		    patente.getText(),vehiculo.getIdVehiculo(),id_user
				    		);


					  DLLVehiculo.editar_veiculo(car);
					  DLLMovimiento.agregarMovimiento(detalle, fecha);
		            	dispose();
		            }
		          
			
		   }
		}
		});
		contentPane.add(btnNewButton);
	}
}

