package presentacion.Productos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import negocio.Productos.TProducto;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;
import presentacion.Productos.VistasCasos_de_UsoPr.VBajaProducto;
import presentacion.Productos.VistasCasos_de_UsoPr.VvincularPrProv;

@SuppressWarnings("serial")
public class VProducto extends JFrame implements IGUI {
	
	private JFrame jframe;
	private Set<TProducto> productos, filterSet;
	JComboBox<String> selMarca, selProv, selSecc;
	
	public VProducto(JFrame jframe, Set<TProducto> productos){
		this.jframe = jframe;
		this.productos = productos;
		init_GUI();
	}
	
	private void init_GUI() {
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(700,500));
		jframe.add(mainPanel);
		
		// FILTER PANEL
		JPanel fPanel = new JPanel();
		fPanel.setLayout(new BoxLayout(fPanel,BoxLayout.X_AXIS));
		
		
		selMarca = new JComboBox<String>();
		selProv = new JComboBox<String>();
		selSecc = new JComboBox<String>();
		
		selMarca.addItem("Marca");
		selProv.addItem("Proveedor");
		selSecc.addItem("Seccion");
		
		initComboBoxes();
		
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border loweredbevel = BorderFactory.createLoweredBevelBorder();
		fPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		JButton apply = new JButton("apply");
		apply.setBackground(new Color(39, 174, 95));
		apply.setForeground(Color.white);
		apply.setBorder(BorderFactory.createCompoundBorder(loweredbevel, raisedbevel));
		apply.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Object[] args = new Object[2];
				String[] f = new String[3];
				
