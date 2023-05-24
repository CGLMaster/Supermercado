package presentacion.launcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import negocio.Trabajadores.TTrabajador;
import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;
import presentacion.Trabajadores.VistasCasos_de_uso.VIdentificarTrabajador;

public class VPrincipal implements IGUI {

	private JMenuBar topMenu;
	private JFrame frame;
	boolean logged;
	private TTrabajador loggedTrabajador;

	public VPrincipal(JFrame frame, TTrabajador loggedTrabajador) {
		super();
		this.frame = frame;
		this.loggedTrabajador = loggedTrabajador;
		logged = loggedTrabajador != null;
		initGUI();
	}

	public void initGUI() {
		frame.getContentPane().removeAll();
		
		Image icon = new ImageIcon(getClass().getClassLoader().getResource("Logo_idea.png")).getImage();
		frame.setIconImage(icon);
		JFrame.setDefaultLookAndFeelDecorated(true);
		JPanel mainpanel = new JPanel(new BorderLayout());
		JPanel topPanel = creaTopPanel();
		JPanel botPanel = creaBotPanel();
		JPanel midPanel = creaMidPanel();
		JPanel westPanel = crearPubli("video1_def.gif");
		westPanel.setBorder(BorderFactory.createTitledBorder("Publicidad"));
		JPanel eastPanel = crearPubli("video2_def.gif");
		eastPanel.setBorder(BorderFactory.createTitledBorder("Publicidad"));

		topMenu = crearTopMenu();
		if (!logged) {
			topMenu.setVisible(false);
		}
		frame.setJMenuBar(topMenu);

		frame.setPreferredSize(new Dimension(1150, 750));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(mainpanel);
		mainpanel.add(topPanel, BorderLayout.NORTH);
		mainpanel.add(botPanel, BorderLayout.SOUTH);
		mainpanel.add(midPanel, BorderLayout.CENTER);
		mainpanel.add(westPanel,BorderLayout.WEST);
		mainpanel.add(eastPanel,BorderLayout.EAST);

		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
	}

