package integracion.Seccion;

import java.util.Set;

import negocio.Secciones.TSeccion;


public interface DAOSecciones {
	
	public int crearSeccion(TSeccion seccion);

	public int eliminarSeccion(int id);

	public Set<TSeccion> buscarTodos();

	public TSeccion buscarSeccion(int ID_Seccion);

	public int editarSeccion(TSeccion seccion);
}