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

import modele.CaseModele;
import modele.Game;
import modele.Status;

/**
 *
 * @author Loic
 */
@SuppressWarnings("serial")
public class ViewTriangle extends JFrame {

	private Game game;

	JLabel lblAffichage;

	JComponent pan;

	public ViewTriangle(Game g) {
		super();
		this.game = g;
		CaseVue.colorFont = Color.BLACK;
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
					int option = JOptionPane.showConfirmDialog(null, "Bravo "
							+ ((Game) arg0).getWinner().getName()
							+ " ! Voulez-vous continuer à jouer ?", "Gagné !!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						continuer();
					} else {
						quit();
					}
				}
				if (game.getStatus() == Status.Loose) {
					int option = JOptionPane.showConfirmDialog(null,
							"Voulez-vous continuer à jouer ?", "Perdu !!",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

					if (option == JOptionPane.OK_OPTION) {
						continuer();
					} else {
						quit();
					}
				}
				if (game.getStatus() == Status.Playing) {
					lblAffichage.setText(game.getText());
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
				againThis();
			}
		});
		again.add(againThis);

		JMenuItem againNew = new JMenuItem("nouvelle partie");
		againNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				againNew();
			}
		});
		again.add(againNew);

		menu.add(again);

		setJMenuBar(menuBar);

		JComponent window = new JPanel(new BorderLayout());
		JPanel status = new JPanel(new FlowLayout());
		lblAffichage = new JLabel(this.game.getText());
		status.add(lblAffichage);
		setTitle("Démineur");

		pan = new JPanel(new GridLayout(this.game.getGrid().getHeight(),
				this.game.getGrid().getWidth()));

		Border blackline = BorderFactory.createLineBorder(Color.black, 1);

		for (int y = 0; y < this.game.getGrid().getHeight(); y++) {
			int rang = 1;
			for (int x = 0; x < this.game.getGrid().getWidth(); x++) {
				if (x + y >= this.game.getGrid().getHeight() - 1
						&& x < this.game.getGrid().getWidth()
								- (this.game.getGrid().getHeight() - (y + 1))) {
					if (rang % 2 == 1) {
						JComponent ptest = new CaseVueTriangleUp(this.game
								.getGrid().getGrid()[x][y]);
						ptest.setBorder(blackline);
						pan.add(ptest);
					} else {
						JComponent ptest = new CaseVueTriangleDown(this.game
								.getGrid().getGrid()[x][y]);
						ptest.setBorder(blackline);
						pan.add(ptest);
					}
					rang++;
				} else {
					pan.add(new JPanel());
				}
			}
		}
		pan.setBorder(blackline);

		window.add(status, BorderLayout.NORTH);
		window.add(pan, BorderLayout.CENTER);
		add(window);
		setSize((int) (status.getSize().getWidth() + 36
				* game.getGrid().getWidth() + 36), 36 * game.getGrid()
				.getHeight());
	}


	protected void continuer() {
		int option = JOptionPane.showConfirmDialog(null,
				"Recommencer cette partie ?", "Partie",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option == JOptionPane.OK_OPTION) {
			againThis();
		} else {
			againNew();
		}
	}

	protected void againThis() {
		game.playAgain();
	}

	protected void againNew() {
		CaseModele.resetFirstCase();
		ViewTriangle.this.dispose();
		new Menu().setVisible(true);
	}

	protected void quit() {
		System.exit(0);
	}
}
