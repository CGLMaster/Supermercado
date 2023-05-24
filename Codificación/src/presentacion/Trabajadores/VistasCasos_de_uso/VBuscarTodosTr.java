package presentacion.Trabajadores.VistasCasos_de_uso;

import javax.swing.JFrame;

import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VBuscarTodosTr extends JFrame implements IGUI {

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_BUSCAR_TODOS_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "Trabajadores buscados correctamente");
		} else if (evento == Evento.RES_BUSCAR_TODOS_TRABAJADOR_KO) {
			JOptionPane.showMessageDialog(null, "No se han podido buscar los trabajadores");
		}
	}
}