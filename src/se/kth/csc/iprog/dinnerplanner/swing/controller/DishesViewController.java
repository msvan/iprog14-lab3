package se.kth.csc.iprog.dinnerplanner.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.swing.view.DishWindow;
import se.kth.csc.iprog.dinnerplanner.swing.view.DishesView;
import se.kth.csc.iprog.dinnerplanner.swing.view.DishesView.DishView;

public class DishesViewController implements ActionListener {
	DinnerModel model;
	DishesView view;

	public DishesViewController(DinnerModel model, DishesView view) {
		this.model = model;
		this.view = view;
		updateDishListeners();
	}

	public void updateDishListeners() {
		// Add listeners for each dish view button.
		for (DishView dishView : view.dishButtons) {
			dishView.button.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DishView dv = null;
		// Find which dishView button that was pressed.
		for (DishView dishView : view.dishButtons) {
			if (dishView.button.equals(e.getSource())) {
				dv = dishView;
				break;
			}
		}
		// Create the detailed dish window in that case.
		if (dv != null) {
			DishWindow dishWindow = new DishWindow(model, dv.dish);
			dishWindow.setVisible(true);
			return;
		}
	}
}
