package presentacion.Clientes.VistasCasos_de_Uso;

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
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import negocio.Clientes.TCliente;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class VModificarCliente extends JFrame implements IGUI {
	private ArrayList<JTextField> textFields;
	private TCliente cliente;
	private JFrame jframe;

	
	public VModificarCliente(JFrame jframe, TCliente cliente){
		super("Editar Cliente");
		setTitle("Modificar Datos de Cliente");
		textFields = new ArrayList<JTextField>();
		this.cliente = cliente;
		this.jframe = jframe;
		init_GUI();
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
		names.add("Mail");
		names.add("DNI");
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(cliente.getNombre());
		values.add(cliente.getMail());
		values.add(cliente.getDNI());
		
		FormComponent formComponent = new FormComponent(names, "Editar Cliente", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
						&& textFields.get(2).getText().length() > 2) {
					cliente.setNombre(textFields.get(0).getText());
					cliente.setMail(textFields.get(1).getText());
					cliente.setDNI(textFields.get(2).getText());
					
					Controlador.obtenerInstancia().accion(Evento.UPDATE_CLIENTE, cliente);
				}else{
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
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
	
	private JPanel backButtonContainer(){
		
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		
		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));	
		backButton.setToolTipText("Volver a Clientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VCLIENTE, null);
			}
			
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
	
	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_MODIFICAR_CLIENTE_OK){
			JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
		}else if(evento == Evento.RES_MODIFICAR_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar cliente");
		}
		
		Controlador.obtenerInstancia().accion(Evento.CREAR_VCLIENTE, null);
	}
}