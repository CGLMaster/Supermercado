package presentacion.Trabajadores.VistasCasos_de_uso;

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

import negocio.Trabajadores.TTrabajador;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VAltaTrabajador extends JFrame implements IGUI {

	private JFrame jframe;
	private ArrayList<JTextField> textFields;
	
	public VAltaTrabajador(JFrame jframe) {
		super("Nuevo Trabajador");
		setTitle("Alta Trabajador");
		textFields = new ArrayList<JTextField>();
		this.jframe = jframe;
		init_GUI();
	}

	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_ALTA_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "Trabajador creado con exito con id: " + (int)datos);
		} else if (evento == Evento.RES_ALTA_TRABAJADOR_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido añadir el trabajador");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VTRABAJADOR, null);
	}

	public void init_GUI() {
		jframe.getContentPane().removeAll();

		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		// BACK BUTTON
		JPanel buttonPanel = backButtonContainer();

		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Mail");
		names.add("DNI");

		FormComponent formComponent = new FormComponent(names, "Alta Trabajador", textFields, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
									&& textFields.get(2).getText().length() > 2) {
					TTrabajador trabajador = new TTrabajador();
					trabajador.setNombre(textFields.get(0).getText());
					trabajador.setMail(textFields.get(1).getText());
					trabajador.setDNI(textFields.get(2).getText());
					Controlador.obtenerInstancia().accion(Evento.GUARDAR_TRABAJADOR, trabajador);
				} else {
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
				}
				
			}
		});

		jframe.add(mainPanel);
		mainPanel.add(buttonPanel);
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
		backButton.setToolTipText("Volver a Trabajadores");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VTRABAJADOR, null);
			}
		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}
}