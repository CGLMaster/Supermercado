package negocio.Trabajadores;

import java.util.Set;

public interface SATrabajador {

	public int crearTrabajador(TTrabajador trabajador);

	public int eliminarTrabajador(int id);

	public TTrabajador buscarTrabajador(int id);

	public Set<TTrabajador> buscarTodos();

	public int modificarTrabajador(TTrabajador trabajador);

	public TTrabajador identificarTrabajador(int id);
}