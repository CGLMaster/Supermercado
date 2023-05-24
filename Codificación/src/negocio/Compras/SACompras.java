
package negocio.Compras;

import java.util.TreeMap;

public interface SACompras {

	public TProductoCantidad aniadirProductos(int id, int cantidad);

	
	public int eliminarProducto(int id, int cantidad);

	
	public int pagarCompra(TreeMap<Integer, TProductoCantidad> productosEnCarrito, int ID_Cliente, int ID_Trabajador, double cantidadPagada);
}