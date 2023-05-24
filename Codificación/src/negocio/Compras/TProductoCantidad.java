
package negocio.Compras;


public class TProductoCantidad {
	
	public TProductoCantidad(String producto, int cantidad, double precio_unitario){
		this.producto=producto;
		this.cantidad=cantidad;
		this.precio_unitario = precio_unitario;
		precioTotal=precio_unitario * cantidad;
	}

	private String producto;
	private int cantidad;
	private double precio_unitario;
	private double precioTotal;

	public void setProducto(String producto) {
		// begin-user-code
		// TODO Auto-generated method stub
		this.producto=producto;
		// end-user-code
	}
	
	public void setCantidad(int cantidad){
		this.cantidad=cantidad;
		this.precioTotal= this.precio_unitario * cantidad;
	}

	public String getProducto() {
		// begin-user-code
		// TODO Auto-generated method stub
		return producto;
		// end-user-code
	}
	
	public int getCantidad(){
		return cantidad;
	}
	
	public double getPrecioTotal(){
		return precioTotal;
	}
}