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
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import negocio.Productos.TProducto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VModificarPr extends JFrame implements IGUI {
	private ArrayList<JTextField> textFields;
	private TProducto producto;
	private JFrame jframe;
	JComboBox<String> selNoPer;
	JTextField tFecha;
	private String[] nList= {"Enlatados", "Encartonados" , "Plastificados", "Embotellados"};

	public VModificarPr(JFrame frame, TProducto producto){
		super("Editar Proucto");
		setTitle("Modificar Datos de Producto");
		textFields = new ArrayList<JTextField>();
		this.producto = producto;
		jframe = frame;
		init_GUI();
	}
	

	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_MODIFICAR_PRODUCTO_OK){
			JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
		}
		else if(evento == Evento.RES_MODIFICAR_PRODUCTO_KO && (int)datos == -2){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar producto, revise los datos");
		}else if(evento == Evento.RES_MODIFICAR_PRODUCTO_KO && (int)datos == -3){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar producto, revise la fecha(yyyy/mm/dd)");
		}else if(evento == Evento.RES_MODIFICAR_PRODUCTO_KO && (int)datos == -1){
			JOptionPane.showMessageDialog(null, "No se ha podido modificar producto, revise los datos, puede que no existan los ids proporcionados");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VPRODUCTO, null);
	}
	
	private void init_GUI() {
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		JPanel backButtonContainer = backButtonContainer();
		
		// SELECTION PANEL
		JPanel selectionPanel = new JPanel();
		selectionPanel.add(Box.createRigidArea(new Dimension(20,0)));
		selectionPanel.setLayout(new BoxLayout(selectionPanel,BoxLayout.X_AXIS));
		selectionPanel.setMaximumSize(new Dimension(440,30));
		JLabel pTipo = new JLabel("Tipo:  ");
		selectionPanel.add(pTipo);
		selectionPanel.add(Box.createRigidArea(new Dimension(20,0)));
		if(producto.getTipo() !=null){
			pTipo.setText(pTipo.getText() + "No perecedero");
			selNoPer = new JComboBox<String>();
			for(String s :nList){
				selNoPer.addItem(s);
			}
			selNoPer.setSelectedItem(producto.getTipo());
			selectionPanel.add(selNoPer);
			selectionPanel.add(Box.createRigidArea(new Dimension(20,0)));
			
		}else{
			pTipo.setText(pTipo.getText() + "Perecedero");
			tFecha = new JTextField(10);
			tFecha.setText(producto.getFecha().toString());
			JLabel lfecha = new JLabel("Fecha de caducidad:  ");
			selectionPanel.add(lfecha);
			selectionPanel.add(tFecha);
			selectionPanel.add(Box.createRigidArea(new Dimension(20,0)));
		}
		
		
		
		// FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Precio");
		names.add("Stock");
		names.add("Marca");
		names.add("Seccion");
		
		
		
		ArrayList<String> values = new ArrayList<String>();
		values.add(producto.getNombre());
		values.add(Double.toString(producto.getPrecio()));
		values.add(Integer.toString(producto.getStock()));
		values.add(Integer.toString(producto.getMarca().getId()));
		values.add(Integer.toString(producto.getSeccion().getId()));
		
		
		FormComponent formComponent = new FormComponent(names, "Editar Producto", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					producto.setNombre(textFields.get(0).getText());
					producto.setPrecio(Double.parseDouble(textFields.get(1).getText()));
					producto.setStock(Integer.parseInt(textFields.get(2).getText()));
					producto.getMarca().setId(Integer.parseInt(textFields.get(3).getText()));
					producto.getSeccion().setId(Integer.parseInt(textFields.get(4).getText()));
					if(producto.getTipo() != null){
						System.out.println(selNoPer.getSelectedItem().toString());
						producto.setTipo(selNoPer.getSelectedItem().toString());
					}else{
						String[] dateNums = tFecha.getText().split("/");
						Date sqlDate = new Date(0,0,0);;
						if(dateNums.length==3){
							int year = Integer.parseInt(dateNums[0]);
							int month = Integer.parseInt(dateNums[1]);
							int day = Integer.parseInt(dateNums[2]);
							sqlDate = new Date(year,month,day); 	
						}
						producto.setFecha(sqlDate);
					}
					Controlador.obtenerInstancia().accion(Evento.UPDATE_PRODUCTO, producto);
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "formato invalido: debe introducir valores numericos en:\n marca, seccion y fecha.");
				}
			}
		});
		
		formComponent.setValues(values);
		
		
		
		// CONSTUIR VISTA
		jframe.add(mainPanel);
		mainPanel.add(backButtonContainer);
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
}