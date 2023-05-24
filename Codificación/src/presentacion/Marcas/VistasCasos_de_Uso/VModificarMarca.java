package presentacion.Marcas.VistasCasos_de_Uso;

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
import javax.swing.JTextField;

import negocio.Marcas.TMarca;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VModificarMarca extends JFrame implements IGUI {
	
	private ArrayList<JTextField> textFields;
	private TMarca marca;
	private JFrame jframe;

	public VModificarMarca (TMarca marca, JFrame jframe){
		super("Editar Marca");
		textFields = new ArrayList<JTextField>();
		this.marca = marca;
		this.jframe = jframe;
		init_GUI();
	}
	
	public void init_GUI() {
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JPanel backButtonContainer = backButtonContainer();
		
		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Web");
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(marca.getNombre());
		values.add(marca.getWeb());
		
		FormComponent formComponent = new FormComponent(names, "Editar Marca", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				marca.setNombre(textFields.get(0).getText());
				marca.setWeb(textFields.get(1).getText());
				
				Controlador.obtenerInstancia().accion(Evento.UPDATE_MARCA, marca);
			}
		});
		
		formComponent.setValues(values);
		
		// CONSTRUIR VISTA
		jframe.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(formComponent);
		
		jframe.pack();
		jframe.setVisible(true);
	}

		private JPanel backButtonContainer() {
		
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		
		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VMARCA, null);
			}
		});
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}

	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_MODIFICAR_MARCA_OK ){
			JOptionPane.showMessageDialog(null, "Marca modificada correctamente");
		}
		else if(evento == Evento.RES_MODIFICAR_MARCA_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar marca");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VMARCA, null);
	}
	
}