package DLL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import BLL.Movimiento;
import BLL.Personal;

public class DLLMovimiento {

	private static Connection con = Conexion.getInstance().getConnection();
	
	public static void agregarMovimiento(String detalle, Date fecha) {
		try {
			
			
	        
	        Movimiento movimiento = new Movimiento( detalle, fecha);
			Personal.getMovimientos().add(movimiento);
			
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO movimiento (detalle, fecha) VALUES (?, ?)"
            );
            statement.setString(1, detalle);
            statement.setDate(2, fecha);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Movimiento agregado correctamente.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	
	
	 public static LinkedList<Movimiento> mostrarMovimiento() {
	        LinkedList<Movimiento> movimientos = new LinkedList<>();
	        try {
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM movimiento");
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	            	Movimiento u = new Movimiento(
	            		    rs.getInt("id"),
	            		    rs.getString("detalle"),
	            		    rs.getDate("fecha")
	            		);

	                movimientos.add(u);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return movimientos;
	    }
	
	
}
