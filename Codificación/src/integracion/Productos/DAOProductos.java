package integracion.Productos;

import negocio.Productos.TProducto;
import java.util.Set;

public interface DAOProductos {

	public int crearProducto(TProducto producto);

	public int vincularProveedor(int id_producto, int id_proveedor, double precio);

	public Set<TProducto> buscarTodos();

	public TProducto buscarProducto(int id_producto);

	public int modificarProducto(TProducto producto);

	public int eliminarProducto(int id_producto);

	public int desvincularProveedor(int id_producto, int id_proveedor);
}