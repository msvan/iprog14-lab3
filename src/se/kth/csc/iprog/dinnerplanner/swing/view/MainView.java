package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSeparator;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;

public class MainView extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// The sub views.
	public ContentView contentView;	
	public SelectionView selectionView;

	public MainView(DinnerModel model) {
		Dimension dimension = new Dimension(855, 400);

		setLayout(new FlowLayout());
		setPreferredSize(dimension);

		// Create sub views.
		contentView = new ContentView(model);
		JSeparator separator = new JSeparator();
		separator.setOrientation(JSeparator.NORTH);
		separator.setPreferredSize(new Dimension(1, 390));		
		selectionView = new SelectionView(model);
		
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
