package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// ---------------------------------------------------------------
/**
 * View and update the right triangle model with buttons that increment the base
 * and height by 1.
 *
 * @author David Brown from Byron Weber-Becker
 * @version 2017-06-19
 */
@SuppressWarnings("serial")
public class RTButtonView extends JPanel {

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class BaseButtonListener implements ActionListener {
		/**
		 * Determines whether values are incremented (+) or decremented (-).
		 */
		private int direction = 0;

		public BaseButtonListener(final int direction) {
			this.direction = direction;
		}

		@Override
		public void actionPerformed(final ActionEvent evt) {
			RTButtonView.this.model.setBase(RTButtonView.this.model.getBase() + this.direction);
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the base and hypotenuse labels whenever the
	 * model's base attribute is updated.
	 */
	private class BaseListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			RTButtonView.this.base.setText(RTButtonView.f.format(RTButtonView.this.model.getBase()));
			RTButtonView.this.hypo.setText(RTButtonView.f.format(RTButtonView.this.model.getHypotenuse()));
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class HeightButtonListener implements ActionListener {
		/**
		 * Determines whether values are incremented (+) or decremented (-).
		 */
		private int direction = 0;

		public HeightButtonListener(final int direction) {
			this.direction = direction;
		}

		@Override
		public void actionPerformed(final ActionEvent evt) {
			RTButtonView.this.model.setHeight(RTButtonView.this.model.getHeight() + this.direction);
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the height and hypotenuse labels whenever the
	 * model's height attribute is updated.
	 */
	private class HeightListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			RTButtonView.this.height.setText(RTButtonView.f.format(RTButtonView.this.model.getHeight()));
			RTButtonView.this.hypo.setText(RTButtonView.f.format(RTButtonView.this.model.getHypotenuse()));
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * The formatter for displaying numeric output.
	 */
	private static final DecimalFormat f = new DecimalFormat("###.##");
	/**
	 * Displays the model's base value.
	 */
	private final JLabel base = new JLabel(" ");
	/**
	 * Decrements base by 1.
	 */
	private final JButton baseDown = new JButton("-");
	/**
	 * Increments base by 1.
	 */
	private final JButton baseUp = new JButton("+");
	/**
	 * Displays the model's height value.
	 */
	private final JLabel height = new JLabel(" ");
	/**
	 * Decrements height by 1.
	 */
	private final JButton heightDown = new JButton("-");
	/**
	 * Increments height by 1.
	 */
	private final JButton heightUp = new JButton("+");
	/**
	 * Displays the model's hypotenuse value.
	 */
	private final JLabel hypo = new JLabel(" ");
	/**
	 * The right triangle model.
	 */
	private final RTModel model;

	// ---------------------------------------------------------------
	/**
	 * The view constructor.
	 *
	 * @param newModel The right triangle model.
	 */
	public RTButtonView(final RTModel newModel) {
		this.model = newModel;
		this.layoutView();
		this.registerListeners();
		// Initialize the view labels.
		this.base.setText(RTButtonView.f.format(this.model.getBase()));
		this.height.setText(RTButtonView.f.format(this.model.getHeight()));
		this.hypo.setText(RTButtonView.f.format(this.model.getHypotenuse()));
	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(3, 4));
		this.add(new JLabel("Base: "));
		this.add(this.baseUp);
		this.add(this.baseDown);
		this.base.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(this.base);
		this.add(new JLabel("Height: "));
		this.add(this.heightUp);
		this.add(this.heightDown);
		this.height.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(this.height);
		this.add(new JLabel("Hypotenuse: "));
		this.add(new JLabel());
		this.add(new JLabel());
		this.hypo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(this.hypo);
	}

	// ---------------------------------------------------------------
	/**
	 * Assigns listeners to the view widgets and the model.
	 */
	private void registerListeners() {
		// Add widget listeners.
		this.baseUp.addActionListener(new BaseButtonListener(5));
		this.baseDown.addActionListener(new BaseButtonListener(-5));
		this.heightUp.addActionListener(new HeightButtonListener(5));
		this.heightDown.addActionListener(new HeightButtonListener(-5));
		// Add model listeners.
		this.model.addPropertyChangeListener(RTModel.Type.BASE, new BaseListener());
		this.model.addPropertyChangeListener(RTModel.Type.HEIGHT, new HeightListener());
	}

	// ---------------------------------------------------------------
}