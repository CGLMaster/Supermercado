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
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Marcas.TMarca;

@SuppressWarnings("serial")
public class VCrearMarca extends JFrame implements IGUI {
	
	private JFrame jframe;
	
	private ArrayList<JTextField> textFields;
	
	public VCrearMarca(JFrame jframe){
		super("Crear Marca");
		textFields = new ArrayList<JTextField>();
		this.jframe = jframe;
		init_GUI();
	}
	
	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_ALTA_MARCA_OK ){
			JOptionPane.showMessageDialog(null, "Marca creada correctamente cond id: " + datos);
			Controlador.obtenerInstancia().accion(Evento.CREAR_VMARCA, null);
		}
		else if(evento == Evento.RES_ALTA_MARCA_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir marca");
		}
	}
	
	public void init_GUI(){
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		//BACK BUTTON
		JPanel backbuttonContainer = backButtonContainer();
		
		//FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Web");
		
		FormComponent formComponent = new FormComponent(names, "Crear Marca", textFields, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TMarca marca = new TMarca(textFields.get(0).getText());
				if(textFields.get(1).getText().length() > 2){
					marca.setWeb(textFields.get(1).getText());
				}
				
				if(textFields.get(0).getText().length()>1) Controlador.obtenerInstancia().accion(Evento.GUARDAR_MARCA, marca);
				else JOptionPane.showMessageDialog(null, "Nombre Intoducido no válido");
			}
		});
		
		
		//BUILD
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
}