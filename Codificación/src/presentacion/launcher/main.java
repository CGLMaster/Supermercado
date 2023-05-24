package presentacion.launcher;

import presentacion.Clientes.Evento;
import presentacion.Controlador.Controlador;

public class main {

	public static void main(String[] args) {
		Controlador.obtenerInstancia().accion(Evento.CREAR_VPRINCIPAL, null);
	}

}
