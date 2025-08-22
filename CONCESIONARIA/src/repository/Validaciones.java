package repository;
import javax.swing.JOptionPane;

public interface Validaciones {
	
	
	static boolean ValidarMail(String data) {
		
		if (data.contains("@condoleoautos.com.ar")) {
			return true;
		}else {
			return false;
		}
	}
}
