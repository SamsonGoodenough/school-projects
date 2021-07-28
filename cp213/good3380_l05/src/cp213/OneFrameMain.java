package cp213;

import javax.swing.JFrame;

//---------------------------------------------------------------
/**
 * Places three right triangle views into the same JFrame.
 *
 * @author David Brown from Byron Weber-Becker
 * @version 2017-06-19
 */
public class OneFrameMain {

    public static void main(final String args[]) {
	final RTModel model = new RTModel();
	final OneFrameView view = new OneFrameView(model);

	final JFrame f = new JFrame("Right Triangle");
	f.setContentPane(view);
	f.setSize(300, 300);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setVisible(true);
    }

    // ---------------------------------------------------------------
}
