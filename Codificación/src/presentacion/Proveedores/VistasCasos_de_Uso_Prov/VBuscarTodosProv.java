package presentacion.Proveedores.VistasCasos_de_Uso_Prov;

import javax.swing.JFrame;

import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import negocio.Proveedores.TProveedor;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VBuscarTodosProv extends JFrame implements IGUI {
	
	private JFrame jframe;
	private Set<TProveedor> proveedores;
	
	public VBuscarTodosProv(JFrame jframe, Set<TProveedor> proveedores){
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
		mainPanel.setPreferredSize(new Dimension(500,500));


		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setAutoscrolls(true);

		// HEADER
		JPanel headerContainer = new JPanel(new BorderLayout());
		headerContainer.setMaximumSize(new Dimension(1000, 50));
		
		// TITLE
		JLabel title = new JLabel("Proveedores");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		// NEW PROVEEDOR BUTTON
		JButton newProveedorButton = new JButton("Nuevo Proveedor");
		newProveedorButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		newProveedorButton.setBackground(new Color(39, 174, 95));
		newProveedorButton.setForeground(Color.white);
		newProveedorButton.setBorderPainted(false);
		
		newProveedorButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.ALTA_PROVEEDOR, null);
				
			}
			
		});
		
		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_pequeño.png")));
		icon.setBorderPainted(false);
		icon.setBackground(new Color(237, 237, 237));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon, BorderLayout.CENTER);
			
		icon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Controlador.obtenerInstancia().accion(Evento.BUSCAR_TODOS_PROVEEDOR, null);
					
			}		
		});
		
		
		// CONSTRUIR VISTA
		jframe.add(mainPanel);
		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		mainPanel.add(scrollFrame);
		contentContainer.add(headerContainer);
		headerContainer.add(title, BorderLayout.WEST);
		headerContainer.add(newProveedorButton, BorderLayout.EAST);
		
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
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.jpg")));
		editButton.setBackground(new Color(39, 174, 95));
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
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.BAJA_PROVEEDOR, proveedor.getId());
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
		
		// CONSTRUIR PANEL
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		
		return panel;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		
		
	}
}