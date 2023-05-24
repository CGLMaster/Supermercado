
package integracion.Compras;

import negocio.Compras.TCompraTotal;
import negocio.Compras.TProductoCantidad;

public interface DAOCompra {
	public TProductoCantidad aniadirProductos(int ID_Producto, int cantidad);

	public int eliminarProducto(int ID_Producto, int cantidad);

	public int pagarCompra(TCompraTotal compra);
}