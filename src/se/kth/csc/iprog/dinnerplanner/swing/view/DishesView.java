package se.kth.csc.iprog.dinnerplanner.swing.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import se.kth.csc.iprog.dinnerplanner.model.DinnerModel;
import se.kth.csc.iprog.dinnerplanner.model.Dish;

/**
 * 
 * A class handling the list of all dishes.
 *
 */
public class DishesView extends JPanel {

	private static final long serialVersionUID = 1L;

	private Set<Dish> dishes;
	public Set<DishView> dishButtons;
	
	private JPanel searchBarPanel;
	private JTextField searchInput;

	private JPanel dishesPanel;
	private JScrollPane scrollPane;

	private Dimension searchBarDimension = new Dimension(500, 50);
	private Dimension scrollDimension = new Dimension(500, 300);
	private Dimension dishDimension = new Dimension(150, 160);

	public DishesView(DinnerModel model, int type) {
		setLayout(new BorderLayout());

		// Create the search input and search bar field.
		searchInput = new JTextField();
		searchInput.setText("search");
		searchInput.setPreferredSize(new Dimension(200, 24));
		searchBarPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		searchBarPanel.setPreferredSize(searchBarDimension);
		searchBarPanel.add(searchInput);

		// Get all the dishes.
		dishes = model.getDishesOfType(type);
		dishButtons = new HashSet<DishView>();

		// Create the dishes view list and insert it to a scroll pane.
		dishesPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		scrollPane = new JScrollPane(dishesPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(scrollDimension);

		// Add each dish to the dishes view list.
		addDishes(dishes);

		// Insert the search bar panel and scroll pane.
		add(searchBarPanel, BorderLayout.PAGE_START);
		add(scrollPane, BorderLayout.PAGE_END);
	}

	public void addDishes(Set<Dish> dishes) {
		this.dishes = dishes;

		dishesPanel.removeAll();
		// Calculate the height of the dishes list panel (so the flow layout will work correctly).
		dishesPanel.setPreferredSize(new Dimension(
				(10 + dishDimension.width) * 3, (10 + dishDimension.height)
						* ((int) Math.ceil(dishes.size() / 3.0))));
		dishButtons.clear();

		// Create a new dish view for each dish and add it to the list.
		for (Dish d : dishes) {
			DishView dv = new DishView(d);
			dishButtons.add(dv);
			dishesPanel.add(dv);
		}

		updateUI();
	}
	
	/**
	 *
	 * A class handling the view of each dish.
	 *
	 */
	public class DishView extends Container {
		private static final long serialVersionUID = 1L;
		public Dish dish;
		public JButton button;
		public DishView(Dish dish) {
			this.dish = dish;
			setLayout(new BorderLayout());
			button = new JButton(dish.getName(), new ImageIcon(
					"images/" + dish.getImage()));
			button.setPreferredSize(dishDimension);
			button.setVerticalTextPosition(AbstractButton.BOTTOM);
			button.setHorizontalTextPosition(AbstractButton.CENTER);
			add(button);
		}
	}

}
