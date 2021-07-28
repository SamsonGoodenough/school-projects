package cp213;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

// -------------------------------------------------------------------------------
/**
 * Displays a right triangle as a graphic within a JComponent with a blank
 * border of size BORDER. The upper right corner of the triangle can be selected
 * and used to change the size of the triangle by dragging with with mouse.
 *
 * @author David Brown from Byron Weber-Becker
 * @version 2017-06-19
 */
@SuppressWarnings("serial")
public class RTGraphicView extends JComponent {

    // -------------------------------------------------------------------------------
    /**
     * Inner class that displays the current state of the model.
     */
    private class DiagramListener implements PropertyChangeListener {

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
	    RTGraphicView.this.repaint();
	}
    }

    // -------------------------------------------------------------------------------
    /**
     * Inner class for allowing the triangle to be dragged in order to change
     * its size. The cursor is changed depending on whether the triangle can be
     * resized.
     */
    private class MMListener extends MouseMotionAdapter {
	private boolean canDrag = false;

	@Override
	public void mouseDragged(final MouseEvent evt) {
	    // Change the model values to match the mouse position.
	    if (this.canDrag) {
		RTGraphicView.this.model
			.setBase(evt.getX() / RTGraphicView.this.scaleX);
		RTGraphicView.this.model
			.setHeight((RTGraphicView.this.getHeight() - evt.getY())
				/ RTGraphicView.this.scaleY);
	    }
	}

	// -------------------------------------------------------------------------------
	/**
	 * The mouse moved: check if it is over the dragable regions and adjust
	 * the cursor accordingly.
	 */
	@Override
	public void mouseMoved(final MouseEvent evt) {

	    if (RTGraphicView.this.handle
		    .contains(RTGraphicView.this.getMousePosition())) {
		this.canDrag = true;
		RTGraphicView.this.setCursor(
			Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	    } else {
		this.canDrag = false;
		RTGraphicView.this.setCursor(
			Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	    }
	}
    }

    // -------------------------------------------------------------------------------
    /**
     * Define the border information.
     */
    private static final int BORDER_SIZE = 4;
    private static final Insets BORDER_INSETS = new Insets(BORDER_SIZE,
	    BORDER_SIZE, BORDER_SIZE, BORDER_SIZE);
    private static final Border BORDER = new EmptyBorder(BORDER_INSETS);
    /**
     * Define the mouse handle information.
     */
    private static final int HANDLE_SIZE = 6;
    private static final Dimension HANDLE_DIMENSION = new Dimension(
	    RTGraphicView.HANDLE_SIZE, RTGraphicView.HANDLE_SIZE);
    private final Rectangle handle = new Rectangle(HANDLE_DIMENSION);
    /**
     * Define the triangle points.
     */
    private final Point left = new Point(0, 0);
    private final Point top = new Point(0, 0);
    private final Point right = new Point(0, 0);
    /**
     * The right triangle model.
     */
    private RTModel model = null;
    /**
     * How much should the triangle be scaled?
     */
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    /**
     * The polygon definition for the triangle.
     */
    private final Path2D triangle = new Path2D.Double();

    // -------------------------------------------------------------------------------
    /**
     * The View constructor.
     *
     * @param model
     *            The model to view.
     */
    public RTGraphicView(final RTModel model) {
	this.model = model;
	this.layoutView();
	this.registerListeners();
    }

    // -------------------------------------------------------------------------------
    /**
     * Overrides JComponent's paintComponent method. Draws the triangle and adds
     * a handle to the top corner of the triangle.
     *
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    @Override
    public void paintComponent(final Graphics g) {
	final Graphics2D g2D = (Graphics2D) g;
	g2D.setColor(Color.black);
	g2D.setStroke(new BasicStroke(1));
	this.setScales();

	// Define the triangle points.
	this.left.setLocation(BORDER_SIZE, this.getHeight() - BORDER_SIZE);
	this.right.setLocation(this.model.getBase() * this.scaleX + BORDER_SIZE,
		this.left.getY());
	this.top.setLocation(this.right.getX(), this.getHeight() - BORDER_SIZE
		- this.scaleY * this.model.getHeight());

	this.triangle.reset();
	this.triangle.moveTo(this.left.getX(), this.left.getY());
	this.triangle.lineTo(this.right.getX(), this.right.getY());
	this.triangle.lineTo(this.top.getX(), this.top.getY());
	this.triangle.lineTo(this.left.getX(), this.left.getY());
	g2D.draw(this.triangle);

	this.handle.setLocation(this.top);
	this.handle.translate(-HANDLE_SIZE / 2, -HANDLE_SIZE / 2);
	g2D.fill(this.handle);
    }

    // -------------------------------------------------------------------------------
    /**
     * Empty method: the JComponent contains no further elements.
     */
    private void layoutView() {
	this.setBorder(BORDER);
    }

    // -------------------------------------------------------------------------------
    /**
     * Adds the mouse and change listeners.
     */
    private void registerListeners() {
	// Add widget listeners.
	this.addMouseMotionListener(new MMListener());
	// Add property listeners.
	this.model.addPropertyChangeListener(new DiagramListener());
    }

    // -------------------------------------------------------------------------------
    /**
     * Calculate the triangle to window scaling. The same scaling is applied to
     * both the base and height in order to preserve the 'look' of the triangle.
     */
    private void setScales() {
	this.scaleX = (this.getWidth() - 2 * BORDER_SIZE) / RTModel.MAX_SIDE;
	this.scaleY = (this.getHeight() - 2 * BORDER_SIZE) / RTModel.MAX_SIDE;
    }

    // -------------------------------------------------------------------------------
}