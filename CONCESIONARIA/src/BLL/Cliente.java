package BLL;
import DLL.DLLUser;
import DLL.DLLVehiculo;
import GUI.MenuPersonal;
import GUI.menuCliente;
import DLL.DLLMovimiento;
import BLL.Movimiento;
import BLL.Consulta;

import repository.Validaciones;
import repository.Encriptador;



import DLL.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Cliente extends User{
	
	public LinkedList<Deseados> deseado = new LinkedList<>();
	public static LinkedList<Consulta> consulta = new LinkedList<>();
	public LinkedList<CuentaD> cuenta = new LinkedList<>();
	 private static Connection con = Conexion.getInstance().getConnection();
	
	
	
	public static LinkedList<Consulta> getConsulta() {
		return consulta;
	}

	public void setConsulta(LinkedList<Consulta> consulta) {
		this.consulta = consulta;
	}
	
	

	public LinkedList<Deseados> getCarritos() {
		return deseado;
	}

	public void setCarritos(LinkedList<Deseados> carritos) {
		this.deseado = carritos;
	}
	
	

	@Override
	public String toString() {
		return "Cliente [carritos=" + deseado + ", consulta=" + consulta + "]";
	}

	public Cliente(int id_user, byte[] img, String nombre, String mail, String contrasena, String rol) {
		super(id_user, img, nombre, mail, contrasena, rol);
	}
	
	
	

	public Cliente( byte[] img, String nombre, String mail, String contrasena, String rol) {
		super( img, nombre, mail, contrasena, rol);
	};
	
	
	
	
	public static void crearCuenta(byte[] fotoPerfil, String nombre, String email, String contrasena, String rol) {
	  
	    DLL.DLLUser.crearCuenta(fotoPerfil, nombre, email, rol, contrasena);
	    JOptionPane.showMessageDialog(null, "Usuario registrado con éxito.");
	}

	
	
	public static void accederMenu(Cliente cliente, Concesionaria vehiculo) {
		
		menuCliente menu = new menuCliente(cliente);
		menu.run(cliente);
	}

	private static void MenuCarrito(Cliente cliente, Deseados deseado) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Menu del vehiculo seleccionado en proceso" + deseado.toString());
		JOptionPane.showMessageDialog(null, "Volviendo al menus");
	}
	
	private static void MenuVehiculo(Cliente cliente, Vehiculo vehiculoSeleccionado) {
		// TODO Auto-generated method stub
		ImageIcon logo = new ImageIcon("src\\img\\vehiculo\\"+vehiculoSeleccionado.getImg());
		
		JOptionPane.showMessageDialog(null, "Menu del vehiculo seleccionado en proceso" + vehiculoSeleccionado.getMarca()+ ": "+vehiculoSeleccionado.getNombre() + ". Año: "+vehiculoSeleccionado.getAno() );
		
		String[] acciones = { "Comprar", "Agregar a deseados", "Consultas", "Volver" };
		int opcion = 0;
		do {
		    opcion = JOptionPane.showOptionDialog(
		    		null,                                
			        "Menu del vehiculo: "+vehiculoSeleccionado.toString(),            
			        "Menú de inicio de CONDOLEOAUTOS",                    
			        JOptionPane.DEFAULT_OPTION,          
			        JOptionPane.INFORMATION_MESSAGE,     
			        logo,                                
			        acciones,              
			        acciones[0]  
		    );

		    switch (opcion) {
		        case 0:
		        	
		        case 1:
		        	metodoCompra(cliente, vehiculoSeleccionado);
		            break;
		        
		            
		        case 2:
		        	JOptionPane.showMessageDialog(null,  cliente.getConsulta());
		            break;
		            
		        case 3:
		            
		            break;
		        default:
		            break;
		    }

		} while (opcion != 3);
	}

	
	
	
	private static void metodoCompra(Cliente cliente, Vehiculo vehiculoSeleccionado) {
		// TODO Auto-generated method stub
		ImageIcon logo = new ImageIcon("src\\img\\vehiculo\\"+vehiculoSeleccionado.getImg());
		
		JOptionPane.showMessageDialog(null, "Menu del vehiculo seleccionado en proceso" + vehiculoSeleccionado.getMarca()+ ": "+vehiculoSeleccionado.getNombre() + ". Año: "+vehiculoSeleccionado.getAno() );
		
		String[] acciones = { "Cuotas", "De contado", "Volver" };
		int opcion = 0;
		do {
		    opcion = JOptionPane.showOptionDialog(
		    		null,                                
			        "Menu del vehiculo: "+vehiculoSeleccionado.toString(),            
			        "Menú de inicio de CONDOLEOAUTOS",                    
			        JOptionPane.DEFAULT_OPTION,          
			        JOptionPane.INFORMATION_MESSAGE,     
			        logo,                                
			        acciones,              
			        acciones[0]  
		    );

		    switch (opcion) {
		        case 0:
		        	
		        	break;
		        case 1:
		        	ventaVehiculoContado(cliente, vehiculoSeleccionado);
		            break;
		        
		            
		            
		        case 2:
		            
		            break;
		        default:
		            break;
		    }

		} while (opcion != 2);
	}
	
	private static void ventaVehiculoContado(Cliente cliente, Vehiculo vehiculoSeleccionado) {
		// TODO Auto-generated method stub
	    JOptionPane.showMessageDialog(null, "El vehículo: " + vehiculoSeleccionado.toString());

	       	  
	        JOptionPane.showMessageDialog(null, "El cliente debera pagar: "+vehiculoSeleccionado.getPrecio());
	        
	        
	        
	        String detalle = "El "+vehiculoSeleccionado.getIdVehiculo() + ": "+ vehiculoSeleccionado.getMarca()+" " +vehiculoSeleccionado.getNombre() +" fue comprado por el cliente:: " ;
	        Date fecha = new Date(System.currentTimeMillis());; 
	        
	        
	        //Vehiculo car = new Vehiculo(getId(), vehiculoSeleccionado.getIdVehiculo());
			
			//DLLVehiculo.venta_cliente(car);
	        
	        
	        DLLMovimiento.agregarMovimiento(detalle, fecha);
		        
	        int id_cliente = cliente.getId_user();
	        int id_vehiculo = vehiculoSeleccionado.getIdVehiculo();
	        Date fecha_venta = new Date(System.currentTimeMillis());; 
	        
	        //DLLVenta.agregarVenta_cliente(fecha_venta, id_vehiculo, id_cliente);
	}

	

}
