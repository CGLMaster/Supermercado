
package negocio.Productos;

import java.util.Set;

public interface SAProducto {

	public int crearProducto(TProducto producto);

	public int vincularProveedor(int id_producto, int id_proveedor, double precio);

	public Set<TProducto> buscarTodos();

	public TProducto buscarProducto(int id);

	public int modificarProducto(TProducto producto);

	public int eliminarProducto(int id);

	public int desvincularProveedor(int id_producto, int id_proveedor);
	
	public Set<TProducto> mostrarPorFiltro(Set<TProducto> prs, String[] filter);

}