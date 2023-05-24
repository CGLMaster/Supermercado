package negocio.Marcas;

import java.util.Set;

public interface SAMarca {

	public int crearMarca(TMarca marca);

	public int eliminarMarca(int id);

	public TMarca buscarMarca(int id);

	public Set<TMarca> buscarTodosMarca();

	public int modificarMarca(TMarca marca);
}