package cp213;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JComponent;

/**
 * Provides an updateable bar to represent a depth or level.
 *
 * @author David Brown
 * @version 2021-07-19
 */
@SuppressWarnings("serial")
public abstract class BarView extends JComponent {

	public static final float BORDER = (float) 4.0;
	public static final Stroke STROKE = new BasicStroke(BORDER);
	private Color color = null;
	private double modelRange = 0;
	protected double scale = 0;
	protected double modelValue = 0;

	/**
	 * Empty constructor.
	 */
	public BarView() {
	}

	/**
	 * Defines the default color for the bar.
	 *
	 * @param color      The bar color.
	 * @param modelRange The possible range of values from 0 in the model.
	 */
	public BarView(final Color color, final double modelRange) {
		this.color = color;
		this.modelRange = modelRange;
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Graphics2D g2d = (Graphics2D) g;
		this.setScale();
		final double x = this.getWidth() * this.scale;
		// Clear the rectangle.
		g2d.clearRect(0, 0, this.getWidth(), this.getHeight());
		// Draw the colored bar.
		g2d.setColor(this.color);
		g2d.fillRect(0, 0, (int) x, this.getHeight());
		// Draw the border
		g2d.setStroke(STROKE);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(0, 0, this.getWidth(), this.getHeight());
	}

	/**
	 * Set the scale for model's current values against its range.
	 */
	public void setScale() {
		this.scale = this.modelValue / this.modelRange;
	}

}
