package presentacion.Controlador;

public abstract class Controlador {
	
	private static Controlador instancia;

	public static Controlador obtenerInstancia() {
		if (instancia== null)
			instancia = new ControladorImp();
			return instancia;
	}
	
	public abstract void accion(int evento, Object datos);
}