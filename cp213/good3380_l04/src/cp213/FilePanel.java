package cp213;


import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// ---------------------------------------------------------------------
/**
 * @author David Brown
 * @version 2017-06-19
 *
 *          Demonstrates catching file opening exceptions.
 */
@SuppressWarnings("serial")
public class FilePanel extends JPanel {

    // ---------------------------------------------------------------------
    /**
     * An inner class that uses an ActionListener to access
     * <code>openButton</code>. It displays the result of the division when
     * pressed and catches any input and division errors.
     */
    private class OpenListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent evt) {
	    try {
		// Find the file named by the user.
		FilePanel.this.findFile();
	    } catch (FileNotFoundException e) {
		// Catch the file not found exception.
		FilePanel.this.fileNameLabel.setText(" ");
		JOptionPane.showMessageDialog(null,
			"File not found: "
				+ FilePanel.this.fileNameField.getText(),
				"Error", JOptionPane.ERROR_MESSAGE);
	    } catch (Exception e) {
		// Catch any other possible exception.
		FilePanel.this.fileNameLabel.setText(" ");
		JOptionPane.showMessageDialog(null, e.toString(), "Error",
			JOptionPane.ERROR_MESSAGE);
	    } finally {
		// Return focus to the filename input field.
		FilePanel.this.fileNameField.requestFocusInWindow();
		FilePanel.this.fileNameField.selectAll();
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
	FilePanel view = new FilePanel();
	JFrame f = new JFrame("Open File");
	f.setContentPane(view);
	f.setSize(420, 150);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setVisible(true);
    }

    // ---------------------------------------------------------------------
    /**
     * Field for user entry of the file name.
     */
    private final JTextField fileNameField = new JTextField(12);

    /**
     * Label for displaying first line of file.
     */
    private final JLabel fileNameLabel = new JLabel(" ");

    /**
     * Performs the file opening when pressed.
     */
    private final JButton openButton = new JButton("Open");

    /**
     * Defines the font for the panel.
     */
    private final Font font = new Font("Sans-Serif", Font.PLAIN, 20);
    // ---------------------------------------------------------------------
    /**
     * Constructor. Lays out the view and registers the listeners.
     */
    public FilePanel() {
	this.layoutView();
	this.registerListeners();
    }

    // ---------------------------------------------------------------------
    /**
     * Attempts to find a file by the user-entered file name.
     *
     * @throws FileNotFoundException
     */
    private void findFile() throws FileNotFoundException {
	String fileName = FilePanel.this.fileNameField.getText();
	// Attempt to open the file.
	Scanner fileScanner = new Scanner(new File(fileName));
	this.fileNameLabel.setText("File found: " + fileName);
	fileScanner.close();
    }

    // ---------------------------------------------------------------------
    /**
     * Lays out the panel view. Uses default (Flow) layout.
     */
    private void layoutView() {
	this.add(new JLabel("Filename: "));
	this.add(this.fileNameField);
	this.add(this.openButton);
	this.add(this.fileNameLabel);
	for (Component child : this.getComponents()) {
	    child.setFont(font);
	}
    }

    // ---------------------------------------------------------------------
    /**
     * Assigns ActionListeners to <code>openButton</code>.
     */
    private void registerListeners() {
	this.openButton.addActionListener(new OpenListener());
    }

    // ---------------------------------------------------------------------
}
