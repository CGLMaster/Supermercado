
package negocio.Compras;

import java.util.Map;
import java.util.TreeMap;

import integracion.FactoríaIntegración.FactoriaIntegracion;


public class SAComprasImp implements SACompras {

	
	public TProductoCantidad aniadirProductos(int ID_Producto, int cantidad) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOCompras().aniadirProductos(ID_Producto, cantidad);
	}

	
	public int eliminarProducto(int ID_Producto, int cantidad) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOCompras().eliminarProducto(ID_Producto, cantidad);
	}

	
	public int pagarCompra(TreeMap<Integer, TProductoCantidad> productosEnCarrito, int ID_Cliente, int ID_Trabajador, double cantidadPagada) {
		
		double total = 0;
		for (Map.Entry<Integer, TProductoCantidad> entry : productosEnCarrito.entrySet()) {
			total += entry.getValue().getPrecioTotal();
		}
		
		TCompras compraInfo = new TCompras();
		compraInfo.setIdCliente(ID_Cliente);
		compraInfo.setIdTrabajador(ID_Trabajador);
		compraInfo.setPrecioEfectivo(cantidadPagada);
		compraInfo.setPrecioFinal(total);
		
		TCompraTotal compraTotal = new TCompraTotal(compraInfo, productosEnCarrito);
		
		return FactoriaIntegracion.obtenerInstancia().generarDAOCompras().pagarCompra(compraTotal);
	}

}