				f[0] = selMarca.getSelectedItem().toString();
				f[1] = selProv.getSelectedItem().toString();
				f[2] = selSecc.getSelectedItem().toString();
				args[0] = f;
				args[1] = productos;
				Controlador.obtenerInstancia().accion(Evento.BUSCAR_PRODUCTOS_MARCA, args);
				
			}
			
		});
		
		JButton clear = new JButton("clear");
		clear.setBackground(new Color(56, 150, 219));
		clear.setBorder(BorderFactory.createCompoundBorder(loweredbevel, raisedbevel));
		clear.setForeground(Color.white);
		clear.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRODUCTO, filterSet);
				
			}
			
		});
		
		JLabel filter = new JLabel("Filter: ");
		fPanel.add(filter);
		fPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		fPanel.add(selMarca);
		fPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		fPanel.add(selProv);
		fPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		fPanel.add(selSecc);
		fPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		fPanel.add(apply);
		fPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		fPanel.add(clear);
		fPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		
		
		fPanel.setMaximumSize(new Dimension(1000,25));
		
		// CONTENT CONTAINER
		
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		contentContainer.setAutoscrolls(true);
		
		mainPanel.add(scrollFrame);
		
		
		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));
		
		// TITLE
		JLabel title = new JLabel("Productos");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(350, 0)));
		
		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_pequeño.png")));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(280, 0)));

			
		icon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRINCIPAL, null); 		
			}		
		});
		
		// NEW PROVEEDOR BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		JButton newButton = new JButton("Nuevo Producto");
		newButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("producto_icon.jpg")));
		newButton.setToolTipText("Añadir nuevo producto");
		newButton.setBackground(new Color(39, 174, 95));
		newButton.setForeground(Color.white);
		newButton.setAlignmentX(CENTER_ALIGNMENT);
		newButton.setAlignmentY(CENTER_ALIGNMENT);
		newButton.setBorder(BorderFactory.createBevelBorder(0));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		buttonPanel.add(newButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		headerContainer.add(buttonPanel);
		headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));
	
		newButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.ALTA_PRODUCTO, null);
				
			}
			
		});
		
		contentContainer.add(headerContainer);
		
		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un producto para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
					
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		contentContainer.add(fPanel);
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		for(TProducto pr : productos){
			contentContainer.add(productoPanel(pr));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel productoPanel(TProducto pr){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 60));
		JPanel buttonContainer = new JPanel(new FlowLayout());
		
		// NEW LINK BUTTON
		JButton linkButton = new JButton();
		linkButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("link_icon.jpg")));
		linkButton.setToolTipText("Vincularlo con un proveedor");
		linkButton.setPreferredSize(new Dimension(25, 25));
		linkButton.setBackground(new Color(39, 174, 95));
		linkButton.setBorderPainted(false);
		if(pr.getProveedor() != null){
			linkButton.setVisible(false);
		}
		
		linkButton.addActionListener(new ActionListener(){

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog vVin = new VvincularPrProv(jframe, pr);
			}
			
		});
		
		
		// NEW UNLINK BUTTON
		JButton unlinkButton = new JButton();
		unlinkButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("unlink_icon.jpg")));
		unlinkButton.setToolTipText("Desvincularlo de su proveedor");
		unlinkButton.setPreferredSize(new Dimension(25, 25));
		unlinkButton.setBackground(new Color(236, 115, 115));
		unlinkButton.setBorderPainted(false);
		if(pr.getProveedor() == null){
			unlinkButton.setVisible(false);
		}
		
		unlinkButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(null, "¿Desea desvincular " + pr.getNombre()+ "\n del proveedor " + pr.getProveedor().getNombre() + "?");
				if(res == 0){
					int[] ids = {pr.getID(),pr.getProveedor().getId()};
					Controlador.obtenerInstancia().accion(Evento.DESVINCULAR_PRODUCTO_PROVEEDOR, ids);
				}
			}
			
		});
		
		// OTROS BOTONES
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.png")));
		editButton.setToolTipText("Editar producto");
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setToolTipText("Eliminar producto");
		
		editButton.setBackground(new Color(39, 174, 95));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);
		
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog vBaja = new VBajaProducto(jframe, pr);		
			}
			
		});
		
		
		editButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.MODIFICAR_PRODUCTO, pr.getID());
			}
			
		});
		
		buttonContainer.add(linkButton);
		buttonContainer.add(unlinkButton);
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		
		//LABEL
		JLabel label = new JLabel(pr.getNombre());
		label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				Controlador.obtenerInstancia().accion(Evento.BUSCAR_PRODUCTO, pr.getID());
				
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		label.setToolTipText("Información del Producto");
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
				
		return panel;
	}
	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_BAJA_PRODUCTO_OK){
			JOptionPane.showMessageDialog(null, "Se ha eliminado el producto con éxito");
		}
		else if(evento == Evento.RES_BAJA_PRODUCTO_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido eliminar el producto");
		}else if(evento == Evento.RES_VINCULAR_PRODUCTO_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "Producto vinculado al proveedor con id: " + datos);
		}else if(evento == Evento.RES_VINCULAR_PRODUCTO_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido vincular el producto con el proveedor");
		}else if(evento == Evento.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "Producto desvinculado con exito");
		}else if(evento == Evento.RES_DESVINCULAR_PRODUCTO_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido des vincular el producto del proveedor");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VPRODUCTO, null);
	}
	
	void initComboBoxes(){
		ArrayList<String> items;
		
		for(int i = 0; i < 3; i++){
			items = new ArrayList<String>();
			for(TProducto pr :productos){
				String[] aux = new String[3]; 
				aux[0] = pr.getMarca().getNombre();
				aux[1] = pr.getSeccion().getZona();
				if(pr.getProveedor() != null){
					aux[2] = pr.getProveedor().getNombre();
				}
				
				if(!items.contains(aux[i])){
					items.add(aux[i]);
					switch(i){
						case 0:
							selMarca.addItem(aux[i]);
						break;
					
						case 1:
							selSecc.addItem(aux[i]);
						break;
						case 2:
							if(aux[i] != null){
								selProv.addItem(aux[i]);
							}
						break;
					}
				}
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	void aplicarFiltro(){
		String[] f = new String[3];
		filterSet = new HashSet();
		filterSet.addAll(productos);
		
		f[0] = selMarca.getSelectedItem().toString();
		f[1] = selProv.getSelectedItem().toString();
		f[2] = selSecc.getSelectedItem().toString();
		
		
		for(TProducto p : productos){
			if(!p.getMarca().getNombre().equals(f[0]) && !f[0].equals("Marca")){
				filterSet.remove(p);
			}
			else if(p.getProveedor() == null && !f[1].equals("Proveedor")){
				filterSet.remove(p);
			}else if(!f[1].equals("Proveedor") && !p.getProveedor().getNombre().equals(f[1])){
				filterSet.remove(p);
			}
			else if(!p.getSeccion().getZona().equals(f[2]) && !f[2].equals("Seccion")){
				filterSet.remove(p);
			}
			
		}
		
		
	}
	
	
	
	
}