
package negocio.Secciones;

import java.util.Set;

import integracion.FactoríaIntegración.FactoriaIntegracion;

public class SASeccionImp implements SAPSeccion {
	
	
	public int crearSeccion(TSeccion seccion) {
		if(seccion.getZona().trim().length() == 0){
			return -2;
		}
		if(seccion.getPasillo() <= 0 || seccion.getPasillo() > 6){
			return -2;
		}
		return FactoriaIntegracion.obtenerInstancia().generarDAOSecciones().crearSeccion(seccion);
	}

	public TSeccion buscarSeccion(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOSecciones().buscarSeccion(id);
	}

	public Set<TSeccion> buscarTodas() {
		return FactoriaIntegracion.obtenerInstancia().generarDAOSecciones().buscarTodos();
	}

	public int modificarSeccion(TSeccion seccion) {
		if(seccion.getPasillo() <= 0 || seccion.getPasillo() > 6){
			return -2;
		}
		return FactoriaIntegracion.obtenerInstancia().generarDAOSecciones().editarSeccion(seccion);
	}

	public int eliminarSeccion(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOSecciones().eliminarSeccion(id);
	}
}