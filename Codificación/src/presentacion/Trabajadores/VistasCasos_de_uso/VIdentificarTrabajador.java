
package presentacion.Trabajadores.VistasCasos_de_uso;

import javax.swing.JFrame;

import presentacion.FormComponent;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VIdentificarTrabajador extends JDialog implements IGUI {

	@SuppressWarnings("unused")
	private JFrame jframe;
	private ArrayList<JTextField> textFields;

	public VIdentificarTrabajador(JFrame jframe) {
		super(jframe, true);
		this.jframe = jframe;
		this.textFields = new ArrayList<JTextField>();
		initGUI();
	}

	private void initGUI() {

		this.setTitle("Identificar Trabajador");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		ArrayList<String> names = new ArrayList<String>();
		names.add("ID Trabajador");

		FormComponent formComponent = new FormComponent(names, "Identificar Trabajador", textFields,
				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							int id = Integer.parseInt(textFields.get(0).getText());
							if (id < 0)
								JOptionPane.showMessageDialog(null,
										"ID no válido. Este identificador es exclusivo para la autocompra");
							else {
								setVisible(false);
								Controlador.obtenerInstancia().accion(Evento.IDENTIFICAR_TRABAJADOR, id);
							}

						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null, "ID no valido, debe introduccir valores numericos");
						}
					}
				});

		mainPanel.add(formComponent);
		this.add(mainPanel);
		this.pack();
		this.setLocation(getParent().getLocation().x + 100, getParent().getLocation().y + 100);
		this.setVisible(true);
	}

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_IDENTIFICAR_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "Ha accedido correctamente");
		} else if (evento == Evento.RES_IDENTIFICAR_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "No se ha podido acceder");
		}
	}

}