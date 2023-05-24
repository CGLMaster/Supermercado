package presentacion.Productos.VistasCasos_de_UsoPr;

import javax.swing.JFrame;

import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import negocio.Productos.TProducto;

import javax.swing.JLabel;


@SuppressWarnings("serial")
public class VBuscarProducto extends JFrame implements IGUI {
	private final String msgProv = "No tiene proveedor vinculado.";
	TProducto producto;
	JFrame jframe;
	
	public VBuscarProducto(JFrame jframe, TProducto producto){
		this.jframe = jframe;
		this.producto = producto;
		init_GUI();
	}
	
	private void init_GUI() {
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,500));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		// BACK BUTTON 
		JPanel backButtonContainer = backButtonContainer();
		
		// TITULO
		JLabel titleLabel = new JLabel("Producto");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));
		
		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);
		
		// INFO
		JLabel idLabel = new JLabel("ID: " + producto.getID());
		JLabel nombreLabel = new JLabel("Nombre: " + producto.getNombre());
		JLabel tipoProd = new JLabel("Tipo: ");
		JLabel especial = new JLabel();
		if(producto.getTipo() == null){
			tipoProd.setText(tipoProd.getText() + "Perecedero");
			especial.setText("Fecha de caducidad: " + producto.getFecha());
		}else{
			tipoProd.setText(tipoProd.getText() + "No perecedero");
			especial.setText("Tipo de no perecedero: " + producto.getTipo());
		}
		JLabel precioLabel = new JLabel("Precio: " + producto.getPrecio());
		JLabel stockLabel = new JLabel("Stock: " + producto.getStock());
		JLabel marcaLabel = new JLabel("Marca: " + producto.getMarca().getNombre());
		JLabel seccionLabel = new JLabel("Seccion: " + producto.getSeccion().getZona() + " Pasillo: " + producto.getSeccion().getPasillo());
		JLabel proveedorLabel = new JLabel("Proveedor: ");
		if(producto.getProveedor() == null){
			proveedorLabel.setText(proveedorLabel.getText() + msgProv);
		}else{
			proveedorLabel.setText(proveedorLabel.getText() + producto.getProveedor().getNombre());
		}
		
		
		
		// CONSTRUIR VISTA
		jframe.add(mainPanel);
		mainPanel.add(backButtonContainer);
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
			contentContainer.add(idLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(nombreLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(tipoProd);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(especial);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(precioLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(stockLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(marcaLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(seccionLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(proveedorLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));
		
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

	}
}