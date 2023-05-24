
package negocio.Clientes;

import java.util.Set;
import integracion.FactoríaIntegración.FactoriaIntegracion;


public class SAClienteImp implements SACliente {

	public int crearCliente(TCliente cliente) {
		if(cliente.getNombre().trim().length() == 0){
			return -2;
		}
		
		if(!checkDNI(cliente.getDNI())){
			return -3;
		}
		
		return FactoriaIntegracion.obtenerInstancia().generaDAOClientes().crearCliente(cliente);
	}

	public int eliminarCliente(int id) {
		return FactoriaIntegracion.obtenerInstancia().generaDAOClientes().eliminarCliente(id);
	}

	public TCliente buscarCliente(int id) {
		return FactoriaIntegracion.obtenerInstancia().generaDAOClientes().buscarCliente(id);	
	}

	public Set<TCliente> buscarTodos() {
		return FactoriaIntegracion.obtenerInstancia().generaDAOClientes().buscarTodos();
	}

	public int modificarCliente(TCliente cliente) {
		
		if(cliente.getNombre().trim().length() == 0){
			return -2;
		}
		
		if(!checkDNI(cliente.getDNI())){
			return -3;
		}
		
		return FactoriaIntegracion.obtenerInstancia().generaDAOClientes().modificarCliente(cliente);
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