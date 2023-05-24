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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.Proveedores.TProveedor;


@SuppressWarnings("serial")
public class VModificarProveedor extends JFrame implements IGUI {
	
	private ArrayList<JTextField> textFields;
	private TProveedor proveedor;
	private JFrame jframe;

	public VModificarProveedor(JFrame jframe, TProveedor proveedor){
		super("Editar Proveedor");
		textFields = new ArrayList<JTextField>();
		this.proveedor = proveedor;
		this.jframe = jframe;
		init_GUI();
	}
	

	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_MODIFICAR_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar el proveedor");
		}else if(evento == Evento.RES_MODIFICAR_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "El proveedor se ha modificado correctamente");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VPROVEEDOR, null);
		
	}
	
	public void init_GUI(){
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JPanel backButtonContainer = backButtonContainer();
		
		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(proveedor.getNombre());
		
		FormComponent formComponent = new FormComponent(names, "Editar Proveedor", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFields.get(0).getText().length() > 2){
					proveedor.setNombre(textFields.get(0).getText());
					proveedor.setId(proveedor.getId());
					Controlador.obtenerInstancia().accion(Evento.UPDATE_PROVEEDOR, proveedor);
				}else{
					JOptionPane.showMessageDialog(null, "Nombre Intoducido no válido");
				}
			
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
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Proveedores");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPROVEEDOR, null);
			}
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
}