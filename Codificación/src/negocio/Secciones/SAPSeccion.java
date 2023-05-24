package negocio.Secciones;

import java.util.Set;

public interface SAPSeccion {

	public int crearSeccion(TSeccion seccion);

	public TSeccion buscarSeccion(int id);

	public Set<TSeccion> buscarTodas();

	public int modificarSeccion(TSeccion seccion);

	public int eliminarSeccion(int id);
}