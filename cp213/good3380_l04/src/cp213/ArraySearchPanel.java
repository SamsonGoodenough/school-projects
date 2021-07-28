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
 *          Test of user defined ValueNotFoundException.
 */
@SuppressWarnings("serial")
public class ArraySearchPanel extends JPanel {

    // ---------------------------------------------------------------------
    /**
     * An inner class that uses an ActionListener to access
     * <code>searchButton</code>. It displays the result of the array search
     * when pressed and catches any input and search errors.
     */
    private class SearchListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent evt) {
	    try {
		ArraySearchPanel.this.findValue();
	    } catch (NumberFormatException e) {
		// Catch the bad input exception.
		JOptionPane.showMessageDialog(null,
			"Bad Number: Use Integers Only", "Error",
			JOptionPane.ERROR_MESSAGE);
	    } catch (ValueNotFoundException e) {
		// Catch the value not found exception.
		JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
			JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
		// Catch any other possible exception.
		JOptionPane.showMessageDialog(null, e.toString(), "Error",
			JOptionPane.ERROR_MESSAGE);
	    } finally {
		// Return focus to the value input field.
		ArraySearchPanel.this.valueField.requestFocusInWindow();
		ArraySearchPanel.this.valueField.selectAll();
	    }
	}
    }

    // ---------------------------------------------------------------------
    /**
     * Main method for testing the class.
     *
     * @param args
     *            Unused.
     */
    public static void main(String[] args) {
	ArraySearchPanel view = new ArraySearchPanel();
	JFrame f = new JFrame("Search Array");
	f.setContentPane(view);
	f.setSize(420, 150);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setVisible(true);
    }

    // ---------------------------------------------------------------------
    /**
     * The array to be searched.
     */
    private final int[] array = new int[4];

    /**
     * The label containing the result of the search.
     */
    private final JLabel indexLabel = new JLabel();

    /**
     * Performs the search when pressed.
     */
    private final JButton searchButton = new JButton("Search");

    /**
     * Field for user entry of the value to search for.
     */
    private final JTextField valueField = new JTextField(1);

    /**
     * Defines the font for the panel.
     */
    private final Font font = new Font("Sans-Serif", Font.PLAIN, 20);
    // ---------------------------------------------------------------------
    /**
     * Constructor. Initializes the array, lays out the view and registers the
     * listeners.
     */
    public ArraySearchPanel() {

	for (int i = 0; i < 4; i++) {
	    this.array[i] = i;
	}
	this.layoutView();
	this.registerListeners();
    }

    // ---------------------------------------------------------------------
    /**
     * Searches the array for the value entered by the user.
     *
     * @throws ValueNotFoundException
     *             Thrown if <code>value</code> not found in the array.
     */
    private void findValue() throws ValueNotFoundException {
	String text = ArraySearchPanel.this.valueField.getText();
	int value = Integer.parseInt(text);

	try {
	    int i = 0;

	    while (value != this.array[i]) {
		// Searches until the value is found.
		i++;
	    }
	    // Set the index label to the search result.
	    ArraySearchPanel.this.indexLabel.setText("" + i);
	} catch (ArrayIndexOutOfBoundsException e) {
	    // If the end of the array is reached, the value was not found.
	    // Replace the out of bounds exception with the custom exception.
	    // The ValueNotFoundException constructor expects an object as a
	    // parameter.
	    throw new ValueNotFoundException(value);
	}
    }

    // ---------------------------------------------------------------------
    /**
     * Lays out the panel view. Uses default (Flow) layout.
     */
    private void layoutView() {
	this.add(new JLabel("Array Value: "));
	this.valueField.setText("" + 0);
	this.add(this.valueField);
	this.add(this.searchButton);
	this.add(new JLabel("Array Index: "));
	this.indexLabel.setText("" + 0);
	this.add(this.indexLabel);
	for (Component child : this.getComponents()) {
	    child.setFont(font);
	}
    }

    // ---------------------------------------------------------------------
    /**
     * Assigns ActionListeners to <code>searchButton</code>.
     */
    private void registerListeners() {
	this.searchButton.addActionListener(new SearchListener());
    }

    // ---------------------------------------------------------------------
}
