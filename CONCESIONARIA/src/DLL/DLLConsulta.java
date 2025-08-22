package DLL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Cliente;
import BLL.Concesionaria;
import BLL.Consulta;
import BLL.User;
import BLL.Vehiculo;
public class DLLConsulta {

	private static Connection con = Conexion.getInstance().getConnection();
	
	
	public static LinkedList<Consulta> mostrarConsultas() {
        LinkedList<Consulta> consultas = new LinkedList<>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM consultas");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta v = new Consulta(
                	rs.getInt("id_consulta"),
                	rs.getString("detalle"),
                    rs.getString("respuesta"),
                    rs.getInt("id_quienConsulto")
                );

                consultas.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultas;
    }
	
	
	
	
	public static LinkedList<Consulta> VerConsultasPorIdUsuario(int id_usuario) {
	    LinkedList<Consulta> consultas = new LinkedList<>();
	    try {
	        PreparedStatement stmt = con.prepareStatement("SELECT * FROM consultas WHERE id_quienConsulto = ?");
	        stmt.setInt(1, id_usuario);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Consulta c = new Consulta(
	                rs.getInt("id_consulta"),
	                rs.getInt("id_quienConsulto"),
	                rs.getString("detalle"),
	                rs.getString("respuesta")
	            );
	            consultas.add(c);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return consultas;
	}

	
	
	
	
	public static boolean cargarRespuesta(int id_consulta, String detalle , String respuesta) {
        try {
            PreparedStatement stmt = con.prepareStatement(
            		"UPDATE consultas SET respuesta = ?, detalle = ? WHERE id_consulta = ?"
            );
            
            stmt.setString(1, respuesta);     
            stmt.setString(2, detalle);       
            stmt.setInt(3, id_consulta); 
            

            int filas = stmt.executeUpdate();
            if (filas > 0) {
            	 JOptionPane.showMessageDialog(null, "Respuesta subida correctamente");
                return true;
            }

        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Error de integridad en los datos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }




	public static void AgregarConsulta(String detalle, int id_user) {
		// TODO Auto-generated method stub
		try {
			
			 
	        Consulta consulta = new Consulta(detalle, id_user);
			Cliente.getConsulta().add(consulta);
			
         PreparedStatement statement = con.prepareStatement(
             "INSERT INTO consultas (detalle, id_quienConsulto ) VALUES ( ?, ?)"
         );
        statement.setString(1, detalle);
         
         statement.setInt(2, id_user);

         int filas = statement.executeUpdate();
         if (filas > 0) {
        	 JOptionPane.showMessageDialog(null, "Consulta subida correctamente");
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
	
	}}
