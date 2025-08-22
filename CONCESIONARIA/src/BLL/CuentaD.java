package BLL;

public class CuentaD {
	
	private int id_cuenta;
	private int id_user;
	private double sueldo;
	private double caja;
	public int getId_cuenta() {
		return id_cuenta;
	}
	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public double getCaja() {
		return caja;
	}
	public void setCaja(double caja) {
		this.caja = caja;
	}
	@Override
	public String toString() {
		return "CuentaD [id_cuenta=" + id_cuenta + ", id_user=" + id_user + ", sueldo=" + sueldo + ", caja=" + caja
				+ "]";
	}
	public CuentaD(int id_cuenta, int id_user, double sueldo, double caja) {
		super();
		this.id_cuenta = id_cuenta;
		this.id_user = id_user;
		this.sueldo = sueldo;
		this.caja = caja;
	}
	
	

	public CuentaD( int id_user, double sueldo, double caja) {
		super();
		this.id_user = id_user;
		this.sueldo = sueldo;
		this.caja = caja;
	}
	
}
