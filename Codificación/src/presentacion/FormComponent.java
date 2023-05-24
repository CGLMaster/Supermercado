package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FormComponent extends JPanel {

	private ArrayList<JTextField> textFields;

	public FormComponent(ArrayList<String> names, String title, ArrayList<JTextField> textFields, ActionListener actionListener) {
		this.textFields = textFields;

		// FORM CONTAINER
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.setSize(formContainer.getPreferredSize());
		formContainer.setAlignmentX(CENTER_ALIGNMENT);
		formContainer.setAlignmentY(CENTER_ALIGNMENT);
		formContainer.setBackground(Color.white);
		formContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		formContainer.setAutoscrolls(true);

		// TITULO
		JLabel titleLabel = new JLabel(title);
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 20));

		// SUBMIT BUTTON
		JButton submitButton = new JButton("Submit");
		submitButton.setMaximumSize(new Dimension(1000, 30));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(39, 174, 95));
		submitButton.setForeground(Color.white);
		submitButton.setBorderPainted(false);
		submitButton.addActionListener(actionListener);

		// CONSTRUIR COMPONENTE
		formContainer.add(titleLabel);
		formContainer.add(Box.createRigidArea(new Dimension(10, 20)));
		for (String name : names) {
			formContainer.add(createFormField(name));
			formContainer.add(Box.createRigidArea(new Dimension(10, 20)));
		}
		formContainer.add(submitButton);

		JScrollPane scrollFrame = new JScrollPane(formContainer);
		scrollFrame.setPreferredSize(new Dimension(400, 500));

		this.add(scrollFrame);
	}

	private JPanel createFormField(String title) {
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

	public void setValues(ArrayList<String> values) {
		for (int i = 0; i < textFields.size(); i++) {
			textFields.get(i).setText(values.get(i));
		}
	}
}
