package cp213;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//---------------------------------------------------------------------
/**
 * @author David Brown
 * @version 2021-07-06
 *
 *          Demonstrates catching bad number format and divide by zero
 *          exceptions.
 */
@SuppressWarnings("serial")
public class DividePanel extends JPanel {

    // ---------------------------------------------------------------------
    /**
     * An inner class that uses an ActionListener to access
     * <code>divideButton</code>. It displays the result of the division when
     * pressed and catches any input and division errors.
     */
    private class DivideListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent evt) {
	    try {
		DividePanel.this.divide();
	    } catch (ArithmeticException e) {
		// Catch the division by zero exception.
		JOptionPane.showMessageDialog(null, "Division by Zero", "Error",
			JOptionPane.ERROR_MESSAGE);
	    } catch (NumberFormatException e) {
		// Catch the bad input exception.
		JOptionPane.showMessageDialog(null,
			"Bad Number: Use Integers Only", "Error",
			JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
		// Catch any other possible exception.
		JOptionPane.showMessageDialog(null, e.toString(), "Error",
			JOptionPane.ERROR_MESSAGE);
	    } finally {
		// Return focus to the divisor input field.
		DividePanel.this.divisorField.requestFocusInWindow();
		DividePanel.this.divisorField.selectAll();
	    }
	}
    }

    // ---------------------------------------------------------------------
    /**
     * The default dividend value.
     */
    private final static int BASE = 100;

    // ---------------------------------------------------------------------
    /**
     * Main method for tesing the class.
     *
     * @param args
     *            Unused.
     */
    public static void main(String[] args) {
	DividePanel view = new DividePanel();
	JFrame f = new JFrame("Divide Test");
	f.setContentPane(view);
	f.setSize(420, 150);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setVisible(true);
    }

    // ---------------------------------------------------------------------
    /**
     * The label containing the result of the division.
     */
    private final JLabel answerLabel = new JLabel("" + BASE);

    /**
     * Performs the division when pressed.
     */
    private final JButton divideButton = new JButton("Divide");

    /**
     * Field for user entry of the divisor.
     */
    private final JTextField divisorField = new JTextField(3);

    /**
     * Defines the font for the panel.
     */
    private final Font font = new Font("Sans-Serif", Font.PLAIN, 20);
    // ---------------------------------------------------------------------
    /**
     * Constructor. Lays out the view and registers the listeners.
     */
    public DividePanel() {
	this.layoutView();
	this.registerListeners();
    }

    // ---------------------------------------------------------------------
    /**
     * Attempt to divide the base value by the user-entered value.
     */
    private void divide() {
	String text = DividePanel.this.divisorField.getText();
	int value = Integer.parseInt(text);
	int result = BASE / value;
	// Set the answer label to the division result.
	DividePanel.this.answerLabel.setText("" + result);
    }

    // ---------------------------------------------------------------------
    /**
     * Lays out the panel view. Uses default (Flow) layout.
     */
    private void layoutView() {
	this.add(new JLabel("" + BASE + " / "));
	this.divisorField.setText("1");
	this.add(this.divisorField);
	this.add(new JLabel(" = "));
	this.add(this.answerLabel);
	this.add(this.divideButton);
	for (Component child : this.getComponents()) {
	    child.setFont(font);
	}
    }

    // ---------------------------------------------------------------------
    /**
     * Assigns ActionListeners to <code>divideButton</code>.
     */
    private void registerListeners() {
	this.divideButton.addActionListener(new DivideListener());
    }

    // ---------------------------------------------------------------------
}
