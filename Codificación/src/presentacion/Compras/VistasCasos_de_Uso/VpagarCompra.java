package presentacion.Compras.VistasCasos_de_Uso;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import negocio.Compras.TProductoCantidad;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class VpagarCompra extends JFrame implements ActionListener, IGUI {
	
	private JFrame jframe;
	
	private HashMap<String, Object> args;
	
	private TreeMap<Integer, TProductoCantidad> productosEnCarrito;
	private double total;
	
	@SuppressWarnings("unchecked")
	public VpagarCompra(JFrame jframe, HashMap<String, Object> args){
		this.jframe = jframe;
		this.args = args;
		
		this.total = (double)args.get("total");
		this.productosEnCarrito = (TreeMap<Integer, TProductoCantidad>)args.get("productosEnCarrito");
		init_GUI();
	}
	
	public void actionPerformed(ActionEvent e) {

	}

	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_PAGAR_COMPRA_KO ){
			JOptionPane.showMessageDialog(null, "La compra no se ha podido realizar");
		}else if(evento == Evento.RES_PAGAR_COMPRA_OK){			
			pago();
			JOptionPane.showMessageDialog(null, "Pago realizado con exito. ¡Gracias por su compra!");
		}
		else if(evento == Evento.RES_BUSCAR_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "El cliente no existe en la base de datos");
		}
		
	}
	
	public void pago(){
		jframe.getContentPane().removeAll();
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,500));
		mainPanel.setBackground(new Color(197, 233, 205));
		jframe.add(mainPanel);
		
		//CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);
		contentContainer.setBackground(new Color(197, 233, 205));
		
		//HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));
			
			//ICONO DE MENU
			JLabel icon = new JLabel();
			icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_IS_pequeño.png"))));
			icon.setBackground(new Color(39, 174, 95));
			icon.setAlignmentX(CENTER_ALIGNMENT);
			headerContainer.add(icon);
		
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0,20)));
		
		//HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Pago realizado con éxito");
		help.setForeground(new Color(57, 119, 70));
		help.setFont(new Font("sans-serif", 1, 17));
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.setBackground(new Color(197, 233, 205));
		helpPanel.add(help);
		
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0,20)));
		
		//DESPEDIDA 1
		JPanel goodbye = new JPanel();
		goodbye.setMaximumSize(new Dimension(1000, 350));
		goodbye.setBackground(new Color(197, 233, 205));
		goodbye.setLayout(new BoxLayout(goodbye, BoxLayout.Y_AXIS));
			//MENSAJE
			JLabel mensaje = new JLabel("¡Gracias por comprar en Supermercado!");
			mensaje.setFont(new Font("sans-serif", 1, 25));
			mensaje.setForeground(new Color(57, 119, 70));
			mensaje.setBackground(new Color(197, 233, 205));
			mensaje.setAlignmentX(CENTER_ALIGNMENT);
			//GIF
			JLabel gif = new JLabel();
			gif.setIcon(new ImageIcon((getClass().getClassLoader().getResource("despedida.gif"))));
			gif.setBackground(new Color(197, 233, 205));
			gif.setAlignmentX(CENTER_ALIGNMENT);
		
		goodbye.add(mensaje);
		goodbye.add(Box.createRigidArea(new Dimension(0,10)));
		goodbye.add(gif);
		
		contentContainer.add(goodbye);
		contentContainer.add(Box.createRigidArea(new Dimension(0,20)));
		
		//DESPEDIDA 2
		JPanel byePanel = new JPanel();
		byePanel.setBackground(new Color(197, 233, 205));
		JLabel bye = new JLabel("¡VUELVA PRONTO!");
		bye.setForeground(new Color(57, 119, 70));
		bye.setFont(new Font("sans-serif", 1, 25));
		byePanel.setMaximumSize(new Dimension(1000, 60));
		byePanel.add(bye);
				
		contentContainer.add(byePanel);
		mainPanel.add(contentContainer);
		
		jframe.pack();
		jframe.setVisible(true);
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
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setAutoscrolls(true);
		
		//TOTAL CONTAINER
		JPanel totalContainer = new JPanel();
		totalContainer.setLayout(new BoxLayout(totalContainer, BoxLayout.Y_AXIS));
		totalContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		DecimalFormat df = new DecimalFormat("#.##");
		JLabel totalLabel = new JLabel("TOTAL: " + df.format(total) + " €");
		totalLabel.setFont(new Font(totalLabel.getFont().getName(), Font.PLAIN, 30));
		

		// INFORMACION DE PAGO CONTAINER
		JPanel informacionPagoContainer = new JPanel();	
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		
		//HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Seleccione una forma de pago");
		helpPanel.setMaximumSize(new Dimension(1000, 30));
		helpPanel.add(help);
		
		// METODOS DE PAGO BUTTONS CONTAINER
		JPanel buttonsContainer = new JPanel(new FlowLayout());
		buttonsContainer.setMaximumSize(new Dimension(1000, 40));
		
		JButton efectivoButton = new JButton("Efectivo");
		efectivoButton.setBackground(new Color(39, 174, 95));
		efectivoButton.setForeground(Color.white);
		efectivoButton.setFont(new Font("sans-serif", 1, 15));
		efectivoButton.setPreferredSize(new Dimension(100, 30));
		efectivoButton.setBorder(BorderFactory.createBevelBorder(0));
		
		efectivoButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				informacionPagoContainer.removeAll();
				informacionPagoContainer.revalidate();
				informacionPagoContainer.repaint();
				
				informacionPagoContainer.add(crearEfectivoInformacionDePago());
			}
		});
		
		JButton tarjetaButton = new JButton("Tarjeta");
		tarjetaButton.setBackground(new Color(39, 174, 95));
		tarjetaButton.setForeground(Color.white);
		tarjetaButton.setFont(new Font("sans-serif", 1, 15));
		tarjetaButton.setPreferredSize(new Dimension(100, 30));
		tarjetaButton.setBorder(BorderFactory.createBevelBorder(0));
		
		tarjetaButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				
				informacionPagoContainer.removeAll();
				informacionPagoContainer.revalidate();
				informacionPagoContainer.repaint();
				
				informacionPagoContainer.add(crearTarjetaInformacionDePago());
			}
		});
		
		// Construir vista
		jframe.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);
		
			// BACK BUTTON
			contentContainer.add(backButtonContainer());
		
			contentContainer.add(Box.createRigidArea(new Dimension(0, 20)));
			
			//TOTAL
			contentContainer.add(totalContainer);
			totalContainer.add(totalLabel);
			
			contentContainer.add(Box.createRigidArea(new Dimension(0, 25)));
			
			//HELP
			contentContainer.add(helpPanel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			
			// METODOS DE PAGO BUTTONS
			contentContainer.add(buttonsContainer);
			buttonsContainer.add(efectivoButton);
			buttonsContainer.add(tarjetaButton);
			
			contentContainer.add(Box.createRigidArea(new Dimension(0, 40)));
			
			//INFORMACION DE PAGO
			contentContainer.add(informacionPagoContainer);
			
			
		jframe.pack();
		jframe.setVisible(true);
		
	}
	
	private JPanel backButtonContainer() {

		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 70));

		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);

		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VCOMPRA, args);
			}

		});

		backButtonContainer.add(backButton);

		return backButtonContainer;
	}
	
	
	private JPanel createFormField(String title, ArrayList<JTextField> textFields) {
		
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setOpaque(false);
		container.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel label = new JLabel(title);
		label.setAlignmentX(CENTER_ALIGNMENT);
		
		JTextField input = new JTextField(6);
		input.setMaximumSize(new Dimension(1000, 30));
		input.setPreferredSize(new Dimension(400, 30));
		textFields.add(input);
		
		container.add(label);
		container.add(input);
		
		return container;
	}
	
	private JPanel crearTarjetaInformacionDePago(){
		
		ArrayList<JTextField> textFields = new ArrayList<JTextField>();
		
		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		informacionPagoContainer.setMaximumSize(new Dimension(400, 300));
		informacionPagoContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel imgLabel = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("creditCards.png"))));
		imgLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel expirationDateAndCVVContainer = new JPanel(new GridLayout(1,2,5,5));
		expirationDateAndCVVContainer.setMaximumSize(new Dimension(1000, 60));
		
		JButton submitButton = new JButton("Pagar");
		submitButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pago_icon.jpg"))));
		submitButton.setMaximumSize(new Dimension(1000, 40));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(39, 174, 95));
		submitButton.setForeground(Color.white);
		submitButton.setBorderPainted(false);
		
		JButton boton = new JButton();
		boton.addActionListener(new ActionListener() {
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog vIden = new VmostrarAyuda();
			}
		});
		
		submitButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
							
				try{
					args.put("cantidadPagada", total);
					args.put("idCliente", Integer.parseInt(textFields.get(3).getText()));
					args.put("productosEnCarrito", productosEnCarrito);
				}
				catch(Exception exception){
					JOptionPane.showMessageDialog(null, "ID debe ser un número entero");
					return;
				}
				
				Controlador.obtenerInstancia().accion(Evento.PAGAR_COMPRA, args);
			}
		});
		
		
		informacionPagoContainer.add(imgLabel);
		
		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
		
		informacionPagoContainer.add(createFormField("Card number", textFields));
		
		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
		
		informacionPagoContainer.add(expirationDateAndCVVContainer);
		expirationDateAndCVVContainer.add(createFormField("Expiration Date", textFields));
		expirationDateAndCVVContainer.add(createFormField("CVV", textFields));
		
		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
		
		informacionPagoContainer.add(createFormField("ID de Cliente", textFields));
		
		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
		
		informacionPagoContainer.add(submitButton);
		

		return informacionPagoContainer;
	}
	
	private JPanel crearEfectivoInformacionDePago(){
		
		ArrayList<JTextField> textFields = new ArrayList<JTextField>();
		
		JPanel informacionPagoContainer = new JPanel();
		informacionPagoContainer.setLayout(new BoxLayout(informacionPagoContainer, BoxLayout.Y_AXIS));
		informacionPagoContainer.setMaximumSize(new Dimension(400, 300));
		
		JButton submitButton = new JButton("Pagar");
		submitButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("pago_icon.jpg"))));
		submitButton.setMaximumSize(new Dimension(1000, 40));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(39, 174, 95));
		submitButton.setForeground(Color.white);
		submitButton.setBorderPainted(false);
		
		submitButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
								
				try{
					args.put("cantidadPagada", Double.parseDouble(textFields.get(0).getText()));
					args.put("idCliente", Integer.parseInt(textFields.get(1).getText()));
					args.put("productosEnCarrito", productosEnCarrito);
					
					if((double)args.get("cantidadPagada") < total){
						throw new IllegalArgumentException("La cantidad que usted ha introducido es menor que el precio total de la compra");
					}
				}
				catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "ID y cantidad en efectivo deben ser números enteros");
					return; 
				}
				catch(IllegalArgumentException exception){
					JOptionPane.showMessageDialog(null, exception.getMessage());
					return;
				}
				
				Controlador.obtenerInstancia().accion(Evento.PAGAR_COMPRA, args);
			}
		});
		

		informacionPagoContainer.add(createFormField("Cantidad en efectivo a pagar", textFields));
		
		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
		
		informacionPagoContainer.add(createFormField("ID de Cliente", textFields));
		
		informacionPagoContainer.add(Box.createRigidArea(new Dimension(0, 20)));
		
		informacionPagoContainer.add(submitButton);
		

		return informacionPagoContainer;
	}
}