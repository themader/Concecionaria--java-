package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import BLL.Cliente;
import BLL.Concesionaria;
import BLL.Vehiculo;

public class DLLVehiculo {
	
	private static Connection con = Conexion.getInstance().getConnection();


	
	
	
	
	
	
	
	
	  public static LinkedList<Vehiculo> mostrarVehiculo() {
	        LinkedList<Vehiculo> vehiculos = new LinkedList<>();
	        try {
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vehiculo");
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                Vehiculo v = new Vehiculo(
	                    rs.getBytes("img"),
	                    rs.getString("nombre"),
	                    rs.getString("marca"),
	                    rs.getString("categoria"),
	                    rs.getString("transmision"),
	                    rs.getInt("ano"),
	                    rs.getDouble("precio"),
	                    rs.getInt("km"),
	                    rs.getInt("numberChasis"),
	                    rs.getString("patente"),
	                    rs.getInt("id_vehiculo"),
	                    rs.getInt("id_quienPublico"),
	                    rs.getInt("id_quienVendio"),
	                    rs.getInt("id_quienCompro")
	                );

	                vehiculos.add(v);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return vehiculos;
	    }
	  
	  public static Vehiculo VerVehiculoPorId(int id) {
		    Vehiculo vehiculo = null;
		    try {
		        
		        PreparedStatement stmt = con.prepareStatement("SELECT * FROM vehiculo WHERE id_vehiculo = ?");
		        stmt.setInt(1, id);  
		        ResultSet rs = stmt.executeQuery();

		        if (rs.next()) {
		            Vehiculo v = new Vehiculo(
		                rs.getBytes("img"),
		                rs.getString("nombre"),
		                rs.getString("marca"),
		                rs.getString("categoria"),
		                rs.getString("transmision"),
		                rs.getInt("ano"),
		                rs.getDouble("precio"),
		                rs.getInt("km"),
		                rs.getInt("numberChasis"),
		                rs.getString("patente"),
		                rs.getInt("id_vehiculo"),
		                rs.getInt("id_quienPublico"),
		                rs.getInt("id_quienVendio"),
		                rs.getInt("id_quienCompro")
		            );
		            vehiculo = v;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return vehiculo;
		}

	  
	  
	  
	  
	  
	  
	
	  public static boolean editar_veiculo(Vehiculo vehiculoSeleccionado) {
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "UPDATE vehiculo SET img = ?, nombre = ?, marca = ?, categoria = ?, transmision = ?, ano = ?, precio = ?, km = ?, numberChasis = ?, patente = ?, id_quienPublico = ? WHERE id_vehiculo = ?"
	            );

	            stmt.setBytes(1, vehiculoSeleccionado.getImg());
	            stmt.setString(2, vehiculoSeleccionado.getNombre());
	            stmt.setString(3, vehiculoSeleccionado.getMarca());
	            stmt.setString(4, vehiculoSeleccionado.getCategoria());
	            stmt.setString(5, vehiculoSeleccionado.getTransmision());
	            stmt.setInt(6, vehiculoSeleccionado.getAno());
	            stmt.setDouble(7, vehiculoSeleccionado.getPrecio());
	            stmt.setInt(8, vehiculoSeleccionado.getKm());
	            stmt.setInt(9, vehiculoSeleccionado.getnChasis());
	            stmt.setString(10, vehiculoSeleccionado.getPatente());
	            stmt.setInt(11, vehiculoSeleccionado.getIdQuienPublico());
	            stmt.setInt(12, vehiculoSeleccionado.getIdVehiculo());

	            int filas = stmt.executeUpdate();
	            if (filas > 0) {
	                System.out.println("Vehículo editado correctamente.");
	                return true;
	            }

	        } catch (MySQLIntegrityConstraintViolationException e) {
	            JOptionPane.showMessageDialog(null, "Error de integridad en los datos (por ejemplo, claves foráneas no válidas).");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	  
	  
	  
	  public static boolean editar_venta(Vehiculo vehiculoSeleccionado) {
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "UPDATE vehiculo SET  id_quienVendio = ?, id_quienCompro = ? WHERE id_vehiculo = ?"
	            );
	            
	         
	            
	            stmt.setInt(1, vehiculoSeleccionado.getIdQuienVendio());
	            stmt.setInt(2, vehiculoSeleccionado.getIdQuienCompro());
	            stmt.setInt(3, vehiculoSeleccionado.getIdVehiculo());

	            int filas = stmt.executeUpdate();
	            if (filas > 0) {
	            	 JOptionPane.showMessageDialog(null, "Venta editado correctamente.");
	                return true;
	            }

	        } catch (MySQLIntegrityConstraintViolationException e) {
	            JOptionPane.showMessageDialog(null, "Error de integridad en los datos.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	  
	  
	  
	  public static boolean compraCliente(Vehiculo vehiculoSeleccionado) {
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "UPDATE vehiculo SET  id_quienCompro = ? WHERE id_vehiculo = ?"
	            );
	            
	            stmt.setInt(1, vehiculoSeleccionado.getIdQuienCompro());
	            stmt.setInt(2, vehiculoSeleccionado.getIdVehiculo());

	            int filas = stmt.executeUpdate();
	            if (filas > 0) {
	            	 JOptionPane.showMessageDialog(null, "Compra realizada.");
	                return true;
	            }

	        } catch (MySQLIntegrityConstraintViolationException e) {
	            JOptionPane.showMessageDialog(null, "Error de integridad en los datos.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	
	  public static boolean venta(Vehiculo vehiculoSeleccionado) {
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "UPDATE vehiculo SET  id_quienVendio = ?, id_quienCompro = ? WHERE id_vehiculo = ?"
	            );
	            
	         
	            
	            stmt.setInt(1, vehiculoSeleccionado.getIdQuienVendio());
	            stmt.setInt(2, vehiculoSeleccionado.getIdQuienCompro());
	            stmt.setInt(3, vehiculoSeleccionado.getIdVehiculo());

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
	  
	  
	  
	  public static boolean venta_cliente(Vehiculo vehiculoSeleccionado) {
	        try {
	            PreparedStatement stmt = con.prepareStatement(
	                "UPDATE vehiculo SET  id_quienVendio = ?, id_quienCompro = ? WHERE id_vehiculo = ?"
	            );
	            
	         
	            
	            stmt.setInt(1, vehiculoSeleccionado.getIdQuienVendio());
	            stmt.setInt(2, vehiculoSeleccionado.getIdQuienCompro());
	            stmt.setInt(3, vehiculoSeleccionado.getIdVehiculo());

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
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	 	public static void agregar_vehiculo(byte[] img, String nombre, String marca, String categoria, String tranmision, int ano, double precio, int km, 
	 			int nChasis, String patente, int idQuienPublico) {
			try {
		
	 
		    Vehiculo vehiculo = new Vehiculo( img, nombre, marca, categoria, tranmision, ano, precio, km, nChasis, patente, idQuienPublico);
			Concesionaria.getLista_vehiculo().add(vehiculo);
				
	         PreparedStatement statement = con.prepareStatement(
	        		 "INSERT INTO vehiculo (img, nombre, marca, categoria, transmision, ano, precio, km, numberChasis, patente, id_quienPublico) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

	         );
	        statement.setBytes(1, img);
	         
	         statement.setString(2, nombre);
	         statement.setString(3, marca);
	         statement.setString(4, categoria);
	         statement.setString(5, tranmision);
	         statement.setInt(6, ano);
	         statement.setDouble(7, precio);
	         statement.setInt(8, km);
	         statement.setInt(9, nChasis);
	         statement.setString(10, patente);
	         statement.setInt(11, idQuienPublico);
	         
	         int filas = statement.executeUpdate();
	         if (filas > 0) {
	        	 JOptionPane.showMessageDialog(null, "vehiculo agregado al catalogo correctamente");
	         }
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		}
	
}
