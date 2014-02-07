package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class DishWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public DishWindow(DinnerModel model, Dish dish) {
		Dimension dimension = new Dimension(555, 400);
		setPreferredSize(dimension);
		setTitle("Dinner Planner - " + dish.getName());
		pack();

		JPanel view = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 3, 3, 3);

		// Create the image icon and add it to the top left.
		ImageIcon image = new ImageIcon("images/" + dish.getImage());
		JLabel imageLabel = new JLabel("", image, JLabel.LEADING);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		view.add(imageLabel, gbc);

		// Create the dish name label and add it to the top right.
		JLabel dishName = new JLabel(dish.getName());
		dishName.setFont(new Font("Sans Serif", Font.BOLD, 28));
		dishName.setHorizontalAlignment(JLabel.LEFT);
		dishName.setAlignmentX(Component.LEFT_ALIGNMENT);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		view.add(dishName, gbc);

		// Create the price label and add it below the dish name.
		int numberOfGuests = model.getNumberOfGuests();
		float totalPrice = dish.getTotalPrice() * numberOfGuests;
		JLabel dishPrice = new JLabel("$ " + totalPrice + " for " + numberOfGuests + " persons");
		dishPrice.setHorizontalAlignment(JLabel.LEFT);
		dishPrice.setAlignmentX(Component.LEFT_ALIGNMENT);		
		gbc.gridx = 1;
		gbc.gridy = 1;
		view.add(dishPrice, gbc);

		// Create the textarea and add it to the left of ingredients list.
		JTextArea textArea = new JTextArea(dish.getDescription());
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
		textArea.setPreferredSize(new Dimension(dimension.width / 2 - 20,
				dimension.height / 2 - 20));
		gbc.gridx = 0;
		gbc.gridy = 2;
		view.add(textArea, gbc);

		// Create the ingredients list and add it to the right of the preparations.
		IngredientsListView ingredientsListView = new IngredientsListView(dish.getIngredients());
		ingredientsListView.setPreferredSize(new Dimension(dimension.width / 2 - 20,
				dimension.height / 2 - 20));
		gbc.gridx = 1;
		gbc.gridy = 2;
		view.add(ingredientsListView, gbc);

		setContentPane(view);
	}
}
