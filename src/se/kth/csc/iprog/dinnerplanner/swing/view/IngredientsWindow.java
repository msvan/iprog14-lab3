package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Dimension;
import javax.swing.JFrame;
import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class IngredientsWindow extends JFrame {
	private static final long serialVersionUID = 1L;

	public IngredientsWindow(DinnerModel model) {
		Dimension dimension = new Dimension(555, 400);

		setPreferredSize(dimension);
		pack();
		
		// Create the ingredients list view and add it to this window.
		IngredientsListView ingredientsListView = new IngredientsListView(model.getAllIngredients());
		ingredientsListView.setPreferredSize(dimension);
		setContentPane(ingredientsListView);
	}
}
