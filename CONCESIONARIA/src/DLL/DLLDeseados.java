package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import BLL.Concesionaria;
import BLL.Deseados;
import BLL.Vehiculo;

public class DLLDeseados {

	
	private static Connection con = Conexion.getInstance().getConnection();

	public static LinkedList<Vehiculo> VerFavoritos(int id ) {
		LinkedList<Vehiculo>  favoritos = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement(
            		"SELECT id_carrito, id_vehiculo FROM `carrito` WHERE id_user = ?"

            );
            stmt.setInt(1, id);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id_carrito = rs.getInt("id_carrito");
                int id_vehiculo = rs.getInt("id_vehiculo");     
                favoritos.add(DLLVehiculo.VerVehiculoPorId(id_vehiculo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return favoritos; 
    }
	
	
	

	 public static LinkedList<Deseados> mostrarDeseados() {
	        LinkedList<Deseados> listaDeseados = new LinkedList<>();

	        try {
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM carrito");
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Deseados d = new Deseados(
	                    rs.getInt("id_carrito"),
	                    rs.getInt("id_vehiculo"),
	                    rs.getInt("id_user")
	                );
	                listaDeseados.add(d);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return listaDeseados;
	    }

	 
	 
	 
	 
	 public static void agregar_deseado(int idUser, int idVehiculo) {
			try {
		
				
				Deseados vehiculo = new Deseados(idUser, idVehiculo);
     			
				
	         PreparedStatement statement = con.prepareStatement(
	        		 "INSERT INTO carrito (id_vehiculo, id_user) VALUES ( ?, ?)"

	         );
	         statement.setInt(1, idVehiculo);
	        statement.setInt(2, idUser);
	         
	         
	         int filas = statement.executeUpdate();
	         if (filas > 0) {
	        	 JOptionPane.showMessageDialog(null, "vehiculo agregado a deseados");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		}




	
 
	
	
	
}
