package BLL;

import DLL.Conexion;
import DLL.DLLUser;
import repository.Validaciones;
import repository.Encriptador;

import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class User<T extends User> implements Encriptador, Validaciones {

    public int id_user;
    private static byte[] img;  
    public String nombre;
    public String mail;
    public String contrasena;
    public String rol;

    protected static Connection con = Conexion.getInstance().getConnection();

    
    public User() {
        
    }

   
    public User(int id_user, byte[] img, String nombre, String mail, String contrasena, String rol) {
        this.id_user = id_user;
        this.img = img;
        this.nombre = nombre;
        this.mail = mail;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    
    public User(int id_user, String nombre, String mail, String rol) {
        this.id_user = id_user;
        this.nombre = nombre;
        this.mail = mail;
        this.rol = rol;
    }

    
    public User(byte[] img, String nombre, String mail, String contrasena, String rol) {
        this.img = img;
        this.nombre = nombre;
        this.mail = mail;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    // Getters y setters

    public User(byte[] img, String nombre, String mail, String contrasena, String rol,
			int id_user) {
		// TODO Auto-generated constructor stub
    	this.id_user = id_user;
        this.img = img;
        this.nombre = nombre;
        this.mail = mail;
        this.contrasena = contrasena;
        this.rol = rol;
	}


	public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public static byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return " nombre=" + nombre + ", mail=" + mail ;
    }


    public void inicionSesion(User logeado, String mail, String contrasena, Concesionaria miConcesionaria) {
    	if (this.mail.equals(mail) && this.contrasena.equals(contrasena)) {

            JOptionPane.showMessageDialog(null, "Se logea correctamente");
            
        } else {
            JOptionPane.showMessageDialog(null, "Error al loguearse");
        }
    }

    public static User verificarCuenta(Concesionaria miConcesionaria, String mail, String contrasena) {
        ImageIcon iconoUsuario = new ImageIcon("src/img/usuario.png");   
        

        User usuario = DLLUser.login(mail, contrasena);

        if (usuario != null) {
            if (usuario instanceof Cliente) {
            	
  

                Cliente.accederMenu((Cliente) usuario, miConcesionaria);
            } else if (usuario instanceof Personal) {
            	((Personal) usuario).inicionSesion(usuario, usuario.getMail(), usuario.getContrasena(), miConcesionaria);
            } else {
                JOptionPane.showMessageDialog(null, "Bienvenido Usuario: " + usuario.getNombre());
            }
            return usuario;
        } else {
            JOptionPane.showMessageDialog(null, "Mail o contraseña incorrectos.");
            return null;
        }
    }
	


    

    public void accederMenu() {
        
    }

    public static void crearCuenta() {
        
    }
    
    
  
    


   

}
