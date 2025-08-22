package BLL;

import DLL.DLLMovimiento;
import DLL.DLLVehiculo;
import GUI.MenuPersonal;
import repository.Validaciones;

import java.sql.Date;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Personal extends User implements Validaciones {

    public static LinkedList<Movimiento> movimientos = DLLMovimiento.mostrarMovimiento();

   
    public Personal(int id_user, byte[] img, String nombre, String mail, String contrasena, String rol) {
        super(id_user, img, nombre, mail, contrasena, rol);
    }

    
    public Personal(int id_user, String nombre, String mail, String rol) {
        super(id_user, nombre, mail, rol);
    }

    
    public Personal(byte[] img, String nombre, String mail, String contrasena, String rol) {
        super(img, nombre, mail, contrasena, rol);
    }

    
    public Personal() {
        super();
    }

    public static LinkedList<Movimiento> getMovimientos() {
        return movimientos;
    }

    public static void setMovimientos(LinkedList<Movimiento> movimientos) {
        Personal.movimientos = movimientos;
    }

    @Override
    public String toString() {
        return "Personal [id_user=" + id_user + ", nombre=" + nombre + ", mail=" + mail + ", rol=" + rol + "]";
    }












	public void inicionSesion(User personal, String mail, String contrasena, Concesionaria miConcesionaria) {
		ImageIcon imgUsuario = new ImageIcon("src\\img\\usuarios\\" +getImg());

		if (Validaciones.ValidarMail(mail) && this.getMail().equals(mail) && this.contrasena == contrasena) {
			
			MenuPersonal menu = new MenuPersonal(personal);
			menu.run(personal);
			
			//accederMenu( miConcesionaria);
		}else {
			JOptionPane.showMessageDialog(null, "No esta autorizado a ingresar debido a que su mail");
			return;	
		}
		
	}
	
	
	public void accederMenu(  Concesionaria miConcesionaria) {
		ImageIcon logo = new ImageIcon("src\\img\\logo.png");
		
		String[] acciones = { "Clientes", "Personal", "Vehiculos", "Movimientos", "Ver cuenta", "Cerrar sesion" };
		int opcion = 0;
		do {
		    opcion = JOptionPane.showOptionDialog(
		        null,                                
		        "Seleccioná una opción:\n "
		        + "Clientes: Te lleva a la lista de los clientes registados y al seleccionar uno te lleva al menu de opciones\n "
		        + "Personal: Te lleva a la lista del personal registado y al seleccionar uno te lleva al menu de opciones, Tambien podes crear un nuevo personal\n "
		        + "vehiculos: Te lleva a un menu que tenes la opcion de ver los vehiculos en venta y otra donde podes agregar otro.\n"
		        + "En cuanto a la primera opcion al seleccionar uno te lleva a su menu que podes eliminarlo, modificarlo o venderlo\n"
		        + "Movimientos: Se visuavilizan las acciones realizadas por el personal"
		        + "Ver cuenta: Se muestra datos del usuario logeado"
		        + " ",
		        "Menú de inicio de CONDOLEOAUTOS",                    
		        JOptionPane.DEFAULT_OPTION,          
		        JOptionPane.INFORMATION_MESSAGE,     
		        logo,                       
		        acciones, acciones[0]            
		    );

		    switch (opcion) {
		        case 0:
		        	JOptionPane.showMessageDialog(null, "te mostraria los clientes");
		            break;
		        case 1:
		        	JOptionPane.showMessageDialog(null, "Te mostraria el menu de las opciones con el personal");
		            break;
		        
		        case 2:
		        	opcionesVehiculo(  miConcesionaria);
		            break;
		            
		        case 3:
		        	JOptionPane.showMessageDialog(null, getMovimientos());
		            break;
		            
		        case 4:
		        	JOptionPane.showMessageDialog(null, this.getNombre() + ", Rol: "+this.getRol() );
		            break;
		            
		        case 5:
		            
		            break;
		        
		        default:
		            break;
		    }

		} while (opcion != 5);
		
	}





	private void opcionesVehiculo( Concesionaria vehiculo) {
		// TODO Auto-generated method stub
		
		
		
		ImageIcon logo = new ImageIcon("src\\img\\logo.png");
		
		String[] acciones = { "Lista vehiculos", "Agregar vehiculo a la venta", "Cotizar", "Volver" };
		int opcion = 0;
		do {
		    opcion = JOptionPane.showOptionDialog(
		        null,                                
		        "Seleccioná una opción",
		        "Menú de inicio de CONDOLEOAUTOS",                    
		        JOptionPane.DEFAULT_OPTION,          
		        JOptionPane.INFORMATION_MESSAGE,     
		        logo,                       
		        acciones, acciones[0]            
		    );

		    switch (opcion) {
		        case 0:
		        	
		        	if (vehiculo.getLista_vehiculo().isEmpty()) {
		                JOptionPane.showMessageDialog(null, "NO hay vehículos disponibles.");
		            } else {
		                String[] datas = new String[vehiculo.getLista_vehiculo().size()];
		                for (int i = 0; i < vehiculo.getLista_vehiculo().size(); i++) {
		                    datas[i] = vehiculo.getLista_vehiculo().get(i).toString();
		                }

		                int seleccion = JOptionPane.showOptionDialog(
		                        null,
		                        "Seleccioná un vehículo disponible:",
		                        "Vehículos disponibles",
		                        JOptionPane.DEFAULT_OPTION,
		                        JOptionPane.INFORMATION_MESSAGE,
		                        logo,
		                        datas,
		                        datas[0]
		                );

		                if (seleccion != -1) {
		                    Vehiculo vehiculoSeleccionado = vehiculo.getLista_vehiculo().get(seleccion);
		                    MenuVehiculo( vehiculoSeleccionado, vehiculo);  // <- método para interactuar con ese vehículo
		                }
		            }
		        	
		            break;
		        case 1:
		        	JOptionPane.showMessageDialog(null, "Te mostraria el menu de las opciones con el personal");
		            break;
		        
		        case 2:
		            break;
		            
		            
		        case 3:
		            
		            break;
		        
		        default:
		            break;
		    }

		} while (opcion != 3);
			
	}





	private void MenuVehiculo( Vehiculo vehiculoSeleccionado, Concesionaria concesionaria) {
			// TODO Auto-generated method stub
		
		ImageIcon logo = new ImageIcon("src\\img\\vehiculo\\"+vehiculoSeleccionado.getImg());
		
			JOptionPane.showMessageDialog(null, "Menu del vehiculo seleccionado en proceso" + vehiculoSeleccionado.getMarca()+ ": "+vehiculoSeleccionado.getNombre() + ". Año: "+vehiculoSeleccionado.getAno() );
			
		
			
			String[] acciones = { "Vender", "Modificar", "Consultas", "Volver" };
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
			        	metodoCompra(vehiculoSeleccionado, concesionaria);
			        	break;
			        case 1:
			        	opcionesEdicion(vehiculoSeleccionado);
			            break;
			        
			            
			        case 2:
			        	JOptionPane.showMessageDialog(null,  "  ");
			            break;
			            
			        case 3:
			            
			            break;
			        default:
			            break;
			    }

			} while (opcion != 3);
		
	}

	
	
	
	
	private static void metodoCompra(Vehiculo vehiculoSeleccionado, Concesionaria concesionaria) {
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
		        	
		        	cuotasPago(vehiculoSeleccionado, concesionaria);
		        	
		        	break;
		        case 1:
		        	

		            pagoContado(vehiculoSeleccionado, concesionaria);
		            break;
		        
		            
		        case 2:
		            
		            break;
		        default:
		            break;
		    }

		} while (opcion != 2);
	}




	private static void pagoContado(Vehiculo vehiculoSeleccionado, Concesionaria concesionaria) {
		// TODO Auto-generated method stub
		 Integer[] opcionCliente = {1, 2, 3};

         Integer cliente = (Integer) JOptionPane.showInputDialog( null, "Seleccione el ID del cliente:",  "Cliente", JOptionPane.QUESTION_MESSAGE,  null,  opcionCliente, opcionCliente[0] );

         if (cliente != null) {
             JOptionPane.showMessageDialog(null, "Cantidad de cuotas seleccionada: " + cliente );
             
             
             JOptionPane.showMessageDialog(null, "El cliente debera pagar:");
             
             
             
             String detalle = "El "+vehiculoSeleccionado.getIdVehiculo() + ": "+ vehiculoSeleccionado.getMarca()+" " +vehiculoSeleccionado.getNombre() +" fue vendido por el vendedor: "+ ". El cliente debera pagar: "+vehiculoSeleccionado.getPrecio();
 	        Date fecha = new Date(System.currentTimeMillis());; 
 	        
 	        
 	     //   Vehiculo car = new Vehiculo(getId(),  cliente, vehiculoSeleccionado.getIdVehiculo());
 			
 		//	DLLVehiculo.venta(car);
 	        
 	        
 	        DLLMovimiento.agregarMovimiento(detalle, fecha);
 	        
         	}
         }









	private static void cuotasPago(Vehiculo vehiculoSeleccionado, Concesionaria concesionaria) {
	    ImageIcon logo = new ImageIcon("src\\img\\vehiculo\\" + vehiculoSeleccionado.getImg());

	    Integer[] opcionCuota = {6, 12, 18};

        Integer cuota = (Integer) JOptionPane.showInputDialog(
            null,
            "Seleccione la cantidad de cuotas:",
            "Pago en cuotas",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opcionCuota,
            opcionCuota[0]
        );

        if (cuota != null) {
            JOptionPane.showMessageDialog(null, "Cantidad de cuotas seleccionada: " + cuota );
            
            

    	    Integer[] opcionCliente = {1, 2, 3};

            Integer cliente = (Integer) JOptionPane.showInputDialog( null, "Seleccione el ID del cliente:",  "Cliente", JOptionPane.QUESTION_MESSAGE,  null,  opcionCliente, opcionCliente[0] );

            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Cantidad de cuotas seleccionada: " + cliente );
                
                int pago = (int) (vehiculoSeleccionado.getPrecio() / cuota);
                
                JOptionPane.showMessageDialog(null, "El cliente debera pagar: "+cuota+ " de $"+pago);
                
                
                
                String detalle = "El "+vehiculoSeleccionado.getIdVehiculo() + ": "+ vehiculoSeleccionado.getMarca()+" " +vehiculoSeleccionado.getNombre() +" fue vendido por el vendedor:   El cliente debera pagar: "+cuota+ " de $"+pago;
    	        Date fecha = new Date(System.currentTimeMillis());; 
    	        
    	        
    	//        Vehiculo car = new Vehiculo(getId(),  cliente, vehiculoSeleccionado.getIdVehiculo());
    			
    		//	DLLVehiculo.venta(car);
    	        
    	        
    	        DLLMovimiento.agregarMovimiento(detalle, fecha);
                
                
                
                
           
            } else {
                
                JOptionPane.showMessageDialog(null, "No se seleccionó ninguna cliente");
            }
            
            
            
            
        } else {
            
            JOptionPane.showMessageDialog(null, "No se seleccionó ninguna opción de cuotas.");
        }
	}






	private void opcionesEdicion(Vehiculo vehiculoSeleccionado) {
		// TODO Auto-generated method stub
		ImageIcon logo = new ImageIcon("src\\img\\vehiculo\\"+vehiculoSeleccionado.getImg());
		
		JOptionPane.showMessageDialog(null, "Menu del vehiculo seleccionado en proceso" + vehiculoSeleccionado.getMarca()+ ": "+vehiculoSeleccionado.getNombre() + ". Año: "+vehiculoSeleccionado.getAno() );
		
	
		
		String[] acciones;

		if (vehiculoSeleccionado.getIdQuienVendio() == 0 || vehiculoSeleccionado.getIdQuienCompro() == 0) {
		    acciones = new String[] { "Editar vehiculo", "Volver" };
		} else {
		    acciones = new String[] { "Editar vehiculo", "Editar datos de venta", "Volver" };
		}
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
		        	editarVehiculo(vehiculoSeleccionado);
		        	break;
		        	
		        case 1:
		        	editarVenta(vehiculoSeleccionado);
		            break;
		        
		            
		
		            
		        case 2:
		        	
		            break;
		        default:
		            break;
		    }

		} while (opcion != 2);
	}





	private void editarVehiculo(Vehiculo vehiculoSeleccionado) {
		// TODO Auto-generated method stub
		  	String img = JOptionPane.showInputDialog("Ingrese la URL de la imagen:");
	        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del vehículo:");
	        String marca = JOptionPane.showInputDialog("Ingrese la marca:");
	        
	        
	        String[] opcionCategoria = {"Sedan", "hatchback", "Pick up"};
	        String categoria = (String) JOptionPane.showInputDialog(
	        	    null, "Seleccione una opción de categoría:", "Categoría",
	        	    JOptionPane.QUESTION_MESSAGE,
	        	    null,opcionCategoria,  opcionCategoria[0]
	        	);
	        
	        
	        String[] opcionTransmision = {"Manual", "Automatico"};
	        String transmision = (String) JOptionPane.showInputDialog(
	        	    null, "Seleccione una opción de categoría:", "Categoría",
	        	    JOptionPane.QUESTION_MESSAGE,
	        	    null,opcionTransmision,  opcionTransmision[0]
	        	);

	        

	        int ano = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año:"));
	        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio:"));
	        int km = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el kilometraje:"));
	        int nChasis = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de chasis:"));
	        String patente = JOptionPane.showInputDialog("Ingrese la patente:");
	        int idQuienPublico = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID de quien publicó:"));

	        //Vehiculo car = new Vehiculo(img, nombre, marca, categoria, transmision, ano, precio, km, nChasis, patente,
	         //                            vehiculoSeleccionado.getIdVehiculo(), idQuienPublico);

	      //  DLLVehiculo.editar_veiculo(car);
	        
	        String detalle = "Vehiculo "+vehiculoSeleccionado.getIdVehiculo() + ": "+ vehiculoSeleccionado.getMarca()+" " +vehiculoSeleccionado.getNombre() +" fue modificado correctamente a: "+marca+nombre;
	        Date fecha = new Date(System.currentTimeMillis());; 
	        
	        DLLMovimiento.agregarMovimiento(detalle, fecha);
	        
	        
	        
	}









	private void editarVenta(Vehiculo vehiculoSeleccionado) {
		// TODO Auto-generated method stub
			
			String quienCompro = JOptionPane.showInputDialog("Ingrese el id de quien compro");
			
			int idQuienCompro = Integer.parseInt(quienCompro);
			
			String quienvendio= JOptionPane.showInputDialog("Ingrese el id de quien vendio");
			
			int idQuienVendio = Integer.parseInt(quienvendio);
	        
	        Vehiculo car = new Vehiculo(idQuienVendio,  idQuienCompro, vehiculoSeleccionado.getIdVehiculo());
			
			DLLVehiculo.editar_venta(car);
			
			
			String detalle = "Los datos de venta Vehiculo "+vehiculoSeleccionado.getIdVehiculo() + ": "+ vehiculoSeleccionado.getMarca()+" " +vehiculoSeleccionado.getNombre() +" Fueron cambiados";
	        Date fecha = new Date(System.currentTimeMillis());; 
	        
	        DLLMovimiento.agregarMovimiento(detalle, fecha);
		  
	}
}
