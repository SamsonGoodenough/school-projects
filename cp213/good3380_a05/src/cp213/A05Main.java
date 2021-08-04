package cp213;

import java.text.DecimalFormat;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * Used to test Reactor class from command line or GUI. Comment out approach
 * method calls.
 *
 * @author David Brown
 * @version 2021-07-19
 */
public class A05Main {

	public static void main(String args[]) {
		int initialTemperature = 400;
		int initialRodsDepth = 50;
		// Initialize the Reactor model.
		Reactor model = new Reactor(initialTemperature, initialRodsDepth);

		// Run one or the other of the following methods:

		// Text method:
//		A05Main.runReactor(model);
		// GUI method:
		A05Main.runReactorController(model);
	}

	/**
	 * Run a Reactor model with a manual controller.
	 *
	 * @param model The Reactor to run.
	 */
	public static void runReactor(Reactor model) {
		DecimalFormat decimalFormat = new DecimalFormat("#####.#");

		Scanner keyboard = new Scanner(System.in);
		String input = null;

		System.out.println("Commands:");
		System.out.println("q: quit");
		System.out.println("d: drop rods");
		System.out.println("+: raise rods");
		System.out.println("-: lower rods");
		System.out.println("ENTER: do nothing");
		System.out.println();

		while (model.getStatus() == Reactor.Status.OPERATING) {
			System.out.println("Tick: " + model.getTicks());
			System.out.println("Rod Depths: " + model.getRodsDepth());
			System.out.println("Temperature: " + decimalFormat.format(model.getTemperature()));
			System.out.println("Power:       " + decimalFormat.format(model.getPower()));
			System.out.println();
			System.out.print("Input: ");
			input = keyboard.nextLine();

			if (input.equals("q")) {
				model.quit();
			} else if (input.equals("d")) {
				model.dropRods();
			} else if (input.equals("+")) {
				model.raiseRods();
			} else if (input.equals("-")) {
				model.lowerRods();
			}
			System.out.println();
			model.tick();
		}
		System.out.println("----------------------------------");
		System.out.println("Status: " + model.getStatus());
		System.out.println("Rod Depths: " + model.getRodsDepth());
		System.out.println("Tick: " + model.getTicks());
		System.out.println("Average Temperature: " + decimalFormat.format(model.getAverageTemperature()));
		System.out.println("Average Power:       " + decimalFormat.format(model.getAveragePower()));
		System.out.println();

		keyboard.close();
	}

	/**
	 * Run a Reactor model with an automatic controller.
	 *
	 * @param model The Reactor to run.
	 */
	public static void runReactorController(Reactor model) {
		ReactorControllerView view = new ReactorControllerView(model);

		final JFrame f = new JFrame("Reactor");
		f.setContentPane(view);
		f.setSize(500, 400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		ReactorController rc = new ReactorController(model);
		rc.run();
	}

}
