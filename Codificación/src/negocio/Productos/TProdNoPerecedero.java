
package negocio.Productos;


import negocio.Marcas.TMarca;
import negocio.Proveedores.TProveedor;
import negocio.Secciones.TSeccion;
import presentacion.Date;

public class TProdNoPerecedero extends TProducto {
	
	private String tipo;
	
	public TProdNoPerecedero(String Nombre, int Stock, double Precio, String tipo, TMarca marca, TSeccion seccion){
		super(Nombre, Stock, Precio,marca,seccion);
		this.tipo=tipo;
	}
	
	public TProdNoPerecedero(String Nombre, int Stock, double Precio, String tipo){
		super(Nombre, Stock, Precio);
		this.tipo=tipo;
	}
	
	public TProdNoPerecedero(String Nombre, int Stock, double Precio, TMarca marca, TProveedor proveedor, TSeccion seccion, String tipo){
		super(Nombre, Stock, Precio, marca, proveedor, seccion);
		this.tipo=tipo;

	}

	@Override
	public String getTipo() {
		return this.tipo;
	}

	@Override
	public Date getFecha() {
		return null;
	}

	@Override
	public void setFecha(Date date) {
	}

	@Override
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}