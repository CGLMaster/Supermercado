
package negocio.Productos;


import negocio.Marcas.TMarca;
import negocio.Proveedores.TProveedor;
import negocio.Secciones.TSeccion;
import presentacion.Date;

public class TProdPerecedero extends TProducto {
	private Date fecha;
	
	public TProdPerecedero(String Nombre, int Stock, double Precio, Date fecha, TMarca marca, TSeccion seccion){
		super(Nombre, Stock, Precio, marca, seccion);
		this.fecha=fecha;
	}
	
	public TProdPerecedero(String Nombre, int Stock, double Precio, Date fecha){
		super(Nombre, Stock, Precio);
		this.fecha=fecha;
	}
	
	public TProdPerecedero(String Nombre, int Stock, double Precio, TMarca marca, TProveedor proveedor, TSeccion seccion, Date fecha){
		super(Nombre, Stock, Precio, marca, proveedor, seccion);
		this.fecha=fecha;
	}

	@Override
	public String getTipo() {
		return null;
	}

	@Override
	public Date getFecha() {
		return this.fecha;
	}

	@Override
	public void setTipo(String tipo) {
	}

	@Override
	public void setFecha(Date date) {
		fecha = date;
	}
}