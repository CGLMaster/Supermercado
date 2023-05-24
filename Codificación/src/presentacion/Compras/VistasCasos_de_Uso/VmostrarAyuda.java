package presentacion.Compras.VistasCasos_de_Uso;

import java.awt.event.ActionListener;
import presentacion.Clientes.IGUI;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VmostrarAyuda extends JDialog implements ActionListener, IGUI {

	private JPanel generalPanel, panel1, panel2, panel3, panel4;
	private JLabel labelIcon1, labelIcon2, labelIcon3, labelIcon4;
	private JLabel labelText1, labelText2, labelText3, labelText4, consejo;


	public VmostrarAyuda() {
		setTitle("Ayuda al Cliente");
		setResizable(false);
		setSize(new Dimension(600, 500));
		setLocationRelativeTo(null);
		generalPanel = new JPanel();

		panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
		labelText1 = new JLabel();
		labelIcon1 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/añadir_iconhelp.png"))));

		panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelText2 = new JLabel();
		labelIcon2 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/trash_iconhelp.jpg"))));
		
		panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelText3 = new JLabel();
		labelIcon3 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/pago_iconhelp.jpg"))));
		
		panel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelText4 = new JLabel();
		labelIcon4 = new JLabel(new ImageIcon((getClass().getClassLoader().getResource("help/back_iconhelp.png"))));
		
		consejo = new JLabel();
		
		setContentPane(generalPanel);
		getContentPane().setLayout(new BoxLayout(generalPanel, BoxLayout.Y_AXIS));
		init_GUI();
	}

	public void init_GUI() {
		
		labelText1.setText("<html>Al pulsar '<i>Añadir</i>', se agregará el producto deseado al carrito de la compra, <br>aumentando la cantidad correspondiente al precio de la compra total.</html>");
		panel1.add(Box.createRigidArea(new Dimension(10, 80)));
		panel1.add(labelIcon1);
		panel1.add(labelText1);
		
		labelText2.setText("<html>Al pulsar '<i>Delete</i>', se borrará el producto del carrito de la compra, <br>restando la cantidad correspondiente al precio de la compra total.</html>");
		panel2.add(Box.createRigidArea(new Dimension(10, 80)));
		panel2.add(labelIcon2);
		panel2.add(labelText2);
		
		labelText3.setText("<html>Al pulsar '<i>Pagar</i>', se pasará a efectuar la compra, <br>asegúrese de tener suficiente dinero.</html>");
		panel3.add(Box.createRigidArea(new Dimension(10, 80)));
		panel3.add(labelIcon3);
		panel3.add(labelText3);
		
		labelText4.setText("<html>Al pulsar '<i>Atrás</i>', volverá a la pagina principal de compra.");
		panel4.add(Box.createRigidArea(new Dimension(10, 80)));
		panel4.add(labelIcon4);
		panel4.add(labelText4);
		
		generalPanel.add(panel1);
		generalPanel.add(panel2);
		generalPanel.add(panel3);
		generalPanel.add(panel4);
		
		consejo.setText("<html><i>·Si lo desea puede solicitar la ayuda de uno de nuestros 'Trabajadores Supermercado'</i>.<html>");
		consejo.setAlignmentX(CENTER_ALIGNMENT);
		generalPanel.add(consejo);
	}

	public void actionPerformed(ActionEvent e) {}

	public void actualizar(int evento, Object datos) {}
}