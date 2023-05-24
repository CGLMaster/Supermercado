package presentacion.Compras;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import negocio.Compras.TProductoCantidad;
import negocio.Trabajadores.TTrabajador;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Compras.VistasCasos_de_Uso.VmostrarAyuda;
import presentacion.Controlador.Controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VCompra extends JFrame implements IGUI, ActionListener {
	
	private JFrame jframe;
	
	private HashMap<String, Object> args;
	
	private TreeMap<Integer, TProductoCantidad> productosEnCarrito;
	private JLabel total;
	private TTrabajador loggedTrabajador;
	
	@SuppressWarnings("unchecked")
	public VCompra(JFrame jframe, HashMap<String, Object> args){
		this.jframe = jframe;
		this.args = args;
	
		this.productosEnCarrito = (TreeMap<Integer, TProductoCantidad>)args.get("productosEnCarrito");
		if(productosEnCarrito == null){
			this.productosEnCarrito = new TreeMap<Integer, TProductoCantidad>();
			args.put("productosEnCarrito", this.productosEnCarrito);
		}
		
		this.loggedTrabajador = (TTrabajador)args.get("loggedTrabajador");
		
		init_GUI();
	}

	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_ANIADIR_PRODUCTO_COMPRA_KO ){
			JOptionPane.showMessageDialog(null, (String)datos);
		}
		
	}

	public void actionPerformed(ActionEvent e) {

	}
	
	public void init_GUI(){
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,500));


		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));			
			
		// AÑADIR PRODUCTOS CONTAINER
		JPanel addProductsContainer = new JPanel(new GridLayout(0, 3, 10, 10));
		addProductsContainer.setMaximumSize(new Dimension(1000, 30));
		addProductsContainer.setOpaque(false);
			
			// ID PRODUCTO INPUT 
			JTextField idInput = new JTextField(6);
			idInput.setText("ID de producto");
			idInput.setForeground(Color.GRAY);
			idInput.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {

					idInput.setText(null);
					idInput.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				
			});;
			
			// ID PRODUCTO INPUT 
			JTextField cantidadInput = new JTextField(6);
			cantidadInput.setText("Cantidad");
			cantidadInput.setForeground(Color.GRAY);
			cantidadInput.addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent e) {

					cantidadInput.setText(null);
					cantidadInput.setForeground(Color.BLACK);
				}

				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				
			});	

			
			// AÑADIR BUTTON
			JButton addButton = new JButton("Añadir");
			addButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("añadir_icon.png"))));
			addButton.setBackground(new Color(39, 174, 95));
			addButton.setForeground(Color.white);
			addButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
				
					try{
						
						args.put("id", Integer.parseInt(idInput.getText()));
						
						int cantidad;
						
						if(productosEnCarrito.containsKey(Integer.parseInt(idInput.getText()))){
							cantidad = productosEnCarrito.get(Integer.parseInt(idInput.getText())).getCantidad() + Integer.parseInt(cantidadInput.getText());
						}
						else{
							cantidad = Integer.parseInt(cantidadInput.getText());
						}
						
						if(cantidad <= 0)
							throw new Exception();
						
						args.put("cantidad", cantidad);
						
						args.put("productosEnCarrito", productosEnCarrito);
					}
					catch(Exception exception){
						JOptionPane.showMessageDialog(null, "ID y cantidad deben ser números enteros positivos");
						return; 
					}

					Controlador.obtenerInstancia().accion(Evento.ANIADIR_PRODUCTO_COMPRA, args);
				}
			});
			
		
		// PRODUCTOS EN CARRITO CONTAINER
		JPanel gridOutsideProductsContainer = new JPanel(new GridLayout(0,1)); // Esto es para que ocupe todo el espacio que sobra
		JPanel productosContainer = new JPanel();
		productosContainer.setLayout(new BoxLayout(productosContainer, BoxLayout.Y_AXIS));
		productosContainer.setBorder(new EmptyBorder(10, 10, 10, 10));
		productosContainer.setBackground(new Color(197, 233, 205));
		
		//PANEL DEL FINAL
		
		JPanel bottomContainer = bottomContainer();
		
		// CONSTRUIR VISTA
		jframe.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);
		
			// BACK BUTTON
			contentContainer.add(backButtonContainer());
			
			contentContainer.add(Box.createRigidArea(new Dimension(0, 15)));
			
			// AÑADIR PRODUCTOS
			contentContainer.add(addProductsContainer);
				addProductsContainer.add(idInput);
				addProductsContainer.add(cantidadInput);
				addProductsContainer.add(addButton);
			
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			
			// PRODUCTOS EN CARRITO
			contentContainer.add(gridOutsideProductsContainer);
			gridOutsideProductsContainer.add(productosContainer);
			
				if(productosEnCarrito.isEmpty()){
					productosContainer.setLayout(new GridLayout(0,1));
					productosContainer.add(new JLabel("Carrito vacío", SwingConstants.CENTER));
				}
				
				for(Map.Entry<Integer,TProductoCantidad> producto : productosEnCarrito.entrySet())
				{
					productosContainer.add(productoPanel(producto.getValue(), producto.getKey()));
					productosContainer.add(Box.createRigidArea(new Dimension(0, 10)));
				}
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(bottomContainer);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	
	private JPanel productoPanel(TProductoCantidad productoCantidad, int id){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 100));
		
		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		// DELETE BUTTON
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("trash_icon.jpg"))));
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				productosEnCarrito.remove(id);
				Controlador.obtenerInstancia().accion(Evento.CREAR_VCOMPRA, args);
			}
			
		});
		
		//PRODUCT INFO CONTAINER
		JPanel productInfoContainer = new JPanel();
		productInfoContainer.setLayout(new BoxLayout(productInfoContainer,BoxLayout.Y_AXIS));
		productInfoContainer.setOpaque(false);
		productInfoContainer.setAlignmentY(CENTER_ALIGNMENT);
		
		//LABELS
		JLabel nombre = new JLabel(productoCantidad.getProducto() + " X " + productoCantidad.getCantidad());
		nombre.setFont(new Font(nombre.getFont().getName(), Font.PLAIN, 15));
		
		DecimalFormat df = new DecimalFormat("#.##");
		JLabel precio = new JLabel(df.format(productoCantidad.getPrecioTotal()) + " €");

		
		// CONSTRUIR PANEL
		panel.add(productInfoContainer, BorderLayout.WEST);
			productInfoContainer.add(nombre);
			productInfoContainer.add(precio);
		panel.add(buttonContainer, BorderLayout.EAST);
			buttonContainer.add(deleteButton);
		
		return panel;
	}


	
	private double calcularTotal(){
		if(productosEnCarrito.isEmpty()) return 0;
		
		double total = 0;
		
		for(Map.Entry<Integer,TProductoCantidad> producto : productosEnCarrito.entrySet()){
			total += producto.getValue().getPrecioTotal();
		}
		
		return total;
	}
	
	@SuppressWarnings("unused")
	private void actualizarTotal(){
		total.setText("TOTAL: " + calcularTotal() + " €");
	}
	
	private JPanel backButtonContainer() {
		
		JPanel backButtonContainer = new JPanel(new BorderLayout());
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		backButtonContainer.setOpaque(false);
		
		JButton backButton = new JButton();
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setBorderPainted(false);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRINCIPAL, loggedTrabajador);
			}
		});
		
		// TITLE
		JLabel title = new JLabel("Carrito");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		//TOTAL
		DecimalFormat df = new DecimalFormat("#.##");
		total = new JLabel("TOTAL: " + df.format(calcularTotal()) + " €");
		total.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		total.setAlignmentX(CENTER_ALIGNMENT);
		
				
		backButtonContainer.add(backButton, BorderLayout.WEST);
		backButtonContainer.add(total, BorderLayout.EAST);
		
		return backButtonContainer;
	}
	private JPanel bottomContainer(){
		JPanel bottomContainer = new JPanel();
		bottomContainer.setLayout(new BoxLayout(bottomContainer, BoxLayout.X_AXIS));
		bottomContainer.setMaximumSize(new Dimension(1000, 50));
		bottomContainer.setOpaque(false);
		
		
		
		//PAGAR BUTTON
		JButton pagarButton = new JButton("Pagar");
		pagarButton.setBackground(new Color(39, 174, 95));
		pagarButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pago_icon.jpg"))));
		pagarButton.setForeground(Color.white);
		pagarButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_V_PAGAR_COMPRA, args);
			}
		});
		
		
		//AYUDA BUTTON
		JButton botonAyuda = new JButton();
		JDialog vIden = new VmostrarAyuda();
		botonAyuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vIden.setVisible(true);
			}
		});
		
		botonAyuda.setBackground(new Color(237, 237, 237));
		botonAyuda.setBorderPainted(false);
		botonAyuda.setPreferredSize(new Dimension(60, 50));
		botonAyuda.setMinimumSize(new Dimension(40, 50));
		botonAyuda.setMaximumSize(new Dimension(40, 50));
		botonAyuda.setIcon(new ImageIcon((getClass().getClassLoader().getResource("ayuda_icon.png"))));
		botonAyuda.setToolTipText("Necesito ayuda");
		
		bottomContainer.add(Box.createRigidArea(new Dimension(450, 0)));
		bottomContainer.add(pagarButton);
		bottomContainer.add(Box.createRigidArea(new Dimension(400, 0)));
		bottomContainer.add(botonAyuda);
		
		return bottomContainer;
	}
}