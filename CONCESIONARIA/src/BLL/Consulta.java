package BLL;
import java.time.LocalDate;

public class Consulta {
	public int id_consulta;
	public int id_cliente;
	public String detalle;   
	public String respuesta;
	
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	
	
	
	public int getId_consulta() {
		return id_consulta;
	}
	public void setId_consulta(int id_consulta) {
		this.id_consulta = id_consulta;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public Consulta(String detalle,  String respuesta) {
		super();
		this.detalle = detalle;
		this.respuesta = respuesta;
	}
	@Override
	public String toString() {
		return "Consulta [id_consulta=" + id_consulta + ", id_cliente=" + id_cliente +  "detalle=" + detalle +  ", respuesta=" + respuesta + "]";
	}
	public Consulta(int id_consulta, int id_cliente, int id_personal, String detalle,
			String respuesta) {
		super();
		this.id_consulta = id_consulta;
		this.id_cliente = id_cliente;
		this.detalle = detalle;
		this.respuesta = respuesta;
	}

	
	public Consulta( String detalle,
			String respuesta, int id_cliente) {
		super();
		
		this.detalle = detalle;
		this.respuesta = respuesta;
		this.id_cliente = id_cliente;
	}
	
	public Consulta(int id_consulta, int id_cliente, String detalle, String respuesta) {
		super();
		this.id_consulta = id_consulta;
		this.id_cliente = id_cliente;
		this.detalle = detalle;
		this.respuesta = respuesta;
	}
	
	public Consulta(int id_consulta, String detalle, String respuesta, int id_cliente) {
		super();
		this.id_consulta = id_consulta;
		
		this.detalle = detalle;
		this.respuesta = respuesta;
		this.id_cliente = id_cliente;
	}
	
	
	public Consulta(int id_consulta, int id_cliente, String detalle) {
		super();
		this.id_consulta = id_consulta;
		this.id_cliente = id_cliente;
		this.detalle = detalle;
	}
	
	
	public Consulta(int id_cliente, String respuesta, String detalle) {
	    super();
	    this.id_cliente = id_cliente;
	    this.respuesta = respuesta;
	    this.detalle = detalle;
	}

	
	public Consulta(int id_cliente, String respuesta) {
	    super();
	    this.id_cliente = id_cliente;
	    this.respuesta = respuesta;
	}
	
	public Consulta(String detalle, int id_cliente) {
	    super();
	    this.detalle = detalle;
	    this.id_cliente = id_cliente;
	   
	}

}
