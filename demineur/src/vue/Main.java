package vue;

import javax.swing.SwingUtilities;

/**
 *
 * @author Loic
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				Menu menu = new Menu();
				menu.setVisible(true);

			}
		});

	}

}
