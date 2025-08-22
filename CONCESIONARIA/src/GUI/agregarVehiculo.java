package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import BLL.User;
import BLL.Vehiculo;
import DLL.DLLMovimiento;
import DLL.DLLVehiculo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;

public class agregarVehiculo extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField marca, nombre, ano, precio, km, chasis, patente;
    private JComboBox<String> categoria, transmision;
    private JButton btnSeleccionarFoto;
    private byte[] fotoVehiculo;

    public void run(Vehiculo vehiculo, User usuario) {
        try {
            agregarVehiculo frame = new agregarVehiculo(vehiculo, usuario);
            frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public agregarVehiculo(Vehiculo vehiculo, User usuario) {
        setTitle("Agregar Vehículo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 360, 780); 
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(15, 20, 15, 20));
        contentPane.setLayout(null);
        setContentPane(contentPane);

   
        JButton btnVolver = new JButton("Volver");
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnVolver.setBackground(new Color(200, 0, 0));
        btnVolver.setBounds(10, 10, 90, 30);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> dispose());
        contentPane.add(btnVolver);

    
        JLabel titulo = new JLabel("Ingrese los datos del vehículo");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBounds(30, 50, 300, 30);
        contentPane.add(titulo);

      
        int labelX = 20, labelWidth = 150, labelHeight = 20;
        int fieldX = 20, fieldWidth = 300, fieldHeight = 35;
        int startY = 90, gapY = 70;


        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblMarca.setBounds(labelX, startY, labelWidth, labelHeight);
        contentPane.add(lblMarca);

        marca = new JTextField();
        marca.setBounds(fieldX, startY + 20, fieldWidth, fieldHeight);
        contentPane.add(marca);

      
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(labelX, startY + gapY, labelWidth, labelHeight);
        contentPane.add(lblNombre);

        nombre = new JTextField();
        nombre.setBounds(fieldX, startY + gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(nombre);

     
        JLabel lblCategoria = new JLabel("Categoría:");
        lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCategoria.setBounds(labelX, startY + 2*gapY, labelWidth, labelHeight);
        contentPane.add(lblCategoria);

        String[] categorias = { "Sedan", "Hatchback", "SUV", "Camioneta", "Moto", "Pick up" };
        categoria = new JComboBox<>(categorias);
        categoria.setFont(new Font("Tahoma", Font.PLAIN, 14));
        categoria.setBounds(fieldX, startY + 2*gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(categoria);

       
        JLabel lblTransmision = new JLabel("Transmisión:");
        lblTransmision.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTransmision.setBounds(labelX, startY + 3*gapY, labelWidth, labelHeight);
        contentPane.add(lblTransmision);

        String[] transmisiones = { "Manual", "Automático" };
        transmision = new JComboBox<>(transmisiones);
        transmision.setFont(new Font("Tahoma", Font.PLAIN, 14));
        transmision.setBounds(fieldX, startY + 3*gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(transmision);

     
        JLabel lblAno = new JLabel("Año:");
        lblAno.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAno.setBounds(labelX, startY + 4*gapY, labelWidth, labelHeight);
        contentPane.add(lblAno);

        ano = new JTextField();
        ano.setBounds(fieldX, startY + 4*gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(ano);


        JLabel lblPrecio = new JLabel("Precio: $");
        lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPrecio.setBounds(labelX, startY + 5*gapY, labelWidth, labelHeight);
        contentPane.add(lblPrecio);

        precio = new JTextField();
        precio.setBounds(fieldX, startY + 5*gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(precio);

        
        JLabel lblKM = new JLabel("KM:");
        lblKM.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblKM.setBounds(labelX, startY + 6*gapY, labelWidth, labelHeight);
        contentPane.add(lblKM);

        km = new JTextField();
        km.setBounds(fieldX, startY + 6*gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(km);

  
        JLabel lblChasis = new JLabel("Número de chasis:");
        lblChasis.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblChasis.setBounds(labelX, startY + 7*gapY, labelWidth, labelHeight);
        contentPane.add(lblChasis);

        chasis = new JTextField();
        chasis.setBounds(fieldX, startY + 7*gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(chasis);

  
        JLabel lblPatente = new JLabel("Patente:");
        lblPatente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPatente.setBounds(labelX, startY + 8*gapY, labelWidth, labelHeight);
        contentPane.add(lblPatente);

        patente = new JTextField();
        patente.setBounds(fieldX, startY + 8*gapY + 20, fieldWidth, fieldHeight);
        contentPane.add(patente);

        
        btnSeleccionarFoto = new JButton("Seleccionar Foto");
        btnSeleccionarFoto.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnSeleccionarFoto.setBackground(new Color(70, 130, 180));
        btnSeleccionarFoto.setForeground(Color.WHITE);
        btnSeleccionarFoto.setBounds(20, startY + 9*gapY + 10, 300, 35);
        btnSeleccionarFoto.setFocusPainted(false);
        btnSeleccionarFoto.addActionListener(e -> seleccionarFoto());
        contentPane.add(btnSeleccionarFoto);

 
        JButton btnAgregar = new JButton("Agregar vehículo");
        btnAgregar.setBackground(new Color(50, 205, 50));
        btnAgregar.setForeground(Color.BLACK);
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnAgregar.setBounds(20, startY + 10*gapY + 15, 300, 40);
        btnAgregar.setFocusPainted(false);
        btnAgregar.addActionListener(e -> agregarVehiculo(usuario));
        contentPane.add(btnAgregar);
    }

    private void seleccionarFoto() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG, JPEG)", "jpg", "jpeg", "png");
        chooser.setFileFilter(filter);

        int option = chooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            try {
                fotoVehiculo = Files.readAllBytes(selectedFile.toPath());
                JOptionPane.showMessageDialog(this, "Imagen cargada correctamente.");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al leer la imagen.");
            }
        }
    }

    private void agregarVehiculo(User usuario) {
        try {
            String marcaVehiculo = marca.getText().trim();
            String nombreVehiculo = nombre.getText().trim();
            String anoVehiculo = ano.getText().trim();
            String precioVehiculo = precio.getText().trim();
            String kmVehiculo = km.getText().trim();
            String chasisVehiculo = chasis.getText().trim();
            String patenteVehiculo = patente.getText().trim();

            if (marcaVehiculo.isEmpty() || nombreVehiculo.isEmpty() || anoVehiculo.isEmpty()
                    || precioVehiculo.isEmpty() || kmVehiculo.isEmpty() || chasisVehiculo.isEmpty()
                    || patenteVehiculo.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            }

            if (fotoVehiculo == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione una imagen del vehículo.");
                return;
            }

            int año = Integer.parseInt(anoVehiculo);
            double precioNum = Double.parseDouble(precioVehiculo);
            int kilometros = Integer.parseInt(kmVehiculo);
            int numeroChasis = Integer.parseInt(chasisVehiculo);

            DLLVehiculo.agregar_vehiculo(
                    fotoVehiculo,
                    nombreVehiculo,
                    marcaVehiculo,
                    categoria.getSelectedItem().toString(),
                    transmision.getSelectedItem().toString(),
                    año,
                    precioNum,
                    kilometros,
                    numeroChasis,
                    patenteVehiculo,
                    usuario.getId_user()
            );

            String detalle = "El usuario: " + usuario.getMail() + " agregó un " + marcaVehiculo + ": " + nombreVehiculo;
            Date fecha = new Date(System.currentTimeMillis());
            DLLMovimiento.agregarMovimiento(detalle, fecha);

            JOptionPane.showMessageDialog(this, "Vehículo agregado correctamente.");
            dispose();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Asegúrese de ingresar valores numéricos válidos en Año, Precio, KM y Chasis.");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar el vehículo.");
        }
    }
}
