package BLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import DLL.Conexion;
import DLL.DLLUser;
import DLL.DLLDeseados;
import DLL.DLLVehiculo;

public class Deseados {
	public Vehiculo vehiculo;
	private int id_carrito;
	private int id_vehiculo;
	private int id_user;
	public static LinkedList<Deseados> deseado = DLLDeseados.mostrarDeseados();
	
	
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	

	public int getId_carrito() {
		return id_carrito;
	}
	public void setId_carrito(int id_carrito) {
		this.id_carrito = id_carrito;
	}
	public int getId_vehiculo() {
		return id_vehiculo;
	}
	public void setId_vehiculo(int id_vehiculo) {
		this.id_vehiculo = id_vehiculo;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	


	public Deseados(int id_carrito, int id_vehiculo, int id_user) {
		super();
		this.id_carrito = id_carrito;
		this.id_vehiculo = id_vehiculo;
		this.id_user = id_user;
	}
	public Deseados(int id_user, int idVehiculo) {
		// TODO Auto-generated constructor stub
		this.id_vehiculo = id_vehiculo;
		this.id_user = id_user;
	}



	
	
}
