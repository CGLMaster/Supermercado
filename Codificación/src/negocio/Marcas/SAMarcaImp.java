package negocio.Marcas;

import java.util.Set;

import integracion.FactoríaIntegración.FactoriaIntegracion;

public class SAMarcaImp implements SAMarca {

	public int crearMarca(TMarca marca) {
		
		if(marca.getNombre().trim().length() == 0){
			return -2;
		}
		return FactoriaIntegracion.obtenerInstancia().generarDAOMarcas().crearMarca(marca);
	}

	public int eliminarMarca(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOMarcas().eliminarMarca(id);
	}

	public TMarca buscarMarca(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOMarcas().buscarMarca(id);
	}

	public Set<TMarca> buscarTodosMarca() {
		return FactoriaIntegracion.obtenerInstancia().generarDAOMarcas().buscarTodas();
	}

	public int modificarMarca(TMarca marca) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOMarcas().modificarMarca(marca);
	}
}