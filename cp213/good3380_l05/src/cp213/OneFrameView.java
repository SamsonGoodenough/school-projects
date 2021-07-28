package cp213;

import java.awt.BorderLayout;

import javax.swing.JPanel;

//---------------------------------------------------------------
/**
 * Puts three right triangle views into the same JPanel using various layouts.
 * Each view is updated individually rather than through top view.
 *
 * @author David Brown from Byron Weber-Becker
 * @version 2017-06-19
 */
@SuppressWarnings("serial")
public class OneFrameView extends JPanel {
    // The model views.
    private RTButtonView bView = null;
    private RTGraphicView gView = null;
    private RTNumericView nView = null;
    private RTSameView sView = null;

    // ---------------------------------------------------------------
    /**
     * View constructor.
     *
     * @param model
     *            The model to attach the individual views to.
     */
    public OneFrameView(final RTModel model) {
	this.nView = new RTNumericView(model);
	this.bView = new RTButtonView(model);
	this.gView = new RTGraphicView(model);
	this.sView = new RTSameView(model);
	this.layoutView();
    }

    // ---------------------------------------------------------------
    /**
     * Lays out the individual model views within the main frame.
     */
    private void layoutView() {
	// Place the numeric and button views on top and the graphic view
	// underneath.
	this.setLayout(new BorderLayout());
	this.add(this.gView, BorderLayout.CENTER);
	this.add(this.nView, BorderLayout.WEST);
	this.add(this.bView, BorderLayout.NORTH);
	this.add(this.sView, BorderLayout.SOUTH);
    }

    // ---------------------------------------------------------------
}
