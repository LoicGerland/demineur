package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.Game;
import modele.Point;

/**
 *
 * @author Loic
 */
public class View extends JFrame {

	private Game game;

	public View(Game g) {
		super();
		this.game = g;
		build();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				super.windowClosing(arg0);
				System.exit(0);
			}
		});

	}

	public void build() {

		JMenuBar jm = new JMenuBar();

		JMenu m = new JMenu("Jeu");

		JMenuItem mi = new JMenuItem("Partie");

		m.add(mi);

		jm.add(m);

		setJMenuBar(jm);

		setTitle("Démineur");
		setSize(400, 400);
		JComponent pan = new JPanel(new GridLayout(game.getGrid().getWidth(),
				game.getGrid().getHeight()));
		Border blackline = BorderFactory.createLineBorder(Color.black, 1);

		for (int i = 0; i < game.getGrid().getWidth(); i++) {
			for (int j = 0; j < game.getGrid().getHeight(); j++) {
				JComponent ptest = new CaseVue(
						this.game.getGrid().getGrid()[i][j]);
				ptest.setBorder(blackline);
				pan.add(ptest);
			}
		}
		pan.setBorder(blackline);
		add(pan);
		// setContentPane(pan);
	}

}
