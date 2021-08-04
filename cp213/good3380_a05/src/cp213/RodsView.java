package cp213;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Horizontal bar view of current reactor rod depth.
 *
 * @author David Brown
 * @version 2021-07-19
 */
@SuppressWarnings("serial")
public class RodsView extends BarView {

	private Reactor model = null;

	/**
	 * Constructor.
	 *
	 * @param model The reactor.
	 */
	public RodsView(Reactor model) {

		super(new Color(196, 69, 255), Reactor.ROD_LENGTH);
		this.model = model;

	}

	@Override
	public void paintComponent(Graphics g) {

		this.modelValue = this.model.getRodsDepth();
		super.paintComponent(g);

	}

}
