package vue;

import java.util.Scanner;

import javax.swing.SwingUtilities;

import modele.Game;
import modele.Player;

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

				/*
				 * Scanner sc = new Scanner(System.in);
				 * System.out.println("Entrer la hauteur : "); int x =
				 * sc.nextInt(); System.out.println("Entrer la largeur : "); int
				 * y = sc.nextInt();
				 * System.out.println("Entrer le nombre de bombes : "); int
				 * nbBomb = sc.nextInt();
				 */

				Menu menu = new Menu();
				menu.setVisible(true);
				/*Player p1 = new Player("p1");
				Player[] players = new Player[]{p1};
				Game game = new Game(10,10,20,players);
				View fenetre = new View(game);
				fenetre.setVisible(true);*/

			}
		});

	}

}
