package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of current reactor temperature.
 *
 * @author David Brown
 * @version 2021-07-19
 */
@SuppressWarnings("serial")
public class TemperatureView extends BarView {

	private Reactor model = null;

	/**
	 * Constructor.
	 *
	 * @param model The reactor.
	 */
	public TemperatureView(Reactor model) {
		super(new Color(69, 69, 255), Reactor.MAX_TEMP - Reactor.MIN_TEMP);
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		this.modelValue = this.model.getTemperature() - Reactor.MIN_TEMP;
		super.paintComponent(g);
	}
}
