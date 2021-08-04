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

/**
 * ReactorController GUI.
 *
 * @author David Brown
 * @version 2021-07-19
 */
@SuppressWarnings("serial")
public class ReactorControllerView extends JPanel {

	// ---------------------------------------------------------------
	/**
	 * Quit button listener.
	 */
	private class QuitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(final ActionEvent e) {
			ReactorControllerView.this.model.quit();
			ReactorControllerView.this.updateView();
		}
	}

	// ---------------------------------------------------------------
	/**
	 * Reactor properties listener.
	 */
	private class ReactorListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			ReactorControllerView.this.updateView();
		}
	}

	// ---------------------------------------------------------------
	private static final DecimalFormat decimalFormat = new DecimalFormat("#####.#");
	private final JLabel averagePowerLabel = new JLabel("", SwingConstants.TRAILING);
	private final JLabel averageTemperatureLabel = new JLabel("", SwingConstants.TRAILING);
	private final JButton quitButton = new JButton("Quit");
	private Reactor model = null;
	private final JLabel powerLabel = new JLabel("", SwingConstants.TRAILING);
	private final JLabel rodsLabel = new JLabel("", SwingConstants.TRAILING);
	private final JLabel statusLabel = new JLabel("", SwingConstants.CENTER);
	private final JLabel temperatureLabel = new JLabel("", SwingConstants.TRAILING);
	private final JLabel timeLabel = new JLabel("", SwingConstants.CENTER);
	private TemperatureView temperatureView = null;
	private PowerView powerView = null;
	private AverageTemperatureView averageTemperatureView = null;
	private AveragePowerView averagePowerView = null;
	private RodsView rodsView = null;

	/**
	 * The view constructor.
	 *
	 * @param model The reactor.
	 */
	public ReactorControllerView(final Reactor model) {
		this.model = model;
		this.layoutView();
		this.registerListeners();
		this.updateView();
	}

	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(8, 3));

		this.temperatureView = new TemperatureView(this.model);
		this.powerView = new PowerView(this.model);
		this.averageTemperatureView = new AverageTemperatureView(this.model);
		this.averagePowerView = new AveragePowerView(this.model);
		this.rodsView = new RodsView(this.model);

		// Display Status
		this.add(new JLabel("Status: "));
		this.add(this.statusLabel);
		this.add(new JLabel(""));
		// Display Time
		this.add(new JLabel("Time: "));
		this.add(this.timeLabel);
		this.add(new JLabel());
		// Display Temperature
		this.add(new JLabel("Temperature: "));
		this.add(this.temperatureView);
		this.add(this.temperatureLabel);
		// Display Average Temperature
		this.add(new JLabel("Avg Temperature: "));
		this.add(this.averageTemperatureView);
		this.add(this.averageTemperatureLabel);
		// Display Power
		this.add(new JLabel("Power: "));
		this.add(this.powerView);
		this.add(this.powerLabel);
		// Display Average Power
		this.add(new JLabel("Avg Power: "));
		this.add(this.averagePowerView);
		this.add(this.averagePowerLabel);
		// Display Rods Depth
		this.add(new JLabel("Rods Depth: "));
		this.add(this.rodsView);
		this.add(this.rodsLabel);
		this.add(new JLabel());
		this.add(this.quitButton);
	}

	/**
	 * Assigns ActionListeners to the buttons.
	 */
	private void registerListeners() {
		this.quitButton.addActionListener(new QuitButtonListener());
		this.model.addPropertyChangeListener(new ReactorListener());
	}

	/**
	 * Updates the GUI with the current state of the reactor.
	 */
	private void updateView() {
		this.temperatureLabel.setText(decimalFormat.format(this.model.getTemperature()));
		this.averageTemperatureLabel.setText(decimalFormat.format(this.model.getAverageTemperature()));
		this.powerLabel.setText(decimalFormat.format(this.model.getPower()));
		this.averagePowerLabel.setText(decimalFormat.format(this.model.getAveragePower()));
		this.statusLabel.setText(this.model.getStatus().toString());
		this.timeLabel.setText(Integer.toString(this.model.getTicks()));
		this.rodsLabel.setText(Integer.toString(this.model.getRodsDepth()));
		this.temperatureView.repaint();
		this.powerView.repaint();
		this.averageTemperatureView.repaint();
		this.averagePowerView.repaint();
		this.rodsView.repaint();
	}

}
