package presentacion.Clientes.VistasCasos_de_Uso;

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
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import negocio.Clientes.TCliente;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class VBuscarCliente extends JFrame implements IGUI {
	private JFrame jframe;
	private TCliente cliente;
	
	public VBuscarCliente(JFrame jframe, TCliente cliente){
		this.jframe = jframe;
		this.cliente = cliente;
		init_GUI();
	}


	public void actualizar(int evento, Object datos) {
		
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
		JLabel titleLabel = new JLabel("Cliente");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));
		
		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setBackground(Color.white);
		
		// INFO
		JLabel idLabel = new JLabel("ID: " + cliente.getId());
		JLabel nombreLabel = new JLabel("Nombre: " + cliente.getNombre());
		JLabel mailLabel = new JLabel("Mail: " + cliente.getMail());
		JLabel dniLabel = new JLabel("DNI: " + cliente.getDNI());
		
		
		
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
			contentContainer.add(dniLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			contentContainer.add(mailLabel);
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		
		
		jframe.pack();
		jframe.setVisible(true);
	}
	
	private JPanel backButtonContainer(){
		
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 75));
		
		JButton backButton = new JButton();
		backButton.setBackground(new Color(237, 237, 237));
		backButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("back_icon.png"))));
		backButton.setToolTipText("Volver a Clientes");
		backButton.setPreferredSize(new Dimension(60, 60));
		backButton.setBorderPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Controlador.obtenerInstancia().accion(Evento.CREAR_VCLIENTE, null);
			}
			
		});
		
		
		backButtonContainer.add(backButton);
		
		return backButtonContainer;
	}
}