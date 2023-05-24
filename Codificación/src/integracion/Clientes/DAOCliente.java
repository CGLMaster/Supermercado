
package integracion.Clientes;

import negocio.Clientes.TCliente;

import java.util.Set;


public interface DAOCliente {

	public int crearCliente(TCliente cliente);

	public int eliminarCliente(int id_cliente);

	public TCliente buscarCliente(int id);

	public Set<TCliente> buscarTodos();

	public int modificarCliente(TCliente cliente);
}