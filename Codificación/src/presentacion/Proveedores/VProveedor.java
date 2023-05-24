package presentacion.Proveedores;

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

import negocio.Proveedores.TProveedor;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;
import presentacion.Proveedores.VistasCasos_de_Uso_Prov.VBajaProveedor;


@SuppressWarnings("serial")
public class VProveedor extends JFrame implements IGUI {
	private JFrame jframe;
	private Set<TProveedor> proveedores;
	
	public VProveedor(JFrame jframe, Set<TProveedor> proveedores){
		this.jframe = jframe;
		this.proveedores = proveedores;
		init_GUI();
	}
	
	public void init_GUI() {
		jframe.getContentPane().removeAll();
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(520,500));


		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setAutoscrolls(true);

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));
		
		// TITLE
		JLabel title = new JLabel("Proveedores");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(330, 0)));

		
		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_pequeño.png")));
		icon.setToolTipText("Volver al Menu Principal");
		icon.setBorderPainted(false);
		icon.setBackground(new Color(39, 174, 95));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(270, 0)));

			
		icon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRINCIPAL, null);				
			}		
		});

		
		// NEW PROVEEDOR BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		JButton newProveedorButton = new JButton("Nuevo Proveedor");
		newProveedorButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		newProveedorButton.setBackground(new Color(39, 174, 95));
		newProveedorButton.setToolTipText("Añadir nuevo proveedor");
		newProveedorButton.setForeground(Color.white);
		newProveedorButton.setAlignmentX(CENTER_ALIGNMENT);
		newProveedorButton.setAlignmentY(CENTER_ALIGNMENT);
		newProveedorButton.setBorder(BorderFactory.createBevelBorder(0));
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		buttonPanel.add(newProveedorButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		headerContainer.add(buttonPanel);
		headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));
		
		newProveedorButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.ALTA_PROVEEDOR, null);
				
			}
			
		});
		
		
		
		// CONSTRUIR VISTA
		jframe.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);
		contentContainer.add(headerContainer);
		
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Haga click en un proveedor para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
					
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		for(TProveedor proveedor : proveedores){
			contentContainer.add(proveedorPanel(proveedor));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel proveedorPanel(TProveedor proveedor){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 100));
		
		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		// EDIT BUTTON
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.png")));
		editButton.setBackground(new Color(39, 174, 95));
		editButton.setToolTipText("Editar Proveedor");
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);
		
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			
				Controlador.obtenerInstancia().accion(Evento.MODIFICAR_PROVEEDOR, proveedor.getId());
			}
		});
		
		
		// DELETE BUTTON
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setToolTipText("Eliminar Proveedor");
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){
			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JDialog vBaja = new VBajaProveedor(jframe, proveedor);
				
			}
			
		});
		
		
		// LABEL
		JLabel label = new JLabel(proveedor.getNombre());
		label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				Controlador.obtenerInstancia().accion(Evento.BUSCAR_PROVEEDOR, proveedor.getId());
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		label.setToolTipText("Información del Proveedor");
		
		// CONSTRUIR PANEL
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		
		return panel;
	}
	
	public void actualizar(int evento, Object datos) {
		if(evento == Evento.RES_BAJA_PROVEEDOR_OK){
			JOptionPane.showMessageDialog(null, "Se ha dado de baja el proveedor con éxito");
		}
		else if(evento == Evento.RES_BAJA_PROVEEDOR_KO){
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja el proveedor");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VPROVEEDOR, null);
	}
}