
package integracion.Trabajadores;

import negocio.Trabajadores.TTrabajador;
import java.util.Set;

public interface DAOTrabajadores {

	public int crearTrabajador(TTrabajador trabajador);

	public TTrabajador buscarTrabajador(int ID_Trabajador);

	public Set<TTrabajador> buscarTodos();

	public int eliminarTrabajador(int ID_Trabajador);

	public int modificarTrabajador(TTrabajador Trabajador);

	public TTrabajador identificarTrabajador(int ID_Trabajador);
}