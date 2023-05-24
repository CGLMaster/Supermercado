package integracion.Marcas;

import negocio.Marcas.TMarca;
import java.util.Set;

public interface DAOMarcas {

	public int crearMarca(TMarca marca);

	public TMarca buscarMarca(int ID_Marca);

	public Set<TMarca> buscarTodas();

	public int modificarMarca(TMarca marca);

	public int eliminarMarca(int ID_Marca);
}