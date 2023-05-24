package presentacion.Productos.VistasCasos_de_UsoPr;

import javax.swing.JFrame;

import presentacion.FormComponent;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import negocio.Productos.TProducto;

@SuppressWarnings("serial")
public class VvincularPrProv extends JDialog implements IGUI {
	private ArrayList<JTextField> textFields;
	TProducto producto;
	
	public VvincularPrProv(JFrame frame, TProducto pr){	
		super(frame, true);
		producto = pr;
		this.setTitle("Vincular "+ pr.getNombre());
		textFields = new ArrayList<JTextField>();
		init_GUI();
	}
	
	private void init_GUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
		//FORM COMPONENT
		ArrayList<String> names = new ArrayList<String>();
		names.add("ID Proveedor");
		names.add("Precio de Suministro");
				
		FormComponent formComponent = new FormComponent(names, "Vincular Producto a Proveedor", textFields, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					Number[] ids = {producto.getID() ,Integer.parseInt(textFields.get(0).getText()), Double.parseDouble(textFields.get(1).getText())};
					setVisible(false);
					Controlador.obtenerInstancia().accion(Evento.VINCULAR_PRODUCTO_PROVEEDOR, ids);
				}catch(NumberFormatException e){
					JOptionPane.showMessageDialog(null, "ID no valido.");
				}
			}
		});


		// CONSTRUIR VISTA
		this.add(mainPanel);
		mainPanel.add(formComponent);
				
				
		this.pack();
		this.setVisible(true);
		
	}

	public void actualizar(int evento, Object datos) {
		
	}
}