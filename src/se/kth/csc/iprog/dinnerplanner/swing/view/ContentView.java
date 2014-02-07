package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.swing.controller.DishesViewController;

public class ContentView extends Container {
	private static final long serialVersionUID = 1L;

	JTabbedPane tabbedPane;

	public ContentView(DinnerModel model) {
		Dimension dimension = new Dimension(500, 400);

		setLayout(new BorderLayout());
		setPreferredSize(dimension);

		// Set the size of the tab pane.
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(new Dimension(500, 390));

		// Create a new dishes view for each tab type, and add it to the tab pane.
		for (int i = 0; i < DinnerModel.dishTypes.length; ++i) {
			JPanel tmpPanel = new JPanel();
			DishesView dishesView = new DishesView(model, i + 1);
			tmpPanel.add(dishesView);
			new DishesViewController(model, dishesView);
			tabbedPane.addTab(DinnerModel.dishTypes[i], tmpPanel);
		}

		// Insert the tab pane.
		add(tabbedPane, BorderLayout.PAGE_START);
	}
}
