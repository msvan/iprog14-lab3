package se.kth.csc.iprog.dinnerplanner.swing.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;
import se.kth.csc.iprog.dinnerplanner.swing.view.DishWindow;
import se.kth.csc.iprog.dinnerplanner.swing.view.DishListView;
import se.kth.csc.iprog.dinnerplanner.swing.view.IngredientsWindow;
import se.kth.csc.iprog.dinnerplanner.swing.view.PreparationsWindow;
import se.kth.csc.iprog.dinnerplanner.swing.view.DishListView.DishView;
import se.kth.csc.iprog.dinnerplanner.swing.view.MainView;
import se.kth.csc.iprog.dinnerplanner.swing.view.SelectionView.SelectedDishListView;

public class MainViewController implements ActionListener, ChangeListener {
	DinnerModel model;
	MainView view;

	public MainViewController(DinnerModel model, MainView view) {
		this.model = model;
		this.view = view;

		//
		updateDishListeners();
		updateSelectListeners();

		// Add actions listeners for the ingredients and preparations buttons.
		view.selectionView.ingredientsButton.addActionListener(this);
		view.selectionView.preparationButton.addActionListener(this);
		view.selectionView.numberOfGuestsSpinner.addChangeListener(this);
	}

	public void updateDishListeners() {
		// Add listeners for each dish view button.
		for (DishListView dishListView : view.contentView.dishListViews) {
			dishListView.searchInput.addActionListener(this);
			for (DishView dishView : dishListView.dishButtons) {
				dishView.pickButton.addActionListener(this);
				dishView.infoButton.addActionListener(this);
			}
		}
	}

	public void updateSelectListeners() {
		// Add listeners for each dish view button.
		for (SelectedDishListView selectedDishListView : view.selectionView.selectedDishListView) {
			selectedDishListView.deleteButton.addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Find which dish view button (add or info) that was pressed in which tab.
		for (DishListView dishListView : view.contentView.dishListViews) {
			// If a search has been performed.
			if (dishListView.searchInput.equals(e.getSource())) {
				// Filter the dishes.
				Set<Dish> dishes = model.filterDishesOfType(dishListView.type, dishListView.searchInput.getText());
				// Add the new dishes to the view.
				dishListView.addDishes(dishes);
				// Update the listeners.
				updateDishListeners();
				return;
			}
			for (DishView dishView : dishListView.dishButtons) {
				if (dishView.infoButton.equals(e.getSource())) {
					// Create a new dish information window.
					DishWindow dishWindow = new DishWindow(model, dishView.dish);
					dishWindow.setVisible(true);
					return;
				} else if (dishView.pickButton.equals(e.getSource())) {
					// Select this dish in the model and let it notify the view.
					model.setSelectedDish(dishView.dish);
					// Add new button listeners to the selected dish view.
					updateSelectListeners();
					return;
				}
			}
		}
		// Find which selected dish view button that was pressed (delete).
		for (SelectedDishListView selectedDishListView : view.selectionView.selectedDishListView) {
			if (e.getSource() == selectedDishListView.deleteButton) {
				model.removeSelectedDish(selectedDishListView.dish);
				updateSelectListeners();
				break;
			}
		}
		// Open an ingredients window if the ingredients button was pressed.
		if (e.getSource() == view.selectionView.ingredientsButton) {
			IngredientsWindow ingredientsWindow = new IngredientsWindow(model);
			ingredientsWindow.setTitle("Ingredients");
			ingredientsWindow.setVisible(true);
		}
		// Open a preparation window if the preparation button was pressed.
		else if (e.getSource() == view.selectionView.preparationButton) {
			PreparationsWindow preparationsFrame = new PreparationsWindow(model);
			preparationsFrame.setTitle("Preparation");
			preparationsFrame.setVisible(true);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// Update the number of guests in the model when the view changes.
		if (e.getSource() == view.selectionView.numberOfGuestsSpinner) {
			model.setNumberOfGuests((Integer) view.selectionView.numberOfGuestsSpinner
					.getValue());
		}
	}
}