	private JMenuBar crearTopMenu() {
		JMenuBar menu = new JMenuBar();

		JMenu personasPersonal = new JMenu("Socios y Personal");
		personasPersonal.setMnemonic(KeyEvent.VK_S);

		JMenu productosSeccion = new JMenu("Productos y Seccion");
		productosSeccion.setMnemonic(KeyEvent.VK_P);

		JMenu marcasProveedores = new JMenu("Marcas y Proveedores");
		marcasProveedores.setMnemonic(KeyEvent.VK_M);

		JMenuItem Clientes = new JMenuItem("Clientes");
		Clientes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VCLIENTE, null);

			}

		});
		Clientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

		JMenuItem Trabajadores = new JMenuItem("Trabajadores");
		Trabajadores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VTRABAJADOR, null);

			}

		});
		Trabajadores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));

		personasPersonal.add(Clientes);
		personasPersonal.add(Trabajadores);

		JMenuItem Productos = new JMenuItem("Productos");
		Productos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPRODUCTO, null);

			}

		});
		Productos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

		JMenuItem Seccion = new JMenuItem("Seccion");
		Seccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VSECCION, null);

			}

		});
		Seccion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		productosSeccion.add(Productos);
		productosSeccion.add(Seccion);

		JMenuItem Marcas = new JMenuItem("Marcas");
		Marcas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VMARCA, null);

			}

		});
		Marcas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));

		JMenuItem Proveedores = new JMenuItem("Proveedores");
		Proveedores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.obtenerInstancia().accion(Evento.CREAR_VPROVEEDOR, null);

			}

		});
		Proveedores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

		marcasProveedores.add(Marcas);
		marcasProveedores.add(Proveedores);

		menu.add(personasPersonal);
		menu.add(productosSeccion);
		menu.add(marcasProveedores);

		return menu;
	}

	public JPanel creaTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(39, 174, 95));
		topPanel.setPreferredSize(new Dimension(200, 60));
		topPanel.setMinimumSize(new Dimension(200, 60));
		topPanel.setMaximumSize(new Dimension(200, 60));

		JButton logIn = new JButton();
		logIn.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog vIden = new VIdentificarTrabajador(frame);

			}

		});
		logIn.setBorder(BorderFactory.createBevelBorder(0));
		logIn.setBackground(new Color(39, 174, 95));
		logIn.setPreferredSize(new Dimension(60, 40));
		logIn.setMinimumSize(new Dimension(45, 30));
		logIn.setMaximumSize(new Dimension(45, 30));
		logIn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		logIn.setToolTipText("Acceder como trabajador");

		JButton logout = new JButton();
		logout.setVisible(false);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logIn.setVisible(true);
				logout.setVisible(false);
				topMenu.setVisible(false);
				loggedTrabajador = null;

				Controlador.obtenerInstancia().accion(Evento.LOGOUT, null);


			}

		});
		logout.setBorder(BorderFactory.createBevelBorder(0));
		logout.setBackground(new Color(236, 115, 115));
		logout.setPreferredSize(new Dimension(60, 40));
		logout.setMinimumSize(new Dimension(45, 30));
		logout.setMaximumSize(new Dimension(45, 30));
		logout.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logout_icon.png")));
		logout.setToolTipText("Acceder como trabajador");

		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		if (logged) {
			logIn.setVisible(false);
			logout.setVisible(true);
		}
		topPanel.add(logIn);
		topPanel.add(logout);

		return topPanel;
	}

	public JPanel creaBotPanel() {
		JPanel botPanel = new JPanel(new BorderLayout());
		botPanel.setPreferredSize(new Dimension(10, 60));
		botPanel.setMinimumSize(new Dimension(10, 60));
		botPanel.setBackground(new Color(39, 174, 95));

		return botPanel;
	}

	public JPanel creaMidPanel() {
		JPanel midpanel = new JPanel();
		midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.Y_AXIS));
		midpanel.setBackground(new Color(197, 233, 205));

		JLabel bienvenida = new JLabel("Bienvenido a Supermercado");
		bienvenida.setLayout(new BorderLayout());
		bienvenida.setForeground(new Color(57, 119, 70));
		bienvenida.setPreferredSize(new Dimension(200, 50));
		bienvenida.setFont(new Font("Comic Sans MS", 1, 30));
		bienvenida.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		JLabel icono = new JLabel();
		icono.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_IS_mediano.png")));
		icono.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		
		JLabel publi = new JLabel();
		publi.setIcon(new ImageIcon("icons/Yo mazao James Webb.png"));
		publi.setAlignmentX(JPanel.LEFT_ALIGNMENT);

		JButton compra = new JButton();
		compra.setBackground(new Color(39, 174, 95));
		compra.setPreferredSize(new Dimension(200, 80));
		compra.setText("INICIAR COMPRA");
		compra.setFont(new Font("sans-serif", 1, 20));
		compra.setForeground(Color.white);
		compra.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Carro_icon.png")));
		compra.setBorder(BorderFactory.createBevelBorder(0));
		compra.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		compra.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				HashMap<String, Object> args = new HashMap<String, Object>();
				args.put("loggedTrabajador", loggedTrabajador);
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VCOMPRA, args);
			}

		});

		midpanel.add(Box.createRigidArea(new Dimension(0, 70)));
		midpanel.add(bienvenida);
		midpanel.add(Box.createRigidArea(new Dimension(0, 30)));
		midpanel.add(icono);
		midpanel.add(Box.createRigidArea(new Dimension(0, 50)));
		midpanel.add(compra);

		midpanel.setVisible(true);
		return midpanel;
	}
	
	
	public JPanel crearPubli(String url){
		JPanel publiPanel = new JPanel();
		publiPanel.setLayout(new BoxLayout(publiPanel, BoxLayout.Y_AXIS));
		publiPanel.setBackground(new Color(197, 233, 205));
		JLabel publi = new JLabel();
		publi.setIcon(new ImageIcon(getClass().getClassLoader().getResource(url)));
		//imageIcon.setImageObserver(publi);
		publi.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		
		
		publiPanel.add(publi);
		return publiPanel;
	}

	@Override
	public void actualizar(int evento, Object datos) {
		if (evento == Evento.RES_IDENTIFICAR_TRABAJADOR_OK) {
			JOptionPane.showMessageDialog(null, "Trabajador identificado con exito");
			Controlador.obtenerInstancia().accion(Evento.CREAR_VPRINCIPAL, null);
		} else if (evento == Evento.RES_IDENTIFICAR_TRABAJADOR_KO) {
			JOptionPane.showMessageDialog(null, "No se ha podido identificar al trabajador");
		}

	}
}
