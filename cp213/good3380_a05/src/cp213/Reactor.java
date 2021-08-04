package cp213;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/**
 * A simple nuclear reactor simulation. Given a starting temperature and control
 * rods depths, attempt to control the reactor over a period of time.
 *
 * @author Samson Goodenough
 * @author David Brown
 * @version 2021-07-19
 *
 */
public class Reactor {

	// ---------------------------------------------------------------
	/**
	 * Enumerated type for the Reactor status. The reactor is assumed to be in
	 * OPERATING mode at the beginning of the simulation.
	 */
	public enum Status {
		FINISHED("Finished"), MELTDOWN("MELTDOWN!!"), OPERATING("Operating"), SHUTDOWN("Shutdown");

		private String statusString;

		Status(final String statusString) {
			this.statusString = statusString;
		}

		@Override
		public String toString() {
			return this.statusString;
		}
	}

	// ---------------------------------------------------------------
	// Public Constants.
	// °C - Room temperature.
	public static final double MIN_TEMP = 25;
	// °C - Meltdown if exceeded.
	public static final double MAX_TEMP = 1000;
	// Minimum temperature at which power is generated.
	public static final double MIN_POWER_TEMP = 100;
	// Maximum power in Mw output at maximum temperature.
	public static final double MAX_POWER = 800;
	// Lengths of rods in cm.
	public static final int ROD_LENGTH = 200;
	// Temperature multiplier per tick.
	public static final double TEMP_FACTOR = 1.125;
	// Range of temperature decrease/increase.
	public static final int RAND_HIGH = 3;
	public static final int RAND_LOW = -3;

	// Private Constants.
	// Power calculation factor. Multiplies current temperature.
	private static final double POWER_FACTOR = MAX_POWER / (MAX_TEMP - MIN_POWER_TEMP);

	/**
	 * Calculates and returns the temperature random factor.
	 *
	 * @return value
	 */
	private static int randomFactor() {
		final int value = new Random().nextInt(RAND_HIGH - RAND_LOW + 1) + RAND_LOW;
		return value;
	}

	// Allows views to listen to generic changes in the model.
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private double power = 0;
	private boolean rodsChanged = false;
	private int rodsDepth = 0;
	private Status status = Status.OPERATING;
	private double temperature = 0;
	private int ticks = 0;
	private long totalPower = 0;
	private long totalTemperature = 0;

	/**
	 * Reactor constructor.
	 *
	 * @param initialTemperature The initial temperature of the reactor.
	 * @param initialRodsDepth   The initial depths of the reactor control rods.
	 */
	public Reactor(final double initialTemperature, final int initialRodsDepth) {
		this.temperature = initialTemperature;
		this.rodsDepth = initialRodsDepth;
		// Initialize the total temperature to the current temperature.
		this.totalTemperature = (long) this.temperature;
		// Calculate the initial power level.
		this.power = this.temperature >= MIN_POWER_TEMP ? (long) ((this.temperature - 100) * POWER_FACTOR) : 0;
		// Initialize the total power to the current power.
		this.totalPower = (long) this.power;
	}

	// ---------------------------------------------------------------
	/**
	 * Attaches listeners to the model.
	 *
	 * @param listener The listener to attach to the model.
	 */
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Attaches listeners to the model for a particular property.
	 *
	 * @param propertyName The name of the property to listen for.
	 * @param listener     The listener to attach to the model.
	 */
	public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Drops the rods entirely into the reactor core - i.e. set the rods lengths to
	 * the maximum rods lengths.
	 */
	public void dropRods() {

		this.rodsDepth = ROD_LENGTH;
		this.status = Status.SHUTDOWN;
		this.rodsChanged = true;

	}

	/**
	 * Returns the average power produced by the reactor since the start of a
	 * simulation.
	 *
	 * @return average power.
	 */
	public double getAveragePower() {
		long averagePower = 0;

		if (this.getTicks() > 0) {
			averagePower = this.totalPower / this.getTicks();
		}

		return averagePower;
	}

	/**
	 * Returns the average temperature of the reactor since the start of a
	 * simulation.
	 *
	 * @return average temperature.
	 */
	public double getAverageTemperature() {
		long averageTemp = 0;

		if (this.getTicks() > 0) {
			averageTemp = this.totalTemperature / this.getTicks();
		}

		return averageTemp;
	}

	/**
	 * Returns the reactor's current power level.
	 *
	 * @return power.
	 */
	public double getPower() {
		return this.power;
	}

	/**
	 * Returns the reactor's current rod depths.
	 *
	 * @return rodsDepth.
	 */
	public int getRodsDepth() {
		return this.rodsDepth;
	}

	/**
	 * Returns the reactor's current status.
	 *
	 * @return status.
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * Returns the reactor's current temperature.
	 *
	 * @return temperature.
	 */
	public double getTemperature() {
		return this.temperature;
	}

	/**
	 * Returns the number of ticks since the beginning of a simulation.
	 *
	 * @return ticks
	 */
	public int getTicks() {
		return this.ticks;
	}

	/**
	 * Lower the rods by one step. Rods cannot be lowered by more than one step per
	 * tick.
	 */
	public void lowerRods() {

		if (!this.rodsChanged && this.rodsDepth < ROD_LENGTH) {
			this.rodsDepth++;
			this.rodsChanged = true;
		}

	}

	/**
	 * Sets reactor status to FINISHED.
	 */
	public void quit() {
		this.status = Status.FINISHED;
		return;
	}

	/**
	 * Raise the rods by one step. Rods cannot be raised by more than one step per
	 * tick.
	 */
	public void raiseRods() {

		if (!this.rodsChanged && this.rodsDepth > 0) {
			this.rodsDepth--;
			this.rodsChanged = true;
		}

	}

	/**
	 * Increment the simulation tick by one. Update the reactor temperature, power,
	 * and status (in that order), and allow the rods to be raised or lowered during
	 * this tick.
	 */
	public void tick() {

		this.updateTemperature();
		this.updatePower();
		this.updateStatus();
		
		this.rodsChanged = false;
		this.ticks++;

	}

	/**
	 * Updates the current reactor power level.
	 * 		P = (T(t) - 100) � Max Power / (Max Temp - 100)
	 */
	private void updatePower() {

		if (this.getTemperature() <= MIN_POWER_TEMP) { // 0
			this.power = 0;
		} else if (this.getTemperature() == MAX_TEMP) { // output max
			this.power = MAX_POWER;
		} else {
			this.power = (this.getTemperature() - 100) * POWER_FACTOR;
		}
		
		this.totalPower += this.getPower();
		this.pcs.firePropertyChange("power", null, this.power);
		
	}

	/**
	 * Updates the current reactor status.
	 */
	private void updateStatus() {
		
		if (this.getTemperature() > MAX_TEMP) {
			this.status = Status.MELTDOWN;
			this.pcs.firePropertyChange("status", null, this.status);
		}

	}

	/**
	 * Updates the current reactor temperature.
	 */
	private void updateTemperature() {

		this.temperature = (TEMP_FACTOR * this.getTemperature()) - this.getRodsDepth() + randomFactor();
		
		if (this.getTemperature() < MIN_TEMP) {
			this.temperature = MIN_TEMP;
		}
		
		this.totalTemperature += this.getTemperature();
		this.pcs.firePropertyChange("temperature", null, this.temperature);

	}
}
