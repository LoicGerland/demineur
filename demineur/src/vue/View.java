package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
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
@SuppressWarnings("serial")
public class View extends JFrame {

	private Game game;

	JLabel lblNbBomb;

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
				if (game.getStatus() == Status.Win) {
					int option = JOptionPane.showConfirmDialog(null,
							"Voulez-vous lancer une nouvelle partie ?",
							"Gagné !!", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						again();
					} else {
						quit();
					}
				}
				if (game.getStatus() == Status.Loose) {
					int option = JOptionPane.showConfirmDialog(null,
							"Voulez-vous lancer une nouvelle partie ?",
							"Perdu !!", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						again();
					} else {
						quit();
					}
				}
				if (game.getStatus() == Status.Playing) {
					lblNbBomb.setText(((Integer) (game.getNbBombs() - game
							.getNbFlags())).toString());
				}
			}
		});
	}

	public void build() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Option");
		JMenu again = new JMenu("Recommencer");
		
		JMenuItem againThis = new JMenuItem("cette partie");
		againThis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.getGrid().hideAll();
				game.setStatus(Status.Playing);
			}
		});
		
		
		JMenuItem againNew = new JMenuItem("nouvelle partie");
		againNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				again();
			}
		});

		JMenuItem quit = new JMenuItem("Quitter");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quit();
			}
		});
		again.add(againThis);
		again.add(againNew);
		
		menu.add(again);
		menu.add(quit);
		
		menuBar.add(menu);

		setJMenuBar(menuBar);

		JComponent window = new JPanel(new BorderLayout());
		JPanel status = new JPanel(new FlowLayout());
		lblNbBomb = new JLabel(((Integer) game.getNbBombs()).toString());
		status.add(lblNbBomb);

		setTitle("Démineur");
		setSize(40 * game.getGrid().getHeight(), 40 * game.getGrid().getWidth());
		JComponent pan = new JPanel(new GridLayout(game.getGrid().getWidth(),
				game.getGrid().getHeight()));

		pan.setSize(40 * game.getGrid().getHeight(), 40 * game.getGrid()
				.getWidth());

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

		window.add(status, BorderLayout.NORTH);
		window.add(pan, BorderLayout.CENTER);
		add(window);
		// setContentPane(pan);
	}

	protected void again() {
		View.this.dispose();
		new Menu().setVisible(true);
	}

	protected void quit() {
		//System.exit(0);
		this.game.getGrid().showAll();
	}

}
