package presentacion.Secciones.VistasCasos_de_Uso;

import javax.swing.JFrame;

import presentacion.FormComponent;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Secciones.TSeccion;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VCrearSeccion extends JFrame implements IGUI {

	private JFrame jframe;

	private ArrayList<JTextField> textFields;

	public VCrearSeccion(JFrame jframe) {
		textFields = new ArrayList<JTextField>();
		this.jframe = jframe;
		jframe.setTitle("Crear seccion");
		init_GUI();
	}

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_ALTA_SECCION_OK) {
			JOptionPane.showMessageDialog(null, "Seccion creada correctamente con id: " + (int) datos);
		} else if (evento == Evento.RES_ALTA_SECCION_KO && ((int) datos == -2)) {
			JOptionPane.showMessageDialog(null, "No se ha podido añadir seccion, pasillo no válido");
		} else if (evento == Evento.RES_ALTA_SECCION_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido añadir seccion");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VSECCION, null);
	}

	public void init_GUI() {
		jframe.getContentPane().removeAll();

		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// BACK BUTTON
		JPanel backbuttonContainer = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Zona");
		names.add("Pasillo");

		FormComponent formComponent = new FormComponent(names, "Crear Seccion", textFields, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					if(textFields.get(0).getText().length() > 1){
						TSeccion seccion = new TSeccion(Integer.parseInt(textFields.get(1).getText()), textFields.get(0).getText());
						
						Controlador.obtenerInstancia().accion(Evento.GUARDAR_SECCION, seccion);
					}else{
						JOptionPane.showMessageDialog(null, "Introduzca una zona valida.");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduzca un pasillo valido(valor numerico).");
				}
			}
		});

		// BUILD
		jframe.add(mainPanel);
		mainPanel.add(backbuttonContainer);
		mainPanel.add(formComponent);

		jframe.pack();
		jframe.setVisible(true);
	}

	private JPanel backButtonContainer() {
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Secciones");
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VSECCION, null);
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}
}