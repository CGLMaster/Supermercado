package presentacion.Secciones.VistasCasos_de_Uso;

import javax.swing.JFrame;

import presentacion.FormComponent;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
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
public class VEditarSeccion extends JFrame implements IGUI {
	
	private ArrayList<JTextField> textFields;
	private TSeccion seccion;
	private JFrame jframe;
	
	public VEditarSeccion(TSeccion seccion, JFrame jframe) throws HeadlessException {
		jframe.setTitle("Modificar Datos de Seccion");
		textFields = new ArrayList<JTextField>();
		this.seccion = seccion;
		this.jframe = jframe;
		init_GUI();
	}
	private void init_GUI() {
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JPanel backButtonContainer = backButtonContainer();
		
		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Zona");
		names.add("Pasillo");
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(seccion.getZona());
		values.add(Integer.toString(seccion.getPasillo()));
		
		FormComponent formComponent = new FormComponent(names, "Editar Seccion", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					if(textFields.get(0).getText().length() > 1){
						seccion.setZona(textFields.get(0).getText());
						seccion.setPasillo(Integer.parseInt(textFields.get(1).getText()));
						Controlador.obtenerInstancia().accion(Evento.UPDATE_SECCION, seccion);
					}else{
						JOptionPane.showMessageDialog(null, "Introduzca una zona valida.");
					}
				}catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(null, "Introduzca un pasillo valido(valor numerico).");
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
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setToolTipText("Volver a Secciones");
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VSECCION, null);
			}
			
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
	
	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_MODIFICAR_SECCION_OK){
			JOptionPane.showMessageDialog(null, "Seccion modificada correctamente");
		}
		else if(evento == Evento.RES_MODIFICAR_SECCION_KO && (int)datos == -2){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar la Seccion, pasillo no valido");
		}else if(evento == Evento.RES_MODIFICAR_SECCION_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar la Seccion");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VSECCION, null);
	}
	
}
