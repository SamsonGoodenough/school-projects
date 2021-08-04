package cp213;

import cp213.Reactor.Status;

/**
 * A class to control a Reactor model. It's job is to initialize a Reactor and
 * maximize its power output while avoiding a meltdown.
 *
 * @author -- your name here --
 * @author David Brown
 * @version 2021-07-19
 */
public class ReactorController implements Runnable {

	private static final int MAX_VARIANCE = 4;
	private static final int WIGGLEROOM = 10;
	// The reactor to control.
	private Reactor model = null;

	/**
	 * Constructor.
	 *
	 * @param model The reactor model.
	 */
	public ReactorController(Reactor model) {
		this.model = model;
	}

	/**
	 * Update the rods depending on the reactor's current state.
	 */
	private void updateModel() {
		
		double avgNextTemp = (Reactor.TEMP_FACTOR * this.model.getTemperature()) - this.model.getRodsDepth();
		double shiftingWeight = this.model.getTemperature() - avgNextTemp;

		int ticksBeforeMeltdown = 0;
		double currTemp = this.model.getTemperature();
		double prevTemp;
		boolean flag = false;
		while (currTemp <= Reactor.MAX_TEMP && !flag) {
			prevTemp = currTemp;
			currTemp = (Reactor.TEMP_FACTOR * currTemp) - this.model.getRodsDepth() + Reactor.RAND_HIGH;
			ticksBeforeMeltdown++;
			
			if (currTemp - prevTemp < 0) {
				ticksBeforeMeltdown = 10000; // safe no matter what
				flag = true;
			}
		}
		
		if (ticksBeforeMeltdown < WIGGLEROOM/2) {
			this.model.dropRods(); // meltdown inbound
		}
		
		if (shiftingWeight < 0) { // trending up on average
			if (ticksBeforeMeltdown < WIGGLEROOM) {
				this.model.lowerRods();
			} else if (shiftingWeight < Reactor.RAND_LOW) { // trending up rapidly
				this.model.lowerRods(); // try to escape rapid trend
			} else {
				if (ticksBeforeMeltdown > WIGGLEROOM) {
					this.model.raiseRods(); // we can go higher
				}
				
				// stay where we are at
			}
		} else if (shiftingWeight + MAX_VARIANCE > 0) { // trending down safely
			if (ticksBeforeMeltdown > WIGGLEROOM) {
				this.model.raiseRods(); // we can go higher
			} else if (ticksBeforeMeltdown < WIGGLEROOM * 0.66) {
				this.model.lowerRods();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 *
	 * Run the reactor control.
	 */
	@Override
	public void run() {

		try {
			while (this.model.getStatus() == Status.OPERATING) {
				this.updateModel();
				this.model.tick();
				Thread.sleep(10);
			}
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
