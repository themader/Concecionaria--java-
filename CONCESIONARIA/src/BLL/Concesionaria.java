package BLL;
import DLL.Conexion;
import DLL.DLLUser;
import DLL.DLLVehiculo;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Concesionaria {
	public ImageIcon logo;
	public String nombre;
	public static LinkedList<User> usuario = DLLUser.mostrarUsuario();
	public static LinkedList<Vehiculo> Lista_vehiculo = DLLVehiculo.mostrarVehiculo();
	
	
	
	public ImageIcon getLogo() {
		return logo;
	}
	
	
	
	
	




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public static LinkedList<User> getUsuario() {
		return usuario;
	}




	public void setUsuario(LinkedList<User> usuario) {
		this.usuario = usuario;
	}






	public static LinkedList<Vehiculo> getLista_vehiculo() {
		return Lista_vehiculo;
	}




	public void setLista_vehiculo(LinkedList<Vehiculo> lista_vehiculo) {
		Lista_vehiculo = lista_vehiculo;
	}




	public void setLogo(ImageIcon logo) {
		this.logo = logo;
	}




	@Override
	public String toString() {
		return "Concesionaria [logo=" + logo + ", nombre=" + nombre + ", usuario=" + usuario +  ", Lista_vehiculo=" + Lista_vehiculo + "]";
	}




	

	
	
}
