package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class PreparationsWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public PreparationsWindow(DinnerModel model) {
		Dimension dimension = new Dimension(555, 400);
		setPreferredSize(dimension);
		pack();

		// Set a vertical box layout and add it to a scroll pane.
		JPanel preparationsPanel = new JPanel();
		preparationsPanel.setLayout(new BoxLayout(preparationsPanel,
				BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(preparationsPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(dimension);

		// Create a preparation view for each dish.
		for (Dish dish : model.getFullMenu()) {
			preparationsPanel.add(new DishPreparationView(dish));
		}

		setContentPane(scrollPane);
	}

	public class DishPreparationView extends JPanel {
		private static final long serialVersionUID = 1L;

		public DishPreparationView(Dish dish) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			Dimension dimension = new Dimension(300, 100);

			setPreferredSize(dimension);

			// Create the dish type and name label.
			JLabel label = new JLabel(DinnerModel.getDishType(dish.getType())
					+ ": " + dish.getName());
			label.setFont(new Font("Sans Serif", Font.BOLD, 24));
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			label.setPreferredSize(new Dimension(250, 50));
			add(label);

			// Create the text area of preparation instructions below it.
			JTextArea textArea = new JTextArea(dish.getDescription());
			textArea.setMargin(new Insets(10,10,10,10));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
			textArea.setPreferredSize(new Dimension(250, 50));
			add(textArea);
		}
	}
}
