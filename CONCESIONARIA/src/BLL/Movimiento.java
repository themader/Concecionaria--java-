package BLL;
import DLL.Conexion;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import DLL.Conexion;

public class Movimiento {
	public int ID;
	public String detalle;
	public Date fecha;
	
	
	private static Connection con = Conexion.getInstance().getConnection();

	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Movimiento [detalle=" + detalle + ", fecha=" + fecha + "]";
	}
	public Movimiento(String detalle, Date fecha) {
		super();
		this.detalle = detalle;
		this.fecha = fecha;
	}
	public Movimiento(int iD, String detalle, Date date) {
		super();
		this.ID = iD;
		this.detalle = detalle;
		this.fecha = date;
	}

	
	
	
	
	
}
