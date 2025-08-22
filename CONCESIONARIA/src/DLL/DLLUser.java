package DLL;
import BLL.User;
import BLL.Vehiculo;
import BLL.Cliente;
import BLL.Concesionaria;
import BLL.Personal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;



public class DLLUser {
		
	private static Connection con = Conexion.getInstance().getConnection();
	
	public static User login(String mail, String contrasena) {
		
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT * FROM user WHERE mail = ? AND contrasena = ?"
            );
            stmt.setString(1, mail);
            stmt.setString(2, contrasena);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id_user = rs.getInt("id_user");
                byte[] img = rs.getBytes("img");     
                String nombre = rs.getString("nombre");
                String rol = rs.getString("rol");


                switch (rol) {
                    case "Jefe":
                        return new Personal(id_user, img, nombre, mail, contrasena, rol);

                    case "Cliente":
                        // El constructor Cliente parece tener más parámetros, ajusta si es necesario
                        return new Cliente(id_user, img, nombre, mail, contrasena, rol);
                    default:
                        return new User(id_user, img, nombre, mail, contrasena, rol);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // No se encontró el usuario
    }
	
	
	 // public static LinkedList<Personal> mostrarPersonal() {
    // LinkedList<Personal> personal = new LinkedList<>();
    // try {
    //     PreparedStatement stmt = con.prepareStatement("SELECT * FROM user");
  //       ResultSet rs = stmt.executeQuery();

        // while (rs.next()) {
          //   Personal u = new Personal(
            //     rs.getInt("id_user"),
          //       rs.getString("img"),
        //         rs.getString("nombre"),
     ///            rs.getString("mail"),
   //              rs.getString("contrasena"),
 //                rs.getString("rol")
//              );
//
      //       personal.add(u);
    //     }
  //   } catch (Exception e) {
      //   e.printStackTrace();
    // }
  //   return personal;
// }
	  
 
 
 
 
 
	public static LinkedList<User> mostrarUsuario() {
     LinkedList<User> usuarios = new LinkedList<>();
     try {
         PreparedStatement stmt = con.prepareStatement("SELECT * FROM User");
         ResultSet rs = stmt.executeQuery();

         while (rs.next()) {
             User u = new User(
                 rs.getInt("id_user"),
                 rs.getBytes("img"),
                 rs.getString("nombre"),
                 rs.getString("mail"),
                 rs.getString("contrasena"),
                 rs.getString("rol")
             );
             usuarios.add(u);
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
     return usuarios;
 }
 
 
 
 	public static void crearCuenta(byte[] img, String nombre, String mail, String rol, String contrasena) {
		try {
	
 
	        Cliente cliente = new Cliente( img, nombre, mail, contrasena, rol);
			Concesionaria.getUsuario().add(cliente);
			
         PreparedStatement statement = con.prepareStatement(
             "INSERT INTO user (img, nombre, mail, rol, contrasena) VALUES ( ?, ?, ?, ?, ?)"
         );
        statement.setBytes(1, img);
         
         statement.setString(2, nombre);
         statement.setString(3, mail);
         statement.setString(4, rol);
         statement.setString(5, contrasena);

         int filas = statement.executeUpdate();
         if (filas > 0) {
        	 JOptionPane.showMessageDialog(null, "Usuario creado correctamente");
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
	}
 	
 	
 	
 	
 	
 	public static boolean modificar_user(User user) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE user SET img = ?, nombre = ?, mail = ?, contrasena = ?, rol = ? WHERE id_user = ?"
            );
            
            stmt.setBytes(1, user.getImg()); 
            stmt.setString(2, user.getNombre());
            stmt.setString(3, user.getMail());
            stmt.setString(4, user.getContrasena());
            stmt.setString(5, user.getRol());
            stmt.setInt(6, user.getId_user());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
            	 JOptionPane.showMessageDialog(null, "Venta realizada");
                return true;
            }

        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Error de integridad en los datos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
  
	
	
}
