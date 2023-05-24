
package negocio.Productos;


import negocio.Marcas.TMarca;
import negocio.Proveedores.TProveedor;
import negocio.Secciones.TSeccion;
import presentacion.Date;

public abstract class TProducto {
	
	
	public TProducto(String Nombre, int Stock, double Precio, TMarca marca, TProveedor proveedor, TSeccion seccion){
		this.marca=marca;
		this.Stock=Stock;
		this.Nombre=Nombre;
		this.Precio=Precio;
		this.proveedor=proveedor;
		this.seccion=seccion;
	}
	
	public TProducto(String Nombre, int Stock, double Precio){
		this.Stock=Stock;
		this.Nombre=Nombre;
		this.Precio=Precio;
	}
	public TProducto(String Nombre, int Stock, double Precio, TMarca marca, TSeccion seccion) {
		this.marca=marca;
		this.Stock=Stock;
		this.Nombre=Nombre;
		this.Precio=Precio;
		this.seccion=seccion;
	}

	private String Nombre;

	private int Stock;

	private int ID_Producto;

	private double Precio;

	private TMarca marca;

	private TProveedor proveedor;

	private TSeccion seccion;

	public String getNombre() {
		return Nombre;
	}

	public int getStock() {
		return Stock;
	}

	public int getID() {
		return ID_Producto;
	}

	public Double getPrecio() {
		return Precio;
	}

	public TMarca getMarca() {
		return marca;
	}

	public TProveedor getProveedor() {
		return proveedor;
	}

	public TSeccion getSeccion() {
		return seccion;
	}

	public void setNombre(String Nombre) {
		this.Nombre=Nombre;
	}

	public void setStock(int Stock) {
		this.Stock=Stock;
	}

	public void setID(int ID) {
		this.ID_Producto=ID;
	}

	public void setPrecio(Double precio) {
		this.Precio=precio;
	}

	public void setMarca(TMarca marca) {
		this.marca=marca;
	}

	public void setProveedor(TProveedor proveedor) {
		this.proveedor=proveedor;
	}

	public void setSeccion(TSeccion seccion) {
		this.seccion=seccion;
	}
	
	public abstract String getTipo();

	public abstract Date getFecha();
	
	public abstract void setTipo(String tipo);
	
	public abstract void setFecha(Date date);
}