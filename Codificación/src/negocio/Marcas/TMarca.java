
package negocio.Marcas;

public class TMarca {
	
	public TMarca(){
		
	}
	
	public TMarca (String nombre){
		this.Nombre=nombre;
	}

	private String Nombre;

	private int Id_Marca;
	
	private String Web;

	public int getId() {
		return Id_Marca;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setId(int id) {
		Id_Marca=id;
	}

	public void setNombre(String nombre) {
		Nombre=nombre;
	}

	public String getWeb() {
		return Web;
	}

	public void setWeb(String web) {
		Web = web;
	}
}