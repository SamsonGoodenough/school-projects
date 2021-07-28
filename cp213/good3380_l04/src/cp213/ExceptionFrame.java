package cp213;


import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * @author David Brown
 * @version 2021-07-06
 */
@SuppressWarnings("serial")
public class ExceptionFrame extends JFrame {

    public static void main(String[] args) {
	// Create panel borders.
	Border blackline = BorderFactory.createLineBorder(Color.black);

	// Define the individual panels.
	ArrayPanel aView = new ArrayPanel();
	aView.setBorder(blackline);
	FilePanel fView = new FilePanel();
	fView.setBorder(blackline);
	DividePanel dView = new DividePanel();
	dView.setBorder(blackline);
	ArraySearchPanel asView = new ArraySearchPanel();
	asView.setBorder(blackline);

	// Define the main panel.
	JPanel p = new JPanel();
	p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
	p.add(aView);
	p.add(fView);
	p.add(dView);
	p.add(asView);

	// Define the main frame.
	JFrame f = new JFrame("Exception Testing");
	f.setContentPane(p);
	f.setSize(420, 400);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setVisible(true);
    }
}
