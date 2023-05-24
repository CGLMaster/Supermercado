package presentacion.Productos.VistasCasos_de_UsoPr;

import javax.swing.JFrame;

import presentacion.Date;
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Marcas.TMarca;
import negocio.Productos.TProdNoPerecedero;
import negocio.Productos.TProdPerecedero;
import negocio.Productos.TProducto;
import negocio.Secciones.TSeccion;

@SuppressWarnings("serial")
public class VAltaProducto extends JFrame implements IGUI {
	
	private JFrame jframe;
	private ArrayList<JTextField> textFields;
	JComboBox<String> selTipe, selNoPer;
	JTextField date;
	
	public VAltaProducto(JFrame jframe){
		textFields = new ArrayList<JTextField>();
		this.jframe = jframe;
		init_GUI();
	}

	public void init_GUI(){
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Main Panel.
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JPanel buttonPanel = backButtonContainer();
		
		//Panel Seleccion Perecedero/No perecedero.
		selTipe = new JComboBox<String>();
		selTipe.setToolTipText("Tipo de producto");
		selTipe.addItem("Perecedero");
		selTipe.addItem("No Perecedero");
		
		JLabel noPerInf = new JLabel("Tipo:   ");
		noPerInf.setVisible(false);
		selNoPer = new JComboBox<String>();
		selNoPer.setToolTipText("Tipo de envase");
		selNoPer.addItem("Enlatados");
		selNoPer.addItem("Plastificados");
		selNoPer.addItem("Encartonados");
		selNoPer.addItem("Embotellado");
		selNoPer.setVisible(false);
		
		JLabel dateInf = new JLabel("Fecha de caducidad:   ");
		date = new JTextField(10);
		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel,BoxLayout.X_AXIS));
		selectionPanel.add(Box.createRigidArea(new Dimension(20,0)));
		selectionPanel.add(selTipe);
		selectionPanel.add(Box.createRigidArea(new Dimension(20,0)));
		selTipe.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selTipe.getSelectedItem().equals("No Perecedero")){
					dateInf.setVisible(false);
					date.setVisible(false); 
					noPerInf.setVisible(true);
					selNoPer.setVisible(true);
				}else if(selTipe.getSelectedItem().equals("Perecedero")){
					dateInf.setVisible(true);
					date.setVisible(true);
					noPerInf.setVisible(false);
					selNoPer.setVisible(false);
				}
				
			}}
		);
		selectionPanel.add(dateInf);
		selectionPanel.add(date);
		selectionPanel.add(noPerInf);
		selectionPanel.add(selNoPer);
		selectionPanel.add(Box.createRigidArea(new Dimension(20,0)));
		selectionPanel.setMaximumSize(new Dimension(440,30));
		
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Stock");
		names.add("Precio");
		names.add("Marca(ID)");
		names.add("Seccion(ID)");
		
		
		FormComponent formComponent = new FormComponent(names, "Alta Producto", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				TProducto pr;
				TMarca m = new TMarca();
				TSeccion s = new TSeccion();
				try{
					if(selTipe.getSelectedItem().equals("Perecedero")){
						String[] dateNums = date.getText().split("/");
						Date sqlDate = new Date(0,0,0);
						
						if(dateNums.length==3){
							int year = Integer.parseInt(dateNums[0]);
							int month = Integer.parseInt(dateNums[1]);
							int day = Integer.parseInt(dateNums[2]);
							sqlDate = new Date(year,month,day);	
						}
						
						pr = new TProdPerecedero(textFields.get(0).getText(),Integer.parseInt(textFields.get(1).getText()),
								Double.parseDouble(textFields.get(2).getText()),sqlDate);
					}else{
						pr = new TProdNoPerecedero(textFields.get(0).getText(),Integer.parseInt(textFields.get(1).getText()),
								Double.parseDouble(textFields.get(2).getText()), selNoPer.getSelectedItem().toString());
					}
					m.setId(Integer.parseInt(textFields.get(3).getText()));
					s.setId(Integer.parseInt(textFields.get(4).getText()));
					pr.setMarca(m);
					pr.setSeccion(s);
					Controlador.obtenerInstancia().accion(Evento.GUARDAR_PRODUCTO, pr);
					
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "formato invalido: debe introducir valores numericos en:\n marca, seccion y fecha(yyyy/mm/dd).");
				}
				
			}
		});
		
		jframe.add(mainPanel);
		mainPanel.add(buttonPanel);
		mainPanel.add(selectionPanel);
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
		backButton.setToolTipText("Volver a Productos");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);	
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRODUCTO, null);
			}
			
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
	
	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_ALTA_PRODUCTO_OK ){
			JOptionPane.showMessageDialog(null, "Producto creado correctamente con ID: " + (int)datos);
			Controlador.obtenerInstancia().accion(Evento.CREAR_VPRODUCTO, null);
		}else if(evento == Evento.RES_ALTA_PRODUCTO_KO && (int) datos == -4){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir el Producto, no puede poner stock o precio negativo.");
		}
		else if(evento == Evento.RES_ALTA_PRODUCTO_KO && (int) datos == -2){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir el Producto, revise la fecha(yyyy/mm/dd).");
		}
		else if(evento == Evento.RES_ALTA_PRODUCTO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido añadir el Producto, revise los datos");
		}
	}
}