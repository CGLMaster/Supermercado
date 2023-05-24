
package negocio.Compras;

import java.sql.Date;


public class TCompras {
	
	public TCompras(){
		
	}
	
	public TCompras(Date fecha, int id_cliente, int id_trabajador, double PrecioAPagar, double precioPagado){
		this.Fecha=fecha;
		this.ID_Cliente=id_cliente;
		this.ID_Trabajador=id_trabajador;
		this.precioEfectivo=PrecioAPagar;
		this.precioFinal=precioPagado;
	}
	
	private double precioFinal;
	
	private Date Fecha;
	
	private double precioEfectivo;
	
	private int ID_Cliente;
	
	private int ID_Trabajador;

	
	public void setPrecioFinal(double nuevoPrecioFinal) {
		precioFinal=nuevoPrecioFinal;
	}

	
	public void setFecha(Date nuevaFecha) {
		Fecha=nuevaFecha;
	}

	
	public void setPrecioEfectivo(double precioAPagar) {
		precioEfectivo=precioAPagar;
	}

	
	public void setIdCliente(int id) {
		ID_Cliente=id;
	}

	
	public void setIdTrabajador(int id) {
		ID_Trabajador=id;
	}

	public double getPrecioFinal() {
		return precioFinal;
	}

	
	public Date getFecha() {
		return Fecha;
	}

	
	public double getPrecioEfectivo() {
		return precioEfectivo;
	}

	
	public int getIdCliente() {
		return ID_Cliente;
	}

	
	public int getIdTrabajador() {
		return ID_Trabajador;
	}
}