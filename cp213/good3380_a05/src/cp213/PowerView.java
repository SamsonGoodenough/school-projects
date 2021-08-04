package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar for reactor power.
 *
 * @author David Brown
 * @version 2021-07-19
 */
@SuppressWarnings("serial")
public class PowerView extends BarView {

	private Reactor model = null;

	/**
	 * Constructor.
	 *
	 * @param model The reactor.
	 */
	public PowerView(Reactor model) {

		super(new Color(255, 69, 69), Reactor.MAX_POWER);
		this.model = model;

	}

	@Override
	public void paintComponent(Graphics g) {

		this.modelValue = this.model.getPower();
		super.paintComponent(g);

	}
}
