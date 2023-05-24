package presentacion.Secciones;

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

import negocio.Secciones.TSeccion;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;
import presentacion.Secciones.VistasCasos_de_Uso.VEliminarSeccion;


@SuppressWarnings("serial")
public class VSeccion extends JFrame implements IGUI {
	private JFrame jframe;
	private Set<TSeccion> secciones;
	
	public VSeccion(JFrame jframe, Set<TSeccion> secciones){
		this.jframe = jframe;
		this.secciones = secciones;
		init_GUI();
	}
	
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_BAJA_SECCION_OK) {
			JOptionPane.showMessageDialog(null, "Se ha dado de baja la sección con éxito");
		} 
		else if (evento == Evento.RES_BAJA_SECCION_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido dar de baja la sección, seguramente todavia tenga productos");
		}
		Controlador.obtenerInstancia().accion(Evento.CREAR_VSECCION, null);
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
		contentContainer.setAutoscrolls(true);

		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setBackground(new Color(39, 174, 95));
		headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));
		
		// TITLE
		JLabel title = new JLabel("Secciones");
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
		headerContainer.add(Box.createRigidArea(new Dimension(300, 0)));

			
		icon.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRINCIPAL, null);		
					
			}		
		});
		
		// NEW SECCION BUTTON
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(39, 174, 95));
		
		
		JButton newSeccionButton = new JButton("Nueva seccion");
		newSeccionButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		newSeccionButton.setToolTipText("Añadir nueva Sección");
		newSeccionButton.setBorder(BorderFactory.createBevelBorder(0));
		newSeccionButton.setBackground(new Color(39, 174, 95));
		newSeccionButton.setForeground(Color.white);
		newSeccionButton.setAlignmentX(CENTER_ALIGNMENT);
		newSeccionButton.setAlignmentY(CENTER_ALIGNMENT);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		buttonPanel.add(newSeccionButton);
		buttonPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		headerContainer.add(buttonPanel);
		headerContainer.add(Box.createRigidArea(new Dimension(20, 0)));
		
		newSeccionButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.ALTA_SECCION, null);
				
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
		JLabel help = new JLabel("Haga click en una seccion para mostrar mas informacion");
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
					
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		for(TSeccion seccion : secciones){
			contentContainer.add(seccionPanel(seccion));
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel seccionPanel(TSeccion seccion){
		
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setMaximumSize(new Dimension(1000, 75));
		
		// BUTTON CONTAINER
		JPanel buttonContainer = new JPanel(new FlowLayout());
		buttonContainer.setOpaque(false);
		buttonContainer.setSize(buttonContainer.getPreferredSize());
		
		// EDIT BUTTON
		JButton editButton = new JButton("Edit");
		editButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("edit_icon.png")));
		editButton.setToolTipText("Editar Sección");
		editButton.setBackground(new Color(39, 174, 95));
		editButton.setForeground(Color.white);
		editButton.setBorderPainted(false);
		
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
			
				Controlador.obtenerInstancia().accion(Evento.MODIFICAR_SECCION, seccion.getId());
			}
		});
		
		
		// DELETE BUTTON
		JButton deleteButton = new JButton("Delete");
		deleteButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("trash_icon.jpg")));
		deleteButton.setToolTipText("Eliminar Sección");
		deleteButton.setBackground(new Color(236, 115, 115));
		deleteButton.setForeground(Color.white);
		deleteButton.setBorderPainted(false);
		
		deleteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				JDialog vBaja = new VEliminarSeccion(jframe, seccion);
			}
			
		});
		
		
		// LABEL
		JLabel label = new JLabel(seccion.getZona());
		label.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {

				Controlador.obtenerInstancia().accion(Evento.BUSCAR_SECCION, seccion.getId());
			}

			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		label.setToolTipText("Información de la Sección");
		
		// CONSTRUIR PANEL
		panel.add(label, BorderLayout.WEST);
		panel.add(buttonContainer, BorderLayout.EAST);
		buttonContainer.add(editButton);
		buttonContainer.add(deleteButton);
		
		return panel;
	}
}