package presentacion.Proveedores.VistasCasos_de_Uso_Prov;

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
import javax.swing.JTextField;

import negocio.Proveedores.TProveedor;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class VAltaProveedor extends JFrame implements IGUI {
	
	private JFrame jframe;
	
	private ArrayList<JTextField> textFields;
	
	
	public VAltaProveedor(JFrame jframe){
		super("Nuevo Proveedor");
		textFields = new ArrayList<JTextField>();
		this.jframe = jframe;
		init_GUI();
	}


	public void actualizar(int evento, Object datos) {

		if(evento == Evento.RES_ALTA_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir el proveedor");
		}else if(evento == Evento.RES_ALTA_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "El proveedor se ha creado correctamente con id: "+ (int) datos);
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VPROVEEDOR, null);
		
	}
	
	public void init_GUI(){
		jframe.getContentPane().removeAll();	
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		// BACK BUTTON 
		JPanel backButtonContainer = backButtonContainer();
		
		//FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		
		FormComponent formComponent = new FormComponent(names, "Alta Proveedor", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TProveedor proveedor = new TProveedor();
				proveedor.setNombre(textFields.get(0).getText());
				
				Controlador.obtenerInstancia().accion(Evento.GUARDAR_PROVEEDOR, proveedor);
			}
		});

		
		// CONSTRUIR VISTA
		jframe.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(formComponent);
		
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		
		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Proveedores");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButtonContainer.add(backButton);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPROVEEDOR, null);
			}
			
		});
	
		
		return backButtonContainer;
	}
}