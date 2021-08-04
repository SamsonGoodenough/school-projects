package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of the reactor average power.
 *
 * @author David Brown
 * @version 2021-07-19
 */
@SuppressWarnings("serial")
public class AveragePowerView extends BarView {

	private Reactor model = null;

	/**
	 * Constructor.
	 *
	 * @param model The reactor.
	 */
	public AveragePowerView(Reactor model) {

		super(new Color(255, 103, 69), Reactor.MAX_POWER);
		this.model = model;

	}

	@Override
	public void paintComponent(Graphics g) {

		this.modelValue = this.model.getAveragePower();
		super.paintComponent(g);

	}

}
