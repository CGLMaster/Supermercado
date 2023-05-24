
package negocio.Clientes;


public class TCliente {
	
	private int id_Socio;
	private String DNI;
	private String mail;
	private String nombre;
	
	public TCliente(String dni, String mail, String nombre){
		this.DNI = dni;
		this.mail = mail;
		this.nombre = nombre;
	}
	
	public TCliente(){
		
	}
	
	public Integer getId() {
		return id_Socio;
	}

	public String getDNI() {
		return DNI;
	}

	public String getMail() {
		return mail;
	}

	public String getNombre() {
		return nombre;
	}

	public void setId(int Parameter1) {
		this.id_Socio=Parameter1;
	}

	public void setDNI(String Parameter1) {
		this.DNI=Parameter1;
	}

	public void setMail(String Parameter1) {
		this.mail=Parameter1;
	}

	public void setNombre(String Parameter1) {
		this.nombre=Parameter1;
	}
}