
package presentacion.Productos.VistasCasos_de_UsoPr;

import javax.swing.JFrame;

import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

import negocio.Productos.TProducto;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class VBajaProducto extends JDialog implements IGUI {
	private final String msgProv = "No tiene proveedor vinculado.";
	TProducto producto;

	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_BAJA_PRODUCTO_OK){
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el producto con éxito");
		}
		else if(evento == Evento.RES_BAJA_PRODUCTO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja el producto");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VPRODUCTO, null);
	}
	
	public VBajaProducto(JFrame frame,TProducto producto){
		super(frame, true);
		this.producto = producto;
		init_GUI();
	}
	
	public void init_GUI(){	
		this.setLocation(getParent().getLocation().x + 100, getParent().getLocation().y + 100);
		this.setTitle("Eliminar Producto");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,350));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		// TITULO
		JLabel titleLabel = new JLabel("¿Desea eliminar el producto con la siguiente informacion?");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 15));
		
		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);
		
		// INFO
		JLabel idLabel = new JLabel("ID: " + producto.getID());
		JLabel nombreLabel = new JLabel("Nombre: " + producto.getNombre());
		JLabel precioLabel = new JLabel("Precio: " + producto.getPrecio());
		JLabel stockLabel = new JLabel("Stock: " + producto.getStock());
		JLabel idMLabel = new JLabel("ID_Marca: " + producto.getMarca().getId());
		JLabel idPLabel;
		if(producto.getProveedor() == null){
			idPLabel = new JLabel("ID_Proveedor: " + msgProv);
		}else{
			idPLabel = new JLabel("ID_Proveedor: " + producto.getProveedor().getId());
		}
		JLabel idSLabel = new JLabel("ID_Seccion: " + producto.getSeccion().getId());
		
		// CONSTRUIR VISTA
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(idLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(nombreLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(precioLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(stockLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(idMLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(idPLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(idSLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// BOTONES
		JButton yesButton = new JButton("YES");
		JButton noButton = new JButton("NO");
		yesButton.setBackground(new Color(39, 174, 95));
		yesButton.setForeground(Color.white);
		yesButton.setBorderPainted(false);
			
		noButton.setBackground(new Color(236, 115, 115));
		noButton.setForeground(Color.white);
		noButton.setBorderPainted(false);
			
		noButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
				
			});
			
			
		yesButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					Controlador.obtenerInstancia().accion(Evento.BAJA_PRODUCTO, producto.getID());
				}
				
			});
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonsPanel.add(yesButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonsPanel.add(noButton);
		
		this.add(mainPanel);
		mainPanel.add(buttonsPanel);
		this.pack();
		this.setVisible(true);
		
		
	}
}