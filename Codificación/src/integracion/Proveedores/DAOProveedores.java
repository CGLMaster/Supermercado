package integracion.Proveedores;

import negocio.Proveedores.TProveedor;
import java.util.Set;

public interface DAOProveedores {
	public int crearProveedor(TProveedor proveedor);
	
	public TProveedor buscarProveedor(int ID_Proveedor);

	public Set<TProveedor> buscarTodos();

	public int eliminarProveedor(int ID_proveedor);

	public int editarProveedor(TProveedor proveedor);
}