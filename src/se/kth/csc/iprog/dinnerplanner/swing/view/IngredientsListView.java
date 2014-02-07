package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.Set;

import javax.swing.JTable;

import se.kth.csc.iprog.dinnerplanner.model.Ingredient;

public class IngredientsListView extends Container {

	private static final long serialVersionUID = 1L;
	
	private static final String[] columnNames = { 
		"Ingredients", "Quantity", "Cost"
	};

	public IngredientsListView(Set<Ingredient> ingredients) {
		setLayout(new BorderLayout());

		// Create the table array.
		Object[][] data = new Object[ingredients.size()][columnNames.length];

		int i = 0;
		// For each ingredient, add it to the table.
		for (Ingredient ingredient : ingredients) {
			data[i][0] = ingredient.getName();
			data[i][1] = ingredient.getQuantity() + " " + ingredient.getUnit();
			data[i][2] = ingredient.getPrice() + " $";
			i++;
		}
		// Create the table.
		JTable table = new JTable(data, columnNames);
		add(table.getTableHeader(), BorderLayout.PAGE_START);
		add(table, BorderLayout.CENTER);
		setSize(table.getPreferredSize());
	}
}
