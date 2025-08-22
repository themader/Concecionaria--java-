package BLL;
import DLL.DLLVehiculo;
import DLL.Conexion;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.JOptionPane;






public class Vehiculo {
	
	 	private int idVehiculo;
	    private int idQuienPublico;
	    private int idQuienVendio;
	    private int idQuienCompro;

	    private byte[] img;
	    private String nombre;
	    private String marca;
	    private String categoria;
	    private String transmision; 
	    private int ano;
	    private double precio;
	    private int km;
	    private int nChasis;
	    private String patente;
	
	
	
	    private static Connection con = Conexion.getInstance().getConnection();

	
	
	public int getIdVehiculo() {
			return idVehiculo;
		}



		public void setIdVehiculo(int idVehiculo) {
			this.idVehiculo = idVehiculo;
		}



		public int getIdQuienPublico() {
			return idQuienPublico;
		}



		public void setIdQuienPublico(int idQuienPublico) {
			this.idQuienPublico = idQuienPublico;
		}



		public int getIdQuienVendio() {
			return idQuienVendio;
		}



		public void setIdQuienVendio(int idQuienVendio) {
			this.idQuienVendio = idQuienVendio;
		}



		public int getIdQuienCompro() {
			return idQuienCompro;
		}



		public void setIdQuienCompro(int idQuienCompro) {
			this.idQuienCompro = idQuienCompro;
		}



		public byte[] getImg() {
			return img;
		}



		public void setImg(byte[] img) {
			this.img = img;
		}



		public String getNombre() {
			return nombre;
		}



		public void setNombre(String nombre) {
			this.nombre = nombre;
		}



		public String getMarca() {
			return marca;
		}



		public void setMarca(String marca) {
			this.marca = marca;
		}



		public String getCategoria() {
			return categoria;
		}



		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}



		public String getTransmision() {
			return transmision;
		}



		public void setTransmision(String transmision) {
			this.transmision = transmision;
		}



		public int getAno() {
			return ano;
		}



		public void setAno(int ano) {
			this.ano = ano;
		}



		public double getPrecio() {
			return precio;
		}



		public void setPrecio(double precio) {
			this.precio = precio;
		}



		public int getKm() {
			return km;
		}



		public void setKm(int km) {
			this.km = km;
		}



		public int getnChasis() {
			return nChasis;
		}



		public void setnChasis(int nChasis) {
			this.nChasis = nChasis;
		}



		public String getPatente() {
			return patente;
		}



		public void setPatente(String patente) {
			this.patente = patente;
		}



		public static Connection getCon() {
			return con;
		}



		public static void setCon(Connection con) {
			Vehiculo.con = con;
		}



	@Override
	public String toString() {
		return marca+ ": " + nombre+ ". Año: " +ano+ " \n";
	}
	
	
	
	public Vehiculo(byte[] img, String nombre, String marca, String categoria, String transmision, int ano, double precio, int km,
            int nChasis, String patente, int idVehiculo, int idQuienPublico, int idQuienVendio, int idQuienCompro) {
		this.img = img;
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.transmision = transmision;
		this.ano = ano;
		this.precio = precio;
		this.km = km;
		this.nChasis = nChasis;
		this.patente = patente;
		this.idVehiculo = idVehiculo;
		this.idQuienPublico = idQuienPublico;
		this.idQuienVendio = idQuienVendio;
		this.idQuienCompro = idQuienCompro;
	}
	
	public Vehiculo(int idVehiculo, byte[] img, String nombre, String marca, String categoria, double precio) {
		super();
		this.idVehiculo = idVehiculo;
		this.img = img;
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
	}



	
	//EDITAR DATOS DEL VEHICULO
	public Vehiculo(byte[] img, String nombre, String marca, String categoria, String transmision, int ano, double precio, int km,
            int nChasis, String patente, int idVehiculo, int idQuienPublico) {
		this.img = img;
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.transmision = transmision;
		this.ano = ano;
		this.precio = precio;
		this.km = km;
		this.nChasis = nChasis;
		this.patente = patente;
		this.idVehiculo = idVehiculo;
		this.idQuienPublico = idQuienPublico;
	}
	
	public Vehiculo(int idVehiculo,String nombre, String marca, String categoria, double precio  ,byte[] img ) {
		super();
		this.idVehiculo = idVehiculo;
		this.img = img;
		this.nombre = nombre;
		this.marca = marca;
		this.categoria = categoria;
		this.precio = precio;
	}
	// PUBLICARR VEHICULO
		public Vehiculo(byte[] img, String nombre, String marca, String categoria, String transmision, int ano, double precio, int km,
	            int nChasis, String patente,  int idQuienPublico) {
			this.img = img;
			this.nombre = nombre;
			this.marca = marca;
			this.categoria = categoria;
			this.transmision = transmision;
			this.ano = ano;
			this.precio = precio;
			this.km = km;
			this.nChasis = nChasis;
			this.patente = patente;
			this.idQuienPublico = idQuienPublico;
		}
	
	
	
	//EDITAR VENTA
	public Vehiculo(int idQuienVendio, int idQuienCompro, int idVehiculo) {
		this.idQuienVendio = idQuienVendio;
		this.idQuienCompro = idQuienCompro;
		this.idVehiculo = idVehiculo;
	}
	
	public Vehiculo( int idQuienCompro, int idVehiculo) {
		this.idQuienCompro = idQuienCompro;
		this.idVehiculo = idVehiculo;
	}



	public Vehiculo(int id_vehiculo, String nombre, String marca, String categoria, double precio) {
		// TODO Auto-generated constructor stub
	}



		
	
		
	
}
