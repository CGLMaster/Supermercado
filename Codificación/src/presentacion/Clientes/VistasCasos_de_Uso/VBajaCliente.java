package presentacion.Clientes.VistasCasos_de_Uso;

import presentacion.Clientes.Evento;
import presentacion.Clientes.IGUI;
import presentacion.Controlador.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

import negocio.Clientes.TCliente;


@SuppressWarnings("serial")
public class VBajaCliente extends JDialog implements IGUI {
	TCliente cliente;
	@SuppressWarnings("unused")
	private JFrame jframe;

	
	public VBajaCliente(JFrame frame, TCliente cl){
		super(frame,true);
		this.jframe = frame;
		cliente = cl;
		init_GUI();
	}
	
	public void init_GUI(){
	
		this.setTitle("Baja Cliente");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,300));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
				
		// TITULO
		JLabel titleLabel = new JLabel("�Desea eliminar el cliente con la siguiente informacion?");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 15));
		
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
		
		
		// CONSTRUIR VISTAS
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
					Controlador.obtenerInstancia().accion(Evento.BAJA_CLIENTE, cliente.getId());
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
		this.setLocation(getParent().getLocation().x + 100, getParent().getLocation().y + 100);
		this.pack();
		this.setVisible(true);
	}
	
	public void actualizar(int evento, Object datos) {
		
	}
}