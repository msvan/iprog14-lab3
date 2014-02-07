package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.swing.controller.SelectionViewController;

public class MainView extends JPanel {

	private static final long serialVersionUID = 1L;

	public MainView(DinnerModel model) {
		Dimension dimension = new Dimension(855, 400);

		setLayout(new FlowLayout());
		setPreferredSize(dimension);

		// Create main views.
		ContentView contentView = new ContentView(model);
		JSeparator separator = new JSeparator();
		separator.setOrientation(JSeparator.NORTH);
		separator.setPreferredSize(new Dimension(1, 390));		
		SelectionView selectionView = new SelectionView(model);
		
		// Hookup controllers.
		new SelectionViewController(model, selectionView);

		// Pseudo margins.
		JPanel b = new JPanel(new FlowLayout());
		b.setPreferredSize(new Dimension(5, 1));
		JPanel b2 = new JPanel(new FlowLayout());
		b2.setPreferredSize(new Dimension(5, 1));

		// Insert views.
		add(contentView);
		add(b);
		add(separator);
		add(b2);
		add(selectionView);
	}
}
