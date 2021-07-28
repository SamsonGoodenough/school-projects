package cp213;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

//---------------------------------------------------------------
/**
 * Creates three separate JFrames for three separate views of the right triangle
 * model.
 *
 * @author David Brown from Byron Weber-Becker
 * @version 2017-06-19
 */
public class ThreeFramesMain {

    public static void main(final String[] args) {
	final RTModel model = new RTModel();

	final JPanel buttonView = new RTButtonView(model);
	final JFrame buttonFrame = new JFrame("Right Triangle");
	buttonFrame.setContentPane(buttonView);
	buttonFrame.setSize(300, 100);
	buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	buttonFrame.setVisible(true);

	final JPanel numericView = new RTNumericView(model);
	final JFrame numericFrame = new JFrame("Right Triangle");
	numericFrame.setContentPane(numericView);
	numericFrame.setSize(100, 200);
	numericFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	numericFrame.setVisible(true);

	final JComponent graphicView = new RTGraphicView(model);
	final JFrame graphicFrame = new JFrame("Right Triangle");
	graphicFrame.setContentPane(graphicView);
	graphicFrame.setSize(300, 300);
	graphicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	graphicFrame.setVisible(true);
    }

    // ---------------------------------------------------------------
}
