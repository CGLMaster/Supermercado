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

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Clientes.TCliente;


@SuppressWarnings("serial")
public class VAltaCliente extends JFrame implements IGUI {
	
	private ArrayList<JTextField> textFields;
	private JFrame jframe;
	
	public VAltaCliente(JFrame father){
		super("Nuevo Cliente");
		setTitle("Alta Cliente");
		textFields = new ArrayList<JTextField>();
		jframe = father;
		init_GUI();
	}

	public void actualizar(int evento, Object datos) {	
		if(evento == Evento.RES_ALTA_CLIENTE_OK ){
			JOptionPane.showMessageDialog(null, "Cliente creado correctamente con ID: " + datos);
			Controlador.obtenerInstancia().accion(Evento.CREAR_VCLIENTE, null);
		}
		else if(evento == Evento.RES_ALTA_CLIENTE_KO && (int)datos == -1){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir cliente");
		}else if(evento == Evento.RES_ALTA_CLIENTE_KO && (int)datos == -2){
			JOptionPane.showMessageDialog(null, "Nombre no valido");
		}else if(evento == Evento.RES_ALTA_CLIENTE_KO && (int)datos == -3){
			JOptionPane.showMessageDialog(null, "DNI no valido");
		}
	}

	public void init_GUI(){
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		jframe.add(mainPanel);
		
		JPanel backButtonContainer = backButtonContainer();
		mainPanel.add(backButtonContainer);
		
		//FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Mail");
		names.add("DNI");
		
		
		FormComponent formComponent = new FormComponent(names, "Alta Cliente", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2
						&& textFields.get(2).getText().length() > 2) {
				
					TCliente cliente = new TCliente();
					cliente.setNombre(textFields.get(0).getText());
					cliente.setMail(textFields.get(1).getText());
					cliente.setDNI(textFields.get(2).getText());
					
					Controlador.obtenerInstancia().accion(Evento.GUARDAR_CLIENTE, cliente);
				}else{
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
				}
			}
		});
		
		mainPanel.add(formComponent);
		
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		//BACK BUTTON
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		
		JButton backButton = new JButton();
		
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setToolTipText("Volver a Clientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButtonContainer.add(backButton);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VCLIENTE, null);
			}
			
		});
		
		return backButtonContainer;
	}
}