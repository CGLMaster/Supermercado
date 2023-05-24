
package negocio.Secciones;

public class TSeccion {
	private String zona;
	private int pasillo;
	private int Id_seccion;
	
	public TSeccion(){
		
	}

	public TSeccion(int pasillo, String zona){
		this.pasillo=pasillo;
		this.zona=zona;
	}
	
	public int getId() {
		return Id_seccion;
	}

	public int getPasillo() {
		return pasillo;
	}

	public String getZona() {
		return zona;
	}

	public void setPasillo(int numero_pasillo) {
		pasillo=numero_pasillo;
	}

	public void setZona(String zona) {	
		this.zona=zona;
	}
	
	public void setId(int id){
		this.Id_seccion=id;
	}
}