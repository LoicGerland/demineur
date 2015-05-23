package vue;

import javax.swing.SwingUtilities;

import modele.Game;
import modele.Player;

/**
 *
 * @author Loic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Player[] p = new Player[1];
				Player p1 = new Player("P1");
				p[0] = p1;
				Game game = new Game(10,10,20,p);
				
				View fenetre = new View(game);
				fenetre.setVisible(true);
			}
		});

    }

}
