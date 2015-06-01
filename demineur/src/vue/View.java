package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import modele.Game;
import modele.Status;

/**
 *
 * @author Loic
 */
public class View extends JFrame {

	private Game game;
	
	JPanel popUp = new JPanel();
	JMenuBar jm = new JMenuBar();
	JMenu m = new JMenu("Jeu");
	JMenuItem mi = new JMenuItem("Partie");

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
		
		this.game.addObserver(new Observer() {

			@Override
			public void update(Observable arg0, Object arg1) {
				if(game.getStatus() == Status.Win) {			
					int option = JOptionPane.showConfirmDialog(null, "Voulez-vous lancer une nouvelle partie ?", "Gagné !!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
								
					if(option == JOptionPane.OK_OPTION){
						View.this.dispose();
						new Menu().setVisible(true);
					}
					else {System.exit(0); }
				}
				if(game.getStatus() == Status.Loose) {
					int option = JOptionPane.showConfirmDialog(null, "Voulez-vous lancer une nouvelle partie ?", "Perdu !!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if(option == JOptionPane.OK_OPTION){
						View.this.dispose();
						new Menu().setVisible(true);
					}
					else {System.exit(0); }
				}
			}
		});
	}

	public void build() {
		
		m.add(mi);

		jm.add(m);

		setJMenuBar(jm);

		setTitle("Démineur");
		setSize(40 * game.getGrid().getHeight(), 40 * game.getGrid().getWidth());
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
