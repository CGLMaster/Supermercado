
package negocio.Clientes;

import java.util.Set;


public interface SACliente {

	public int crearCliente(TCliente cliente);

	public int eliminarCliente(int id);

	public TCliente buscarCliente(int id);

	public Set<TCliente> buscarTodos();

	public int modificarCliente(TCliente cliente);
}