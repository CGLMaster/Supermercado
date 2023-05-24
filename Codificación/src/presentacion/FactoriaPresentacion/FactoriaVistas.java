package presentacion.FactoriaPresentacion;

import javax.swing.JFrame;

import presentacion.Clientes.IGUI;

public abstract class FactoriaVistas {
	private static FactoriaVistas instancia;
	
	public abstract IGUI generaVistas(int id_vista, JFrame frame ,Object datos);
	
	public static FactoriaVistas obtenerInstancia() {
		if(instancia == null)
			instancia = new FactoriaVistasImp();
		
		return instancia;
	}
}