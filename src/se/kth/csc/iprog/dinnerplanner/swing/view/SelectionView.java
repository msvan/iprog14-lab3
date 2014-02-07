package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

public class SelectionView extends Container {
	private static final long serialVersionUID = 1L;

	private Dimension dimension = new Dimension(300, 380);
	public JPanel dishSelection;
	public JSpinner numberOfGuestsSpinner;
	public JButton preparationButton;
	public JButton ingredientsButton;

	public SelectionView(DinnerModel model) {
		setLayout(new GridBagLayout());
		setPreferredSize(dimension);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weighty = 1;

		// Create the number of people label and add it to the top left.
		JLabel numberOfPeople = new JLabel("Number of people: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(numberOfPeople, gbc);
		// Create the number of people spinner and add it to the right of the label.
		SpinnerModel spinnerModel = new SpinnerNumberModel(
				model.getNumberOfGuests(), 0, 15, 1);
		numberOfGuestsSpinner = new JSpinner(spinnerModel);
		numberOfGuestsSpinner.setPreferredSize(new Dimension(30, 24));
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(numberOfGuestsSpinner, gbc);

		// Create the total cost label.
		JLabel totalCost = new JLabel("Total cost: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(totalCost, gbc);
		JLabel menuPrice = new JLabel("$ " + model.getTotalMenuPrice());
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(menuPrice, gbc);

		// Create the dinner menu label.
		JLabel dinnerMenuText = new JLabel("Dinner Menu");
		dinnerMenuText.setFont(new Font("Sans Serif", Font.BOLD, 28));
		dinnerMenuText.setPreferredSize(new Dimension(dimension.width, 28));
		dinnerMenuText.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(dinnerMenuText, gbc);

		// Create the dish selection panel.
		dishSelection = new JPanel(new FlowLayout());
		dishSelection.setPreferredSize(new Dimension(dimension.width, 200));
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		add(dishSelection, gbc);
		// Create each selected dish view.
		setSelection(model);

		// Create the bottom buttons (preparation and ingredients).
		preparationButton = new JButton("Preparations");
		preparationButton.setPreferredSize(new Dimension(150, 24));
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		add(preparationButton, gbc);
		ingredientsButton = new JButton("Ingredients");
		ingredientsButton.setPreferredSize(new Dimension(150, 24));
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = GridBagConstraints.RELATIVE;
		add(ingredientsButton, gbc);
	}

	private void setSelection(DinnerModel model) {
		dishSelection.removeAll();
		// For each selected dish, create a selected dist list view.
		for (Dish d : model.getFullMenu()) {
			SelectedDishListView ddv = new SelectedDishListView(d);
			dishSelection.add(ddv);
		}
		dishSelection.updateUI();
	}
	
	public class SelectedDishListView extends Container {

		private static final long serialVersionUID = 1L;
		public Dish dish;

		public SelectedDishListView(Dish dish) {
			this.dish = dish;
			
			setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.insets = new Insets(3, 3, 3, 3);

			// Create the image icon.
			ImageIcon image = new ImageIcon("images/" + dish.getImage());
			JLabel label = new JLabel("", image, JLabel.LEADING);
			label.setPreferredSize(new Dimension(50, 50));
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 2;
			gbc.anchor = GridBagConstraints.WEST;
			add(label, gbc);

			// Create the dish label type with name.
			JLabel dishLabel = new JLabel(DinnerModel.getDishType(dish.getType()) + ": "
					+ dish.getName());
			dishLabel.setPreferredSize(new Dimension(175, 25));
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.WEST;
			add(dishLabel, gbc);

			// Create the cost label.
			JLabel costLabel = new JLabel("Cost" + ": " + "$ " + dish.getTotalPrice());
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			add(costLabel, gbc);

			// Create the delete button.
			JButton deleteButton = new JButton("X");
			gbc.gridx = 2;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.EAST;
			add(deleteButton, gbc);
		}
	}
}
