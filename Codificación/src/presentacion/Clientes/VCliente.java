package presentacion.Clientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;

import javax.swing.BorderFactory;
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
import javax.swing.border.EmptyBorder;

import negocio.Clientes.TCliente;
import presentacion.Clientes.VistasCasos_de_Uso.VBajaCliente;
import presentacion.Controlador.Controlador;


@SuppressWarnings("serial")
public class VCliente extends JFrame implements IGUI {
	private JFrame jframe;
	private Set<TCliente> clientes;
	
	public VCliente(JFrame jframe, Set<TCliente> clientes){
		this.clientes = clientes;
		setTitle("Clientes");
		this.jframe = jframe;
		init_GUI();
	}
	
	private void init_GUI() {
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,500));
		jframe.add(mainPanel);
		
		
		// CONTENT CONTAINER
		
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		contentContainer.setAutoscrolls(true);
		
		mainPanel.add(scrollFrame);
		
		
		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 300));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));
		
		// TITLE
		JLabel title = new JLabel("Clientes");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(360, 0)));
		
		//ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_IS_pequeño.png"))));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(300, 0)));

			
		icon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRINCIPAL, null); 				
			}		
		});
		
		//NEW CLIENTE BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		JButton newClienteButton = new JButton("Nuevo Cliente");
		newClienteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("user_icon.png"))));
		newClienteButton.setToolTipText("Añadir nuevo cliente");
		newClienteButton.setBackground(new Color(39, 174, 95));
		newClienteButton.setForeground(Color.white);
		newClienteButton.setAlignmentX(CENTER_ALIGNMENT);
		newClienteButton.setAlignmentY(CENTER_ALIGNMENT);
		newClienteButton.setBorder(BorderFactory.createBevelBorder(0));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		buttonPanel.add(newClienteButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		headerContainer.add(buttonPanel);
		headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));
	
		newClienteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.ALTA_CLIENTE, null);
				
			}
			
		});
		
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un cliente para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		for(TCliente cliente : clientes){
			contentContainer.add(clientePanel(cliente));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel clientePanel(TCliente cliente){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 60));
		

		JPanel buttonContainer = new JPanel(new FlowLayout());
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("edit_icon.png"))));
		editButton.setToolTipText("Editar Cliente");
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("trash_icon.jpg"))));
		deleteButton.setToolTipText("Eliminar Cliente");
		
		editButton.setBackground(new Color(39, 174, 95));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);
		
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
		
				@SuppressWarnings("unused")
				JDialog vBaja = new VBajaCliente(jframe,cliente);
			
				
			}
			
		});
		
		
		editButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.MODIFICAR_CLIENTE, cliente.getId());
				
			}
			
		});
		
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		
		//LABEL
		JLabel label = new JLabel(cliente.getNombre());
		label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				Controlador.obtenerInstancia().accion(Evento.BUSCAR_CLIENTE, cliente.getId());
				
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		label.setToolTipText("Informacion del Cliente");
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		
		
		return panel;
	}

	
	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_BAJA_CLIENTE_OK){
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el cliente con éxito");
		}
		else if(evento == Evento.RES_BAJA_CLIENTE_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja el cliente");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VCLIENTE, null);
	}
}