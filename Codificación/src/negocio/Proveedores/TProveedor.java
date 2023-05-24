package negocio.Proveedores;

public class TProveedor {
	
	public TProveedor(String nombre){
		this.nombre=nombre;
	}
	
	public TProveedor(String nombre, int id){
		this.nombre=nombre;
		this.Id_proveedor = id;
	}
	
	public TProveedor() {
		
	}
	
	private String nombre;

	private int Id_proveedor;

	public int getId() {
		return Id_proveedor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setId(int id){
		this.Id_proveedor=id;
	}
}