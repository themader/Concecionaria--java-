package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.CuentaD;
import BLL.Deseados;
import BLL.User;
import BLL.Vehiculo;

public class DLLCuentaD {
	
	private static Connection con = Conexion.getInstance().getConnection();
	
	
	public static LinkedList<CuentaD> obtenerCuentasPorIdUser(int idUser) {
	    LinkedList<CuentaD> cuentas = new LinkedList<>();
	    try {
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT id_cuenta, id_user, sueldo, caja FROM dinero_cliente WHERE id_user = ?"
	        );
	        stmt.setInt(1, idUser);

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id_cuenta = rs.getInt("id_cuenta");
	            double sueldo = rs.getDouble("sueldo");
	            double caja = rs.getDouble("caja");

	            CuentaD cuenta = new CuentaD(id_cuenta, idUser, sueldo, caja);
	            cuentas.add(cuenta);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return cuentas;
	}


	
	
	
	public static boolean modificarCaja(CuentaD user) {
	    try {
	        PreparedStatement stmt = con.prepareStatement(
	            "UPDATE dinero_cliente SET id_user = ?, sueldo = ?, caja = ? WHERE id_cuenta = ?"
	        );
	        
	        stmt.setInt(1, user.getId_user());     
	        stmt.setDouble(2, user.getSueldo());   
	        stmt.setDouble(3, user.getCaja());    
	        stmt.setInt(4, user.getId_cuenta());  

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

	
	
	
	
	 public static void agregar_cuenta(int id_user, double sueldo, double ahorro) {
			try {
		
  			
				
	         PreparedStatement statement = con.prepareStatement(
	        		 "INSERT INTO dinero_cliente (id_user, sueldo, caja) VALUES ( ?, ?, ?)"

	         );
	        statement.setInt(1, id_user);
	         statement.setDouble(2, sueldo);
	         statement.setDouble(3, ahorro);
	         
	         int filas = statement.executeUpdate();
	         if (filas > 0) {
	        	 JOptionPane.showMessageDialog(null, "Cuenta agregada");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		}

}
