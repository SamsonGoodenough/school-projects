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

@SuppressWarnings("serial")
public class RTSameView extends JPanel {
	private class LinkedButtonListener implements ActionListener {
		private int direction = 0;

		public LinkedButtonListener(final int direction) {
			this.direction = direction;
		}

		@Override
		public void actionPerformed(final ActionEvent evt) {
			RTSameView.this.model.setBase(RTSameView.this.model.getBase() + this.direction);
			RTSameView.this.model.setHeight(RTSameView.this.model.getHeight() + this.direction);
		}
	}

	private class AverageButtonListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent evt) {
			double avg = (RTSameView.this.model.getBase() + RTSameView.this.model.getHeight()) / 2;
			RTSameView.this.model.setBase(avg);
			RTSameView.this.model.setHeight(avg);
		}
	}

	private class BaseListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			RTSameView.this.commonHeight.setText((RTSameView.this.model.getHeight() == RTSameView.this.model.getBase())
					? RTSameView.f.format(RTSameView.this.model.getHeight())
					: " ");
			RTSameView.this.hypo.setText(RTSameView.f.format(RTSameView.this.model.getHypotenuse()));
		}
	}

	private class HeightListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			RTSameView.this.commonHeight.setText((RTSameView.this.model.getHeight() == RTSameView.this.model.getBase())
					? RTSameView.f.format(RTSameView.this.model.getHeight())
					: " ");
			RTSameView.this.hypo.setText(RTSameView.f.format(RTSameView.this.model.getHypotenuse()));
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
	/**
	 * Sets to average of both.
	 */
	private final JButton setAverage = new JButton("Avg");
	/**
	 * Displays the model's height and base value when they are equal.
	 */
	private final JLabel commonHeight = new JLabel(" ");
	/**
	 * Decrements both by 1.
	 */
	private final JButton linkedDown = new JButton("-");
	/**
	 * Increments both by 1.
	 */
	private final JButton linkedUp = new JButton("+");
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
	public RTSameView(final RTModel newModel) {
		this.model = newModel;
		this.layoutView();
		this.registerListeners();
		// Initialize the view labels.
		this.commonHeight.setText(RTSameView.this.model.getHeight() == RTSameView.this.model.getBase()
				? RTSameView.f.format((RTSameView.this.model.getHeight()))
				: "");
		this.hypo.setText(RTSameView.f.format(this.model.getHypotenuse()));
	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(3, 4));

		this.add(new JLabel("Average: "));
		this.add(this.setAverage);
		this.add(new JLabel());
		this.commonHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		this.add(this.commonHeight);

		this.add(new JLabel("Linked: "));
		this.add(this.linkedUp);
		this.add(this.linkedDown);
		this.add(new JLabel());

		this.add(new JLabel("Hypotenuse:"));
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
		this.setAverage.addActionListener(new AverageButtonListener());
		this.linkedUp.addActionListener(new LinkedButtonListener(1));
		this.linkedDown.addActionListener(new LinkedButtonListener(-1));
		// Add model listeners.
		this.model.addPropertyChangeListener(RTModel.Type.BASE, new BaseListener());
		this.model.addPropertyChangeListener(RTModel.Type.HEIGHT, new HeightListener());
	}

	// ---------------------------------------------------------------
}