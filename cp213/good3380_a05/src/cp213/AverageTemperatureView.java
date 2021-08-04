package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of the reactor average temperature.
 *
 * @author David Brown
 * @version 2021-07-19
 */
@SuppressWarnings("serial")
public class AverageTemperatureView extends BarView {

	private Reactor model = null;

	/**
	 * Constructor.
	 *
	 * @param model The reactor.
	 */
	public AverageTemperatureView(Reactor model) {
		super(new Color(112, 69, 255), Reactor.MAX_TEMP);
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		this.modelValue = this.model.getAverageTemperature();
		super.paintComponent(g);
	}

}
