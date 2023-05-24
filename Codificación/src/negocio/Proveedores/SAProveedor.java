
package negocio.Proveedores;

import java.util.Set;

public interface SAProveedor {

	public int crearProveedor(TProveedor proveedor);

	public int eliminarProveedor(int id);

	public TProveedor buscarProveedor(int id);

	public int modificarProveedor(TProveedor proveedor);

	public Set<TProveedor> buscarTodosProveedores();
}