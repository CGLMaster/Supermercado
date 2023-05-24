
package negocio.Proveedores;


import java.util.Set;

import integracion.FactoríaIntegración.*;

public class SAProveedorimp implements SAProveedor {

	public int crearProveedor(TProveedor proveedor) {
		if(proveedor.getNombre().trim().length() == 0){
			return -2;
		}
		return FactoriaIntegracion.obtenerInstancia().generarDAOProveedores().crearProveedor(proveedor);
	}

	public int eliminarProveedor(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProveedores().eliminarProveedor(id);
	}

	public TProveedor buscarProveedor(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProveedores().buscarProveedor(id);
	}

	public int modificarProveedor(TProveedor proveedor) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProveedores().editarProveedor(proveedor);
	}

	public Set<TProveedor> buscarTodosProveedores() {
		return FactoriaIntegracion.obtenerInstancia().generarDAOProveedores().buscarTodos();
	}
}