package se.kth.csc.iprog.dinnerplanner.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.swing.view.IngredientsWindow;
import se.kth.csc.iprog.dinnerplanner.swing.view.PreparationsWindow;
import se.kth.csc.iprog.dinnerplanner.swing.view.SelectionView;

public class SelectionViewController implements ActionListener {
	DinnerModel model;
	SelectionView view;

	public SelectionViewController(DinnerModel model, SelectionView view) {
		this.model = model;
		this.view = view;

		// Add actions listeners for the ingredients and preparations buttons.
		view.ingredientsButton.addActionListener(this);
		view.preparationButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Open an ingredients window if the ingredients button was pressed.
		if (e.getSource() == view.ingredientsButton) {
			IngredientsWindow ingredientsWindow = new IngredientsWindow(model);
			ingredientsWindow.setTitle("Ingredients");
			ingredientsWindow.setVisible(true);
		}
		// Open a preparation window if the preparation button was pressed.
		if (e.getSource() == view.preparationButton) {
			PreparationsWindow preparationsFrame = new PreparationsWindow(model);
			preparationsFrame.setTitle("Preparation");
			preparationsFrame.setVisible(true);
		}
	}
}
