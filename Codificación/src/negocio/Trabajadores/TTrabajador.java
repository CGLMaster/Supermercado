package negocio.Trabajadores;

public class TTrabajador {

	public TTrabajador(String Nombre, String Mail, String DNI) {
		this.Nombre = Nombre;
		this.Mail = Mail;
		this.DNI = DNI;
	}

	public TTrabajador() {
	}

	public TTrabajador(String nombre) {
		this.Nombre = nombre;
	}

	private String Nombre;

	private String Mail;

	private String DNI;

	private int ID_Trabajador;

	public String getNombre() {
		return Nombre;
	}

	public String getMail() {
		return Mail;
	}

	public String getDNI() {
		return DNI;
	}

	public int getID_Trabajador() {
		return ID_Trabajador;
	}

	public void setNombre(String Nombre) {
		this.Nombre = Nombre;
	}

	public void setMail(String Mail) {
		this.Mail = Mail;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public void setId(int id) {
		this.ID_Trabajador = id;
	}
}