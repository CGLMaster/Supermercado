package negocio.Trabajadores;

import java.util.Set;

import integracion.FactoríaIntegración.FactoriaIntegracion;

public class SATrabajadorimp implements SATrabajador {

	public int crearTrabajador(TTrabajador trabajador) {
		if(trabajador.getNombre().trim().length() == 0){
			return -2;
		}if(!checkDNI(trabajador.getDNI())){
			return -3;
		}
		
		return FactoriaIntegracion.obtenerInstancia().generarDAOTrabajadores().crearTrabajador(trabajador);
	}

	public int eliminarTrabajador(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOTrabajadores().eliminarTrabajador(id);
	}

	public TTrabajador buscarTrabajador(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOTrabajadores().buscarTrabajador(id);
	}

	public Set<TTrabajador> buscarTodos() {
		return FactoriaIntegracion.obtenerInstancia().generarDAOTrabajadores().buscarTodos();
	}

	public int modificarTrabajador(TTrabajador trabajador) {
		if(trabajador.getNombre().trim().length() == 0){
			return -2;
		}
		
		if(!checkDNI(trabajador.getDNI())){
			return -3;
		}
		return FactoriaIntegracion.obtenerInstancia().generarDAOTrabajadores().modificarTrabajador(trabajador);
	}

	public TTrabajador identificarTrabajador(int id) {
		return FactoriaIntegracion.obtenerInstancia().generarDAOTrabajadores().identificarTrabajador(id);
	}
	
	public boolean checkDNI(String dni){
		boolean ok = false;
		
		if(dni.length() == 9){
			ok = true;
			String nums = dni.substring(0,8);
			try{
				Integer.parseInt(nums);
			}catch(NumberFormatException e){
				ok = false;
			}
			if(ok){
				nums = dni.substring(8);
				try{
					Integer.parseInt(nums);
					ok = false;
				}catch(NumberFormatException e){
				}
			}
		}
		
		return ok;
	}
	
	
	
}